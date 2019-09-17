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
import modelo.TipoGlosa;

/**
 *
 * @author ANPILU
 */
@Stateless
public class TipoGlosaFacade extends AbstractFacade<TipoGlosa> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoGlosaFacade() {
        super(TipoGlosa.class);
    }
    
     public List<TipoGlosa> consXTipoGlosa(int codigoSede) {
        String sqlRecibo = ("select * from TipoGlosa where codigoSede=?1 and activo='S' ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, TipoGlosa.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
    
}
