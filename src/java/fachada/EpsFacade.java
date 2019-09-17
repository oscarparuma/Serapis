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
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class EpsFacade extends AbstractFacade<Eps> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EpsFacade() {
        super(Eps.class);
    }
    
     @Override
    public List<Eps> findRange(int[] range) {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from Eps where activo='S' order by clasificacionEps asc, nombreEps asc", Eps.class);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public List<Eps> findAll() {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from Eps where activo='S' order by clasificacionEps asc, nombreEps asc", Eps.class);
        return q.getResultList();
    }

    //-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//
    public List<Eps> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from Eps u where u.codigoSede=?1 and u.activo='S' order by u.nombreEps").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte EPS por sede------------------ --------------------------------------------------------------//
    public List<Eps> consXEps(int codigoSede) {
        String sqlRecibo = ("select * from Eps  where codigoSede=?1 and activo='S' order by nombreEps ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Eps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------------------//
    
}
