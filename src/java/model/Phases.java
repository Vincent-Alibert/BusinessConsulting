/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author valibert
 */
@Entity
@Table(name = "phases", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Phases.findAll", query = "SELECT p FROM Phases p"),
    @NamedQuery(name = "Phases.findByIdPhase", query = "SELECT p FROM Phases p WHERE p.idPhase = :idPhase"),
    @NamedQuery(name = "Phases.nbreHeurePassee", query = "SELECT SUM(d.nbrHeures) FROM DetailConsultPhases d WHERE d.fkidPhase = :idPhase"),
    @NamedQuery(name = "Phases.findOnByIdAndProjet", query = "SELECT p FROM Phases p WHERE p.idPhase = :idPhase AND P.fkidProjet = :idProjet"),
    @NamedQuery(name = "Phases.findByLibellePhase", query = "SELECT p FROM Phases p WHERE p.libellePhase = :libellePhase"),
    @NamedQuery(name = "Phases.findByMontantPhase", query = "SELECT p FROM Phases p WHERE p.montantPhase = :montantPhase"),
    @NamedQuery(name = "Phases.findByDateDebutPhase", query = "SELECT p FROM Phases p WHERE p.dateDebutPhase = :dateDebutPhase"),
    @NamedQuery(name = "Phases.findByDateFinPhase", query = "SELECT p FROM Phases p WHERE p.dateFinPhase = :dateFinPhase"),
    @NamedQuery(name = "Phases.findByEtatAvancement", query = "SELECT p FROM Phases p WHERE p.etatAvancement = :etatAvancement")
})
public class Phases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPhase")
    private Integer idPhase;
    
    @Basic(optional = false)
    @Column(name = "libellePhase", length = 255)
    private String libellePhase;
    
    @Basic(optional = false)
    @Column(name = "montantPhase")
    private double montantPhase;
    
    @Basic(optional = false)
    @Column(name = "dateDebutPhase")
    @Temporal(TemporalType.DATE)
    private Date dateDebutPhase;
    
    @Basic(optional = false)    
    @Column(name = "dateFinPhase")
    @Temporal(TemporalType.DATE)
    private Date dateFinPhase;
    
    @Basic(optional = false)    
    @Column(name = "etatAvancement")
    private int etatAvancement;
    
    @JoinTable(name = "phase_sect", joinColumns = {
        @JoinColumn(name = "fk_idPhase", referencedColumnName = "idPhase")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_idSecteur", referencedColumnName = "idSecteur")})
    @ManyToMany
    private Collection<Secteurs> secteursCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidPhase")
    private Collection<DetailConsultPhases> detailConsultPhasesCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidPhase")
    private Collection<Factures> facturesCollection;
    
    @JoinColumn(name = "fk_idProjet", referencedColumnName = "idProjet")
    @ManyToOne(optional = false)
    private Projets fkidProjet;

    public Phases() {
    }

    public Phases(Integer idPhase) {
        this.idPhase = idPhase;
    }

    public Phases(Integer idPhase, String libellePhase, double montantPhase, Date dateDebutPhase, Date dateFinPhase, int etatAvancement) {
        this.idPhase = idPhase;
        this.libellePhase = libellePhase;
        this.montantPhase = montantPhase;
        this.dateDebutPhase = dateDebutPhase;
        this.dateFinPhase = dateFinPhase;
        this.etatAvancement = etatAvancement;
    }

    public Integer getIdPhase() {
        return idPhase;
    }

    public void setIdPhase(Integer idPhase) {
        this.idPhase = idPhase;
    }

    public String getLibellePhase() {
        return libellePhase;
    }

    public void setLibellePhase(String libellePhase) {
        this.libellePhase = libellePhase;
    }

    public double getMontantPhase() {
        return montantPhase;
    }

    public void setMontantPhase(double montantPhase) {
        this.montantPhase = montantPhase;
    }

    public Date getDateDebutPhase() {
        return dateDebutPhase;
    }

    public void setDateDebutPhase(Date dateDebutPhase) {
        this.dateDebutPhase = dateDebutPhase;
    }

    public Date getDateFinPhase() {
        return dateFinPhase;
    }

    public void setDateFinPhase(Date dateFinPhase) {
        this.dateFinPhase = dateFinPhase;
    }

    public int getEtatAvancement() {
        return etatAvancement;
    }

    public void setEtatAvancement(int etatAvancement) {
        this.etatAvancement = etatAvancement;
    }

    public Collection<Secteurs> getSecteursCollection() {
        return secteursCollection;
    }

    public void setSecteursCollection(Collection<Secteurs> secteursCollection) {
        this.secteursCollection = secteursCollection;
    }

    public Collection<DetailConsultPhases> getDetailConsultPhasesCollection() {
        return detailConsultPhasesCollection;
    }

    public void setDetailConsultPhasesCollection(Collection<DetailConsultPhases> detailConsultPhasesCollection) {
        this.detailConsultPhasesCollection = detailConsultPhasesCollection;
    }

    public Collection<Factures> getFacturesCollection() {
        return facturesCollection;
    }

    public void setFacturesCollection(Collection<Factures> facturesCollection) {
        this.facturesCollection = facturesCollection;
    }

    public Projets getFkidProjet() {
        return fkidProjet;
    }

    public void setFkidProjet(Projets fkidProjet) {
        this.fkidProjet = fkidProjet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPhase != null ? idPhase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Phases)) {
            return false;
        }
        Phases other = (Phases) object;
        if ((this.idPhase == null && other.idPhase != null) || (this.idPhase != null && !this.idPhase.equals(other.idPhase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Phases[ idPhase=" + idPhase + " ]";
    }
    
}
