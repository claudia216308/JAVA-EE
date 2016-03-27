
package com.claudia.rhclient.managedbean;


import com.claudia.rhclient.entity.UtilisateurFacade;
import com.claudia.rhclient.dao.Utilisateur;
import static com.claudia.rhclient.rest.RestUtilisateur.creationUtilisateur;
import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value ="inscriptionBean")
@ManagedBean
@RequestScoped
public class InscriptionBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    //création de l'utilisateur
    private Utilisateur utilisateur;
    
    //injection de l'EJB pour faire le lien avec la bdd
    @EJB
    private UtilisateurFacade utilisateurFacade;
    
    // Initialisation de l'entité utilisateur
    public InscriptionBean() {
            utilisateur = new Utilisateur();            
    }
    
    //méthode d'inscription appelée lors du clic sur le bouton Valider
    public String inscrire(){
        
        //enregistrement de l'utilisateur dans la base 
        utilisateurFacade.create(utilisateur);
        
        //enregistrement de l'utilisateur dans le service web 
        creationUtilisateur(utilisateur.getMotdepasse(), utilisateur.getPseudo());
       
        //message de validation 
        //FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        //FacesContext.getCurrentInstance().addMessage( null, message );
        
        //création de la session 
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("utilisateur", utilisateur);

        //redirection vers la page index en tant qu'utilisateur authetifié
        return "index";
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
     
    
}
