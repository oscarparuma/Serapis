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
import modelo.Eps;
import modelo.Facturacion;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class FacturacionFacade extends AbstractFacade<Facturacion> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturacionFacade() {
        super(Facturacion.class);
    }

    //----------------- Codigo para generar concecutivo  ---------------------------//
    public long calcularRadicado(long ccsFactura) {
        String sql = " SELECT  "
                + "IFNULL(MAX(m.ccsFactura), 0) + 1 AS maxRadicado "
                + "FROM Facturacion AS m "
                + "WHERE m.codigoSede = ?1";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sql);
        q = q.setParameter(1, ccsFactura);

        String z = q.getSingleResult().toString();
        return Integer.parseInt(z);

    }
//------------------------------------------------------------------------------//

//-------------------------------------Reporte recibo Pago por Sede --------------------------------------------------------------//
    public List<Facturacion> consXFacturacion(int codigoSede) {
        String sqlCitaPersona = "select * from facturacion f \n"
                + "WHERE NOT EXISTS \n"
                + "(SELECT * FROM carteraSede c \n"
                + "WHERE f.codigoCitaPersona = c.codigoCitaPersona) and f.codigoSede=?1 and f.copagoFactura='S' ORDER by fechaRegistro asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlCitaPersona, Facturacion.class).setParameter(1, codigoSede);
        return q.getResultList();
    }

//---------------------------------------------------------------------------------------------------------------------------------------//
    public List<Facturacion> findAll(Sede codigoSede) {
        Query q = em.createQuery("SELECT facturacion.codigoFactura, facturacion.numeroDocumento, facturacion.fechaRegistro, eps.identificacionEps, eps.nombreEps, eps.direccion, eps.telefono, municipio.nombreMunicipio, \n"
                + "departamento.nombreDepartamento, facturacion.valorUnitario, facturacion.valorParcial, facturacion.valorCopago, facturacion.valorTotalFactura, facturacion.codigoEstadoFactura, sede.nombreSede, \n"
                + "eps.codigoEps, sede.codigoSede, facturacion.estadoFactura\n"
                + "FROM facturacion LEFT OUTER JOIN\n"
                + "sede ON facturacion.codigoSede = sede.codigoSede FULL OUTER JOIN\n"
                + "departamento RIGHT OUTER JOIN\n"
                + "eps ON departamento.codigoDepartamento = eps.codigoDepartamento LEFT OUTER JOIN\n"
                + "municipio ON eps.codigoMunicipio = municipio.codigoMunicipio ON facturacion.codigoEps = eps.codigoEps\n"
                + "WHERE sede.codigoSede = ?1 and facturacion.estadoFactura='F' order by facturacion.codigoFactura asc").setParameter(1, codigoSede);
        return q.getResultList();
    }

//-------------------- se creo este query para listar las facturas pendientes radicar----------------------------------------------------//
    public List<Facturacion> consXFactRadicacion() {
        String radFacturas = "select * from facturacion where estadoFactura='F'";
        javax.persistence.Query q = getEntityManager().createNativeQuery(radFacturas, Facturacion.class);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//

    public List<Facturacion> findAll(int codigoEps) {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from facturacion where estadoFactura='F' and codigoEps=?1 order by codigoFactura asc", Facturacion.class).setParameter(1, codigoEps);
        return q.getResultList();
    }

    public List<Facturacion> consXFactRadicacion(Eps codigoEps) {
        Query q = em.createQuery("select * from facturacion where estadoFactura='F' and codigoEps=?1 order by codigoFactura asc", Facturacion.class).setParameter(1, codigoEps);
        return q.getResultList();
    }

    public List<Facturacion> findAll1(int codigoEps) {
        String sqlRecibo = "select * from facturacion where estadoFactura='F' and codigoEps=?1 order by codigoFactura asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Facturacion.class);
        return q.getResultList();
    }

    /*
   public List<Facturacion> findAllLista(Eps codigoEps) {
        if (codigoEps != null) {
            Query q;
            q = em.createNativeQuery("select * from facturacion where codigoEps=?1 and estadoFactura='F' order by codigoFactura asc", Facturacion.class).setParameter(1, codigoEps.getCodigoEps());
            return q.getResultList();
        }
        return null;
    }
     */
    public List<Facturacion> findAllLista(Eps codigoEps) {
        if (codigoEps != null) {
            Query q;
            q = em.createNativeQuery("SELECT  * FROM facturacion LEFT OUTER JOIN\n"
                    + "citapersona ON facturacion.codigoCitaPersona = citapersona.codigoCitaPersona\n"
                    + "WHERE     (facturacion.codigoEps =?1) AND (facturacion.estadoFactura = 'F') and (citapersona.finalizada='S')\n"
                    + "ORDER BY facturacion.codigoFactura", Facturacion.class).setParameter(1, codigoEps.getCodigoEps());
            return q.getResultList();
        }
        return null;
    }

}
