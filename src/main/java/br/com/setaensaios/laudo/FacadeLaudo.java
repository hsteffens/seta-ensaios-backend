package br.com.setaensaios.laudo;

import br.com.setaensaios.buffer.LaudoDTO;
import br.com.setaensaios.buffer.ResultDTO;

public final class FacadeLaudo {

	private FacadeLaudo() {
		
	}
	
	public static ResultDTO<String> insert(LaudoDTO laudo){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		boolean canEdit = BOLaudo.edit(laudo.getId(), laudo);
		if (canEdit || BOLaudo.create(laudo)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<LaudoDTO> getLaudoItem() {
		ResultDTO<LaudoDTO> resultDTO = new ResultDTO<>();
		resultDTO.setResult(BOLaudo.getLaudo());

		resultDTO.setStatus("SUCCESS");

		return resultDTO;
	}

	public static ResultDTO<String> edit(Integer id, LaudoDTO laudo){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOLaudo.edit(id, laudo)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<String> remove(Integer id){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOLaudo.remove(id)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}
}
