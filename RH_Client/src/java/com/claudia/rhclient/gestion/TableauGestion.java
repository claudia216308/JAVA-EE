
package com.claudia.rhclient.gestion;

import com.claudia.rhclient.rest.Departement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named(value = "tableauGestion")
@ManagedBean
@RequestScoped
public class TableauGestion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private List<ligneGestion> tableau  = new ArrayList<>();
    private Gestion gestion  = new Gestion();

    public List<ligneGestion> getTableau() {
       
        return tableau;
    }
    
    
            
    public TableauGestion(){   
        List<Departement> nom  = gestion.getDepartementListe();
        List<String> salaire  = gestion.getSalaireListe();
        List<String> nombre = gestion.getNombreSalarie(); 
        
        System.out.print("Nom " + nom.size());
        System.out.print("Nombre " + nombre.size());
        System.out.print("Salaire " + salaire.size());
        
        for (int i= 0; i < nom.size() ; i++){
            
            tableau.add(new ligneGestion( nom.get(i).getNom(), nombre.get(i) , salaire.get(i)
            ));       
        }
    }
            
    
    
    //Classe interne
    public static class ligneGestion  {
        private String nomDepartement;
        private String nombreSalarie;
        private String coutSalarial;

        public String getNomDepartement() {
            return nomDepartement;
        }

        public void setNomDepartement(String nomDepartement) {
            this.nomDepartement = nomDepartement;
        }

        public String getNombreSalarie() {
            return nombreSalarie;
        }

        public void setNombreSalarie(String nombreSalarie) {
            this.nombreSalarie = nombreSalarie;
        }

        public String getCoutSalarial() {
            return coutSalarial;
        }

        public void setCoutSalarial(String coutSalarial) {
            this.coutSalarial = coutSalarial;
        }
        
        
        
        public ligneGestion(String nom, String sal, String cout){
            this.nomDepartement = nom;
            this.coutSalarial = cout;
            this.nombreSalarie = sal;
        }
    
    }//ligneGestion
    
    
    
}
