package br.com.setaensaios.laudoItem;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import br.com.setaensaios.buffer.ClienteDTO;
import br.com.setaensaios.buffer.FabricanteDTO;
import br.com.setaensaios.buffer.LaudoDTO;
import br.com.setaensaios.buffer.LaudoItemDTO;
import br.com.setaensaios.buffer.TensaoDTO;
import br.com.setaensaios.controller.LaudoItemController;
import br.com.setaensaios.persistencia.Fabricante;
import br.com.setaensaios.persistencia.Laudo;
import br.com.setaensaios.persistencia.LaudoItem;
import br.com.setaensaios.persistencia.Material;
import br.com.setaensaios.persistencia.Tensao;
import br.com.setaensaios.provider.EntityManager;

/**
 * Classe com as regras de negocio de laudos.
 * 
 * @author  Hélinton P. Steffens
 *
 */
public final class BOLaudoItem {

	private BOLaudoItem() {
		
	}
	
	public static boolean create(LaudoItemDTO laudoItemDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();
			
			LaudoItem laudoItem = new LaudoItem();
			laudoItem.setCorrenteFuga(laudoItemDTO.getCorrenteFuga());
			laudoItem.setDataEnsaio(converterDate(laudoItemDTO.getDataEnsaio()));
			laudoItem.setDataReteste(converterDate(laudoItemDTO.getDataReteste()));
			laudoItem.setNumeroSerieFabricante(laudoItemDTO.getNumeroSerieFabricante());
			laudoItem.setNumeroSeta(laudoItemDTO.getNumeroSeta());
			laudoItem.setOrigem(laudoItemDTO.getOrigem());
			laudoItem.setResultado(laudoItemDTO.getResultado());
			laudoItem.setUnidade(laudoItemDTO.getUnidade());
			
			Material material = new Material();
			material.setNome(laudoItemDTO.getMaterial());
			
			laudoItem.setMaterial(material);
			
			FabricanteDTO fabricanteDTO = laudoItemDTO.getFabricante();
			if (fabricanteDTO != null) {
				Fabricante fabricante = new Fabricante();
				fabricante.setCnpj(fabricanteDTO.getCnpj());
				
				laudoItem.setFabricante(fabricante);
			}
			
			LaudoDTO laudoDTO = laudoItemDTO.getLaudo();
			if (laudoDTO != null) {
				Laudo laudo = new Laudo();
				laudo.setId(laudoDTO.getId());
				
				laudoItem.setLaudo(laudo);
			}
			
			TensaoDTO tensaoDTO = laudoItemDTO.getTensao();
			if (tensaoDTO != null) {
				Tensao tensao = new Tensao();
				tensao.setId(tensaoDTO.getId());
				
				laudoItem.setTensao(tensao);
			}

			LaudoItemController controller = new LaudoItemController(factory);
			return controller.create(laudoItem);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}

