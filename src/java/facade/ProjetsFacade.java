/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package facade;

import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Projets;
import model.Utilisateurs;

/**
 *
 * @author valibert
 */
@Stateless
public class ProjetsFacade extends AbstractFacade<Projets> {
    
    @PersistenceContext(unitName = "BusinessConsultingPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public ProjetsFacade() {
        super(Projets.class);
    }
    
    public Projets findByIdAndUser(int idProjet, Utilisateurs chefProj){
        Projets projet;        
        try {
            Query query = em.createNamedQuery("Projets.findByIdAndCProjet", Projets.class);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idProjet", idProjet);
            query.setParameter("idChefProj", chefProj);
            projet = (Projets)query.getSingleResult();
        } catch (Exception e) {
            projet = new Projets();
        }
        return projet;
    }
    
    public int nbrHeuresPassees(Projets currentProjet){
        int heures;
        Query query = em.createNamedQuery("Projets.heuresPassees", Projets.class);
        query.setParameter("idProjet", currentProjet.getIdProjet());
        heures = Integer.parseInt(query.getSingleResult().toString());
        return heures;
    }
    public ArrayList<Projets> findByEtat(int etat){
        ArrayList<Projets> listProjet;        
        try {
            Query query = em.createNamedQuery("Projets.findByEtatFinal", Projets.class);
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("etatFinal", etat);
            listProjet = new ArrayList(query.getResultList()) ;
        } catch (Exception e) {
            listProjet = new ArrayList();
        }
        return listProjet;
    }
}