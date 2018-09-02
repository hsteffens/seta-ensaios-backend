package br.com.setaensaios.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.setaensaios.persistencia.Cliente;
import br.com.setaensaios.persistencia.Laudo;

public class LaudoController {
	private EntityManagerFactory emf = null;
	
    public LaudoController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Laudo laudo){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (laudo.getCliente() != null) {
            	laudo.setCliente(em.getReference(Cliente.class, laudo.getCliente().getCnpj()));
			}
            
            em.persist(laudo);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
                throw new RuntimeException("Laudo " + laudo.getId() + " already exists.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Laudo> findLaudo() {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Laudo.findAll");
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	} finally {
    		if (em.isOpen()) {
    			em.close();
			}
    	}
    }
    
    public boolean edit(Laudo laudo){
    	EntityManager em = null;
    	try {
    		em = getEntityManager();
    		em.getTransaction().begin();

    		if (laudo.getCliente() != null) {
            	laudo.setCliente(em.getReference(Cliente.class, laudo.getCliente().getCnpj()));
			}

    		em.merge(laudo);
    		em.getTransaction().commit();
    		return true;
    	} catch (Exception ex) {
    		String msg = ex.getLocalizedMessage();
    		if (msg == null || msg.length() == 0) {
    			throw new RuntimeException("The Laudo with id " + laudo.getId()+ " no longer exists.");
    		}
    		throw ex;
    	} finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    }
    
    public boolean remove(Integer id){
    	EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Laudo laudo = em.find(Laudo.class, id);
            
            em.remove(laudo);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
               throw new RuntimeException("The Laudo with id " + id + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
