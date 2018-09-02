package br.com.setaensaios.laudo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import br.com.setaensaios.buffer.ClienteDTO;
import br.com.setaensaios.buffer.LaudoDTO;
import br.com.setaensaios.controller.LaudoController;
import br.com.setaensaios.persistencia.Cliente;
import br.com.setaensaios.persistencia.Laudo;
import br.com.setaensaios.provider.EntityManager;

public final class BOLaudo {

	private BOLaudo() {
		
	}
	
	public static boolean create(LaudoDTO laudoDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();
			
			Laudo laudo = new Laudo();
			laudo.setDataLaudo(converterDate(laudoDTO.getDataLaudo()));
			laudo.setCalibracao(laudoDTO.getCalibracao());
			laudo.setEquipamento(laudoDTO.getEquipamento());
			laudo.setTipoTeste(laudoDTO.getTipoTeste());
			
			ClienteDTO clienteDTO = laudoDTO.getCliente();
			if (clienteDTO != null) {
				Cliente cliente = new Cliente();
				cliente.setCnpj(clienteDTO.getCnpj());
				
				laudo.setCliente(cliente);
			}
			

			LaudoController controller = new LaudoController(factory);
			return controller.create(laudo);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}

	public static List<LaudoDTO> getLaudo(){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			LaudoController controller = new LaudoController(factory);
			List<Laudo> laudos = controller.findLaudo();

			if (laudos != null && !laudos.isEmpty()) {
				List<LaudoDTO> laudosDTO = new ArrayList<>();
				for (Laudo laudo : laudos) {
					LaudoDTO laudoDTO = new LaudoDTO();
					laudoDTO.setId(laudo.getId());
					laudoDTO.setDataLaudo(converterDate(laudo.getDataLaudo()));
					laudoDTO.setCalibracao(laudo.getCalibracao());
					laudoDTO.setEquipamento(laudo.getEquipamento());
					laudoDTO.setTipoTeste(laudo.getTipoTeste());
					
					Cliente cliente = laudo.getCliente();
					if (cliente != null) {
						ClienteDTO clienteDTO = new ClienteDTO();
						clienteDTO.setCnpj(cliente.getCnpj());
						clienteDTO.setName(cliente.getName());
						
						laudoDTO.setCliente(clienteDTO);
					}
					
					laudosDTO.add(laudoDTO);
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
			LaudoController controller = new LaudoController(factory);

			return controller.remove(id);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

	public static boolean edit(Integer id, LaudoDTO laudoDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			LaudoController controller = new LaudoController(factory);

			Laudo laudo = new Laudo();
			laudo.setId(id);
			laudo.setDataLaudo(converterDate(laudoDTO.getDataLaudo()));
			laudo.setCalibracao(laudoDTO.getCalibracao());
			laudo.setEquipamento(laudoDTO.getEquipamento());
			laudo.setTipoTeste(laudoDTO.getTipoTeste());
			
			ClienteDTO clienteDTO = laudoDTO.getCliente();
			if (clienteDTO != null) {
				Cliente cliente = new Cliente();
				cliente.setCnpj(clienteDTO.getCnpj());
				
				laudo.setCliente(cliente);
			}
			
			return controller.edit(laudo);

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
