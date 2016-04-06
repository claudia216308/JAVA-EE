
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
    private int itemDepartement;
    private String dateString;

    public Salarie getSal() {
        return sal;
    }

    public void setSal(Salarie sal) {
        this.sal = sal;
    }
        
    
     public int getItemDepartement() {
        return itemDepartement;
    }

    public void setItemDepartement(int itemDepartement) {
        this.itemDepartement = itemDepartement;
    }
    
    
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
        //ne pas oublier d'initialiser l'objet Salalrié !!!!!!!!
        sal = new Salarie();
    }
 
    public String resultatRecherche(){
        
        System.out.print("item departement id " + itemDepartement);
        
        // un nom a été entré ?
        if(!nomRecherche.isEmpty()){
            resultatParNom();
        }
       
        //un département a été séléctionné ?
        else if(itemDepartement !=0){
            resultatParDepartement();
        }
        
        return "resultatSalarie";
    }
    
    //page rechercheSalarie : afficher les résultats de la recherche par nom 
    private void resultatParNom(){      
        
        try {
            
            JSONArray liste  = ConvertJson.convert("rechercheSalarie/" + nomRecherche );
            
            if(liste.isNull(0)){
                System.out.print("pas de résultat");      
            }
            
            else{
                for(int i=0; i< liste.length() ; i++){              
                    JSONObject dep  = liste.getJSONObject(i);

                    //récupération du département 
                    int id_departement  = dep.getInt("idDepartement");              
                    Departement departementSalarie  = RechercheDepartement.getDepartementById(id_departement);

                    //récupération et conversion de la date 
                   String dateStr = dep.getString("dateEmbauche");
                   System.out.print(dateStr);
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                   Date dateEmbauche = sdf.parse(dateStr);

                    //ajout du salarié à la liste 
                    listeSalarie.add(new Salarie( dep.getInt("id"), dep.getString("nom"), dep.getString("prenom"), dep.getDouble("salaireEntre"),  dateEmbauche, departementSalarie, dep.getString("poste")));               
                }//for
                
            }//else
            
         
        } catch (JSONException ex) {
            Logger.getLogger(RechercheDepartement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RechercheSalarie.class.getName()).log(Level.SEVERE, null, ex);
        }             
        
        System.out.print(listeSalarie.get(0).getId());
        System.out.print(listeSalarie.get(0).getNom());
        
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }
    
    
    
    
    
    //afficher les résultats par département 
    private void resultatParDepartement(){
        
        
        try {
            
            JSONArray liste  = ConvertJson.convert("rechercheSalarie/departement/" + itemDepartement );
            
            if(liste.isNull(0)){
                System.out.print("pas de résultat");      
            }
            
            else{
                for(int i=0; i< liste.length() ; i++){              
                    
                    JSONObject dep  = liste.getJSONObject(i);

                    //récupération du département           
                    Departement departementSalarie  = RechercheDepartement.getDepartementById(itemDepartement);

                    //récupération et conversion de la date 
                   String dateStr = dep.getString("dateEmbauche");
                   System.out.print(dateStr);
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                   Date dateEmbauche = sdf.parse(dateStr);

                    //ajout du salarié à la liste 
                    listeSalarie.add(new Salarie( dep.getInt("id"), dep.getString("nom"), dep.getString("prenom"), dep.getDouble("salaireEntre"),  dateEmbauche, departementSalarie, dep.getString("poste")));               
                }//for
                
            }//else
            
         
        } catch (JSONException ex) {
            Logger.getLogger(RechercheDepartement.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RechercheSalarie.class.getName()).log(Level.SEVERE, null, ex);
        }             
        
        System.out.print(listeSalarie.get(0).getId());
        System.out.print(listeSalarie.get(0).getNom());
        
    }
    
    
     //Créer un nouveau salarié 
    public void createSalarie(){
        
        System.out.print(sal.getNom());
        System.out.print(dateString);
        
       
        
     
        JSONObject obj = new JSONObject();    
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date d = sdf.parse(dateString);
            sal.setDate_embauche(d);
       
            obj.put("nom",sal.getNom());
            obj.put("prenom", sal.getPrenom());
            obj.put("poste", sal.getPoste());
            obj.put("salaireEntre", sal.getSalaire_entre());
            obj.put("dateEmbauche", sal.getDate_embauche());
            obj.put("idDepartement", itemDepartement);
            ConvertJson.envoieEnJSON("com.claudia.rhrest.entity.salarie", obj);                
             
        } catch (JSONException ex) {
            Logger.getLogger(InscriptionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(RechercheSalarie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
