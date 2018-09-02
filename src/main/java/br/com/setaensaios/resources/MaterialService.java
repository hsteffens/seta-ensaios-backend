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

import br.com.setaensaios.buffer.MaterialDTO;
import br.com.setaensaios.buffer.ResultDTO;
import br.com.setaensaios.material.FacadeMaterial;

@Path("materiais")
public class MaterialService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<MaterialDTO> getMateriais(){
		return FacadeMaterial.getMaterial();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> insert(MaterialDTO material){
		return FacadeMaterial.insert(material);
	}

	@PUT
	@Path("/update/{nome}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> alterar(@PathParam("nome") String nome, MaterialDTO material){
		return FacadeMaterial.edit(nome, material);
	}

	@DELETE
	@Path("/{nome}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> delete(@PathParam("nome") String nome){
		return FacadeMaterial.remove(nome);
	}
}
