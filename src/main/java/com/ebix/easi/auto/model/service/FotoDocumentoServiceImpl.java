package com.ebix.easi.auto.model.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ebix.easi.auto.model.api.enums.CampoAvaliacaoEnum;
import com.ebix.easi.auto.model.entities.FotoDocumento;
import com.ebix.easi.auto.model.entities.PropriedadesTextoImagem;
import com.ebix.easi.auto.model.entities.dtos.RetornoCRLVDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoReciboDto;
import com.ebix.easi.auto.model.repository.FotoDocumentoRepository;

@Service
public class FotoDocumentoServiceImpl implements FotoDocumentoService {

	@Autowired
	private FotoDocumentoRepository fotoDocumentoRepository;

	@Override
	public FotoDocumento saveCRLV(RetornoCRLVDto retornoCnh) {

		try {

			FotoDocumento fotoDocumentoDocumento = new FotoDocumento();

			fotoDocumentoDocumento.setName(retornoCnh.getImageName());

			ArrayList<PropriedadesTextoImagem> propriedadesTextoImagems = extrairProproedadesTextoCRLV(retornoCnh);

			fotoDocumentoDocumento.setPropriedadesTextoImagems(propriedadesTextoImagems);
			fotoDocumentoDocumento.setTipoFoto(retornoCnh.getTipoFoto());
			fotoDocumentoDocumento.setUrl(retornoCnh.getUrl());

			FotoDocumento fotoDocumentoSaved = fotoDocumentoRepository.save(fotoDocumentoDocumento);

			retornoCnh.setIdRegistroDocumento(fotoDocumentoSaved.getId());

			return fotoDocumentoSaved;
		} catch (Exception e) {
			System.out.println("ocorreu um erro: " + e.getMessage());
			return null;
		}

	}

	private ArrayList<PropriedadesTextoImagem> extrairProproedadesTextoCRLV(RetornoCRLVDto retornoCnh) {
		ArrayList<PropriedadesTextoImagem> propriedadesTextoImagems = new ArrayList<PropriedadesTextoImagem>();

		if (retornoCnh.getAnoVeiculo() != null && !"".equals(retornoCnh.getAnoVeiculo())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.ANOFABRICACAO);
			textoImagem.setDescricao(retornoCnh.getAnoVeiculo());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}

		if (retornoCnh.getChassi() != null && !"".equals(retornoCnh.getChassi())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.CHASSI);
			textoImagem.setDescricao(retornoCnh.getChassi());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}

		if (retornoCnh.getMarcaModelo() != null && !"".equals(retornoCnh.getMarcaModelo())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.MARCAMODELO);
			textoImagem.setDescricao(retornoCnh.getMarcaModelo());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}

		if (retornoCnh.getPlaca() != null && !"".equals(retornoCnh.getPlaca())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.PLACA);
			textoImagem.setDescricao(retornoCnh.getPlaca());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}

		if (retornoCnh.getRenavam() != null && !"".equals(retornoCnh.getRenavam())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.RENAVAM);
			textoImagem.setDescricao(retornoCnh.getRenavam());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}

		if (retornoCnh.getUf() != null && !"".equals(retornoCnh.getUf())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.UF);
			textoImagem.setDescricao(retornoCnh.getUf());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}
		return propriedadesTextoImagems;
	}

	@Override
	public FotoDocumento saveCNH(RetornoReciboDto retornoCnh) {

		try {

			FotoDocumento fotoDocumentoDocumento = new FotoDocumento();

			fotoDocumentoDocumento.setName(retornoCnh.getImageName());

			ArrayList<PropriedadesTextoImagem> propriedadesTextoImagems = extrairPropriedadesTextoCNH(retornoCnh);


			fotoDocumentoDocumento.setPropriedadesTextoImagems(propriedadesTextoImagems);
			fotoDocumentoDocumento.setTipoFoto(retornoCnh.getTipoFoto());
			fotoDocumentoDocumento.setUrl(retornoCnh.getUrl());

			FotoDocumento fotoDocumentoSaved = fotoDocumentoRepository.save(fotoDocumentoDocumento);

			retornoCnh.setIdRegistroDocumento(fotoDocumentoSaved.getId());

			return fotoDocumentoSaved;
		} catch (Exception e) {
			System.out.println("ocorreu um erro: " + e.getMessage());
			return null;
		}

	}

	private ArrayList<PropriedadesTextoImagem> extrairPropriedadesTextoCNH(RetornoReciboDto retornoCnh) {
		ArrayList<PropriedadesTextoImagem> propriedadesTextoImagems = new ArrayList<PropriedadesTextoImagem>();

		if (retornoCnh.getNome() != null && !"".equals(retornoCnh.getNome())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.NOME);
			textoImagem.setDescricao(retornoCnh.getNome());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}

		if (retornoCnh.getCep() != null && !"".equals(retornoCnh.getCep())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.CEP);
			textoImagem.setDescricao(retornoCnh.getCep());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}

		if (retornoCnh.getCpfCnpj() != null && !"".equals(retornoCnh.getCpfCnpj())) {
			PropriedadesTextoImagem textoImagem = new PropriedadesTextoImagem();
			textoImagem.setAtributo(CampoAvaliacaoEnum.CPFCNPJ);
			textoImagem.setDescricao(retornoCnh.getCpfCnpj());
			textoImagem.setDataInclusao(new Date());
			propriedadesTextoImagems.add(textoImagem);
		}
		return propriedadesTextoImagems;
	}

}
