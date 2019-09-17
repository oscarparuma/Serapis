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
import modelo.PagosEps;

/**
 *
 * @author ANPILU
 */
@Stateless
public class PagosEpsFacade extends AbstractFacade<PagosEps> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagosEpsFacade() {
        super(PagosEps.class);
    }
    
     public List<PagosEps> findRange(int[] range, DetalleRadicadoSede s) {
        javax.persistence.Query q = getEntityManager().createQuery("select s from PagosEps s where s.codigoDetalleRadicado=?1");
        q.setParameter(1, s);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
}
