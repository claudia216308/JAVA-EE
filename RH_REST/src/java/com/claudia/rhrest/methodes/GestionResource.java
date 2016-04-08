
package com.claudia.rhrest.methodes;

import com.claudia.rhrest.entity.Salarie;
import com.claudia.rhrest.entity.Salarie_;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;


@Path("gestionResource")
@RequestScoped
public class GestionResource {

    @Context
    private UriInfo context;

     @PersistenceContext(unitName = "RH_RESTPU")
    private EntityManager em;

    public GestionResource() {
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSON() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    
    //Renvoie le nombre de salariés dans l'entreprise
    @GET
    @Path("salarie/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Long count(){ 
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);     
        Root<Salarie> salarie = cq.from(Salarie.class);  

        cq.select(cb.count(salarie));
        return  em.createQuery(cq).getSingleResult();            
    }
    
    //Nombre de salariés par département 
    @GET
    @Path("salarie/{dep}")
    @Produces(MediaType.TEXT_PLAIN)
    public Long countByDepartment(@PathParam("dep") int depId){ 
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);     
        Root<Salarie> salarie = cq.from(Salarie.class);  

        cq.select(cb.count(salarie));
        cq.where(cb.equal(salarie.get(Salarie_.idDepartement),depId));
        
        return  em.createQuery(cq).getSingleResult();            
    }
    
    //Somme de tous les salaires par département 
    @GET
    @Path("salaire/{dep}")
    @Produces(MediaType.TEXT_PLAIN)
    public Double coutSalarialParDepartment(@PathParam("dep") int depId){ 
               
        double rien = 0.5;
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Double> cq = cb.createQuery(Double.class);     
            Root<Salarie> salarie = cq.from(Salarie.class);  

            cq.select(cb.sum(salarie.get(Salarie_.salaireEntre)).as(Double.class)); 
            cq.where(cb.equal(salarie.get(Salarie_.idDepartement),depId));

           return  em.createQuery(cq).getSingleResult();      
        } 
        catch(NoResultException e){
            System.out.print("Pas de résultat");
           return 1.; 
        }
        
        
    }
    
    
}
