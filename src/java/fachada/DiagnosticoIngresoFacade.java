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
import modelo.Citapersona;
import modelo.DiagnosticoIngreso;

/**
 *
 * @author ANPILU
 */
@Stateless
public class DiagnosticoIngresoFacade extends AbstractFacade<DiagnosticoIngreso> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DiagnosticoIngresoFacade() {
        super(DiagnosticoIngreso.class);
    }
     public List<DiagnosticoIngreso> findRange(int[] range, Citapersona c) {
        javax.persistence.Query q = getEntityManager().createQuery("select s from DiagnosticoIngreso s where s.codigoCita=?1");
        q.setParameter(1, c);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
}
