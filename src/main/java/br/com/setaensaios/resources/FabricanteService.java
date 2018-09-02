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

import br.com.setaensaios.buffer.FabricanteDTO;
import br.com.setaensaios.buffer.ResultDTO;
import br.com.setaensaios.fabricante.FacadeFabricante;

@Path("/fabricantes")
public class FabricanteService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<FabricanteDTO> getFabricantes(){
		return FacadeFabricante.getFabricantes();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> insert(FabricanteDTO fabricante){
		return FacadeFabricante.insert(fabricante);
	}

	@PUT
	@Path("/update/{cnpj}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> alterar(@PathParam("cnpj") String cnpj, FabricanteDTO fabricante){
		return FacadeFabricante.edit(cnpj, fabricante);
	}

	@DELETE
	@Path("/{cnpj}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> delete(@PathParam("cnpj") String cnpj){
		return FacadeFabricante.remove(cnpj);
	}
}
