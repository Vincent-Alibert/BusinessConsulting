/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import facade.SecteursFacade;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import model.Secteurs;

/**
 *
 * @author valibert
 */
@Named(value = "secteursManageur")
@SessionScoped
public class SecteursManageur implements Serializable {
    Secteurs currentSecteur;
    ArrayList<Secteurs> listSecteur;
    
    @EJB
    SecteursFacade secteursFacade;

    /**
     * Creates a new instance of SecteurManageur
     */
    
    public SecteursManageur() {
    }

    public Secteurs getCurrentSecteur() {
        return currentSecteur;
    }

    public void setCurrentSecteur(Secteurs currentSecteur) {
        this.currentSecteur = currentSecteur;
    }

    public SecteursFacade getSecteursFacade() {
        return secteursFacade;
    }

    public void setSecteursFacade(SecteursFacade secteursFacade) {
        this.secteursFacade = secteursFacade;
    }

    public ArrayList<Secteurs> getListSecteur() {
        return listSecteur;
    }

    public void setListSecteur(ArrayList<Secteurs> listSecteur) {
        this.listSecteur = listSecteur;
    }
    
    @PostConstruct
    public void initSecteur(){
        listSecteur = new ArrayList();
        this.listSecteur.addAll(secteursFacade.findAll());
    }
    
}
