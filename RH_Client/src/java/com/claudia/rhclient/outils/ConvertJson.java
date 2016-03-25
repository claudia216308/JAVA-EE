
package com.claudia.rhclient.outils;

import com.claudia.rhclient.properties.Url;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONException;


public class ConvertJson {
    
    private static WebResource web;
    private static String url;
    private static Client client;
    
    public static JSONArray convert(String chemin) throws JSONException{
          
        url = Url.REST_RESOURCE + chemin;       
        client = Client.create();
        web = client.resource(url);
        String resultat = web.accept(MediaType.APPLICATION_JSON).get(String.class);       
        System.out.print(resultat);
        
        JSONArray liste = new JSONArray(resultat);
        
        return liste;
    }
    
    
}
