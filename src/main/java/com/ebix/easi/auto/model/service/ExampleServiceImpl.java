package com.ebix.easi.auto.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ebix.easi.auto.model.entities.Example;
import com.ebix.easi.auto.model.repository.ExampleRepository;

@Service
public class ExampleServiceImpl implements ExampleService {

	@Autowired
	ExampleRepository exampleRepository;

	@Override
	public Example findById(Long id) {

		Example example = null;

		Optional<Example> exampleReturned = exampleRepository.findById(id);

		if (exampleReturned.isPresent()) {
			example = exampleReturned.get();
		}


		return example;

	}

	@Override
	public Page<Example> findAll(Pageable pageable) {

		Page<Example> regionsPage = exampleRepository.findAll(pageable);
		List<Example> examples = regionsPage.getContent();

		return new PageImpl<Example>(examples, pageable, regionsPage.getTotalElements());

	}

	@Override
	public void save(Example example) {

		exampleRepository.save(example);
	}

	@Override
	public void update(Example example, Example exampleOriginal) {

		exampleOriginal.setName(example.getName());

		exampleRepository.save(exampleOriginal);
	}

	@Override
	public void delete(Example example) {

		exampleRepository.delete(example);
	}

}
