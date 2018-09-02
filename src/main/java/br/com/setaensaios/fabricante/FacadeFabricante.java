package br.com.setaensaios.fabricante;

import br.com.setaensaios.buffer.FabricanteDTO;
import br.com.setaensaios.buffer.ResultDTO;

/**
 * Classe com as facilitadores para manipulação de fabricantes.
 * 
 * @author Hélinton P. Steffens
 *
 */
public final class FacadeFabricante {

	private FacadeFabricante(){
		
	}
	
	public static ResultDTO<String> insert(FabricanteDTO fabricante){
		ResultDTO<String> resultDTO = new ResultDTO<>();
		
		boolean canEdit = BOFabricante.edit(fabricante.getCnpj(), fabricante);
		if (canEdit || BOFabricante.create(fabricante)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<FabricanteDTO> getFabricantes(){
		ResultDTO<FabricanteDTO> resultDTO = new ResultDTO<>();
		resultDTO.setResult(BOFabricante.getFabricantes());

		resultDTO.setStatus("SUCCESS");

		return resultDTO;
	}

	public static ResultDTO<String> edit(String cnpj, FabricanteDTO fabricante){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOFabricante.edit(cnpj, fabricante)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<String> remove(String cnpj){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOFabricante.remove(cnpj)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}
}
