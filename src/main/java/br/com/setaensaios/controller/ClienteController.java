package br.com.setaensaios.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.setaensaios.persistencia.Cliente;

public class ClienteController implements Serializable {

	private EntityManagerFactory emf = null;
	
    public ClienteController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Cliente cliente){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
                throw new RuntimeException("Cliente " + cliente.getCnpj() + " already exists.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Cliente> findCliente() {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Cliente.findAll");
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	} finally {
    		em.close();
    	}
    }
    
    public boolean edit(Cliente cliente){
    	 EntityManager em = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             em.merge(cliente);
             em.getTransaction().commit();
             return true;
         } catch (Exception ex) {
             String msg = ex.getLocalizedMessage();
             if (msg == null || msg.length() == 0) {
                throw new RuntimeException("The Cliente with id " + cliente.getCnpj() + " no longer exists.");
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
            
            Cliente cliente = em.find(Cliente.class, cnpj);
            
            em.remove(cliente);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
               throw new RuntimeException("The Cliente with id " + cnpj + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
