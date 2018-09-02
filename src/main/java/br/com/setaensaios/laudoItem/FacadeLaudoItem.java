package br.com.setaensaios.laudoItem;

import br.com.setaensaios.buffer.LaudoDTO;
import br.com.setaensaios.buffer.LaudoItemDTO;
import br.com.setaensaios.buffer.ResultDTO;

public final class FacadeLaudoItem {

	private FacadeLaudoItem() {
		
	}
	
	public static ResultDTO<String> insert(Integer idLaudo, LaudoItemDTO laudoItem){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		LaudoDTO laudoDTO = new LaudoDTO();
		laudoDTO.setId(idLaudo);
		
		laudoItem.setLaudo(laudoDTO);
		
		boolean canEdit = BOLaudoItem.edit(laudoItem.getId(), laudoItem);
		if (canEdit || BOLaudoItem.create(laudoItem)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<LaudoItemDTO> getLaudoItem(Integer laudoId) {
		ResultDTO<LaudoItemDTO> resultDTO = new ResultDTO<>();
		resultDTO.setResult(BOLaudoItem.getLaudoItem(laudoId));

		resultDTO.setStatus("SUCCESS");

		return resultDTO;
	}

	public static ResultDTO<String> edit(Integer id, Integer idLaudo, LaudoItemDTO laudoItem){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		LaudoDTO laudoDTO = new LaudoDTO();
		laudoDTO.setId(idLaudo);
		
		laudoItem.setLaudo(laudoDTO);
		
		if (BOLaudoItem.edit(id, laudoItem)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<String> remove(Integer id){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOLaudoItem.remove(id)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}
}
