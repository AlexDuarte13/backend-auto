package com.ebix.easi.auto.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ebix.easi.auto.model.api.enums.StatusType;
import com.ebix.easi.auto.model.entities.Vistoria;

public interface VistoriaService {

	public Vistoria save(Vistoria vistoria);

	public Page<Vistoria> findAll(Pageable paging);

	Page<Vistoria> findAllByStatus(StatusType status, Pageable paging);
	
	public Vistoria findById(Long id);
	
	public Vistoria updateLaudoVistoria(Vistoria vistoriaBase, Vistoria vistoria);

}
