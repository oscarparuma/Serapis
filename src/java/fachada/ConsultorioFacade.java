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
import modelo.Consultorio;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class ConsultorioFacade extends AbstractFacade<Consultorio> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ConsultorioFacade() {
        super(Consultorio.class);
    }
    
     @Override
    public List<Consultorio> findAll() {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from Consultorio where activo='S' order by nombreConsultorio", Consultorio.class);
        return q.getResultList();
    }
    
    
//-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//
    public List<Consultorio> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from Consultorio u where u.codigoSede=?1 and u.activo='S'").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//
    
    //-------------------------------------Reporte Consecutivos por Area --------------------------------------------------------------//
    public List<Consultorio> consXSede(int codigoSede) {
        String sqlRecibo = "select * from Consultorio where codigoSede=?1 and activo='S' order by nombreConsultorio asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Consultorio.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
    
}
