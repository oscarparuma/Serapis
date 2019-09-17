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
import modelo.FacturaRadicacion;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class FacturaRadicacionFacade extends AbstractFacade<FacturaRadicacion> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaRadicacionFacade() {
        super(FacturaRadicacion.class);
    }

    //-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//
    public List<FacturaRadicacion> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from FacturaRadicacion u where u.codigoSede=?1 and u.estadoRadicacion='P' order by codigoEps asc").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//

    //-------------------------------------Reporte Consecutivos por Area --------------------------------------------------------------//
    public List<FacturaRadicacion> consXSede(int codigoSede) {
        String sqlRecibo = "select * from FacturaRadicacion where codigoSede=?1 and u.estadoRadicacion='P' order by codigoEps asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, FacturaRadicacion.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
}
