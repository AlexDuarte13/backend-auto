package com.ebix.easi.auto.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ebix.easi.auto.model.entities.Automovel;
import com.ebix.easi.auto.model.repository.AutomovelRepository;

@Service
public class AutomovelServiceImpl implements AutomovelService {

	@Autowired
	AutomovelRepository automovelRepository;

	@Override
	public Automovel findById(Long id) {

		Automovel automovel = null;

		Optional<Automovel> automovelReturned = automovelRepository.findById(id);

		if (automovelReturned.isPresent()) {
			automovel = automovelReturned.get();
		}


		return automovel;

	}

	@Override
	public Page<Automovel> findAll(Pageable pageable) {

		Page<Automovel> regionsPage = automovelRepository.findAll(pageable);
		List<Automovel> automovels = regionsPage.getContent();

		return new PageImpl<Automovel>(automovels, pageable, regionsPage.getTotalElements());

	}

	@Override
	public void save(Automovel automovel) {

		automovelRepository.save(automovel);
	}

	@Override
	public void update(Automovel automovel, Automovel automovelOriginal) {

		//automovelOriginal.setName(automovel.getName());

		automovelRepository.save(automovelOriginal);
	}

	@Override
	public void delete(Automovel automovel) {

		automovelRepository.delete(automovel);
	}

}
