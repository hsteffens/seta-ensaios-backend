package br.com.setaensaios.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.setaensaios.persistencia.Fabricante;
import br.com.setaensaios.persistencia.Laudo;
import br.com.setaensaios.persistencia.LaudoItem;
import br.com.setaensaios.persistencia.Material;
import br.com.setaensaios.persistencia.Tensao;

public class LaudoItemController {
	
	private EntityManagerFactory emf = null;
	
    public LaudoItemController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public boolean create(LaudoItem laudoItem){
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            if (laudoItem.getMaterial() != null) {
            	laudoItem.setMaterial(em.getReference(Material.class, laudoItem.getMaterial().getNome()));
			}
            
            if (laudoItem.getLaudo() != null) {
            	laudoItem.setLaudo(em.getReference(Laudo.class, laudoItem.getLaudo().getId()));
			}
            
            if (laudoItem.getFabricante() != null) {
            	laudoItem.setFabricante(em.getReference(Fabricante.class, laudoItem.getFabricante().getCnpj()));
			}
            
            if (laudoItem.getTensao() != null) {
            	laudoItem.setTensao(em.getReference(Tensao.class, laudoItem.getTensao().getId()));
			}
            
            em.persist(laudoItem);
            em.getTransaction().commit();
            
            return true;
        } catch (Exception ex) {
                throw new RuntimeException("Item laudo " + laudoItem.getId() + " already exists.", ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<LaudoItem> findLaudo(Integer laudoId) {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("LaudoItem.findByLaudoId");
    		query.setParameter("laudoId", laudoId);
    		return query.getResultList();
    	}catch(NoResultException e){
    		return null;
    	} finally {
    		if (em.isOpen()) {
    			em.close();
			}
    	}
    }
    
    public boolean edit(LaudoItem laudoItem){
    	EntityManager em = null;
    	try {
    		em = getEntityManager();
    		em.getTransaction().begin();

    		if (laudoItem.getMaterial() != null) {
    			laudoItem.setMaterial(em.getReference(Material.class, laudoItem.getMaterial().getNome()));
    		}

    		if (laudoItem.getLaudo() != null) {
    			laudoItem.setLaudo(em.getReference(Laudo.class, laudoItem.getLaudo().getId()));
    		}
    		
            if (laudoItem.getFabricante() != null) {
            	laudoItem.setFabricante(em.getReference(Fabricante.class, laudoItem.getFabricante().getCnpj()));
			}
    		
    		if (laudoItem.getTensao() != null) {
    			laudoItem.setTensao(em.getReference(Tensao.class, laudoItem.getTensao().getId()));
    		}

    		em.merge(laudoItem);
    		em.getTransaction().commit();
    		return true;
    	} catch (Exception ex) {
    		String msg = ex.getLocalizedMessage();
    		if (msg == null || msg.length() == 0) {
    			throw new RuntimeException("The Item Laudo with id " + laudoItem.getId()+ " no longer exists.");
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
            
            LaudoItem laudoItem = em.find(LaudoItem.class, id);
            
            em.remove(laudoItem);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
               throw new RuntimeException("The Item Laudo with id " + id + " no longer exists.");
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
