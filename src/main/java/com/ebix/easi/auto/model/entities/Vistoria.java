package com.ebix.easi.auto.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.ebix.easi.auto.model.api.enums.StatusType;

@Entity
public class Vistoria implements Serializable {

	private static final long serialVersionUID = -614747203161257381L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_vistoria", unique = true, nullable = false)
	private Long id;

	private String nome;

	private Date data;

	private Date dataLaudo;

	@OneToOne(cascade = CascadeType.ALL)
	private DocumentosPessoais documentosPessoais;

	@OneToOne(cascade = CascadeType.ALL)
	private Automovel automovel;

	@OneToOne(cascade = CascadeType.DETACH)
	private User usuarioSegurado;

	@OneToOne(cascade = CascadeType.DETACH)
	private User usuarioVistoriador;

	@Enumerated(EnumType.STRING)
	private StatusType status;

	// Fotos
	@OneToMany(cascade = CascadeType.DETACH)
	private List<FotoVeiculo> fotoVeiculo = new ArrayList<FotoVeiculo>();

	// Fotos
	@OneToMany(cascade = CascadeType.DETACH)
	private List<FotoDocumento> fotoDocumento = new ArrayList<FotoDocumento>();

	@OneToOne(cascade = CascadeType.ALL)
	private Laudo laudo;

	private boolean recomendacao;

	@Transient
	private Long idfotoDianteiraEsquerda;

	@Transient
	private Long idfotoDianteiraDireita;

	@Transient
	private Long idfotoTraseiraEsquerda;

	@Transient
	private Long idfotoTraseiraDireita;

	@Transient
	private Long idfotoChassi;

	@Transient
	private Long idfotoMotor;

	@Transient
	private Long idfotoPainel;

	@Transient
	private Long idfotoPortaMala;

	@Transient
	private Long idCRLV;

	@Transient
	private Long idCNH;

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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public DocumentosPessoais getDocumentosPessoais() {
		return documentosPessoais;
	}

	public void setDocumentosPessoais(DocumentosPessoais documentosPessoais) {
		this.documentosPessoais = documentosPessoais;
	}

	public Automovel getAutomovel() {
		return automovel;
	}

	public void setAutomovel(Automovel automovel) {
		this.automovel = automovel;
	}

	public User getUsuarioSegurado() {
		return usuarioSegurado;
	}

	public void setUsuarioSegurado(User usuarioSegurado) {
		this.usuarioSegurado = usuarioSegurado;
	}

	public User getUsuarioVistoriador() {
		return usuarioVistoriador;
	}

	public void setUsuarioVistoriador(User usuarioVistoriador) {
		this.usuarioVistoriador = usuarioVistoriador;
	}

	public List<FotoVeiculo> getFotoVeiculo() {
		return fotoVeiculo;
	}

	public void setFotoVeiculo(List<FotoVeiculo> fotoVeiculo) {
		this.fotoVeiculo = fotoVeiculo;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public Date getDataLaudo() {
		return dataLaudo;
	}

	public void setDataLaudo(Date dataLaudo) {
		this.dataLaudo = dataLaudo;
	}

	public Long getIdfotoDianteiraEsquerda() {
		return idfotoDianteiraEsquerda;
	}

	public void setIdfotoDianteiraEsquerda(Long idfotoDianteiraEsquerda) {
		this.idfotoDianteiraEsquerda = idfotoDianteiraEsquerda;
	}

	public Long getIdfotoDianteiraDireita() {
		return idfotoDianteiraDireita;
	}

	public void setIdfotoDianteiraDireita(Long idfotoDianteiraDireita) {
		this.idfotoDianteiraDireita = idfotoDianteiraDireita;
	}

	public Long getIdfotoTraseiraEsquerda() {
		return idfotoTraseiraEsquerda;
	}

	public void setIdfotoTraseiraEsquerda(Long idfotoTraseiraEsquerda) {
		this.idfotoTraseiraEsquerda = idfotoTraseiraEsquerda;
	}

	public Long getIdfotoTraseiraDireita() {
		return idfotoTraseiraDireita;
	}

	public void setIdfotoTraseiraDireita(Long idfotoTraseiraDireita) {
		this.idfotoTraseiraDireita = idfotoTraseiraDireita;
	}

	public Long getIdfotoChassi() {
		return idfotoChassi;
	}

	public void setIdfotoChassi(Long idfotoChassi) {
		this.idfotoChassi = idfotoChassi;
	}

	public Long getIdfotoMotor() {
		return idfotoMotor;
	}

	public void setIdfotoMotor(Long idfotoMotor) {
		this.idfotoMotor = idfotoMotor;
	}

	public Long getIdfotoPainel() {
		return idfotoPainel;
	}

	public void setIdfotoPainel(Long idfotoPainel) {
		this.idfotoPainel = idfotoPainel;
	}

	public Long getIdfotoPortaMala() {
		return idfotoPortaMala;
	}

	public void setIdfotoPortaMala(Long idfotoPortaMala) {
		this.idfotoPortaMala = idfotoPortaMala;
	}

	public List<FotoDocumento> getFotoDocumento() {
		return fotoDocumento;
	}

	public void setFotoDocumento(List<FotoDocumento> fotoDocumento) {
		this.fotoDocumento = fotoDocumento;
	}

	public Laudo getLaudo() {
		return laudo;
	}

	public void setLaudo(Laudo laudo) {
		this.laudo = laudo;
	}

	public Long getIdCRLV() {
		return idCRLV;
	}

	public void setIdCRLV(Long idCRLV) {
		this.idCRLV = idCRLV;
	}

	public Long getIdCNH() {
		return idCNH;
	}

	public void setIdCNH(Long idCNH) {
		this.idCNH = idCNH;
	}

	public boolean isRecomendacao() {
		return recomendacao;
	}

	public void setRecomendacao(boolean recomendacao) {
		this.recomendacao = recomendacao;
	}

}
