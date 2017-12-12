/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.Specialites;

/**
 *
 * @author valibert
 */
@Stateless
public class SpecialitesFacade extends AbstractFacade<Specialites> {

    @PersistenceContext(unitName = "BusinessConsultingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SpecialitesFacade() {
        super(Specialites.class);
    }
    
}
