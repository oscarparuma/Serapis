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
import modelo.ListaRadicadoEps;

/**
 *
 * @author ANPILU
 */
@Stateless
public class DetalleRadicadoSedeFacade extends AbstractFacade<DetalleRadicadoSede> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetalleRadicadoSedeFacade() {
        super(DetalleRadicadoSede.class);
    }

    public List<DetalleRadicadoSede> findRange(int[] range, ListaRadicadoEps s) {
        javax.persistence.Query q = getEntityManager().createQuery("select s from DetalleRadicadoSede s where s.codigoListadoRadicado=?1 and s.saldoTotal > 0");
        q.setParameter(1, s);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    
}
