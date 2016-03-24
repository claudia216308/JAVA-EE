
package com.claudia.rhclient.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator( value = "motDePasseValidator" )
public class MotDePasseValidator implements Validator {

    private static String PASSE_DIFFERENT = "Le mot de passe et la confirmation doivent être identiques";
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
    
         UIInput composantMotDePasse = (UIInput) component.getAttributes().get( "composantMotDePasse" );
   
        String motDePasse = (String) composantMotDePasse.getValue();
        /* Récupération de la valeur du champ confirmation */
        String confirmation = (String) value;

        if ( confirmation != null && !confirmation.equals( motDePasse ) ) {
            throw new ValidatorException(
                    new FacesMessage( FacesMessage.SEVERITY_ERROR,PASSE_DIFFERENT , null ) );
    
         }//if
    }
    
}
