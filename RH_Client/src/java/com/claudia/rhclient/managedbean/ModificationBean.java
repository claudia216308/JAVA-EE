
package com.claudia.rhclient.managedbean;


import com.claudia.rhclient.dao.UtilisateurFacade;
import com.claudia.rhclient.entity.Utilisateur;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;


@Named(value = "modificationBean")
@ManagedBean
@RequestScoped
public class ModificationBean {

    @EJB
    private UtilisateurFacade utilisateurFacade;

    private Utilisateur utilisateur;

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
    
    
    
    public ModificationBean() {
          //récupération de l'utilisateur de la session 
        utilisateur = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("utilisateur");
    }
    
    //mise à jour du compte de l'utilisateur
    public String modification(){

        //modification de l'utilisateur
        utilisateurFacade.edit(utilisateur);
        
        
        return "index";
    }
}
