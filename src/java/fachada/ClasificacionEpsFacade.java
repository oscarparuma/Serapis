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
import modelo.ClasificacionEps;
import modelo.Pais;

/**
 *
 * @author ANPILU
 */
@Stateless
public class ClasificacionEpsFacade extends AbstractFacade<ClasificacionEps> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClasificacionEpsFacade() {
        super(ClasificacionEps.class);
    }
    @Override
    public List<ClasificacionEps> findAll() {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from ClasificacionEps where activo='S' order by nombre asc", ClasificacionEps.class);
        return q.getResultList();
    }

//-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//
   public List<ClasificacionEps> findAll(Pais codigoPais) {
        Query q = em.createQuery("select u from ClasificacionEps u where u.codigoPais=?1 and u.activo='S'").setParameter(1, codigoPais);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte Consecutivos por Area --------------------------------------------------------------//
    public List<ClasificacionEps> consXSede(int codigoSede) {
        String sqlRecibo = "select * from ClasificacionEps where codigoSede=?1 and activo='S' order by nombre asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, ClasificacionEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
    
}
