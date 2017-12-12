/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author valibert
 */
@Entity
@Table(name = "postes", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Postes.findAll", query = "SELECT p FROM Postes p")
    , @NamedQuery(name = "Postes.findByIdPoste", query = "SELECT p FROM Postes p WHERE p.idPoste = :idPoste")
    , @NamedQuery(name = "Postes.findByLibelle", query = "SELECT p FROM Postes p WHERE p.libelle = :libelle")})
public class Postes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPoste")
    private Integer idPoste;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "libelle")
    private String libelle;
    @JoinTable(name = "util_postes", joinColumns = {
        @JoinColumn(name = "fk_idPoste", referencedColumnName = "idPoste")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_mailUtil", referencedColumnName = "mailUtil")})
    @ManyToMany
    private Collection<Utilisateurs> utilisateursCollection;

    public Postes() {
    }

    public Postes(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public Postes(Integer idPoste, String libelle) {
        this.idPoste = idPoste;
        this.libelle = libelle;
    }

    public Integer getIdPoste() {
        return idPoste;
    }

    public void setIdPoste(Integer idPoste) {
        this.idPoste = idPoste;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Collection<Utilisateurs> getUtilisateursCollection() {
        return utilisateursCollection;
    }

    public void setUtilisateursCollection(Collection<Utilisateurs> utilisateursCollection) {
        this.utilisateursCollection = utilisateursCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoste != null ? idPoste.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postes)) {
            return false;
        }
        Postes other = (Postes) object;
        if ((this.idPoste == null && other.idPoste != null) || (this.idPoste != null && !this.idPoste.equals(other.idPoste))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Postes[ idPoste=" + idPoste + " ]";
    }
    
}
