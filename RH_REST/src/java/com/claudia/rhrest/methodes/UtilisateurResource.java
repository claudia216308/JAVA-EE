package com.claudia.rhrest.methodes;

import com.claudia.rhrest.entity.Salarie;
import com.claudia.rhrest.entity.Salarie_;
import com.claudia.rhrest.entity.Utilisateur;
import com.claudia.rhrest.entity.Utilisateur_;
import com.claudia.rhrest.service.UtilisateurFacadeREST;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("utilisateur")
@RequestScoped
@WebService
public class UtilisateurResource {

    @Context
    private UriInfo context;

    @PersistenceContext(unitName = "RH_RESTPU")
    private EntityManager em;
     
    @EJB
    private UtilisateurFacadeREST utFacadeRest;
   
     
    public UtilisateurResource() {
    }
    
    //enregistrement d'un utilisateur dans la bdd
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createUtilisateur(Utilisateur ut) {   
        utFacadeRest.create(ut);
        return true;
    }
    
    
    //cette méthode permet de récupérer un utilisateur en fonction de son pseudo
    //si son mot de passe est correcte alors on renvoie l'id de l'utilisateur 
    //cet id permettra d'appeler les autres méthodes du service REST
    @GET
    @Path("{pseudo}/{passe}")
    @Produces(MediaType.TEXT_PLAIN)
    public int auth(@PathParam("pseudo") String pseudo, @PathParam ("passe") String passe) {
             
        Utilisateur utilisateur  = null ;
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);     //type de retour    
            Root<Utilisateur> ut = cq.from(Utilisateur.class);  //table 

            cq.select(ut);
            cq.where(cb.equal(ut.get(Utilisateur_.pseudo),pseudo ));        

            TypedQuery<Utilisateur> query = em.createQuery(cq);

            //récupération de l'utilisateur 
            utilisateur = (Utilisateur) query.getSingleResult();
           
            //vérification de l'égalité des mots de passes
           if(utilisateur.getMotdepasse().equals(passe)){
               //si les mots de passe sont égaux renvoyer l'id de l'utilisateur
               return (int) utilisateur.getId();
           }
           else{
               System.out.print("Les mots de passe ne correspondent pas");
               //ajouter message d'erreur FacesMessage
               return 0;
           }
           
        }catch(NoResultException e){  
            System.out.print("Cet utilisateur n'existe pas ");
            //ajouter un FacesMessage
            return 0;          
        }
        
    }



   
      
}

    
