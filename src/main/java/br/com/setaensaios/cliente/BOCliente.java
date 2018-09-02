package br.com.setaensaios.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import br.com.setaensaios.buffer.ClienteDTO;
import br.com.setaensaios.controller.ClienteController;
import br.com.setaensaios.persistencia.Cliente;
import br.com.setaensaios.provider.EntityManager;

public final class BOCliente {

	private BOCliente() {
		
	}
	
	public static boolean create(ClienteDTO clienteDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();
			
			Cliente cliente = new Cliente();
			cliente.setCnpj(clienteDTO.getCnpj());
			cliente.setCep(clienteDTO.getCep());
			cliente.setEmail(clienteDTO.getEmail());
			cliente.setEndereco(clienteDTO.getEndereco());
			cliente.setEstado(clienteDTO.getEstado());
			cliente.setFone(clienteDTO.getFone());
			cliente.setFone2(clienteDTO.getFone2());
			cliente.setMunicipio(clienteDTO.getMunicipio());
			cliente.setName(clienteDTO.getName());
			cliente.setSolicitante(clienteDTO.getSolicitante());
			cliente.setIe(clienteDTO.getIe());
			
			ClienteController controller = new ClienteController(factory);
			return controller.create(cliente);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}

	public static List<ClienteDTO> getCliente(){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			ClienteController controller = new ClienteController(factory);
			List<Cliente> clientes = controller.findCliente();

			if (clientes != null && !clientes.isEmpty()) {
				List<ClienteDTO> clientesDTO = new ArrayList<>();
				for (Cliente cliente : clientes) {
					ClienteDTO clienteDTO = new ClienteDTO();
					clienteDTO.setCnpj(cliente.getCnpj());
					clienteDTO.setCep(cliente.getCep());
					clienteDTO.setEmail(cliente.getEmail());
					clienteDTO.setEndereco(cliente.getEndereco());
					clienteDTO.setEstado(cliente.getEstado());
					clienteDTO.setFone(cliente.getFone());
					clienteDTO.setFone2(cliente.getFone2());
					clienteDTO.setMunicipio(cliente.getMunicipio());
					clienteDTO.setName(cliente.getName());
					clienteDTO.setSolicitante(cliente.getSolicitante());
					clienteDTO.setIe(cliente.getIe());
					
					clientesDTO.add(clienteDTO);
				}

				return clientesDTO;
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
			ClienteController controller = new ClienteController(factory);

			return controller.remove(cnpj);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

	public static boolean edit(String cnpj, ClienteDTO clienteDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			ClienteController controller = new ClienteController(factory);

			Cliente cliente = new Cliente();
			cliente.setCep(clienteDTO.getCep());
			cliente.setCnpj(cnpj);
			cliente.setEmail(clienteDTO.getEmail());
			cliente.setEndereco(clienteDTO.getEndereco());
			cliente.setEstado(clienteDTO.getEstado());
			cliente.setFone(clienteDTO.getFone());
			cliente.setFone2(clienteDTO.getFone2());
			cliente.setMunicipio(clienteDTO.getMunicipio());
			cliente.setName(clienteDTO.getName());
			cliente.setSolicitante(clienteDTO.getSolicitante());
			cliente.setIe(clienteDTO.getIe());
			
			return controller.edit(cliente);

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}
}
