package com.ebix.easi.auto.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ebix.easi.auto.model.entities.Automovel;


public interface AutomovelService {

	public Automovel findById(Long id);

	public Page<Automovel> findAll(Pageable pageable);

	public void save(Automovel automovel);

	void update(Automovel automovel, Automovel automovelOriginal);

	void delete(Automovel automovel);

}
