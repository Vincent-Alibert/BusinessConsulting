/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author valibert
 */
@Entity
@Table(name = "factures", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Factures.findAll", query = "SELECT f FROM Factures f")
    , @NamedQuery(name = "Factures.findByIdFacture", query = "SELECT f FROM Factures f WHERE f.idFacture = :idFacture")
    , @NamedQuery(name = "Factures.findByDateFacturation", query = "SELECT f FROM Factures f WHERE f.dateFacturation = :dateFacturation")
    , @NamedQuery(name = "Factures.findByMontant", query = "SELECT f FROM Factures f WHERE f.montant = :montant")})
public class Factures implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idFacture")
    private Integer idFacture;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateFacturation")
    @Temporal(TemporalType.DATE)
    private Date dateFacturation;
    @Basic(optional = false)
    @NotNull
    @Column(name = "montant")
    private double montant;
    @JoinColumn(name = "fk_idUtil", referencedColumnName = "idUtil")
    @ManyToOne(optional = false)
    private Utilisateurs fkidUtil;
    @JoinColumn(name = "fk_idPhase", referencedColumnName = "idPhase")
    @ManyToOne(optional = false)
    private Phases fkidPhase;

    public Factures() {
    }

    public Factures(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Factures(Integer idFacture, Date dateFacturation, double montant) {
        this.idFacture = idFacture;
        this.dateFacturation = dateFacturation;
        this.montant = montant;
    }

    public Integer getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(Integer idFacture) {
        this.idFacture = idFacture;
    }

    public Date getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(Date dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Utilisateurs getFkidUtil() {
        return fkidUtil;
    }

    public void setFkidUtil(Utilisateurs fkidUtil) {
        this.fkidUtil = fkidUtil;
    }

    public Phases getFkidPhase() {
        return fkidPhase;
    }

    public void setFkidPhase(Phases fkidPhase) {
        this.fkidPhase = fkidPhase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacture != null ? idFacture.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Factures)) {
            return false;
        }
        Factures other = (Factures) object;
        if ((this.idFacture == null && other.idFacture != null) || (this.idFacture != null && !this.idFacture.equals(other.idFacture))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Factures[ idFacture=" + idFacture + " ]";
    }
    
}
