package br.com.setaensaios.buffer;

import java.time.LocalDate;

public class LaudoDTO {

	private Integer id;
	private ClienteDTO cliente;
	private LocalDate dataLaudo;
	private String calibracao;
	private String tipoTeste;
	private String equipamento;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = cliente;
	}
	public LocalDate getDataLaudo() {
		return dataLaudo;
	}
	public void setDataLaudo(LocalDate dataLaudo) {
		this.dataLaudo = dataLaudo;
	}
	public String getCalibracao() {
		return calibracao;
	}
	public void setCalibracao(String calibracao) {
		this.calibracao = calibracao;
	}
	public String getTipoTeste() {
		return tipoTeste;
	}
	public void setTipoTeste(String tipoTeste) {
		this.tipoTeste = tipoTeste;
	}
	public String getEquipamento() {
		return equipamento;
	}
	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}
}
