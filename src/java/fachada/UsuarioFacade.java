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
import modelo.Citapersona;
import modelo.Sede;
import modelo.Usuario;

/**
 *
 * @author ANPILU
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    public Usuario validarUsuario(Long codigoUsuario, String clave) {
        Usuario aux = null;
        try {
            Query q = em.createQuery("select u from Usuario u where u.codigoUsuario=?1 and u.clave=?2 and u.activo='S'", Usuario.class).setParameter(1, codigoUsuario).setParameter(2, clave);
            aux = (Usuario) q.getSingleResult();
        } catch (Exception e) {
        }
        return aux;
    }

//------- se creo este query para listar los funcionario de las tiposede ----------------//
    public List<Usuario> findAll(Sede codigoSede) {
        Query q = em.createQuery("select u from Usuario u where u.codigoSede=?1 and u.activo='S' and u.esMedico='S'").setParameter(1, codigoSede);
        return q.getResultList();
    }
    
//-------------------------------------Reporte Citas Asignadas por Sede --------------------------------------------------------------//
 public List<Usuario>medicosXSede(int codigoSede) {             
        String sqlCitaPersona = "select * from usuario where codigoSede=?1 and activo='S' order by nombreUsuario asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlCitaPersona, Usuario.class).setParameter(1, codigoSede);
        return q.getResultList();
    }  
 
//---------------------------------------------------------------------------------------------------------------------------------------//
 
 //-------------------------------------Reporte Consecutivos por Dependencia --------------------------------------------------------------//
    public List<Usuario> consXUsuario(int codigoSede) {
        String sqlRecibo = ("select * from Usuario  where codigoSede=?1 and activo='S' and esMedico='S' order by nombreUsuario ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Usuario.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//-------------------------------------------------------------------------------------------------------------------------------------------//
    
    
    //-------------------------------------Reporte Citas Profesional----------------------------------------------------------------------//
    public List<Citapersona> consCitasXProfesional(int codigoProfesional) {
        String sqlRecibo = "select m from Citapersona m where m.codigoProfesional=?1 and m.finalizada='A' order by m.fechaCita asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoProfesional);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
    
    //-------------------------------------Reporte Citas Profesional----------------------------------------------------------------------//
    public List<Citapersona> citaProfesionalXSede(int codigoProfesional) {
        String sqlRecibo = "select m from Citapersona m where m.codigoProfesional=?1 and m.finalizada='A' order by m.fechaCita asc";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Citapersona.class).setParameter(1, codigoProfesional);
        return q.getResultList();
    }
//---------------------------------------------------------------------------------------------------------------------------------------//
    
    /**
     * Query listar usuario Sede
     * @param codigoSede
     * @return 
     */
    
     public List<Usuario> consXUsuarioReg(int codigoSede) {
        String sqlRecibo = ("select * from Usuario  where codigoSede=?1 and activo='S' order by nombreUsuario ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, Usuario.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
//------------------------------------------------------------------------------------------------------------------------------------//
}
