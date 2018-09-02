package br.com.setaensaios.tensao;

import br.com.setaensaios.buffer.ResultDTO;
import br.com.setaensaios.buffer.TensaoDTO;

/**
 * Classe com as facilitadores para manipulação de classes de tensão.
 * 
 * @author Hélinton P. Steffens
 *
 */
public final class FacadeTensao {

	private FacadeTensao() {
		
	}
	
	public static ResultDTO<String> insert(TensaoDTO tensao){
		ResultDTO<String> resultDTO = new ResultDTO<>();
		
		if (tensao != null && tensao.getId() != null) {
			return edit(tensao.getId(), tensao);
		}
		
		if (BOTensao.create(tensao)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<TensaoDTO> getClassesTensao(){
		ResultDTO<TensaoDTO> resultDTO = new ResultDTO<>();
		resultDTO.setResult(BOTensao.getClassesTensao());

		resultDTO.setStatus("SUCCESS");

		return resultDTO;
	}

	public static ResultDTO<String> edit(Integer id, TensaoDTO tensao){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOTensao.edit(id, tensao)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<String> remove(Integer id){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOTensao.remove(id)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}
}
