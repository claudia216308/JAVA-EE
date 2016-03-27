
package com.claudia.rhclient.managedbean;

import com.claudia.rhclient.outils.ConvertJson;
import com.claudia.rhclient.rest.Departement;
import com.claudia.rhclient.rest.Salarie;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Named(value = "rechercheSalarie")
@ManagedBean
@RequestScoped
public class RechercheSalarie {

    private Salarie sal;
    private String nomRecherche;
    private List<Salarie> listeSalarie = new ArrayList<>();

    
    public List<Salarie> getListeSalarie() {
        return listeSalarie;
    }

    public void setListeSalarie(List<Salarie> listeSalarie) {
        this.listeSalarie = listeSalarie;
    }
  
   
    public String getNomRecherche() {
        
        return nomRecherche;
    }

    public void setNomRecherche(String nomRecherche) {
        System.out.print(nomRecherche);
        this.nomRecherche = nomRecherche;
    }
    
    
    public RechercheSalarie(){      
    }
 
    
    //page rechercheSalarie : afficher les résultats de la recherche par nom 
    public String resultatParNom(){
        
        try {
            
            JSONArray liste  = ConvertJson.convert("rechercheSalarie/brouche" );
            
            for(int i=0; i< liste.length() ; i++){              
                 JSONObject dep  = liste.getJSONObject(i);
      
                 //récupération du département 
                 int id_departement  = dep.getInt("idDepartement");              
                 Departement departementSalarie  = RechercheDepartement.getDepartementById(1);
                 
                 //récupération et conversion de la date 
                String dateStr = dep.getString("dateEmbauche");
                System.out.print(dateStr);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                Date dateEmbauche = sdf.parse(dateStr);
                
                 //ajout du salarié à la liste 
                 listeSalarie.add(new Salarie( dep.getInt("id"), dep.getString("nom"), dep.getString("prenom"), dep.getDouble("salaireEntre"),  dateEmbauche, departementSalarie, dep.getString("poste")));
                 
            }
         
        } catch (JSONException ex) {
            Logger.getLogger(RechercheDepartement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RechercheSalarie.class.getName()).log(Level.SEVERE, null, ex);
        }             
        
        System.out.print(listeSalarie.get(0).getId());
        System.out.print(listeSalarie.get(0).getNom());
        return "resultatSalarie";
    }
    
}
