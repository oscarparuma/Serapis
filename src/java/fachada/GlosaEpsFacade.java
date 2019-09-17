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
import modelo.DetalleRadicadoSede;
import modelo.GlosaEps;

/**
 *
 * @author ANPILU
 */
@Stateless
public class GlosaEpsFacade extends AbstractFacade<GlosaEps> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GlosaEpsFacade() {
        super(GlosaEps.class);
    }
    
    public List<GlosaEps> findRange(int[] range, DetalleRadicadoSede s) {
        javax.persistence.Query q = getEntityManager().createQuery("select s from GlosaEps s where s.codigoDetalleRadicado=?1");
        q.setParameter(1, s);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    
    public List<GlosaEps> consXGlosaSede(int codigoSede) {
        String sqlRecibo = ("select * from GlosaEps  where codigoSede=?1 and totalGlosa >0");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, GlosaEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
   
    
}
