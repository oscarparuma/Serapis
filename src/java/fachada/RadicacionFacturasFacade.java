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
import modelo.FacturaRadicacion;
import modelo.RadicacionFacturas;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class RadicacionFacturasFacade extends AbstractFacade<RadicacionFacturas> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RadicacionFacturasFacade() {
        super(RadicacionFacturas.class);
    }
    
    //-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//           papi te amo
    public List<RadicacionFacturas> findAllFact(Eps codigoEps) {
        Query q = em.createQuery("select c from facturacion c  where c.codigoEps=?1 and c.estadoFactura='F'").setParameter(1, codigoEps);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//
    //-------------------- se creo este query para listar suma de radicado Sede---------------------------------------------------------//

    public List<RadicacionFacturas> findRange(int[] range, FacturaRadicacion f) {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select c from RadicacionFacturas c  where c.codigoRadicacionFactura=?1 order by c.codigoFactura asc  ");
        q.setParameter(1, f);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//
////SELECT sum(valorFacturado) AS totalRadicadoFact FROM RadicacionFacturas WHERE codigoRadicacionFactura=?1//


//-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//
    public List<RadicacionFacturas> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from RadicacionFacturas u where u.codigoSede=?1").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte Consecutivos por Area --------------------------------------------------------------//
    public List<RadicacionFacturas> consXSede(int codigoSede) {
        String sqlRecibo = "select u from RadicacionFacturas u where u.codigoSede=?1";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, RadicacionFacturas.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------// 
    
}
