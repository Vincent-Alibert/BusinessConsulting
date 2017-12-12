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
@Table(name = "specialites", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Specialites.findAll", query = "SELECT s FROM Specialites s")
    , @NamedQuery(name = "Specialites.findByIdSpe", query = "SELECT s FROM Specialites s WHERE s.idSpe = :idSpe")
    , @NamedQuery(name = "Specialites.findByLibelleSpe", query = "SELECT s FROM Specialites s WHERE s.libelleSpe = :libelleSpe")})
public class Specialites implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSpe")
    private Integer idSpe;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "libelleSpe")
    private String libelleSpe;
    @ManyToMany(mappedBy = "specialitesCollection")
    private Collection<Utilisateurs> utilisateursCollection;
    @JoinTable(name = "secteur_spe", joinColumns = {
        @JoinColumn(name = "fk_idSpe", referencedColumnName = "idSpe")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_idSecteur", referencedColumnName = "idSecteur")})
    @ManyToMany
    private Collection<Secteurs> secteursCollection;

    public Specialites() {
    }

    public Specialites(Integer idSpe) {
        this.idSpe = idSpe;
    }

    public Specialites(Integer idSpe, String libelleSpe) {
        this.idSpe = idSpe;
        this.libelleSpe = libelleSpe;
    }

    public Integer getIdSpe() {
        return idSpe;
    }

    public void setIdSpe(Integer idSpe) {
        this.idSpe = idSpe;
    }

    public String getLibelleSpe() {
        return libelleSpe;
    }

    public void setLibelleSpe(String libelleSpe) {
        this.libelleSpe = libelleSpe;
    }

    public Collection<Utilisateurs> getUtilisateursCollection() {
        return utilisateursCollection;
    }

    public void setUtilisateursCollection(Collection<Utilisateurs> utilisateursCollection) {
        this.utilisateursCollection = utilisateursCollection;
    }

    public Collection<Secteurs> getSecteursCollection() {
        return secteursCollection;
    }

    public void setSecteursCollection(Collection<Secteurs> secteursCollection) {
        this.secteursCollection = secteursCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSpe != null ? idSpe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Specialites)) {
            return false;
        }
        Specialites other = (Specialites) object;
        if ((this.idSpe == null && other.idSpe != null) || (this.idSpe != null && !this.idSpe.equals(other.idSpe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Specialites[ idSpe=" + idSpe + " ]";
    }
    
}
