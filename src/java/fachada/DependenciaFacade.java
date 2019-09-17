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
import modelo.Dependencia;

/**
 *
 * @author ANPILU
 */
@Stateless
public class DependenciaFacade extends AbstractFacade<Dependencia> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DependenciaFacade() {
        super(Dependencia.class);
    }
    @Override
    public List<Dependencia> findAll() {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from Dependencia where activo='S' order by nombreDependencia", Dependencia.class);
        return q.getResultList();
    }
    
//-------------------------------------Reporte Consecutivos por Dependencia --------------------------------------------------------------//
    public List<Dependencia> consXDependencia(int codigoDependencia) {
        String sqlRecibo = "select * from Dependencia  where codigoSede=?1 and activo='S' order by nombreDependencia" ;
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Dependencia.class).setParameter(1, codigoDependencia);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
    
}
