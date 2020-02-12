package com.ebix.easi.auto.model.entities.dtos;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.ebix.easi.auto.model.api.enums.FotoType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "RetornoCRLV")
public class RetornoCRLVDto {

	@JsonProperty(value = "placa")
	private String placa;

	@JsonProperty(value = "uf")
	private String uf;

	@JsonProperty(value = "chassi")
	private String chassi;

	@JsonProperty(value = "renavam")
	private String renavam;

	@JsonProperty(value = "marcaModelo")
	private String marcaModelo;

	@JsonProperty(value = "anoVeiculo")
	private String anoVeiculo;

	@JsonProperty(value = "url")
	private String url;

	@JsonProperty(value = "imageName")
	private String imageName;

	@JsonProperty(value = "idRegistroDocumento")
	private Long idRegistroDocumento;

	@JsonProperty(value = "tipoFoto")
	@Enumerated(EnumType.STRING)
	private FotoType tipoFoto;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	public String getRenavam() {
		return renavam;
	}

	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}

	public String getAnoVeiculo() {
		return anoVeiculo;
	}

	public void setAnoVeiculo(String anoVeiculo) {
		this.anoVeiculo = anoVeiculo;
	}

	public String getMarcaModelo() {
		return marcaModelo;
	}

	public void setMarcaModelo(String marcaModelo) {
		this.marcaModelo = marcaModelo;
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

	public Long getIdRegistroDocumento() {
		return idRegistroDocumento;
	}

	public void setIdRegistroDocumento(Long idRegistroDocumento) {
		this.idRegistroDocumento = idRegistroDocumento;
	}

	public FotoType getTipoFoto() {
		return tipoFoto;
	}

	public void setTipoFoto(FotoType tipoFoto) {
		this.tipoFoto = tipoFoto;
	}






}
