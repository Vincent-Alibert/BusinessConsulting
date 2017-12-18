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
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Projets;
import model.Utilisateurs;

/**
 *
 * @author valibert
 */
@Named(value = "projetsManageur")
@SessionScoped
public class ProjetsManageur implements Serializable {
    
    private String idProjetRequest, libelle;
    private int idProjet;
    private Projets currentProjet;
    private Date dateDebutProj;
    private Date dateFinProj;
    private int etatProjet;
    private int heuresPassees;
    private Date dateOld;
    
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
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
    
    public Date getDateDebutProj() {
        return dateDebutProj;
    }
    
    public void setDateDebutProj(Date dateDebutProj) {
        this.dateDebutProj = dateDebutProj;
    }
    
    public Date getDateFinProj() {
        return dateFinProj;
    }
    
    public void setDateFinProj(Date dateFinProj) {
        this.dateFinProj = dateFinProj;
    }
    
    public int getEtatProjet() {
        return etatProjet;
    }
    
    public void setEtatProjet(int etatProjet) {
        this.etatProjet = etatProjet;
    }
    
    public Date getDateOld() {
        dateOld = new Date(0);
        return dateOld;
    }
    
    public void setDateOld(Date dateOld) {
        this.dateOld = dateOld;
    }
    
    public int getHeuresPassees() {
        return heuresPassees;
    }
    
    public void setHeuresPassees(int heuresPassees) {
        this.heuresPassees = heuresPassees;
    }
    
    
    /* méthode */
    
    public String etatProjet(){
        String etat;
        switch (currentProjet.getEtatFinal()) {
            case 0:
                etat = "En cours";
                break;
            case 1:
                etat = "Réussi";
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
            currentProjet = projetsFacade.findByIdAndUser(idProjet, currentUser);
            dateDebutProj = currentProjet.getDateDebutProj();
            dateFinProj = currentProjet.getDateFinProj();
            etatProjet = currentProjet.getEtatFinal();
            libelle = currentProjet.getLibelleProj();
        } catch(NumberFormatException e) {
            currentProjet = new Projets();
        }
    }
    
    public void modifProjet(){
        try{
            if(dateFinProj == null || dateDebutProj.before(dateFinProj)){
                if(dateFinProj == null){
                    dateFinProj = new Date(0);
                }
                currentProjet.setLibelleProj(libelle);
                currentProjet.setDateDebutProj(dateDebutProj);
                currentProjet.setDateFinProj(dateFinProj);
                currentProjet.setEtatFinal(etatProjet);
                projetsFacade.edit(currentProjet);
                dateDebutProj = null;
                dateFinProj = null;
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Le projet a bien été mis à jour.");
                context.addMessage("modifProjet", message);
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("Le projet n'a pas pu être mis à jour. Veuillez vérifier les informations rentrées.");
                context.addMessage("modifProjet", message);
            }
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Le projet n'a pas pu être mis à jour. Veuillez vérifier les informations rentrées.");
            context.addMessage("modifProjet", message);
        }
        
    }
    
    public int nbrHeuresPassees(){
        heuresPassees = projetsFacade.nbrHeuresPassees(currentProjet);
        return heuresPassees;
    }
}
