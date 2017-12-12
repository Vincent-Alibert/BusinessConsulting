/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Postes;

/**
 *
 * @author valibert
 */
@Stateless
public class PostesFacade extends AbstractFacade<Postes> {

    @PersistenceContext(unitName = "BusinessConsultingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PostesFacade() {
        super(Postes.class);
    }
    
}
