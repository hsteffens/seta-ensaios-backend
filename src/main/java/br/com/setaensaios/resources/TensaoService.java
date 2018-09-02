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

import br.com.setaensaios.buffer.ResultDTO;
import br.com.setaensaios.buffer.TensaoDTO;
import br.com.setaensaios.tensao.FacadeTensao;

@Path("tensoes")
public class TensaoService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<TensaoDTO> getClassesTensao(){
		return FacadeTensao.getClassesTensao();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> insertTask(TensaoDTO tensaoDTO){
		return FacadeTensao.insert(tensaoDTO);
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> alterarTask(@PathParam("id") Integer id, TensaoDTO tensao){
		return FacadeTensao.edit(id, tensao);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> deleteTask(@PathParam("id") Integer id){
		return FacadeTensao.remove(id);
	}
}
