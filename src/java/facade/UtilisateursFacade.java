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
import model.Utilisateurs;

/**
 *
 * @author valibert
 */
@Stateless
public class UtilisateursFacade extends AbstractFacade<Utilisateurs> {

    @PersistenceContext(unitName = "BusinessConsultingPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateursFacade() {
        super(Utilisateurs.class);
    }
    
    public Utilisateurs findOne(String mail, String mdp){
        Utilisateurs user;        
        try {
            Query query = em.createNamedQuery("Utilisateurs.findOne", Utilisateurs.class);
            query.setParameter("mailUtil", mail);
            query.setParameter("mdpUtil", mdp);
            user = (Utilisateurs)query.getSingleResult();
        } catch (Exception e) {
            user = new Utilisateurs();
        }
        return user;
    }
}
