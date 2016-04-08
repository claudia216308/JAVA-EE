
package com.claudia.rhclient.gestion;

import com.claudia.rhclient.managedbean.RechercheDepartement;
import com.claudia.rhclient.outils.ConvertJson;
import com.claudia.rhclient.properties.Url;
import com.claudia.rhclient.rest.Departement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Gestion {
    
        
    private List<Departement> departementListe  ;
    private List<String> salaireListe = new ArrayList<>();
    private List<String> nombreSalarie = new ArrayList<>();
    private static String nombreTotalSalarie;
    
        //Pour chaque département
    //nombre de salariés
    //coût salarial total
    
    public Gestion(){
        //initialisation de la liste des départements 
        getDepartementListe();
    }
    
    //Cout salarial de chaque département
    public List<String> getSalaireListe() {
        Client client = Client.create();
       
        for(Departement dep : departementListe){
            String url = Url.REST_RESOURCE + "gestionResource/salaire/" + dep.getId();      
            WebResource web = client.resource(url);
            String resultat = web.accept(MediaType.TEXT_PLAIN).get(String.class); 
            salaireListe.add(resultat);         
        } 
        return salaireListe;
    }
    

    //nombre de salariés pour chaque département
    public List<String> getNombreSalarie() {
        Client client = Client.create();
        
        for(Departement dep : departementListe){
            String url = Url.REST_RESOURCE + "gestionResource/salarie/" + dep.getId();      
            WebResource web = client.resource(url);
            String resultat = web.accept(MediaType.TEXT_PLAIN).get(String.class); 
            nombreSalarie.add(resultat);         
        } 
        return nombreSalarie;
    }
    

    //récupération de tous les départements
    public List<Departement> getDepartementListe() {
        
        departementListe = new ArrayList<>(); //il faut réinitialiser la liste à chaque appel de la méthode, sinon on obtient des doublons 
        
        try {
            
            JSONArray liste  = ConvertJson.convert("com.claudia.rhrest.entity.departement");
            
            for(int i=0; i< liste.length() ; i++){
                JSONObject depJson  = liste.getJSONObject(i);
                departementListe.add(new Departement(depJson.getString("nom"), depJson.getInt("id")));
                
            }
         
        } catch (JSONException ex) {
            Logger.getLogger(RechercheDepartement.class.getName()).log(Level.SEVERE, null, ex);
        }            
        return departementListe;
    }

    
    //Nombre total de salariés dans l'entreprise 
    public static String getNombreTotalSalarie() {
        String url = Url.REST_RESOURCE + "salarie/count"; 
        Client client = Client.create();
        WebResource web = client.resource(url);
        nombreTotalSalarie = web.accept(MediaType.TEXT_PLAIN).get(String.class); 
        
        return nombreTotalSalarie;
    }
    
 
    

}
