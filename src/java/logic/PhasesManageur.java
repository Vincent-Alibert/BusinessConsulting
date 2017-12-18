package logic;

import facade.PhasesFacade;
import facade.SecteursFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Phases;
import model.Projets;
import model.Secteurs;

/*

@author valibert
*/
@Named(value = "phasesManageur")
@SessionScoped
public class PhasesManageur implements Serializable {
    private String libelle;
    private double montant;
    private Date dateDebut;
    private Date dateFin;
    private int etatAvancement;
    private Phases currentPhase;
    private int secteurChoise;
    private int idPhase;
    private String IdPhaseRequest;
    private Secteurs currentSecteur;
    private ArrayList<Secteurs> listSecteurs;
    private ArrayList<Phases> listPhases;
    
    @EJB
            PhasesFacade phasesFacade;
    @EJB
            SecteursFacade secteursFacade;
    
    /*
    Creates a new instance of PhasesManageur
    */
    public PhasesManageur() {
    }
    
    public Phases getCurrentPhase() {
        return currentPhase;
    }
    
    public void setCurrentPhase(Phases currentPhase) {
        this.currentPhase = currentPhase;
    }
    
    public PhasesFacade getPhasesFacade() {
        return phasesFacade;
    }
    
    public void setPhasesFacade(PhasesFacade phasesFacade) {
        this.phasesFacade = phasesFacade;
    }
    
    public String getLibelle() {
        return libelle;
    }
    
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    public double getMontant() {
        return montant;
    }
    
    public void setMontant(double montant) {
        this.montant = montant;
    }
    
    public Date getDateDebut() {
        return dateDebut;
    }
    
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    
    public Date getDateFin() {
        return dateFin;
    }
    
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    
    public int getEtatAvancement() {
        return etatAvancement;
    }
    
    public void setEtatAvancement(int etatAvancement) {
        this.etatAvancement = etatAvancement;
    }
    
    public int getSecteurChoise() {
        return secteurChoise;
    }
    
    public void setSecteurChoise(int secteurChoise) {
        this.secteurChoise = secteurChoise;
    }
    
    public SecteursFacade getSecteursFacade() {
        return secteursFacade;
    }
    
    public void setSecteursFacade(SecteursFacade secteursFacade) {
        this.secteursFacade = secteursFacade;
    }
    
    public Secteurs getCurrentSecteur() {
        return currentSecteur;
    }
    
    public void setCurrentSecteur(Secteurs currentSecteur) {
        this.currentSecteur = currentSecteur;
    }
    
    public ArrayList<Secteurs> getListSecteurs() {
        return listSecteurs;
    }
    
    public void setListSecteurs(ArrayList<Secteurs> listSecteurs) {
        this.listSecteurs = listSecteurs;
    }
    
    public ArrayList<Phases> getListPhases() {
        return listPhases;
    }
    
    public void setListPhases(ArrayList<Phases> listPhases) {
        this.listPhases = listPhases;
    }
    
    public int getIdPhase() {
        return idPhase;
    }
    
    public void setIdPhase(int idPhase) {
        this.idPhase = idPhase;
    }
    
    public String getIdPhaseRequest() {
        return IdPhaseRequest;
    }
    
    public void setIdPhaseRequest(String IdPhaseRequest) {
        this.IdPhaseRequest = IdPhaseRequest;
    }
    
    
    /*  méthode */
    public String addPhase(Projets currentProjet){
        try{
            currentSecteur = secteursFacade.findOneById(secteurChoise);
            currentPhase = new Phases(Integer.MIN_VALUE, libelle, montant, dateDebut, dateFin, 0);
            currentPhase.setFkidProjet(currentProjet);
            listSecteurs = new ArrayList();
            listSecteurs.add(currentSecteur);
            currentPhase.setSecteursCollection(listSecteurs);
            phasesFacade.create(currentPhase);
            
            setLibelle(null);
            setDateDebut(null);
            setDateFin(null);
            setMontant(0);
            setEtatAvancement(0);
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("La phase a bien été ajouté");
            context.addMessage("ajoutPhase", message);
            
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("La phase n'a pas pu être ajouté. Veuillez vérifier les informations rentrées.");
            context.addMessage("ajoutPhase", message);
        }
        return "";
    }
    
    public void load(Projets currentProjet){
        try{
            setIdPhase(Integer.parseInt(IdPhaseRequest));
            currentPhase = phasesFacade.findOnByIdAndProjet(idPhase, currentProjet);
            listPhases = new ArrayList();
            listPhases.add(currentPhase);
            dateDebut= currentPhase.getDateDebutPhase();
            dateFin = currentPhase.getDateFinPhase();
            
        } catch(NumberFormatException e) {
            currentPhase = new Phases();
        }
    }
    
    public String deletePhase(int idPhase){
        try{
            currentPhase = phasesFacade.findOnById(idPhase);
            phasesFacade.remove(currentPhase);
            
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("La phase a bien été supprimé");
            context.addMessage("supPhase", message);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("La phase n'a pas pu être supprimé. Veuillez contacter l'administrateur.");
            context.addMessage("supPhase", message);
        }
        return "";
    }
    
    public int nbreHeurePassee (){
        int nbreHeure;
        nbreHeure = phasesFacade.nbreHeurePassee(currentPhase);
        return nbreHeure;
    }
    
    public void modifPhase(){
        try{
            if(dateFin == null || dateDebut.before(dateFin)){
                if(dateFin == null){
                    dateFin = new Date(0);
                }
                currentPhase.setDateDebutPhase(dateDebut);
                currentPhase.setDateFinPhase(dateFin);
                phasesFacade.edit(currentPhase);
                dateDebut = null;
                dateFin = null;                
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("La phase a bien été mis à jour.");
                context.addMessage("modifPhase", message);
            } else {
                FacesContext context = FacesContext.getCurrentInstance();
                FacesMessage message = new FacesMessage();
                message.setSeverity(FacesMessage.SEVERITY_INFO);
                message.setSummary("La phase n'a pas pu être mis à jour. Veuillez vérifier les informations rentrées.");
                context.addMessage("modifPhase", message);
            }
            
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("La phase n'a pas pu être mis à jour. Veuillez vérifier les informations rentrées.");
            context.addMessage("modifPhase", message);
        }
        
    }
}
