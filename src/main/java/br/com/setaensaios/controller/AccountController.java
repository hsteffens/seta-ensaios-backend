package br.com.setaensaios.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.setaensaios.persistencia.Account;
import br.com.setaensaios.persistencia.Fabricante;

public class AccountController {

	private EntityManagerFactory emf = null;
	
    public AccountController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public Account findUsuario(String password) {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Account.findAccount")
    				.setParameter("password", password);
    		return (Account) query.getSingleResult();
    	}catch(NoResultException e){
    		throw new RuntimeException("Senha inválida!");
    	} finally {
    		em.close();
    	}
}
}
