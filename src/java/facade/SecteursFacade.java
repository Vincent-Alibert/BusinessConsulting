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
import model.Secteurs;

/**
 *
 * @author valibert
 */
@Stateless
public class SecteursFacade extends AbstractFacade<Secteurs> {

    @PersistenceContext(unitName = "BusinessConsultingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SecteursFacade() {
        super(Secteurs.class);
    }
    
    public Secteurs findOneById(int idSecteur){
        Secteurs secteurs;        
        try {
            Query query = em.createNamedQuery("Secteurs.findByIdSecteur", Secteurs.class);
            query.setParameter("idSecteur", idSecteur);
            
            secteurs = (Secteurs)query.getSingleResult();
        } catch (Exception e) {
            secteurs = new Secteurs();
        }
        return secteurs;
    }
}
