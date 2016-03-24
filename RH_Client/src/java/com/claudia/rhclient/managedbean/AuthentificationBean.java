package com.claudia.rhclient.managedbean;

import com.claudia.rhclient.dao.UtilisateurFacade;
import com.claudia.rhclient.dao.Utilisateur;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


@Named(value = "authentificationBean")
@ManagedBean
@RequestScoped
public class AuthentificationBean implements Serializable{
    
    @EJB
    private UtilisateurFacade utilisateurFacade;

    private static final long serialVersionUID = 1L;
    
    private String pseudo;
    private String passe;
    private Utilisateur utilisateur;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getPasse() {
        return passe;
    }

    public void setPasse(String passe) {
        this.passe = passe;
    }
    
    public AuthentificationBean() {
    }
    
    
    
    public String authentification(){ 

        
        utilisateur = utilisateurFacade.trouverPseudo(pseudo);
        
        if(utilisateur==null){
            //message d'erreur  
            FacesMessage message = new FacesMessage( "Ce pseudo n'existe pas" );
            FacesContext.getCurrentInstance().addMessage( null, message );
            return "connexion";
        }
        
        else{
            if(!utilisateur.getMotdepasse().equals(passe)){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mot de passe incorrect",null );
                FacesContext.getCurrentInstance().addMessage( null, message );
                return "connexion";
            }         
        }
        
        //Création de la session et enregistrement de l'utilisateur en paramètre
         FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("utilisateur", utilisateur);
        return "index";
    }
    
    
  
    

}
