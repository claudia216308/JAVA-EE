
package com.claudia.rhrest.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "Salarie")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salarie.findAll", query = "SELECT s FROM Salarie s"),
    @NamedQuery(name = "Salarie.findByNom", query = "SELECT s FROM Salarie s WHERE s.nom = :nom"),
    @NamedQuery(name = "Salarie.findByPrenom", query = "SELECT s FROM Salarie s WHERE s.prenom = :prenom"),
    @NamedQuery(name = "Salarie.findById", query = "SELECT s FROM Salarie s WHERE s.id = :id"),
    @NamedQuery(name = "Salarie.findBySalaireEntre", query = "SELECT s FROM Salarie s WHERE s.salaireEntre = :salaireEntre"),
    @NamedQuery(name = "Salarie.findByDateEmbauche", query = "SELECT s FROM Salarie s WHERE s.dateEmbauche = :dateEmbauche"),
    @NamedQuery(name = "Salarie.findByIdDepartement", query = "SELECT s FROM Salarie s WHERE s.idDepartement = :idDepartement"),
    @NamedQuery(name = "Salarie.findByPoste", query = "SELECT s FROM Salarie s WHERE s.poste = :poste")})
public class Salarie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "prenom")
    private String prenom;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "salaire_entre")
    private Double salaireEntre;
    
    @Column(name = "date_embauche")  
    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;
    @Column(name = "id_departement")
    private Short idDepartement;
    @Size(max = 50)
    @Column(name = "poste")
    private String poste;

    public Salarie() {
    }

    public Salarie(Short id) {
        this.id = id;
    }

    public Salarie(Short id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public Double getSalaireEntre() {
        return salaireEntre;
    }

    public void setSalaireEntre(Double salaireEntre) {
        this.salaireEntre = salaireEntre;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Short getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Short idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salarie)) {
            return false;
        }
        Salarie other = (Salarie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.claudia.rhrest.entity.Salarie[ id=" + id + " ]";
    }
    
}
