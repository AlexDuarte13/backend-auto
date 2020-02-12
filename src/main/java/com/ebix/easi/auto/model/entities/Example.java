package com.ebix.easi.auto.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "example")
public class Example implements Serializable{

	private static final long serialVersionUID = -598234583961782681L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "example_id", unique = true, nullable = false)
	private Long id;

	@Length(min = 2, message = "*O nome deve ter no mínimo 2 caracteres")
	@NotEmpty(message = "*O nome é obrigatório")
	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
