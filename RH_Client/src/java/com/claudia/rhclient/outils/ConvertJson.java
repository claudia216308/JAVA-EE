
package com.claudia.rhclient.outils;

import com.claudia.rhclient.properties.Url;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.StringWriter;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ConvertJson {
    
    private static WebResource web;
    private static String url;
    private static Client client;
    
    public static JSONArray convert(String chemin) throws JSONException{
          
        url = Url.REST_RESOURCE + chemin;      
        System.out.print(url);
        client = Client.create();
        web = client.resource(url);
        String resultat = web.accept(MediaType.APPLICATION_JSON).get(String.class);       
        System.out.print(resultat);
        
        JSONArray liste = new JSONArray(resultat);
        
        return liste;
    }
    
    public static JSONObject convertJson(String chemin) throws JSONException{
          
        url = Url.REST_RESOURCE + chemin;      
        System.out.print(url);
        client = Client.create();
        web = client.resource(url);
        String resultat = web.accept(MediaType.APPLICATION_JSON).get(String.class);       
        System.out.print(resultat);
        
        JSONObject objet = new JSONObject(resultat);
        
        return objet;
    }
    
    
    
    public static void envoieEnJSON(String chemin, JSONObject obj) throws JSONException{
        
         url = Url.REST_RESOURCE + chemin;
         StringWriter out = new StringWriter();
         obj.write(out);
            
        String jsonText = out.toString();
            
        client = Client.create();
        web = client.resource(url); 
            
       ClientResponse response = web.type("application/json").post(ClientResponse.class, jsonText);
        
    }
    
    
}
