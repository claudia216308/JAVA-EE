package com.claudia.rhclient.managedbean;

import com.claudia.rhclient.entity.Utilisateur;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/* Bean qui gère les redirections de la barre de navigation */

@Named(value = "navigationController")
@ManagedBean
@RequestScoped
public class NavigationController implements Serializable {

    private String connexion ="Se connecter";
    private String courses= "";
    private String compte ="";
    private Utilisateur ut = null;

    public String getConnexion() {
        return connexion;
    }

    public void setConnexion(String connexion) {
        this.connexion = connexion;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    
    public NavigationController() { 
        ut = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("utilisateur");
        
        if(ut!=null){
            connexion = "Se déconnecter";
            courses = "Mes courses";
            compte = "Mon Compte";
        }
    }
    
    public String redirectionConnexion(){
        
      if(ut!=null){  //se deconnecter
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        connexion ="Se connecter";
        courses= "";
        compte ="";
        return "index";
      } 
      else{
          return "connexion";
      }    
    }
    

    
    
    public String redirectionCompte(){
        if(ut!=null){ //Mon Compte 
            return "compte";
        }
        else{
            return "index";
        }
    }
    
    
}
