/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package logic;

import facade.DetailConsultPhasesFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.DetailConsultPhases;
import model.Phases;
import model.Utilisateurs;

/**
 *
 * @author valibert
 */
@Named(value = "detailManageur")
@SessionScoped
public class DetailManageur implements Serializable {
    private int nbrHeure;
    private String resume;
    private DetailConsultPhases currentDetail;
    private String idDetailRequest;
    
    @EJB
            DetailConsultPhasesFacade detailConsultPhasesFacade;
    /**
     * Creates a new instance of DetailManageur
     */
    public DetailManageur() {
    }
    
    public int getNbrHeure() {
        return nbrHeure;
    }
    
    public void setNbrHeure(int nbrHeure) {
        this.nbrHeure = nbrHeure;
    }
    
    public String getResume() {
        return resume;
    }
    
    public void setResume(String resume) {
        this.resume = resume;
    }
    
    public DetailConsultPhases getCurrentDetail() {
        return currentDetail;
    }
    
    public void setCurrentDetail(DetailConsultPhases currentDetail) {
        this.currentDetail = currentDetail;
    }
    
    public DetailConsultPhasesFacade getDetailConsultPhasesFacade() {
        return detailConsultPhasesFacade;
    }
    
    public void setDetailConsultPhasesFacade(DetailConsultPhasesFacade detailConsultPhasesFacade) {
        this.detailConsultPhasesFacade = detailConsultPhasesFacade;
    }
    
    public String getIdDetailRequest() {
        return idDetailRequest;
    }
    
    public void setIdDetailRequest(String idDetailRequest) {
        this.idDetailRequest = idDetailRequest;
    }
    
    
    /*  méthode */
    public String addDetail(Phases currentPhase, Utilisateurs currentUser){ System.out.println("Libelle : "+currentPhase.getLibellePhase()+" User : "+currentUser.getNomUtil());
        try{
            currentDetail = new DetailConsultPhases ();
            currentDetail.setFkidPhase(currentPhase);
            currentDetail.setFkidUtil(currentUser);
            currentDetail.setNbrHeures(nbrHeure);
            currentDetail.setResume(resume);
            
            detailConsultPhasesFacade.create(currentDetail);
            
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Le ticket a bien été créé");
            context.addMessage("ajoutDetail", message);
            
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Le ticket n'a pas pu être ajouté. Veuillez vérifier les informations rentrées.");
            context.addMessage("ajoutDetail", message);
        }
        return "";
    }
    
    public void load(){
        try{
            int idDetail = Integer.parseInt(idDetailRequest);
            currentDetail = detailConsultPhasesFacade.findOneById(idDetail);
            nbrHeure = currentDetail.getNbrHeures();
            resume = currentDetail.getResume();
            
        } catch(NumberFormatException e) {
            currentDetail = new DetailConsultPhases();
        }
    }
    
    public String deleteDetail(int idDetail){
        try{
            currentDetail = detailConsultPhasesFacade.findOneById(idDetail);
            detailConsultPhasesFacade.remove(currentDetail);
            
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Le ticket a bien été supprimé");
            context.addMessage("supDetail", message);
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Le ticket n'a pas pu être supprimé. Veuillez contacter l'administrateur.");
            context.addMessage("supDetail", message);
        }
        return "";
    }
    
    public void modifDetail(){
        try{
            currentDetail.setNbrHeures(nbrHeure);
            currentDetail.setResume(resume);
            detailConsultPhasesFacade.edit(currentDetail);
            
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Le ticket a bien été mis à jour.");
            context.addMessage("modifDetail", message);
            
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage();
            message.setSeverity(FacesMessage.SEVERITY_INFO);
            message.setSummary("Le ticket n'a pas pu être mis à jour. Veuillez vérifier les informations rentrées.");
            context.addMessage("modifDetail", message);
        }
    }
    public void reset(){
        nbrHeure = 0;
        resume = "";
    }
}
