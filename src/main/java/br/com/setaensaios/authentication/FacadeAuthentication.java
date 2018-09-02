package br.com.setaensaios.authentication;

import java.util.Arrays;

import br.com.setaensaios.buffer.AccountDTO;
import br.com.setaensaios.buffer.ResultDTO;

public final class FacadeAuthentication {

	private FacadeAuthentication() {
		
	}
	
	public static ResultDTO<AccountDTO> auth(String password) {
		ResultDTO<AccountDTO> resultDTO = new ResultDTO<>();
		
		try {
			resultDTO.setResult(Arrays.asList(BOAuthentication.auth(password)));
			resultDTO.setStatus("SUCCESS");
		} catch (RuntimeException e) {
			resultDTO.setStatus("ERROR");
			resultDTO.setMessages(Arrays.asList(e.getMessage()));
		}


		return resultDTO;
	}
}
