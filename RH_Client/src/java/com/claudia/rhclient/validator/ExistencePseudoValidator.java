package com.claudia.rhclient.validator;

import com.claudia.rhclient.entity.UtilisateurFacade;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;


@ManagedBean
@RequestScoped
@Named(value = "pseudoValidator")
public class ExistencePseudoValidator implements Validator{

    private static String PSEUDO_EXISTE = "Ce pseudo existe déjà";
    
    @EJB
    UtilisateurFacade utilisateurFacade;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        String pseudo  =(String) value;
        
        if(utilisateurFacade.trouverPseudo(pseudo)!= null){
            throw new ValidatorException(
                        new FacesMessage( FacesMessage.SEVERITY_ERROR, PSEUDO_EXISTE, null ) );
        }
    }
    
}
