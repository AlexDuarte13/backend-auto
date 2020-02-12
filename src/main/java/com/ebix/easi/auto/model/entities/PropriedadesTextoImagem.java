package com.ebix.easi.auto.model.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ebix.easi.auto.model.api.enums.CampoAvaliacaoEnum;

@Entity
public class PropriedadesTextoImagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_propriedadeTextoImagem", unique = true, nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	private CampoAvaliacaoEnum atributo;

    private String descricao;

    private Date dataInclusao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CampoAvaliacaoEnum getAtributo() {
		return atributo;
	}

	public void setAtributo(CampoAvaliacaoEnum atributo) {
		this.atributo = atributo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}





}
