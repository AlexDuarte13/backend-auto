package com.ebix.easi.auto.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CoresImagem implements Comparable<CoresImagem>{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_CoresImagem", unique = true, nullable = false)
	private Long id;

	private float rgbBlue;

	private float rgbGreen;

	private float rgbRed;

	private String descricaoCor;

	private float score;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getRgbBlue() {
		return rgbBlue;
	}

	public void setRgbBlue(float rgbBlue) {
		this.rgbBlue = rgbBlue;
	}

	public float getRgbGreen() {
		return rgbGreen;
	}

	public void setRgbGreen(float rgbGreen) {
		this.rgbGreen = rgbGreen;
	}

	public float getRgbRed() {
		return rgbRed;
	}

	public void setRgbRed(float rgbRed) {
		this.rgbRed = rgbRed;
	}

	public String getDescricaoCor() {
		return descricaoCor;
	}

	public void setDescricaoCor(String descricaoCor) {
		this.descricaoCor = descricaoCor;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	@Override
	public int compareTo(CoresImagem o) {

		if (this.score > o.getScore()) {
			return -1;
		}

		if (this.score < o.getScore()) {
			return 1;
		}

		return 0;
	}



}
