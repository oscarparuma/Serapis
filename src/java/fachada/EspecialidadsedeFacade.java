/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Especialidadsede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class EspecialidadsedeFacade extends AbstractFacade<Especialidadsede> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EspecialidadsedeFacade() {
        super(Especialidadsede.class);
    }
    
}
