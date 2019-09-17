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
import modelo.Clasificacionindicacionmedica;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class ClasificacionindicacionmedicaFacade extends AbstractFacade<Clasificacionindicacionmedica> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasificacionindicacionmedicaFacade() {
        super(Clasificacionindicacionmedica.class);
    }
    //-------------------- se creo este query para listar las indicaciones medicas por Sede---------------------------------------------------------//
    public List<Clasificacionindicacionmedica> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from Clasificacionindicacionmedica u where u.codigoSede=?1 and u.actvo='S' order by u.recomendacionesMedicas asc").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//
    
    //-------------------------------------se creo este query para listar las indicaciones medicas por Sede --------------------------------------------------------------//
    public List<Clasificacionindicacionmedica> consXClasificacInd(int codigoSede) {
        String sqlRecibo = "select * from Clasificacionindicacionmedica v, indicacionmedica tr where v.codigoIndicacionMedica=tr.codigoIndicacionMedica and v.codigoSede=?1 and v.actvo='S' order by tr.recomendacionesMedicas, v.recomendacionesMedicas asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Clasificacionindicacionmedica.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
}
