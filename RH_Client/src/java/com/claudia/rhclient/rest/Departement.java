
package com.claudia.rhclient.rest;

import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

@Named(value ="departementBean")
@ManagedBean
@RequestScoped
public class Departement {
    
    @NotNull
    private String nom;
    
    private String description;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Departement(){       
    }
    
    public Departement(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }
    
    public Departement(String nom, int id) {
        this.nom = nom;
        this.id = id;
    }
    
   
          
}
