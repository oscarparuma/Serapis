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
import modelo.EpsSede;
import modelo.Procedimientosede;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class ProcedimientosedeFacade extends AbstractFacade<Procedimientosede> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcedimientosedeFacade() {
        super(Procedimientosede.class);
    }
    
    //-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//
    public List<Procedimientosede> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from Procedimientosede u where u.codigoSede=?1 ").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------------------//

    //-------------------------------------Reporte Consecutivos por Dependencia --------------------------------------------------------------//
    public List<Procedimientosede> consXProcedimientosede(int codigoSede) {
        String sqlRecibo = "SELECT eps.nombreEps , procedimientosede .*\n"
                + "FROM procedimientosede LEFT OUTER JOIN\n"
                + "eps ON procedimientosede.codigoEps = eps.codigoEps\n"
                + "where procedimientosede.codigoSede=?1 and procedimientosede.activo='S' order by eps.nombreEps, procedimientosede.codigoCups";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Procedimientosede.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte Consecutivos por Area --------------------------------------------------------------//
    public List<Procedimientosede> consXSede(Long codigoSede) {
        String sqlRecibo = "select * from Procedimientosede where codigoSede=?1 and activo='S' ";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Procedimientosede.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

    public List<Procedimientosede> findAll(Eps codigoEps) {
        if (codigoEps != null) {
            Query q;
            q = em.createNativeQuery("select * from Procedimientosede where codigoEps=?1", Procedimientosede.class).setParameter(1, codigoEps.getCodigoEps());
            return q.getResultList();
        }
        return null;
    }

    public List<Procedimientosede> consXEps(int codigoEps) {
        String sqlRecibo = ("select * from EpsSede where codigoPais=?1  order by nombreEps");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, EpsSede.class).setParameter(1, codigoEps);
        return q.getResultList();
    }
    
}
