package br.com.setaensaios.material;

import br.com.setaensaios.buffer.MaterialDTO;
import br.com.setaensaios.buffer.ResultDTO;

/**
 * Classe com as facilitadores para manipulação de materiais.
 * 
 * @author Hélinton P. Steffens
 *
 */
public final class FacadeMaterial {

	private FacadeMaterial() {
		
	}
	
	public static ResultDTO<String> insert(MaterialDTO material){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		boolean canEdit = BOMaterial.edit(material.getNome(), material);
		if (canEdit || BOMaterial.create(material)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<MaterialDTO> getMaterial() {
		ResultDTO<MaterialDTO> resultDTO = new ResultDTO<>();
		resultDTO.setResult(BOMaterial.getMaterial());

		resultDTO.setStatus("SUCCESS");

		return resultDTO;
	}

	public static ResultDTO<String> edit(String nome, MaterialDTO material){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOMaterial.edit(nome, material)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}

	public static ResultDTO<String> remove(String nome){
		ResultDTO<String> resultDTO = new ResultDTO<>();

		if (BOMaterial.remove(nome)) {
			resultDTO.setStatus("SUCCESS");
		}else{
			resultDTO.setStatus("ERROR");
		}

		return resultDTO;
	}
}
