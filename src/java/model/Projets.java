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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author valibert
 */
@Entity
@Table(name = "projets", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Projets.findAll", query = "SELECT p FROM Projets p"),
    @NamedQuery(name = "Projets.findByIdProjet", query = "SELECT p FROM Projets p WHERE p.idProjet = :idProjet"),
    @NamedQuery(name = "Projets.findByIdAndCProjet", query = "SELECT p FROM Projets p WHERE p.idProjet = :idProjet AND p.fkidChefProj = :idChefProj"),
    @NamedQuery(name = "Projets.findByLibelleProj", query = "SELECT p FROM Projets p WHERE p.libelleProj = :libelleProj"),
    @NamedQuery(name = "Projets.findByMontantProj", query = "SELECT p FROM Projets p WHERE p.montantProj = :montantProj"),
    @NamedQuery(name = "Projets.findByDateDebutProj", query = "SELECT p FROM Projets p WHERE p.dateDebutProj = :dateDebutProj"),
    @NamedQuery(name = "Projets.findByDateFinProj", query = "SELECT p FROM Projets p WHERE p.dateFinProj = :dateFinProj"),
    @NamedQuery(name = "Projets.findByEtatFinal", query = "SELECT p FROM Projets p WHERE p.etatFinal = :etatFinal")})
public class Projets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProjet")
    private Integer idProjet;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "libelleProj")
    private String libelleProj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montantProj")
    private double montantProj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateDebutProj")
    @Temporal(TemporalType.DATE)
    private Date dateDebutProj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateFinProj")
    @Temporal(TemporalType.DATE)
    private Date dateFinProj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "etatFinal")
    private int etatFinal;
    @JoinColumn(name = "fk_idClt", referencedColumnName = "idClient")
    @ManyToOne(optional = false)
    private Clients fkidClt;
    @JoinColumn(name = "fk_idChefProj", referencedColumnName = "idUtil")
    @ManyToOne(optional = false)
    private Utilisateurs fkidChefProj;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidProjet")
    private Collection<Phases> phasesCollection;

    public Projets() {
    }

    public Projets(Integer idProjet) {
        this.idProjet = idProjet;
    }

    public Projets(Integer idProjet, String libelleProj, double montantProj, Date dateDebutProj, Date dateFinProj, int etatFinal) {
        this.idProjet = idProjet;
        this.libelleProj = libelleProj;
        this.montantProj = montantProj;
        this.dateDebutProj = dateDebutProj;
        this.dateFinProj = dateFinProj;
        this.etatFinal = etatFinal;
    }

    public Integer getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(Integer idProjet) {
        this.idProjet = idProjet;
    }

    public String getLibelleProj() {
        return libelleProj;
    }

    public void setLibelleProj(String libelleProj) {
        this.libelleProj = libelleProj;
    }

    public double getMontantProj() {
        return montantProj;
    }

    public void setMontantProj(double montantProj) {
        this.montantProj = montantProj;
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

    public int getEtatFinal() {
        return etatFinal;
    }

    public void setEtatFinal(int etatFinal) {
        this.etatFinal = etatFinal;
    }

    public Clients getFkidClt() {
        return fkidClt;
    }

    public void setFkidClt(Clients fkidClt) {
        this.fkidClt = fkidClt;
    }

    public Utilisateurs getFkidChefProj() {
        return fkidChefProj;
    }

    public void setFkidChefProj(Utilisateurs fkidChefProj) {
        this.fkidChefProj = fkidChefProj;
    }

    public Collection<Phases> getPhasesCollection() {
        return phasesCollection;
    }

    public void setPhasesCollection(Collection<Phases> phasesCollection) {
        this.phasesCollection = phasesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProjet != null ? idProjet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projets)) {
            return false;
        }
        Projets other = (Projets) object;
        if ((this.idProjet == null && other.idProjet != null) || (this.idProjet != null && !this.idProjet.equals(other.idProjet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Projets[ idProjet=" + idProjet + " ]";
    }
    
}
