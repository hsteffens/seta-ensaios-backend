package br.com.setaensaios.tensao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import br.com.setaensaios.buffer.TensaoDTO;
import br.com.setaensaios.controller.TensaoController;
import br.com.setaensaios.persistencia.Tensao;
import br.com.setaensaios.provider.EntityManager;

/**
 * Classe com as regras de negocio de classe de tensão.
 * 
 * @author  Hélinton P. Steffens
 *
 */
public final class BOTensao {

	private BOTensao() {
		
	}
	
	public static boolean create(TensaoDTO tensaoDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;
		try{
			factory = EntityManager.getFactory();

			Tensao tensao = new Tensao();
			tensao.setCodigo(tensaoDTO.getCodigo());
			tensao.setTeste(tensaoDTO.getTeste());
			tensao.setCorrenteFuga(tensaoDTO.getCorrenteFuga());

			TensaoController controller = new TensaoController(factory);
			return controller.create(tensao);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
	}

	public static List<TensaoDTO> getClassesTensao(){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			TensaoController controller = new TensaoController(factory);
			List<Tensao> tensoes = controller.findClasseTensao();

			if (tensoes != null && !tensoes.isEmpty()) {
				List<TensaoDTO> tensoesDTO = new ArrayList<>();
				for (Tensao tensao : tensoes) {
					TensaoDTO tensaoDTO = new TensaoDTO();
					tensaoDTO.setId(tensao.getId());
					tensaoDTO.setCodigo(tensao.getCodigo());
					tensaoDTO.setTeste(tensao.getTeste());
					tensaoDTO.setCorrenteFuga(tensao.getCorrenteFuga());

					tensoesDTO.add(tensaoDTO);
				}

				return tensoesDTO;
			}

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}
		return null;
	}

	public static boolean remove(Integer id){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			TensaoController controller = new TensaoController(factory);

			return controller.remove(id);
		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

	public static boolean edit(Integer id, TensaoDTO tensaoDTO){
		EntityManager.createEntityManagerFactory();
		EntityManagerFactory factory = null;

		try{
			factory = EntityManager.getFactory();
			TensaoController controller = new TensaoController(factory);

			Tensao tensao = new Tensao();
			tensao.setId(id);
			tensao.setCodigo(tensaoDTO.getCodigo());
			tensao.setTeste(tensaoDTO.getTeste());
			tensao.setCorrenteFuga(tensaoDTO.getCorrenteFuga());
			
			return controller.edit(tensao);

		}finally {
			if (factory.isOpen()) {
				EntityManager.destroyEntityManagerFactory();
			}
		}	
	}

}