/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.DetailConsultPhases;

/**
 *
 * @author valibert
 */
@Stateless
public class DetailConsultPhasesFacade extends AbstractFacade<DetailConsultPhases> {

    @PersistenceContext(unitName = "BusinessConsultingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DetailConsultPhasesFacade() {
        super(DetailConsultPhases.class);
    }
    
}
