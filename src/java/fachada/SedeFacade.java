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
import modelo.Sede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class SedeFacade extends AbstractFacade<Sede> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SedeFacade() {
        super(Sede.class);
    }
    
    @Override
    public List<Sede> findAll() {
        javax.persistence.Query q = getEntityManager().createNativeQuery("select * from Sede where activo='S' order by nombreSede", Sede.class);
        return q.getResultList();
    }

//-------------------------------------Reporte Consecutivos por Dependencia --------------------------------------------------------------//
    public List<Sede> consXSede(int codigoSede) {
        String sqlRecibo = "select * from sede  where codigoSede=?1 and activo='S' order by nombreSede";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Sede.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

    /**
     * Query verificación sede en el sisitema
     */
    public List<Sede> consXNitSede(String identificacionNit) {
        String sqlRecibo = "SELECT * FROM sede where identificacionNit=?1";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Sede.class).setParameter(1, identificacionNit);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//

    /**
     *
     * @author RUANLU Validar usuario consultorio
     */
    public Sede validarUsuario(String identificacionNit, String clave) {
        Sede aux = null;
        try {
            Query q = em.createQuery("select u from Sede u where u.identificacionNit=?1 and u.clave=?2 and u.estadoRadicacion='A'", Sede.class).setParameter(1, identificacionNit).setParameter(2, clave);
            aux = (Sede) q.getSingleResult();
        } catch (Exception e) {
        }
        return aux;
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
    
}
