package br.com.setaensaios.cliente;

import br.com.setaensaios.buffer.ClienteDTO;
import br.com.setaensaios.buffer.ResultDTO;

public final class FacadeCliente {

	private FacadeCliente() {
		
	}
	
	public static ResultDTO<String> insert(ClienteDTO cliente){
		ResultDTO<String> resultDTO = new ResultDTO<>();
		
		boolean canEdit = BOCliente.edit(cliente.getCnpj(), cliente);
		if (canEdit || BOCliente.create(cliente)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<ClienteDTO> getClientes(){
		ResultDTO<ClienteDTO> resultDTO = new ResultDTO<>();
		resultDTO.setResult(BOCliente.getCliente());

		resultDTO.setStatus("SUCCESS");

		return resultDTO;
	}

	public static ResultDTO<String> edit(String cnpj, ClienteDTO clienteDTO){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOCliente.edit(cnpj, clienteDTO)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<String> remove(String cnpj){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOCliente.remove(cnpj)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}
}
