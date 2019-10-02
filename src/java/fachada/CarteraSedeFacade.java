/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachada;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.CarteraSede;

/**
 *
 * @author ANPILU
 */
@Stateless
public class CarteraSedeFacade extends AbstractFacade<CarteraSede> {

    @PersistenceContext(unitName = "SerapisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CarteraSedeFacade() {
        super(CarteraSede.class);
    }

    //--------- Codigo para generar concecutivo -------------------------------//
    public long calcularRadicado(long ccsRecibo) {
        String sql = " SELECT  "
                + "IFNULL(MAX(m.ccsRecibo), 0) + 1 AS maxRadicado "
                + "FROM carterasede AS m "
                + "WHERE m.codigoSede = ?1 AND YEAR(m.fechaRegistro) = YEAR(SYSDATE())";
        javax.persistence.Query q = getEntityManager().createNativeQuery(sql);
        q = q.setParameter(1, ccsRecibo);

        String z = q.getSingleResult().toString();
        return Integer.parseInt(z);

    }
//-----------------------------------------------------------------------------//

}
