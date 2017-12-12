/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Phases;

/**
 *
 * @author valibert
 */
@Stateless
public class PhasesFacade extends AbstractFacade<Phases> {

    @PersistenceContext(unitName = "BusinessConsultingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PhasesFacade() {
        super(Phases.class);
    }
    
    public Phases findOnById(int idPhase){
        Phases phase;        
        try {
            Query query = em.createNamedQuery("Phases.findByIdPhase", Phases.class);
            query.setParameter("idPhase", idPhase);
            phase = (Phases)query.getSingleResult();
        } catch (Exception e) {
            phase = new Phases();
        }
        return phase;
    }
}
