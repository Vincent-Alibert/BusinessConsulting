/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author valibert
 */
@Entity
@Table(name = "utilisateurs", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Utilisateurs.findAll", query = "SELECT u FROM Utilisateurs u"),
    @NamedQuery(name = "Utilisateurs.findOne", query = "SELECT u FROM Utilisateurs u WHERE u.mailUtil = :mailUtil AND u.mdpUtil = :mdpUtil"),
    @NamedQuery(name = "Utilisateurs.findByIdUtil", query = "SELECT u FROM Utilisateurs u WHERE u.idUtil = :idUtil"),
    @NamedQuery(name = "Utilisateurs.findByNomUtil", query = "SELECT u FROM Utilisateurs u WHERE u.nomUtil = :nomUtil"),
    @NamedQuery(name = "Utilisateurs.findByPrenomUtil", query = "SELECT u FROM Utilisateurs u WHERE u.prenomUtil = :prenomUtil"),
    @NamedQuery(name = "Utilisateurs.findByTelUtil", query = "SELECT u FROM Utilisateurs u WHERE u.telUtil = :telUtil"),
    @NamedQuery(name = "Utilisateurs.findByMailUtil", query = "SELECT u FROM Utilisateurs u WHERE u.mailUtil = :mailUtil"),
    @NamedQuery(name = "Utilisateurs.findByMdpUtil", query = "SELECT u FROM Utilisateurs u WHERE u.mdpUtil = :mdpUtil")})
public class Utilisateurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "idUtil")
    private int idUtil;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "nomUtil")
    private String nomUtil;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "prenomUtil")
    private String prenomUtil;
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "telUtil")
    private String telUtil;
    @Id
    @Basic(optional = false)
    @Size(min = 1, max = 50)
    @Column(name = "mailUtil")
    private String mailUtil;
    @Basic(optional = false)
    @Size(min = 1, max = 255)
    @Column(name = "mdpUtil")
    private String mdpUtil;
    @JoinTable(name = "util_spe", joinColumns = {
        @JoinColumn(name = "fk_idUtil", referencedColumnName = "idUtil")}, inverseJoinColumns = {
        @JoinColumn(name = "fk_idSpe", referencedColumnName = "idSpe")})
    @ManyToMany
    private Collection<Specialites> specialitesCollection;
    @ManyToMany(mappedBy = "utilisateursCollection")
    private Collection<Postes> postesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidUtil")
    private Collection<DetailConsultPhases> detailConsultPhasesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidChefProj")
    private Collection<Projets> projetsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidUtil")
    private Collection<Factures> facturesCollection;

    public Utilisateurs() {
    }

    public Utilisateurs(String mailUtil) {
        this.mailUtil = mailUtil;
    }

    public Utilisateurs(String mailUtil, int idUtil, String nomUtil, String prenomUtil, String telUtil, String mdpUtil) {
        this.mailUtil = mailUtil;
        this.idUtil = idUtil;
        this.nomUtil = nomUtil;
        this.prenomUtil = prenomUtil;
        this.telUtil = telUtil;
        this.mdpUtil = mdpUtil;
    }

    public int getIdUtil() {
        return idUtil;
    }

    public void setIdUtil(int idUtil) {
        this.idUtil = idUtil;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getPrenomUtil() {
        return prenomUtil;
    }

    public void setPrenomUtil(String prenomUtil) {
        this.prenomUtil = prenomUtil;
    }

    public String getTelUtil() {
        return telUtil;
    }

    public void setTelUtil(String telUtil) {
        this.telUtil = telUtil;
    }

    public String getMailUtil() {
        return mailUtil;
    }

    public void setMailUtil(String mailUtil) {
        this.mailUtil = mailUtil;
    }

    public String getMdpUtil() {
        return mdpUtil;
    }

    public void setMdpUtil(String mdpUtil) {
        this.mdpUtil = mdpUtil;
    }

    public Collection<Specialites> getSpecialitesCollection() {
        return specialitesCollection;
    }

    public void setSpecialitesCollection(Collection<Specialites> specialitesCollection) {
        this.specialitesCollection = specialitesCollection;
    }

    public Collection<Postes> getPostesCollection() {
        return postesCollection;
    }

    public void setPostesCollection(Collection<Postes> postesCollection) {
        this.postesCollection = postesCollection;
    }

    public Collection<DetailConsultPhases> getDetailConsultPhasesCollection() {
        return detailConsultPhasesCollection;
    }

    public void setDetailConsultPhasesCollection(Collection<DetailConsultPhases> detailConsultPhasesCollection) {
        this.detailConsultPhasesCollection = detailConsultPhasesCollection;
    }

    public Collection<Projets> getProjetsCollection() {
        return projetsCollection;
    }

    public void setProjetsCollection(Collection<Projets> projetsCollection) {
        this.projetsCollection = projetsCollection;
    }

    public Collection<Factures> getFacturesCollection() {
        return facturesCollection;
    }

    public void setFacturesCollection(Collection<Factures> facturesCollection) {
        this.facturesCollection = facturesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mailUtil != null ? mailUtil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Utilisateurs)) {
            return false;
        }
        Utilisateurs other = (Utilisateurs) object;
        if ((this.mailUtil == null && other.mailUtil != null) || (this.mailUtil != null && !this.mailUtil.equals(other.mailUtil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Utilisateurs[ mailUtil=" + mailUtil + " ]";
    }
    
}
