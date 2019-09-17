/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Tipofacturable;

/**
 *
 * @author ANPILU
 */
@Stateless
public class TipofacturableFacade extends AbstractFacade<Tipofacturable> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipofacturableFacade() {
        super(Tipofacturable.class);
    }
    
}
