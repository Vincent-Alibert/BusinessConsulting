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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "clients", catalog = "projet_businessconsult", schema = "")
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c")
    , @NamedQuery(name = "Clients.findByIdClient", query = "SELECT c FROM Clients c WHERE c.idClient = :idClient")
    , @NamedQuery(name = "Clients.findByNomClient", query = "SELECT c FROM Clients c WHERE c.nomClient = :nomClient")
    , @NamedQuery(name = "Clients.findByMailClient", query = "SELECT c FROM Clients c WHERE c.mailClient = :mailClient")})
public class Clients implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClient")
    private Integer idClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nomClient")
    private String nomClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "telClient")
    private String telClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "mailClient")
    private String mailClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "rueClient")
    private String rueClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codePostalClient")
    private String codePostalClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "villeClient")
    private String villeClient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidClt")
    private Collection<Projets> projetsCollection;

    public Clients() {
    }

    public Clients(Integer idClient) {
        this.idClient = idClient;
    }

    public Clients(Integer idClient, String nomClient, String telClient, String mailClient, String rueClient, String codePostalClient, String villeClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.telClient = telClient;
        this.mailClient = mailClient;
        this.rueClient = rueClient;
        this.codePostalClient = codePostalClient;
        this.villeClient = villeClient;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getTelClient() {
        return telClient;
    }

    public void setTelClient(String telClient) {
        this.telClient = telClient;
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    public String getRueClient() {
        return rueClient;
    }

    public void setRueClient(String rueClient) {
        this.rueClient = rueClient;
    }

    public String getCodePostalClient() {
        return codePostalClient;
    }

    public void setCodePostalClient(String codePostalClient) {
        this.codePostalClient = codePostalClient;
    }

    public String getVilleClient() {
        return villeClient;
    }

    public void setVilleClient(String villeClient) {
        this.villeClient = villeClient;
    }

    public Collection<Projets> getProjetsCollection() {
        return projetsCollection;
    }

    public void setProjetsCollection(Collection<Projets> projetsCollection) {
        this.projetsCollection = projetsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Clients[ idClient=" + idClient + " ]";
    }
    
}
