package br.com.setaensaios.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.api.container.ContainerException;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

import br.com.setaensaios.authentication.BOAuthentication;
import br.com.setaensaios.buffer.AccountDTO;

@Provider
public class CheckRequestFilter implements ContainerRequestFilter {

	public static final String AUTHENTICATION_HEADER = "Authorization";

	@Context
	private HttpHeaders httRequest;

	@Override
	public ContainerRequest filter(ContainerRequest request) {
		if (!request.getRequestUri().getPath().contains("/auth/") && 
				!request.getMethod().equals("GET") &&
				!request.getRequestUri().getPath().contains("/laudos")) {
			String authCredentials = request.getHeaderValue(AUTHENTICATION_HEADER);

			try {
				AccountDTO auth = BOAuthentication.auth(authCredentials);
				if (auth == null) {
					throw new ContainerException("Not Authorized");
				}
			} catch (RuntimeException e) {
				throw new ContainerException("Not Authorized");
			}

			httRequest.getRequestHeaders().add("admin", Boolean.TRUE.toString());

		}
		return request;
	}
}
