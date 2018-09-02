package br.com.setaensaios.authentication;

import javax.persistence.EntityManagerFactory;

import br.com.setaensaios.buffer.AccountDTO;
import br.com.setaensaios.controller.AccountController;
import br.com.setaensaios.persistencia.Account;
import br.com.setaensaios.provider.EntityManager;

public final class BOAuthentication {

	private BOAuthentication() {
		
	}
	
	public static AccountDTO auth(String password) {
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();

			AccountController controller = new AccountController(factory);
			Account usuario = controller.findUsuario(password);
			
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setUsername(usuario.getUsername());
			accountDTO.setPassword(usuario.getPassword());
			
			return accountDTO;
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}
}
