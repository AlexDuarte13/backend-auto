package com.ebix.easi.auto.model.entities.dtos;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ebix.easi.auto.model.api.enums.FotoType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "RetornoRecibo")
public class RetornoReciboDto {

	@JsonProperty(value = "nome")
	private String nome;

	@JsonProperty(value = "logradouro")
	private String logradouro;

	@JsonProperty(value = "numero")
	private String numero;

	@JsonProperty(value = "cep")
	private String cep;

	@JsonProperty(value = "cpfCnpj")
	private String cpfCnpj;

	@JsonProperty(value = "complemento")
	private String complemento;

	@JsonProperty(value = "url")
	private String url;

	@JsonProperty(value = "imageName")
	private String imageName;

	@JsonProperty(value = "idRegistroDocumento")
	private Long idRegistroDocumento;

	@JsonProperty(value = "tipoFoto")
	@Enumerated(EnumType.STRING)
	private FotoType tipoFoto;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public FotoType getTipoFoto() {
		return tipoFoto;
	}

	public void setTipoFoto(FotoType tipoFoto) {
		this.tipoFoto = tipoFoto;
	}

	public Long getIdRegistroDocumento() {
		return idRegistroDocumento;
	}

	public void setIdRegistroDocumento(Long idRegistroDocumento) {
		this.idRegistroDocumento = idRegistroDocumento;
	}



}
