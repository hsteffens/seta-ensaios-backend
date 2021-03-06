package br.com.setaensaios.provider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * F�brica de acesso a dados.
 * 
 * @author H�linton P. Steffens
 *
 */
public final class EntityManager {

	private static EntityManagerFactory factory;
	
	private EntityManager(){
		
	}
	
	public static void createEntityManagerFactory(){
		if (factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("SetaReportsPU");
		}
		
	}
	
	public static void destroyEntityManagerFactory(){
//		if (factory != null && factory.isOpen()) {
//			factory.close();
//		}
	}

	public static EntityManagerFactory getFactory() {
		return factory;
	}
	
}
