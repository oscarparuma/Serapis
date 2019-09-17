/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Area;
import modelo.Citapersona;
import modelo.Dependencia;
import modelo.Sede;
import modelo.Usuario;

/**
 *
 * @author ANPILU
 */
@Stateless
public class CitapersonaFacade extends AbstractFacade<Citapersona> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitapersonaFacade() {
        super(Citapersona.class);
    }

     //--------- Codigo para generar ingreso paciente  --------------------------//
    public long calcularIngreso(long cscIngreso) {
        String sql = "SELECT  IFNULL(MAX(m.cscIngreso), 0) + 1 AS maxRadicado \n"
                + "FROM Citapersona AS m \n"
                + "WHERE m.codigoPersonaCita = ?1";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sql);
        q = q.setParameter(1, cscIngreso);
        String z = q.getSingleResult().toString();
        return Long.parseLong(z);
    }

//-----------------------------------------------------------------------------//      
//--------------------Codigo para Listar Area Usuario  ------------------------//
    public List findRange(int[] range, Area codigoArea) {
        javax.persistence.Query q = getEntityManager().createQuery("select v from Citapersona v where v.codigoArea=?1 and v.finalizada='N' order by fechaCita asc ");
        q.setParameter(1, codigoArea);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Area u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(v) n from Citapersona v where v.codigoArea=?1 and v.finalizada='N' ");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();

    }
//-----------------------------------------------------------------------------//

    //--------------------Codigo para Listar Dependencia Usuario  ------------------------//
    public List findRange(int[] range, Dependencia codigoDependencia) {
        javax.persistence.Query q = getEntityManager().createQuery("select v from Citapersona v where v.codigoDependecia=?1 and v.finalizada='N' order by v.fechaCita asc ");
        q.setParameter(1, codigoDependencia);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Dependencia u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(v) n from Citapersona v where v.codigoDependecia=?1 and v.finalizada='N' ");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();

    }
//-----------------------------------------------------------------------------//

    //------------------------------- -- codigo para listar todas por regional ordenadas -----////
    @Override
    @SuppressWarnings("unchecked")
    public List<Citapersona> findRange(int[] range) {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select v.* from Citapersona v, Sede sed where v.codigoSede=sed.codigoSede and v.finalizada='N' order by sed.nombreSede, v.fechaCita asc", Citapersona.class);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    ///--------------------Codigo para Listar regionales usuario  ------------------------//--------
    public List findRange(int[] range, Sede codigoSede) {
        javax.persistence.Query q = getEntityManager().createQuery("select v from Citapersona v where v.codigoSede=?1 and v.finalizada='N' order by v.fechaCita asc ");
        q.setParameter(1, codigoSede);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Sede u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(v) n from Citapersona  v where v.codigoSede=?1 and v.finalizada='N'");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();

    }
//-----------------------------------------------------------------------------//

    public List<Citapersona> findRange(int[] range, Usuario u) {
        javax.persistence.Query q = getEntityManager().createQuery("select m from Citapersona m where m.codigoProfesional=?1 and m.finalizada='N' order by m.fechaCita asc");
        q.setParameter(1, u);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Usuario u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(m) n from Citapersona m where m.codigoProfesional=?1 and m.finalizada='N' ");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();
    }
//----------------------------- se creo este query para listar las citas asignadas ----------------------------------------------------------------------------------------------------//

    public List<Citapersona> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from Citapersona u where u.codigoSede=?1 and u.finalizada='N' ORDER by u.fechaCita, u.codigoConsultorio asc ").setParameter(1, codigoSede);
        return q.getResultList();
    }
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte Citas Asignadas por Sede --------------------------------------------------------------//
    public List<Citapersona> consXCitasPersona(int codigoSede) {
        String sqlCitaPersona = "select * from Citapersona where codigoSede=?1 and finalizada='A' ORDER by fechaCita, codigoConsultorio asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlCitaPersona, Citapersona.class).setParameter(1, codigoSede);
        return q.getResultList();
    }

//---------------------------------------------------------------------------------------------------------------------------------------//
//-----------------------------------------------------------------------Sqleserver 2005 --------------------------------------------------------------------------------------------------------//
    public List<Citapersona> consXCitaSede(int codigoSede, Date fechaInicial, Date fechaFinal) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String sqlRecibo = "select * from Citapersona  where codigoSede=?1 and  fechaSolicitud between '" + formatter.format(fechaInicial) + " 00:00' and '" + formatter.format(fechaFinal) + " 23:59'";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte Citas Sede --------------------------------------------------------------//
    public List<Citapersona> citaAsignadaXSede(int codigoSede) {
        String sqlRecibo = "select * from Citapersona where codigoSede=?1 and finalizada='A' ORDER by fechaCita, codigoConsultorio asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte Citas Profesional----------------------------------------------------------------------//
    public List<Citapersona> citaProfesionalXSede(int codigoSede) {
        String sqlRecibo = "select m.* from Citapersona m where m.codigoSede=?1 and m.finalizada='A' order by m.fechaCita asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

    public List<Citapersona> citaProfesionalXProfesional(long codigoProfesional) {
        String sqlRecibo = "select m.* from Citapersona m where m.codigoProfesional=?1 and m.finalizada='A' order by m.fechaCita asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoProfesional);
        return q.getResultList();
    }

    public List<Citapersona> facturacionEpsXSede(int codigoSede) {
        String sqlRecibo = "SELECT facturacion.facturaSede, citapersona.primerApellido, citapersona.segundoApellido, citapersona.primerNombre, citapersona.segundoNombre, citapersona.numeroDocumentoIdentidad, \n"
                + "citapersona.codigoCitaPersona, citapersona.estadoFactura, facturacion.fechaRegistro, clasificacioncopago.codigoNivelCopago, eps.nombreEps, eps.identificacionEps, facturacion.valorUnitario, \n"
                + "facturacion.valorCopago, facturacion.valorTotalFactura, eps.codigoEps, sede.nombreSede, sede.codigoSede\n"
                + "FROM citapersona RIGHT OUTER JOIN\n"
                + "eps RIGHT OUTER JOIN\n"
                + "facturacion LEFT OUTER JOIN\n"
                + "sede ON facturacion.codigoSede = sede.codigoSede ON eps.codigoEps = facturacion.codigoEps ON citapersona.codigoCitaPersona = facturacion.codigoCitaPersona LEFT OUTER JOIN\n"
                + "clasificacioncopago ON facturacion.codigoClasificacionCopago = clasificacioncopago.codigoCopago\n"
                + "WHERE eps.codigoEps = 1 AND sede.codigoSede=1  AND citapersona.estadoFactura = 'F'";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoSede);
        return q.getResultList();
    }

    
    /**
     * 
     * 
     */
    public List<Citapersona> citaAsignadaXSedeAct(int codigoSede) {
        String sqlRecibo = "select * from Citapersona where codigoSede=?1 and citaActiva='A' ORDER by fechaCita, codigoConsultorio asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
    
    public List<Citapersona> citaAsignadaXUsuario(long codigousuario) {
        String sqlRecibo = "select * from Citapersona where codigoProfesional=?1 and citaActiva='A' ORDER by fechaCita, codigoConsultorio asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigousuario);
        return q.getResultList();
    }
     public List<Citapersona> citaProfesionalXUsuario(long codigoProfesional) {
        String sqlRecibo = "select m from Citapersona m where m.codigoProfesional=?1 and m.finalizada='N' order by m.fechaCita asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoProfesional);
        return q.getResultList();
    }
//------------------------------------------------------------------------------------------------------------------------------------------//

  
}
