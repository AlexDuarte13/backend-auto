package com.ebix.easi.auto.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ebix.easi.auto.model.api.enums.CampoAvaliacaoEnum;
import com.ebix.easi.auto.model.api.enums.ComparacaoCamposEnum;
import com.ebix.easi.auto.model.api.enums.TipoCampoEnum;

@Entity
public class Checklist implements Serializable {

	private static final long serialVersionUID = -433993963792298618L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_checklist", unique = true, nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoCampoEnum tipoCampoEnum;

	@Enumerated(EnumType.STRING)
	private CampoAvaliacaoEnum atributo;

	private String valorIA;
	private Date dataInclusaoIA;
	private String valorSegurado;
	private Date dataInclusaoSegurado;

	@Enumerated(EnumType.STRING)
	private ComparacaoCamposEnum comparacaoCamposEnum;

	private Float porcentagemAssertividade;

	private String valorVistoriador;
	private Date dataInclusaoVistoriador;
	private boolean recomendado;
	private String observacao;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TipoCampoEnum getTipoCampoEnum() {
		return tipoCampoEnum;
	}
	public void setTipoCampoEnum(TipoCampoEnum tipoCampoEnum) {
		this.tipoCampoEnum = tipoCampoEnum;
	}
	public CampoAvaliacaoEnum getAtributo() {
		return atributo;
	}
	public void setAtributo(CampoAvaliacaoEnum atributo) {
		this.atributo = atributo;
	}
	public String getValorIA() {
		return valorIA;
	}
	public void setValorIA(String valorIA) {
		this.valorIA = valorIA;
	}
	public Date getDataInclusaoIA() {
		return dataInclusaoIA;
	}
	public void setDataInclusaoIA(Date dataInclusaoIA) {
		this.dataInclusaoIA = dataInclusaoIA;
	}
	public String getValorSegurado() {
		return valorSegurado;
	}
	public void setValorSegurado(String valorSegurado) {
		this.valorSegurado = valorSegurado;
	}
	public Date getDataInclusaoSegurado() {
		return dataInclusaoSegurado;
	}
	public void setDataInclusaoSegurado(Date dataInclusaoSegurado) {
		this.dataInclusaoSegurado = dataInclusaoSegurado;
	}
	public ComparacaoCamposEnum getComparacaoCamposEnum() {
		return comparacaoCamposEnum;
	}
	public void setComparacaoCamposEnum(ComparacaoCamposEnum comparacaoCamposEnum) {
		this.comparacaoCamposEnum = comparacaoCamposEnum;
	}
	public Float getPorcentagemAssertividade() {
		return porcentagemAssertividade;
	}
	public void setPorcentagemAssertividade(Float porcentagemAssertividade) {
		this.porcentagemAssertividade = porcentagemAssertividade;
	}
	public String getValorVistoriador() {
		return valorVistoriador;
	}
	public void setValorVistoriador(String valorVistoriador) {
		this.valorVistoriador = valorVistoriador;
	}
	public Date getDataInclusaoVistoriador() {
		return dataInclusaoVistoriador;
	}
	public void setDataInclusaoVistoriador(Date dataInclusaoVistoriador) {
		this.dataInclusaoVistoriador = dataInclusaoVistoriador;
	}
	public boolean isRecomendado() {
		return recomendado;
	}
	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}


}
