package com.ebix.easi.auto.model.entities.dtos;

import java.util.List;

import com.ebix.easi.auto.model.entities.CoresImagem;
import com.ebix.easi.auto.model.entities.PropriedadesImagem;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "RetornoImagem")
public class RetornoImagemDto {

    private List<PropriedadesImagem> propriedadesImagems;

    private List<CoresImagem> coresImagem;

	public List<PropriedadesImagem> getPropriedadesImagems() {
		return propriedadesImagems;
	}
	public void setPropriedadesImagems(List<PropriedadesImagem> propriedadesImagems) {
		this.propriedadesImagems = propriedadesImagems;
	}
	public List<CoresImagem> getCoresImagem() {
		return coresImagem;
	}
	public void setCoresImagem(List<CoresImagem> coresImagem) {
		this.coresImagem = coresImagem;
	}



}
