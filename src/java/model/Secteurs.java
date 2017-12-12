package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author valibert
 */
@Entity
@Table(name = "secteurs", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Secteurs.findAll", query = "SELECT s FROM Secteurs s")
    , @NamedQuery(name = "Secteurs.findByIdSecteur", query = "SELECT s FROM Secteurs s WHERE s.idSecteur = :idSecteur")
    , @NamedQuery(name = "Secteurs.findByLibelleSecteur", query = "SELECT s FROM Secteurs s WHERE s.libelleSecteur = :libelleSecteur")})
public class Secteurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idSecteur")
    private Integer idSecteur;
    
    @Basic(optional = false)
    @Column(name = "libelleSecteur", length = 50)
    private String libelleSecteur;
    
    @ManyToMany(mappedBy = "secteursCollection")
    private Collection<Specialites> specialitesCollection;
    
    @ManyToMany(mappedBy = "secteursCollection")
    private Collection<Phases> phasesCollection;

    public Secteurs() {
    }

    public Secteurs(Integer idSecteur) {
        this.idSecteur = idSecteur;
    }

    public Secteurs(Integer idSecteur, String libelleSecteur) {
        this.idSecteur = idSecteur;
        this.libelleSecteur = libelleSecteur;
    }

    public Integer getIdSecteur() {
        return idSecteur;
    }

    public void setIdSecteur(Integer idSecteur) {
        this.idSecteur = idSecteur;
    }

    public String getLibelleSecteur() {
        return libelleSecteur;
    }

    public void setLibelleSecteur(String libelleSecteur) {
        this.libelleSecteur = libelleSecteur;
    }

    public Collection<Specialites> getSpecialitesCollection() {
        return specialitesCollection;
    }

    public void setSpecialitesCollection(Collection<Specialites> specialitesCollection) {
        this.specialitesCollection = specialitesCollection;
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
        hash += (idSecteur != null ? idSecteur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Secteurs)) {
            return false;
        }
        Secteurs other = (Secteurs) object;
        if ((this.idSecteur == null && other.idSecteur != null) || (this.idSecteur != null && !this.idSecteur.equals(other.idSecteur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Secteurs[ idSecteur=" + idSecteur + " ]";
    }
    
}
