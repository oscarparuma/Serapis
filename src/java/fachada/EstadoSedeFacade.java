/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.EstadoSede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class EstadoSedeFacade extends AbstractFacade<EstadoSede> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoSedeFacade() {
        super(EstadoSede.class);
    }
    
}
