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

import br.com.setaensaios.buffer.LaudoDTO;
import br.com.setaensaios.buffer.LaudoItemDTO;
import br.com.setaensaios.buffer.ResultDTO;
import br.com.setaensaios.laudo.FacadeLaudo;
import br.com.setaensaios.laudoItem.FacadeLaudoItem;

@Path("laudos")
public class LaudoService {

	@GET	
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<LaudoDTO> getLaudos(){
		return FacadeLaudo.getLaudoItem();
	}

	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> insert(LaudoDTO laudo){
		return FacadeLaudo.insert(laudo);
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> alterar(@PathParam("id") Integer id, LaudoDTO laudo){
		return FacadeLaudo.edit(id, laudo);
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> delete(@PathParam("id") Integer id){
		return FacadeLaudo.remove(id);
	}
	
	@GET
	@Path("/{laudoId}/laudos-item")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<LaudoItemDTO> getLaudosItem(@PathParam("laudoId") Integer laudoId){
		return FacadeLaudoItem.getLaudoItem(laudoId);
	}

	@POST
	@Path("/{laudoId}/laudos-item/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> insert(@PathParam("laudoId") Integer laudoId, LaudoItemDTO laudo){
		return FacadeLaudoItem.insert(laudoId, laudo);
	}

	@PUT
	@Path("/{laudoId}/laudos-item/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> alterar(@PathParam("laudoId") Integer laudoId, @PathParam("id") Integer id, LaudoItemDTO laudo){
		return FacadeLaudoItem.edit(id, laudoId, laudo);
	}

	@DELETE
	@Path("/{laudoId}/laudos-item/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<String> delete(@PathParam("laudoId") Integer laudoId, @PathParam("id") Integer id){
		return FacadeLaudoItem.remove(id);
	}
}
