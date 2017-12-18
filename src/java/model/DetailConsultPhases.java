/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
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
import javax.validation.constraints.Size;

/**
 *
 * @author valibert
 */
@Entity
@Table(name = "detail_consult_phases", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "DetailConsultPhases.findAll", query = "SELECT d FROM DetailConsultPhases d")
    , @NamedQuery(name = "DetailConsultPhases.findByIdDetailCOnsultPhase", query = "SELECT d FROM DetailConsultPhases d WHERE d.idDetailCOnsultPhase = :idDetailCOnsultPhase")
    , @NamedQuery(name = "DetailConsultPhases.findByNbrHeures", query = "SELECT d FROM DetailConsultPhases d WHERE d.nbrHeures = :nbrHeures")})
public class DetailConsultPhases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDetailCOnsultPhase")
    private Integer idDetailCOnsultPhase;
    
    @Basic(optional = false)
    @Column(name = "nbrHeures")
    private int nbrHeures;
    
    @Basic(optional = false)
    @Size(min = 1, max = 65535)
    @Column(name = "resume")
    private String resume;
    
    @JoinColumn(name = "fk_idUtil", referencedColumnName = "idUtil")
    @ManyToOne(optional = false)
    private Utilisateurs fkidUtil;
    
    @JoinColumn(name = "fk_idPhase", referencedColumnName = "idPhase")
    @ManyToOne(optional = false)
    private Phases fkidPhase;

    public DetailConsultPhases() {
    }

    public DetailConsultPhases(Integer idDetailCOnsultPhase) {
        this.idDetailCOnsultPhase = idDetailCOnsultPhase;
    }

    public DetailConsultPhases(Integer idDetailCOnsultPhase, int nbrHeures, String resume) {
        this.idDetailCOnsultPhase = idDetailCOnsultPhase;
        this.nbrHeures = nbrHeures;
        this.resume = resume;
    }

    public Integer getIdDetailCOnsultPhase() {
        return idDetailCOnsultPhase;
    }

    public void setIdDetailCOnsultPhase(Integer idDetailCOnsultPhase) {
        this.idDetailCOnsultPhase = idDetailCOnsultPhase;
    }

    public int getNbrHeures() {
        return nbrHeures;
    }

    public void setNbrHeures(int nbrHeures) {
        this.nbrHeures = nbrHeures;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
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
        hash += (idDetailCOnsultPhase != null ? idDetailCOnsultPhase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailConsultPhases)) {
            return false;
        }
        DetailConsultPhases other = (DetailConsultPhases) object;
        if ((this.idDetailCOnsultPhase == null && other.idDetailCOnsultPhase != null) || (this.idDetailCOnsultPhase != null && !this.idDetailCOnsultPhase.equals(other.idDetailCOnsultPhase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.DetailConsultPhases[ idDetailCOnsultPhase=" + idDetailCOnsultPhase + " ]";
    }
    
}
