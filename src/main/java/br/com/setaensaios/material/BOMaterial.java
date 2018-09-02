package br.com.setaensaios.material;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import br.com.setaensaios.buffer.FabricanteDTO;
import br.com.setaensaios.buffer.MaterialDTO;
import br.com.setaensaios.buffer.TensaoDTO;
import br.com.setaensaios.controller.MaterialController;
import br.com.setaensaios.persistencia.Fabricante;
import br.com.setaensaios.persistencia.Material;
import br.com.setaensaios.persistencia.Tensao;
import br.com.setaensaios.provider.EntityManager;

/**
 * Classe com as regras de negocio de classe de material.
 * 
 * @author  Hélinton P. Steffens
 *
 */
public final class BOMaterial {

	private BOMaterial(){
		
	}
	
	public static boolean create(MaterialDTO materialDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();

			Material material = new Material();
			material.setNome(materialDTO.getNome());
			material.setCautela(materialDTO.getCautela());
			material.setCorrenteFuga(materialDTO.getCorrenteFuga());
			material.setDiasReteste(materialDTO.getDiasReteste());
			material.setNumeroSerieFabricante(materialDTO.getNumeroSerieFabricante());
			material.setNumeroSeta(materialDTO.getNumeroSeta());
			material.setSequencial(materialDTO.getSequencial());
			material.setUnidade(materialDTO.getUnidade());
			
			FabricanteDTO fabricanteDTO = materialDTO.getFabricante();
			if (fabricanteDTO != null) {
				Fabricante fabricante = new Fabricante();
				fabricante.setCnpj(fabricanteDTO.getCnpj());
				
				material.setFabricante(fabricante);
			}
			
			TensaoDTO tensaoDTO = materialDTO.getTensao();
			if (tensaoDTO != null) {
				Tensao tensao = new Tensao();
				tensao.setId(tensaoDTO.getId());
				
				material.setTensao(tensao);
			}

			MaterialController controller = new MaterialController(factory);
			return controller.create(material);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}

	public static List<MaterialDTO> getMaterial(){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			MaterialController controller = new MaterialController(factory);
			List<Material> materiais = controller.findMaterial();

			if (materiais != null && !materiais.isEmpty()) {
				List<MaterialDTO> materiaisDTO = new ArrayList<>();
				for (Material material : materiais) {
					MaterialDTO materialDTO = new MaterialDTO();
					materialDTO.setNome(material.getNome());
					materialDTO.setCautela(material.getCautela());
					materialDTO.setCorrenteFuga(material.getCorrenteFuga());
					materialDTO.setDiasReteste(material.getDiasReteste());
					materialDTO.setNumeroSerieFabricante(material.getNumeroSerieFabricante());
					materialDTO.setNumeroSeta(material.getNumeroSeta());
					materialDTO.setSequencial(material.getSequencial());
					materialDTO.setUnidade(material.getUnidade());
					
					Fabricante fabricante = material.getFabricante();
					if (fabricante != null) {
						FabricanteDTO fabricanteDTO = new FabricanteDTO();
						fabricanteDTO.setCnpj(fabricante.getCnpj());
						fabricanteDTO.setName(fabricante.getName());
						
						materialDTO.setFabricante(fabricanteDTO);
					}
					
					Tensao tensao = material.getTensao();
					if (tensao != null) {
						TensaoDTO tensaoDTO = new TensaoDTO();
						tensaoDTO.setId(tensao.getId());
						tensaoDTO.setCodigo(tensao.getCodigo());
						tensaoDTO.setTeste(tensao.getTeste());
						tensaoDTO.setCorrenteFuga(tensao.getCorrenteFuga());
						
						materialDTO.setTensao(tensaoDTO);
					}

					materiaisDTO.add(materialDTO);
				}

				return materiaisDTO;
			}

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
		return null;
	}

	public static boolean remove(String nome){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			MaterialController controller = new MaterialController(factory);

			return controller.remove(nome);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

	public static boolean edit(String nome, MaterialDTO materialDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			MaterialController controller = new MaterialController(factory);

			Material material = new Material();
			material.setNome(nome);
			material.setCautela(materialDTO.getCautela());
			material.setCorrenteFuga(materialDTO.getCorrenteFuga());
			material.setDiasReteste(materialDTO.getDiasReteste());
			material.setNumeroSerieFabricante(materialDTO.getNumeroSerieFabricante());
			material.setNumeroSeta(materialDTO.getNumeroSeta());
			material.setSequencial(materialDTO.getSequencial());
			material.setUnidade(materialDTO.getUnidade());
			
			FabricanteDTO fabricanteDTO = materialDTO.getFabricante();
			if (fabricanteDTO != null) {
				Fabricante fabricante = new Fabricante();
				fabricante.setCnpj(fabricanteDTO.getCnpj());
				
				material.setFabricante(fabricante);
			}
			
			TensaoDTO tensaoDTO = materialDTO.getTensao();
			if (tensaoDTO != null) {
				Tensao tensao = new Tensao();
				tensao.setId(tensaoDTO.getId());
				
				material.setTensao(tensao);
			}
			
			return controller.edit(material);

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}
}
