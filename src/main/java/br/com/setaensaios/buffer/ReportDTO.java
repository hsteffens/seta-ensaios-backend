package br.com.setaensaios.buffer;

import java.time.LocalDate;

public class ReportDTO {

	private String clienteCnpj;
	private String material;
	private LocalDate dataInicial; 
	private LocalDate dataFinal;
	private String tipoTeste;
	private String equipamento;
	private String calibracao;
	
	public String getClienteCnpj() {
		return clienteCnpj;
	}
	public void setClienteCnpj(String clienteCnpj) {
		this.clienteCnpj = clienteCnpj;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public LocalDate getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}
	public LocalDate getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
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
	public String getCalibracao() {
		return calibracao;
	}
	public void setCalibracao(String calibracao) {
		this.calibracao = calibracao;
	}
	
}
