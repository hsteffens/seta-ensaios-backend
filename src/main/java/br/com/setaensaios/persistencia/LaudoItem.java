package br.com.setaensaios.persistencia;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "laudos_item")
@NamedQueries({
@NamedQuery(name = "LaudoItem.findAll", query = "SELECT a FROM LaudoItem a"),
@NamedQuery(name = "LaudoItem.findByLaudoId", query = "SELECT a FROM LaudoItem a where a.laudo.id = :laudoId")})
public class LaudoItem implements Serializable {

	@Id	
    @GeneratedValue(generator="seq_laudo_item")
    @SequenceGenerator(name="seq_laudo_item",sequenceName="seq_laudo_item", allocationSize=1)
	@Column(name = "did")
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="id_laudo")
	private Laudo laudo;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="material")
	private Material material;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="cnpj_fabricante")
	private Fabricante fabricante;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="id_tensao")
	private Tensao tensao;
	
	@Column(name = "data_ensaio")
	private Date dataEnsaio;
	
	@Column(name = "numero_serie_fabricante")
	private String numeroSerieFabricante;
	
	@Column(name = "resultado")
	private Boolean resultado;
	
	@Column(name = "corrente_fuga")
	private String correnteFuga;
	
	@Column(name = "unidade")
	private String unidade;
	
	@Column(name = "origem")
	private String origem;
	
	@Column(name = "numero_seta")
	private String numeroSeta;
	
	@Column(name = "data_reteste")
	private Date dataReteste;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public Tensao getTensao() {
		return tensao;
	}

	public void setTensao(Tensao tensao) {
		this.tensao = tensao;
	}

	public Date getDataEnsaio() {
		return dataEnsaio;
	}

	public void setDataEnsaio(Date dataEnsaio) {
		this.dataEnsaio = dataEnsaio;
	}

	public String getNumeroSerieFabricante() {
		return numeroSerieFabricante;
	}

	public void setNumeroSerieFabricante(String numeroSerieFabricante) {
		this.numeroSerieFabricante = numeroSerieFabricante;
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

	public String getNumeroSeta() {
		return numeroSeta;
	}

	public void setNumeroSeta(String numeroSeta) {
		this.numeroSeta = numeroSeta;
	}

	public Date getDataReteste() {
		return dataReteste;
	}

	public void setDataReteste(Date dataReteste) {
		this.dataReteste = dataReteste;
	}

	
}
