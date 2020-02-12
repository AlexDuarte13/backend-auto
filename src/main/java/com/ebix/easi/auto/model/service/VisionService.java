package com.ebix.easi.auto.model.service;

import org.springframework.web.multipart.MultipartFile;

import com.ebix.easi.auto.model.api.enums.FotoType;
import com.ebix.easi.auto.model.entities.FotoVeiculo;
import com.ebix.easi.auto.model.entities.dtos.RetornoCRLVDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoImagemDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoReciboDto;


public interface VisionService {

	public RetornoCRLVDto verificarImagemCRLV(MultipartFile file);

	public RetornoReciboDto verificarImagemRecibo(MultipartFile file);

	public RetornoReciboDto verificarImagemCNH(MultipartFile file);

	public RetornoImagemDto verificarPropriedadeImagem(MultipartFile file);

	public FotoVeiculo registrarImagem(MultipartFile file, FotoType tipo);

	public RetornoReciboDto registrarCNH(MultipartFile file, FotoType tipo);

	public RetornoCRLVDto registrarCRLV(MultipartFile file, FotoType tipo);


}
