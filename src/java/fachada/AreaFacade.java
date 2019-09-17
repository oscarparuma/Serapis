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
import modelo.Area;

/**
 *
 * @author ANPILU
 */
@Stateless
public class AreaFacade extends AbstractFacade<Area> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AreaFacade() {
        super(Area.class);
    }

    @Override
    public List<Area> findAll() {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from area where activo='S' order by nombreArea", Area.class);
        return q.getResultList();
    }

//-------------------------------------Reporte Consecutivos por Area --------------------------------------------------------------//
    public List<Area> consXArea(int codigoArea) {
        String sqlRecibo = "select * from Area  where codigoArea=?1 and activo='S' order by nombreArea";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Area.class).setParameter(1, codigoArea);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

}
