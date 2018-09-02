package br.com.setaensaios.fabricante;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import br.com.setaensaios.buffer.FabricanteDTO;
import br.com.setaensaios.controller.FabricanteController;
import br.com.setaensaios.persistencia.Fabricante;
import br.com.setaensaios.provider.EntityManager;

/**
 * Classe com as regras de negocio de fabricantes.
 * 
 * @author  Hélinton P. Steffens
 *
 */
public final class BOFabricante {

	private BOFabricante(){
		
	}
	
	public static boolean create(FabricanteDTO fabricanteDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();

			Fabricante fabricante = new Fabricante();
			fabricante.setCnpj(fabricanteDTO.getCnpj());
			fabricante.setName(fabricanteDTO.getName());

			FabricanteController controller = new FabricanteController(factory);
			return controller.create(fabricante);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}

	public static List<FabricanteDTO> getFabricantes(){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			FabricanteController controller = new FabricanteController(factory);
			List<Fabricante> fabricantes = controller.findFabricante();

			if (fabricantes != null && !fabricantes.isEmpty()) {
				List<FabricanteDTO> fabricantesDTO = new ArrayList<>();
				for (Fabricante fabricante : fabricantes) {
					FabricanteDTO fabricanteDTO = new FabricanteDTO();
					fabricanteDTO.setCnpj(fabricante.getCnpj());
					fabricanteDTO.setName(fabricante.getName());

					fabricantesDTO.add(fabricanteDTO);
				}

				return fabricantesDTO;
			}

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
		return null;
	}

	public static boolean remove(String cnpj){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			FabricanteController controller = new FabricanteController(factory);

			return controller.remove(cnpj);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

	public static boolean edit(String cnpj, FabricanteDTO fabricanteDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			FabricanteController controller = new FabricanteController(factory);

			Fabricante fabricante = new Fabricante();
			fabricante.setCnpj(fabricanteDTO.getCnpj());
			fabricante.setName(fabricanteDTO.getName());

			return controller.edit(fabricante);

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

}
