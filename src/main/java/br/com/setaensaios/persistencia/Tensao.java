package br.com.setaensaios.persistencia;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.eclipse.persistence.annotations.Array;
import org.eclipse.persistence.annotations.Struct;

@Entity
@Table(name = "tensoes")
@Struct(name = "correnteFuga")
@NamedQueries({
@NamedQuery(name = "Tensao.findAll", query = "SELECT a FROM Tensao a")})
public class Tensao implements Serializable{

	@Id
    @GeneratedValue(generator="seq_tensao")
    @SequenceGenerator(name="seq_tensao",sequenceName="seq_tensao", allocationSize=1)
	@Column(name = "did")
	private Integer id;
	
	@Column(name = "codigo")
	private Integer codigo;
	
	@Column(name = "teste")
	private String teste;
	
	@Column(columnDefinition="varchar array", name = "corrente_fuga")
	@Array(databaseType="varchar")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result + ((correnteFuga == null) ? 0 : correnteFuga.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((teste == null) ? 0 : teste.hashCode());
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
		Tensao other = (Tensao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (correnteFuga == null) {
			if (other.correnteFuga != null)
				return false;
		} else if (!correnteFuga.equals(other.correnteFuga))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (teste == null) {
			if (other.teste != null)
				return false;
		} else if (!teste.equals(other.teste))
			return false;
		return true;
	}
	
}
