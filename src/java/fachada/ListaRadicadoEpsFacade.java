/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceContext;
import modelo.ListaRadicadoEps;

/**
 *
 * @author ANPILU
 */
@Stateless
public class ListaRadicadoEpsFacade extends AbstractFacade<ListaRadicadoEps> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ListaRadicadoEpsFacade() {
        super(ListaRadicadoEps.class);
    }

    public List<ListaRadicadoEps> consXListaRadicado(int codigoSede) {
        String sqlRecibo = ("select * from ListaRadicadoEps  where codigoSede=?1 and estado='P' order by codigoEps ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, ListaRadicadoEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
    
    public List<ListaRadicadoEps> consXListaRadicadoRips(int codigoSede) {
        String sqlRecibo = ("select * from ListaRadicadoEps  where codigoSede=?1 and estado='E' order by codigoEps ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, ListaRadicadoEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
    
    public List<ListaRadicadoEps> consXListaRadicadoEpsSede(int codigoSede) {
        String sqlRecibo = ("select * from ListaRadicadoEps  where codigoSede=?1 and estado='E' order by codigoEps ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, ListaRadicadoEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
    
    public List<ListaRadicadoEps> consXListaCarteraEpsSede(int codigoSede) {
        String sqlRecibo = ("select * from ListaRadicadoEps  where codigoSede=?1 and estado='R' order by codigoEps ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, ListaRadicadoEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
    
    public List<ListaRadicadoEps> consXListaGlosaDevEpsSede(int codigoSede) {
        String sqlRecibo = ("select * from ListaRadicadoEps  where codigoSede=?1 and estado='R' order by codigoEps ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlRecibo, ListaRadicadoEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }

    
    @JoinColumn
    public List<ListaRadicadoEps> listReporEgresosXSede(int codigoSede, Date fechaInicial, Date fechaFinal) {
        Format format = new SimpleDateFormat("dd/MM/yyyy");//new SimpleDateFormat("yyyy-MM-dd");
        String sqlEgresoSede = ("select * from ListaRadicadoEps WHERE ListaRadicadoEps.codigoSede = ?1 AND (ListaRadicadoEps.fechaFactura between '" + format.format(fechaInicial) + " 00:01' and '" + format.format(fechaFinal) + " 23:59') ");
        javax.persistence.Query q = getEntityManager().createNativeQuery(sqlEgresoSede, ListaRadicadoEps.class).setParameter(1, codigoSede);
        return q.getResultList();
    }
    
    
}
