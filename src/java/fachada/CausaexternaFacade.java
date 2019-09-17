/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Causaexterna;

/**
 *
 * @author ANPILU
 */
@Stateless
public class CausaexternaFacade extends AbstractFacade<Causaexterna> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CausaexternaFacade() {
        super(Causaexterna.class);
    }
    
}
