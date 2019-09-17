/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Sucursalempresa;

/**
 *
 * @author ANPILU
 */
@Stateless
public class SucursalempresaFacade extends AbstractFacade<Sucursalempresa> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SucursalempresaFacade() {
        super(Sucursalempresa.class);
    }
    
}
