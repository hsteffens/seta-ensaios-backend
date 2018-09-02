package br.com.setaensaios.buffer;

public class MaterialDTO {

	private String nome;
	private TensaoDTO tensao;
	private String correnteFuga;
	private String unidade;
	private FabricanteDTO fabricante;
	private String numeroSerieFabricante;
	private String numeroSeta;
	private String cautela;
	private Integer diasReteste;
	private Boolean sequencial;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public TensaoDTO getTensao() {
		return tensao;
	}
	public void setTensao(TensaoDTO tensao) {
		this.tensao = tensao;
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
	public String getCautela() {
		return cautela;
	}
	public void setCautela(String cautela) {
		this.cautela = cautela;
	}
	public Integer getDiasReteste() {
		return diasReteste;
	}
	public void setDiasReteste(Integer diasReteste) {
		this.diasReteste = diasReteste;
	}
	public Boolean getSequencial() {
		return sequencial;
	}
	public void setSequencial(Boolean sequencial) {
		this.sequencial = sequencial;
	}
}