	public static List<LaudoItemDTO> getLaudoItem(Integer laudoId){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			LaudoItemController controller = new LaudoItemController(factory);
			List<LaudoItem> laudoItens = controller.findLaudo(laudoId);

			if (laudoItens != null && !laudoItens.isEmpty()) {
				List<LaudoItemDTO> laudosDTO = new ArrayList<>();
				for (LaudoItem laudoItem : laudoItens) {
					LaudoItemDTO laudoItemDTO = new LaudoItemDTO();
					laudoItemDTO.setId(laudoItem.getId());
					laudoItemDTO.setMaterial(laudoItem.getMaterial().getNome());
					laudoItemDTO.setCorrenteFuga(laudoItem.getCorrenteFuga());
					laudoItemDTO.setDataEnsaio(converterDate(laudoItem.getDataEnsaio()));
					laudoItemDTO.setDataReteste(converterDate(laudoItem.getDataReteste()));
					laudoItemDTO.setNumeroSerieFabricante(laudoItem.getNumeroSerieFabricante());
					laudoItemDTO.setNumeroSeta(laudoItem.getNumeroSeta());
					laudoItemDTO.setResultado(laudoItem.getResultado());
					laudoItemDTO.setUnidade(laudoItem.getUnidade());
					laudoItemDTO.setOrigem(laudoItem.getOrigem());
					
					Fabricante fabricante = laudoItem.getFabricante();
					if (fabricante != null) {
						FabricanteDTO fabricanteDTO = new FabricanteDTO();
						fabricanteDTO.setCnpj(fabricante.getCnpj());
						fabricanteDTO.setName(fabricante.getName());
						
						laudoItemDTO.setFabricante(fabricanteDTO);
					}
					
					Laudo laudo = laudoItem.getLaudo();
					if (laudo != null) {
						LaudoDTO laudoDTO = new LaudoDTO();
						laudoDTO.setId(laudo.getId());
						laudoDTO.setDataLaudo(converterDate(laudo.getDataLaudo()));
						
						ClienteDTO clienteDTO = new ClienteDTO();
						clienteDTO.setCnpj(laudo.getCliente().getCnpj());
						laudoDTO.setCliente(clienteDTO);
						
						laudoDTO.setCliente(clienteDTO);
					}
					
					Tensao tensao = laudoItem.getTensao();
					if (tensao != null) {
						TensaoDTO tensaoDTO = new TensaoDTO();
						tensaoDTO.setId(tensao.getId());
						tensaoDTO.setCodigo(tensao.getCodigo());
						tensaoDTO.setTeste(tensao.getTeste());
						tensaoDTO.setCorrenteFuga(tensao.getCorrenteFuga());
						
						laudoItemDTO.setTensao(tensaoDTO);
					}

					laudosDTO.add(laudoItemDTO);
				}

				return laudosDTO;
			}

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
		return null;
	}

	public static boolean remove(Integer id){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			LaudoItemController controller = new LaudoItemController(factory);

			return controller.remove(id);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

	public static boolean edit(Integer id, LaudoItemDTO laudoItemDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			LaudoItemController controller = new LaudoItemController(factory);

			LaudoItem laudoItem = new LaudoItem();
			laudoItem.setId(id);
			laudoItem.setCorrenteFuga(laudoItemDTO.getCorrenteFuga());
			laudoItem.setDataEnsaio(converterDate(laudoItemDTO.getDataEnsaio()));
			laudoItem.setDataReteste(converterDate(laudoItemDTO.getDataReteste()));
			laudoItem.setNumeroSerieFabricante(laudoItemDTO.getNumeroSerieFabricante());
			laudoItem.setNumeroSeta(laudoItemDTO.getNumeroSeta());
			laudoItem.setOrigem(laudoItemDTO.getOrigem());
			laudoItem.setResultado(laudoItemDTO.getResultado());
			laudoItem.setUnidade(laudoItemDTO.getUnidade());
			
			Material material = new Material();
			material.setNome(laudoItemDTO.getMaterial());
			
			laudoItem.setMaterial(material);
			
			FabricanteDTO fabricanteDTO = laudoItemDTO.getFabricante();
			if (fabricanteDTO != null) {
				Fabricante fabricante = new Fabricante();
				fabricante.setCnpj(fabricanteDTO.getCnpj());
				
				laudoItem.setFabricante(fabricante);
			}
			
			LaudoDTO laudoDTO = laudoItemDTO.getLaudo();
			if (laudoDTO != null) {
				Laudo laudo = new Laudo();
				laudo.setId(laudoDTO.getId());
				
				laudoItem.setLaudo(laudo);
			}
			
			TensaoDTO tensaoDTO = laudoItemDTO.getTensao();
			if (tensaoDTO != null) {
				Tensao tensao = new Tensao();
				tensao.setId(tensaoDTO.getId());
				
				laudoItem.setTensao(tensao);
			}
			
			return controller.edit(laudoItem);

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}
	
	private static LocalDate converterDate(Date date){
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		return instant.atZone(defaultZoneId).toLocalDate();
	}
	
	private static Date converterDate(LocalDate date) {
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
