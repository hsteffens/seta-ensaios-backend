package br.com.setaensaios.controller;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.setaensaios.persistencia.Tensao;

public class TensaoController implements Serializable {

	private EntityManagerFactory emf = null;
	
    public TensaoController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Tensao tensao){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tensao);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
                throw new RuntimeException("Classe de Tensão " + tensao.getId() + " already exists.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Tensao> findClasseTensao() {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Tensao.findAll");
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	} finally {
    		if (em.isOpen()) {
    			em.close();
			}
    	}
    }
    
    public boolean edit(Tensao tensao){
    	 EntityManager em = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             em.merge(tensao);
             em.getTransaction().commit();
             return true;
         } catch (Exception ex) {
             String msg = ex.getLocalizedMessage();
             if (msg == null || msg.length() == 0) {
                throw new RuntimeException("The Classe de tensão with id " + tensao.getId() + " no longer exists.");
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
            
            Tensao tensao = em.find(Tensao.class, id);
            
            em.remove(tensao);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
               throw new RuntimeException("The task with id " + id + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
