
package com.claudia.rhrest.methodes;

import com.claudia.rhrest.entity.Salarie;
import com.claudia.rhrest.entity.Salarie_;
import com.claudia.rhrest.service.SalarieFacadeREST;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("rechercheSalarie")
@RequestScoped
@WebService
public class SalarieResource {

    @EJB
    private SalarieFacadeREST salarieFacadeREST;

    @Context
    private UriInfo context;

    @PersistenceContext(unitName = "RH_RESTPU")
    private EntityManager em;

    
    
    public SalarieResource() {
    }
      
    
    //ATTENTION POUR FONCTIONNER LE WEB SERVICE DOIT AVOIR AU MOINS UNE METHODE AVEC LE CHEMIN DE BASE 
    
    
    //enregistrement d'un salarié dans la bdd
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean createSalarie(Salarie s) {   
        salarieFacadeREST.create(s);
        return true;
    }
    
    //retourne le salarié en fonction de son nom 
    @GET
    @Path("{nom}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Salarie> findByNom(@PathParam("nom") String nom) {
         
             CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Salarie> cq = cb.createQuery(Salarie.class);     //type de retour    
            Root<Salarie> ut = cq.from(Salarie.class);  //table 

            cq.select(ut);
            cq.where(cb.equal(ut.get(Salarie_.nom),nom));        

            TypedQuery<Salarie> query = em.createQuery(cq);

            //récupération du ou des salariés 
            return query.getResultList();          
    }     
    
    //retourne une liste de salarié en fonction du département choisi 
    @GET
    @Path("/departement/{dep}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Salarie> findByDepartement(@PathParam("dep") int depId) {
         
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Salarie> cq = cb.createQuery(Salarie.class);     //type de retour    
            Root<Salarie> ut = cq.from(Salarie.class);  //table 

            cq.select(ut);
            cq.where(cb.equal(ut.get(Salarie_.idDepartement),1));        

            TypedQuery<Salarie> query = em.createQuery(cq);

            return query.getResultList();          
    } 
    
    
    
    
    
    
    
}
