
package com.claudia.rhclient.gestion;

import com.claudia.rhclient.managedbean.RechercheDepartement;
import com.claudia.rhclient.outils.ConvertJson;
import com.claudia.rhclient.rest.Departement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Gestion {
    
    //Pour chaque département
        //nombre de salariés 
        //coût salarial total 
    
     private List<Departement> departementListe = new ArrayList<>() ;
       
    //récupération  de chaque département 
    public void listeDepartements(){
        try {
            
            JSONArray liste  = ConvertJson.convert("com.claudia.rhrest.entity.departement");
            
            for(int i=0; i< liste.length() ; i++){            
                 JSONObject depJson  = liste.getJSONObject(i);
                 departementListe.add(new Departement(depJson.getString("nom"), depJson.getString("description")));

            }
         
        } catch (JSONException ex) {
            Logger.getLogger(RechercheDepartement.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    
    //requête nombre de salariés pour chaque département
    public void nombreSalarie(){
        
        for(Departement dep : departementListe){
            
        }
        
    }
}
