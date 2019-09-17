/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Area;
import modelo.Datospersona;
import modelo.Dependencia;
import modelo.Sede;
import modelo.Usuario;

/**
 *
 * @author ANPILU
 */
@Stateless
public class DatospersonaFacade extends AbstractFacade<Datospersona> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DatospersonaFacade() {
        super(Datospersona.class);
    }
    
    //--------------------Codigo para Listar Area Usuario  ------------------------//
    public List findRange(int[] range, Area codigoArea) {
        javax.persistence.Query q = getEntityManager().createQuery("select v from Datospersona v where v.codigoArea=?1 and v.estadoActual='S' order by Apellidos asc ");
        q.setParameter(1, codigoArea);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Area u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(v) n from Datospersona v where v.codigoArea=?1 and v.estadoActual='S' ");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();

    }
//-----------------------------------------------------------------------------//

//--------------------Codigo para Listar Dependencia Usuario  ------------------------//
    public List findRange(int[] range, Dependencia codigoDependencia) {
        javax.persistence.Query q = getEntityManager().createQuery("select v from Datospersona v where v.codigoDependecia=?1 and v.estadoActual='S' order by Apellidos asc ");
        q.setParameter(1, codigoDependencia);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Dependencia u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(v) n from Datospersona v where v.codigoDependecia=?1 and v.estadoActual='S' ");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();

    }
//-----------------------------------------------------------------------------//

    //------------------------------- -- codigo para listar todas por regional ordenadas -----////
    @Override
    @SuppressWarnings("unchecked")
    public List<Datospersona> findRange(int[] range) {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select v.* from Datospersona v, Sede sed where v.codigoSede=sed.codigoSede and v.estadoActual='S' order by sed.nombreSede, v.Apellidos asc  ", Datospersona.class);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    ///--------------------Codigo para Listar regionales usuario  ------------------------//--------
    public List findRange(int[] range, Sede codigoSede) {
        javax.persistence.Query q = getEntityManager().createQuery("select v from Datospersona v where v.codigoSede=?1 and v.estadoActual='S' order by v.apellidos asc ");
        q.setParameter(1, codigoSede);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Sede u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(v) n from Datospersona  v where v.codigoSede=?1 and v.estadoActual='S'");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();

    }
//-----------------------------------------------------------------------------//

    public List<Datospersona> findRange(int[] range, Usuario u) {
        javax.persistence.Query q = getEntityManager().createQuery("select m from Datospersona m where m.codigoUsuario=?1 and m.estadoActual='S' order by m.apellidos ");
        q.setParameter(1, u);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count(Usuario u) {
        javax.persistence.Query q = getEntityManager().createQuery("select count(m) n from Datospersona m where m.codigoUsuario=?1 and m.estadoActual='S' ");
        q.setParameter(1, u);
        return ((Long) q.getSingleResult()).intValue();
    }
    // se creo este query para listar los funcionario de las regionales

    public List<Datospersona> findAll(Sede codigoSede) {
        Query q = em.createQuery("select v from Datospersona v where v.codigoSede=?1 and v.estadoActual='S' ").setParameter(1, codigoSede);
        return q.getResultList();
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------//

//------------------------------------------------Query para buscar personas por numero deientificacion-----------------------------------------------//
    public List<Datospersona> findAll(Datospersona d) {
        javax.persistence.Query q = getEntityManager().createQuery("select s from Datospersona s where s.numeroDocumentoIdentidad=?1");
        q.setParameter(1, d);
        return q.getResultList();
    }

    public List<Datospersona> consXCedulaDatosPersona(String numeroDocumentoIdentidad) {
        String sqlRecibo = "SELECT * FROM datospersona where numeroDocumentoIdentidad=?1";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Datospersona.class).setParameter(1, numeroDocumentoIdentidad);
        return q.getResultList();
    }

    public Object consXCedulaDatosPersonaCP(String numeroDocumentoIdentidad) {
        Query q = em.createNativeQuery("SELECT * FROM datospersona where numeroDocumentoIdentidad=?1").setParameter(1, numeroDocumentoIdentidad);
        return q.getSingleResult();
    }

    public String existe(String numeroDocumentoIdentidad) {
        return em.createNativeQuery("SELECT * FROM datospersona where numeroDocumentoIdentidad=?1").setParameter(1, numeroDocumentoIdentidad).getSingleResult().toString();
        
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------//
    
}
