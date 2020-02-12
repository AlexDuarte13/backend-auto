package com.ebix.easi.auto.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebix.easi.auto.model.entities.FotoVeiculo;
import com.ebix.easi.auto.model.repository.FotoVeiculoRepository;

@Service
public class FotoVeiculoServiceImpl implements FotoVeiculoService {

	@Autowired
	private FotoVeiculoRepository fotoVeiculoRepository;

	@Override
	public FotoVeiculo save(FotoVeiculo fotoVeiculo) {

		try {
			//Vistoria vistoriaSaved = vistoriaRepository.save(mockVistoria());
			FotoVeiculo fotoVeiculoSaved = fotoVeiculoRepository.save(fotoVeiculo);

			return fotoVeiculoSaved;
		} catch (Exception e) {
			System.out.println("ocorreu um erro: " + e.getMessage());
			return null;
		}

	}

}
