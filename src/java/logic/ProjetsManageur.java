/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package logic;

import facade.ProjetsFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.ejb.EJB;
import model.Projets;
import model.Utilisateurs;

/**
 *
 * @author valibert
 */
@Named(value = "projetsManageur")
@SessionScoped
public class ProjetsManageur implements Serializable {
    
    private String idProjetRequest;
    private int idProjet;
    private ArrayList<Projets> listProjet;
    private Projets currentProjet;
    private int radioChoise;
    
    @EJB
            ProjetsFacade projetsFacade;
    
    /**
     * Creates a new instance of ProjetsManageur
     */
    public ProjetsManageur() {
    }
    
    public Projets getCurrentProjet() {
        return currentProjet;
    }
    
    public void setCurrentProjet(Projets currentProjet) {
        this.currentProjet = currentProjet;
    }
    
    public String getIdProjetRequest() {
        return idProjetRequest;
    }
    
    public void setIdProjetRequest(String idProjetRequest) {
        this.idProjetRequest = idProjetRequest;
    }
    
    public ProjetsFacade getProjetsFacade() {
        return projetsFacade;
    }
    
    public void setProjetsFacade(ProjetsFacade projetsFacade) {
        this.projetsFacade = projetsFacade;
    }
    
    public int getIdProjet() {
        return idProjet;
    }
    
    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }
    
    public ArrayList<Projets> getListProjet() {
        return listProjet;
    }
    
    public void setListProjet(ArrayList<Projets> listProjet) {
        this.listProjet = listProjet;
    }
    
    public int getRadioChoise() {
        return radioChoise;
    }
    
    public void setRadioChoise(int radioChoise) {
        this.radioChoise = radioChoise;
    }
    
    /* méthode */
    public int arraySize(){
        return listProjet.size();
    }
    
    public String etatProjet(){
        String etat;
        switch (currentProjet.getEtatFinal()) {
            case 0:
                etat = "En cours";
                break;
            case 1:
                etat = "Reussi";
                break;
            case 2:
                etat = "Echec";
                break;
            default:
                etat = "Non défini";
                break;
        }
        return etat;
    }
    
    public void load(Utilisateurs currentUser){
        try{
            setIdProjet(Integer.parseInt(idProjetRequest));
            
            System.out.println("idProjetRequest : "+idProjetRequest);
            currentProjet = projetsFacade.findByIdAndUser(idProjet, currentUser);
            
            listProjet = new ArrayList();
            listProjet.add(currentProjet);
        } catch(NumberFormatException e) {
            currentProjet = new Projets();
        }
    }
}
