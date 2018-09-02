package br.com.setaensaios.persistencia;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "materiais")
@NamedQueries({
@NamedQuery(name = "Material.findAll", query = "SELECT a FROM Material a")})
public class Material implements Serializable{

	@Id
	@Column(name = "nome")
	private String nome;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="cnpj_fabricante")
	private Fabricante fabricante;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="id_tensao")
	private Tensao tensao;
	
	@Column(name = "corrente_fuga")
	private String correnteFuga;
	
	@Column(name = "unidade")
	private String unidade;
	
	@Column(name = "numero_serie_fabricante")
	private String numeroSerieFabricante;
	
	@Column(name = "numero_seta")
	private String numeroSeta;
	
	@Column(name = "sequencial")
	private Boolean sequencial;
	
	@Column(name = "dias_reteste")
	private Integer diasReteste;
	
	@Column(name = "cautela")
	private String cautela;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public Boolean getSequencial() {
		return sequencial;
	}

	public void setSequencial(Boolean sequencial) {
		this.sequencial = sequencial;
	}

	public Integer getDiasReteste() {
		return diasReteste;
	}

	public void setDiasReteste(Integer diasReteste) {
		this.diasReteste = diasReteste;
	}

	public String getCautela() {
		return cautela;
	}

	public void setCautela(String cautela) {
		this.cautela = cautela;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cautela == null) ? 0 : cautela.hashCode());
		result = prime * result + ((correnteFuga == null) ? 0 : correnteFuga.hashCode());
		result = prime * result + ((diasReteste == null) ? 0 : diasReteste.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroSerieFabricante == null) ? 0 : numeroSerieFabricante.hashCode());
		result = prime * result + ((numeroSeta == null) ? 0 : numeroSeta.hashCode());
		result = prime * result + ((sequencial == null) ? 0 : sequencial.hashCode());
		result = prime * result + ((tensao == null) ? 0 : tensao.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (cautela == null) {
			if (other.cautela != null)
				return false;
		} else if (!cautela.equals(other.cautela))
			return false;
		if (correnteFuga == null) {
			if (other.correnteFuga != null)
				return false;
		} else if (!correnteFuga.equals(other.correnteFuga))
			return false;
		if (diasReteste == null) {
			if (other.diasReteste != null)
				return false;
		} else if (!diasReteste.equals(other.diasReteste))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numeroSerieFabricante == null) {
			if (other.numeroSerieFabricante != null)
				return false;
		} else if (!numeroSerieFabricante.equals(other.numeroSerieFabricante))
			return false;
		if (numeroSeta == null) {
			if (other.numeroSeta != null)
				return false;
		} else if (!numeroSeta.equals(other.numeroSeta))
			return false;
		if (sequencial == null) {
			if (other.sequencial != null)
				return false;
		} else if (!sequencial.equals(other.sequencial))
			return false;
		if (tensao == null) {
			if (other.tensao != null)
				return false;
		} else if (!tensao.equals(other.tensao))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}

}
