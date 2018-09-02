package br.com.setaensaios.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.setaensaios.persistencia.Fabricante;
import br.com.setaensaios.persistencia.Material;
import br.com.setaensaios.persistencia.Tensao;

public class MaterialController {

	private EntityManagerFactory emf = null;
	
    public MaterialController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(Material material){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (material.getFabricante() != null) {
            	material.setFabricante(em.getReference(Fabricante.class, material.getFabricante().getCnpj()));
			}
            if (material.getTensao() != null) {
            	material.setTensao(em.getReference(Tensao.class, material.getTensao().getId()));
			}
            
            em.persist(material);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
                throw new RuntimeException("Material " + material.getNome() + " already exists.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Material> findMaterial() {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Material.findAll");
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	} finally {
    		em.close();
    	}
    }
    
    public boolean edit(Material material){
    	 EntityManager em = null;
         try {
             em = getEntityManager();
             em.getTransaction().begin();
             
             if (material.getFabricante() != null) {
             	material.setFabricante(em.getReference(Fabricante.class, material.getFabricante().getCnpj()));
 			}
             if (material.getTensao() != null) {
             	material.setTensao(em.getReference(Tensao.class, material.getTensao().getId()));
 			}
             
             em.merge(material);
             em.getTransaction().commit();
             return true;
         } catch (Exception ex) {
             String msg = ex.getLocalizedMessage();
             if (msg == null || msg.length() == 0) {
                throw new RuntimeException("The Material with id " + material.getNome()+ " no longer exists.");
             }
             throw ex;
         } finally {
             if (em != null) {
                 em.close();
             }
         }
    }
    
    public boolean remove(String nome){
    	EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Material material = em.find(Material.class, nome);
            
            em.remove(material);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
               throw new RuntimeException("The Material with id " + nome + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}