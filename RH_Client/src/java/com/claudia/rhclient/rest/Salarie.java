
package com.claudia.rhclient.rest;

import java.util.Date;
import javax.validation.constraints.NotNull;


public class Salarie {
    
    @NotNull(message="Veuillez entrer un nom")
    private String nom;
    
    private String prenom;
    private int id;
    private Double salaire_entre;
    private Date date_embauche;
    private Departement departement;
    private String poste ;

    public Salarie(int id, String nom, String prenom, Double salaire_entre, Date date_embauche, Departement departement, String poste) {
        this.nom = nom;
        this.prenom = prenom;
        this.salaire_entre = salaire_entre;
        this.date_embauche = date_embauche;
        this.departement = departement;
        this.poste = poste;
        this.id = id;
    }

    public Salarie(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }

    
    
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getSalaire_entre() {
        return salaire_entre;
    }

    public void setSalaire_entre(Double salaire_entre) {
        this.salaire_entre = salaire_entre;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Salarie(){       
    }
    

    
   
}
