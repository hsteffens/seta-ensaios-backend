package br.com.setaensaios.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.setaensaios.persistencia.Fabricante;

public class FabricanteController implements Serializable {

	private EntityManagerFactory emf = null;
	
    public FabricanteController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Fabricante fabricante){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fabricante);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
                throw new RuntimeException("Fabricante " + fabricante.getCnpj() + " already exists.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Fabricante> findFabricante() {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Fabricante.findAll");
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	} finally {
    		em.close();
    	}
    }
    
    public boolean edit(Fabricante fabricante){
    	 EntityManager em = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             em.merge(fabricante);
             em.getTransaction().commit();
             return true;
         } catch (Exception ex) {
             String msg = ex.getLocalizedMessage();
             if (msg == null || msg.length() == 0) {
                throw new RuntimeException("The Fabricante with id " + fabricante.getCnpj() + " no longer exists.");
             }
             throw ex;
         } finally {
             if (em != null) {
                 em.close();
             }
         }
    }
    
    public boolean remove(String cnpj){
    	EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Fabricante fabricante = em.find(Fabricante.class, cnpj);
            
            em.remove(fabricante);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
               throw new RuntimeException("The task with id " + cnpj + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
