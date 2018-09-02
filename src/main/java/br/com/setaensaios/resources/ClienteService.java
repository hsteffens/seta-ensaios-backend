package br.com.setaensaios.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.setaensaios.buffer.ClienteDTO;
import br.com.setaensaios.buffer.ResultDTO;
import br.com.setaensaios.cliente.FacadeCliente;

@Path("clientes")
public class ClienteService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<ClienteDTO> getClientes(){
		return FacadeCliente.getClientes();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> insert(ClienteDTO clienteDTO){
		return FacadeCliente.insert(clienteDTO);
	}

	@PUT
	@Path("/update/{cnpj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> alterar(@PathParam("cnpj") String cnpj, ClienteDTO clienteDTO){
		return FacadeCliente.edit(cnpj, clienteDTO);
	}

	@DELETE
	@Path("/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> delete(@PathParam("cnpj") String cnpj){
		return FacadeCliente.remove(cnpj);
	}
}
