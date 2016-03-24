
package com.claudia.rhclient.dao;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "RH_ClientPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
    //récupération d'un utilisateur par son pseudo 
    public Utilisateur trouverPseudo(String pseudo){
        
        Utilisateur utilisateur  = null ;
        try{
            CriteriaBuilder cb = em.getCriteriaBuilder();        
            CriteriaQuery<Utilisateur> cq = cb.createQuery(Utilisateur.class);     //type de retour    
            Root<Utilisateur> ut = cq.from(Utilisateur.class);  //table 

            cq.select(ut);
            cq.where(cb.equal(ut.get(Utilisateur_.pseudo),pseudo ));        

            TypedQuery<Utilisateur> query = em.createQuery(cq);

            utilisateur = (Utilisateur) query.getSingleResult();
            return utilisateur;
            
        }catch(NoResultException e){
            return null;
        }      
    }

}
