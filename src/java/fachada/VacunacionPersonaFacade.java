/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.VacunacionPersona;

/**
 *
 * @author ANPILU
 */
@Stateless
public class VacunacionPersonaFacade extends AbstractFacade<VacunacionPersona> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VacunacionPersonaFacade() {
        super(VacunacionPersona.class);
    }
    
}
