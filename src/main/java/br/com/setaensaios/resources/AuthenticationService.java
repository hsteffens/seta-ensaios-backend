package br.com.setaensaios.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.setaensaios.authentication.FacadeAuthentication;
import br.com.setaensaios.buffer.AccountDTO;
import br.com.setaensaios.buffer.ResultDTO;

@Path("auth")
public class AuthenticationService {

	@POST
	@Path("/passoword/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public ResultDTO<AccountDTO> logon(@PathParam("password") String password){
		return FacadeAuthentication.auth(password);
}
}
