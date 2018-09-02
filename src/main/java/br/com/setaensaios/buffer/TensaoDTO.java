package br.com.setaensaios.buffer;

import java.util.List;

public class TensaoDTO {

	private Integer id;
	private Integer codigo;
	private String teste;
	private List<String> correnteFuga;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getTeste() {
		return teste;
	}
	public void setTeste(String teste) {
		this.teste = teste;
	}
	public List<String> getCorrenteFuga() {
		return correnteFuga;
	}
	public void setCorrenteFuga(List<String> correnteFuga) {
		this.correnteFuga = correnteFuga;
	}
	
}
