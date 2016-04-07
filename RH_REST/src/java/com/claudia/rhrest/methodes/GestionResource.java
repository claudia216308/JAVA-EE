
package com.claudia.rhrest.methodes;

import com.claudia.rhrest.entity.Salarie;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    @GET
    @Path("salarie/count")
    @Produces(MediaType.TEXT_PLAIN)
    public Long count(){ 
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);     
        Root<Salarie> track = cq.from(Salarie.class);  

        //séléction des courses en fontion de l'id_utilisateur
        cq.select(cb.count(track));
        return  em.createQuery(cq).getSingleResult();
               
    }
    
    
}
