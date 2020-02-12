package com.ebix.easi.auto.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Laudo implements Serializable {

	private static final long serialVersionUID = -433993963792298618L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_laudo", unique = true, nullable = false)
	private Long id;

	private Date dataVistoria;

	private Date dataRevisao;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Checklist> checklist = new ArrayList<Checklist>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDataVistoria() {
		return dataVistoria;
	}

	public void setDataVistoria(Date dataVistoria) {
		this.dataVistoria = dataVistoria;
	}

	public Date getDataRevisao() {
		return dataRevisao;
	}

	public void setDataRevisao(Date dataRevisao) {
		this.dataRevisao = dataRevisao;
	}

	public List<Checklist> getChecklist() {
		return checklist;
	}

	public void setChecklist(List<Checklist> checklist) {
		this.checklist = checklist;
	}

}
