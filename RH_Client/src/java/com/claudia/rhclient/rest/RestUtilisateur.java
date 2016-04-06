
package com.claudia.rhclient.rest;

import com.claudia.rhclient.entity.Utilisateur;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.claudia.rhclient.properties.Url;
import java.io.StringWriter;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;


/* Gestion de l'utilisateur (création / authentification) via le service web  */ 

public class RestUtilisateur {
    
     //Authentification de l'utilisateur et récupération de son ID 
    public static int authentificationREST(){     
        Utilisateur ut = (Utilisateur) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("utilisateur");         

        //construction de l'url 
        String url = Url.REST_RESOURCE + "utilisateur/" + ut.getPseudo() + "/" + ut.getMotdepasse();      
        
        //appel du service web
        Client client = Client.create();
        WebResource web = client.resource(url); 
        String  resultat = web.accept(MediaType.TEXT_PLAIN).get(String.class);
      
        int id  = Integer.parseInt(resultat);
        
        if(id==0){
            System.out.print("L'utilisateur n'est pas inscrit dans le service web SupFitnessREST");
        }      
        return id; 
          
    }
    
    //ajout d'un utilisateur dans le service web 
    public static void creationUtilisateur(String passe, String pseudo){
        
        String url = Url.REST_RESOURCE + "utilisateur";     
        System.out.print(url);
        
        JSONObject obj = new JSONObject();    
        try {
       
            obj.put("username",pseudo);
            obj.put("motdepasse", passe);
            
            StringWriter out = new StringWriter();
            obj.write(out);
            
            String jsonText = out.toString();
            System.out.print(jsonText); 
            
            Client client = Client.create();
            WebResource web = client.resource(url); 
            
            ClientResponse response = web.type("application/json").post(ClientResponse.class, jsonText);
             
        } catch (JSONException ex) {
           
        }
    }
    
}
