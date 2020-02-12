package com.ebix.easi.auto.model.entities;

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
import javax.persistence.Transient;

import com.ebix.easi.auto.model.api.enums.FotoType;

@Entity
public class FotoDocumento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_fotoDocumento", unique = true, nullable = false)
	private Long id;

	private String url;

	private String name;

	@OneToMany(cascade = CascadeType.ALL)
    private List<PropriedadesTextoImagem> propriedadesTextoImagems;

	@Enumerated(EnumType.STRING)
	private FotoType tipoFoto;
	
	@Transient
	private byte[] imagemByteArray;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PropriedadesTextoImagem> getPropriedadesTextoImagems() {
		return propriedadesTextoImagems;
	}

	public void setPropriedadesTextoImagems(List<PropriedadesTextoImagem> propriedadesTextoImagems) {
		this.propriedadesTextoImagems = propriedadesTextoImagems;
	}

	public FotoType getTipoFoto() {
		return tipoFoto;
	}

	public void setTipoFoto(FotoType tipoFoto) {
		this.tipoFoto = tipoFoto;
	}

	public byte[] getImagemByteArray() {
		return imagemByteArray;
	}

	public void setImagemByteArray(byte[] imagemByteArray) {
		this.imagemByteArray = imagemByteArray;
	}

}
