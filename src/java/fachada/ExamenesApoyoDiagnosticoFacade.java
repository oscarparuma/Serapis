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
import modelo.ExamenesApoyoDiagnostico;

/**
 *
 * @author RUANLU
 */
@Stateless
public class ExamenesApoyoDiagnosticoFacade extends AbstractFacade<ExamenesApoyoDiagnostico> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExamenesApoyoDiagnosticoFacade() {
        super(ExamenesApoyoDiagnostico.class);
    }
     public List<ExamenesApoyoDiagnostico> findRange(int[] range, Citapersona c) {
        javax.persistence.Query q = getEntityManager().createQuery("select s from ExamenesApoyoDiagnostico s where s.codigoCita=?1");
        q.setParameter(1, c);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
     
      public List<ExamenesApoyoDiagnostico> findRange(int[] range, DatosPersona u) {
        javax.persistence.Query q = getEntityManager().createQuery("select m from ExamenesApoyoDiagnostico m where m.codigoPersona=?1 and m.resultados='S' order by m.fechaRegistro asc");
        q.setParameter(1, u);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    private static class DatosPersona {

        public DatosPersona() {
        }
    }

   

    
}
