package com.ebix.easi.auto.model.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ebix.easi.auto.model.entities.Example;


public interface ExampleService {

	public Example findById(Long id);

	public Page<Example> findAll(Pageable pageable);

	public void save(Example example);

	void update(Example example, Example exampleOriginal);

	void delete(Example example);

}
