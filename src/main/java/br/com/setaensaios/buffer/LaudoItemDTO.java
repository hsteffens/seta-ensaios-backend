package br.com.setaensaios.buffer;

import java.time.LocalDate;

public class LaudoItemDTO {

	private Integer id;
	private String material;
	private TensaoDTO tensao;
	private Boolean resultado;
	private String correnteFuga;
	private String unidade;
	private String origem;
	private FabricanteDTO fabricante;
	private LaudoDTO laudo;
	private String numeroSerieFabricante;
	private String numeroSeta;
	private LocalDate dataEnsaio;
	private LocalDate dataReteste;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public TensaoDTO getTensao() {
		return tensao;
	}
	public void setTensao(TensaoDTO tensao) {
		this.tensao = tensao;
	}
	public Boolean getResultado() {
		return resultado;
	}
	public void setResultado(Boolean resultado) {
		this.resultado = resultado;
	}
	public String getCorrenteFuga() {
		return correnteFuga;
	}
	public void setCorrenteFuga(String correnteFuga) {
		this.correnteFuga = correnteFuga;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	public FabricanteDTO getFabricante() {
		return fabricante;
	}
	public void setFabricante(FabricanteDTO fabricante) {
		this.fabricante = fabricante;
	}
	public String getNumeroSerieFabricante() {
		return numeroSerieFabricante;
	}
	public void setNumeroSerieFabricante(String numeroSerieFabricante) {
		this.numeroSerieFabricante = numeroSerieFabricante;
	}
	public String getNumeroSeta() {
		return numeroSeta;
	}
	public void setNumeroSeta(String numeroSeta) {
		this.numeroSeta = numeroSeta;
	}
	public LocalDate getDataReteste() {
		return dataReteste;
	}
	public void setDataReteste(LocalDate dataReteste) {
		this.dataReteste = dataReteste;
	}
	public LocalDate getDataEnsaio() {
		return dataEnsaio;
	}
	public void setDataEnsaio(LocalDate dataEnsaio) {
		this.dataEnsaio = dataEnsaio;
	}
	public LaudoDTO getLaudo() {
		return laudo;
	}
	public void setLaudo(LaudoDTO laudo) {
		this.laudo = laudo;
	}
	
}
