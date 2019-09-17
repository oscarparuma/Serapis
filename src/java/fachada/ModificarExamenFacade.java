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
import modelo.ModificarExamen;
import modelo.Procedimientosede;
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class ModificarExamenFacade extends AbstractFacade<ModificarExamen> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ModificarExamenFacade() {
        super(ModificarExamen.class);
    }

    //-------------------- se creo este query para listar los medicos por Sede---------------------------------------------------------//
    public List<ModificarExamen> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from ModificarExamen u where u.codigoSede=?1 and u.activo='S' ").setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------//

//-------------------------------------Reporte Consecutivos por Area --------------------------------------------------------------//
    public List<ModificarExamen> consXSede(int codigoSede) {
        String sqlRecibo = "select * from ModificarExamen where codigoSede=?1";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, ModificarExamen.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

    /**
     *
     *
     */
    public List findRange(int[] i, Procedimientosede actProcedimientosede) {
        javax.persistence.Query q = getEntityManager().createQuery("select c from ModificarExamen c where c.codigoProcedimintoSede=?1 order by c.fechaRegistro desc");
        q.setParameter(1, actProcedimientosede);
        return q.getResultList();
    }
}
