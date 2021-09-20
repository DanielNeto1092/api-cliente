package br.com.builders.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import br.com.builders.annotation.Cpf;
import br.com.builders.util.DataUtil;
import io.swagger.annotations.ApiModelProperty;
/*
 * Criação de DTO para evitar expor a entidade como payload, evitando ataques como Mass Assignment
 */
public class ClienteDTO {
	
	private Long id;

	@ApiModelProperty(name="nome",value="Nome do cliente",example="Matheus",required=true)
	@NotEmpty
    @Size(min = 2, max = 200)
	private String nome;

	@ApiModelProperty(name="cpf",value="CPF do cliente",example="01234567890",required=true)
    @Cpf
    private String cpf;
	
	@ApiModelProperty(name="dataNascimento", value="data de nascimento do cliente",example="10/10/1910",required=true)
	@NotEmpty
    private String dataNascimento;
	
	
	@ApiModelProperty(name="idade",readOnly = true, value="idade do cliente",example="90")
    private Integer idade;
    
    
	public ClienteDTO() {}
	

	public ClienteDTO(Long id, String nome, String cpf, String dataNascimento) {
		setId(id);
		setNome(nome);
		setCpf(cpf);
		setDataNascimento(dataNascimento);
	}

	public ClienteDTO(String nome, String cpf, String dataNascimento) {
		setNome(nome);
		setCpf(cpf);
		setDataNascimento(dataNascimento);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Integer getIdade() {
		return DataUtil.cacularIdade(DataUtil.converterStringToDate(getDataNascimento()));
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idade == null) ? 0 : idade.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		ClienteDTO other = (ClienteDTO) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idade == null) {
			if (other.idade != null)
				return false;
		} else if (!idade.equals(other.idade))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
