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
import modelo.EpsSede;
import modelo.Pais;

/**
 *
 * @author ANPILU
 */
@Stateless
public class EpsSedeFacade extends AbstractFacade<EpsSede> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EpsSedeFacade() {
        super(EpsSede.class);
    }
     /**
     * 
     * Query filtrae eps por pais
     */
    public List<EpsSede> findAll(Pais codigoPais) {
        Query q = em.createQuery("select * from EpsSede where codigoPais=?1  order by nombreEps").setParameter(1, codigoPais);
        return q.getResultList();
    }
    
     public List<EpsSede> consXEps(int codigoPais) {
        String sqlRecibo = ("select * from EpsSede where codigoPais=?1  order by nombreEps");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, EpsSede.class).setParameter(1, codigoPais);
        return q.getResultList();
    }
//-----------------------------------------------------------------------------------------------------------------------------//
}
