package br.com.setaensaios.persistencia;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "laudos")
@NamedQueries({
	@NamedQuery(name = "Laudo.findAll", query = "SELECT a FROM Laudo a")})
public class Laudo implements Serializable{

	@Id	
	@GeneratedValue(generator="seq_laudo")
	@SequenceGenerator(name="seq_laudo",sequenceName="seq_laudo", allocationSize=1)
	@Column(name = "did")
	private Integer id;

	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	@JoinColumn(name="cnpj_customer")
	private Cliente cliente;

	@Column(name = "data_laudo")
	private Date dataLaudo;
	
	@Column(name = "calibracao")
	private String calibracao;
	
	@Column(name = "tipo_teste")
	private String tipoTeste;
	
	@Column(name = "equipamento")
	private String equipamento;

	@OneToMany(mappedBy="laudo", orphanRemoval=true)
	private List<LaudoItem> items;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getDataLaudo() {
		return dataLaudo;
	}

	public void setDataLaudo(Date dataLaudo) {
		this.dataLaudo = dataLaudo;
	}
	
	public List<LaudoItem> getItems() {
		return items;
	}

	public void setItems(List<LaudoItem> items) {
		this.items = items;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calibracao == null) ? 0 : calibracao.hashCode());
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((dataLaudo == null) ? 0 : dataLaudo.hashCode());
		result = prime * result + ((equipamento == null) ? 0 : equipamento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((items == null) ? 0 : items.hashCode());
		result = prime * result + ((tipoTeste == null) ? 0 : tipoTeste.hashCode());
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
		Laudo other = (Laudo) obj;
		if (calibracao == null) {
			if (other.calibracao != null)
				return false;
		} else if (!calibracao.equals(other.calibracao))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (dataLaudo == null) {
			if (other.dataLaudo != null)
				return false;
		} else if (!dataLaudo.equals(other.dataLaudo))
			return false;
		if (equipamento == null) {
			if (other.equipamento != null)
				return false;
		} else if (!equipamento.equals(other.equipamento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		if (tipoTeste == null) {
			if (other.tipoTeste != null)
				return false;
		} else if (!tipoTeste.equals(other.tipoTeste))
			return false;
		return true;
	}

}
