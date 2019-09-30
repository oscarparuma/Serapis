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
import javax.persistence.Query;
import modelo.Indicacionmedica;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class IndicacionmedicaFacade extends AbstractFacade<Indicacionmedica> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IndicacionmedicaFacade() {
        super(Indicacionmedica.class);
    }
    //-------------------- se creo este query para listar las indicaciones medicas por Sede---------------------------------------------------------//
    public List<Indicacionmedica> findAll(Sede codigoSede) {
        em.getEntityManagerFactory().getCache().evict(Indicacionmedica.class); // Remove the data for entities of the specified class (and its subclasses) from the cache.
        Query q = em.createQuery("select u from Indicacionmedica u where u.codigoSede=?1 and u.actvo='S' order by u.recomendacionesMedicas asc").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//
    
    //-------------------------------------se creo este query para listar las indicaciones medicas por Sede --------------------------------------------------------------//
    public List<Indicacionmedica> consXSede(int codigoSede) {
        String sqlRecibo = "select * from Indicacionmedica where codigoSede=?1 and actvo='S' order by recomendacionesMedicas asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Indicacionmedica.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
}
