package com.ebix.easi.auto.model.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ebix.easi.auto.model.entities.dtos.RetornoCRLVDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoImagemDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoReciboDto;
import com.ebix.easi.auto.model.service.VisionService;

@RestController
@RequestMapping("/automovel")
public class AutomovelRestController {

	@Autowired
	private VisionService visionService;


	@RequestMapping(value = "/verificar_propriedades_imagem", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> verificarImagem(@RequestParam("foto") MultipartFile file) {

		try {

			RetornoImagemDto retornoImagemDto = visionService.verificarPropriedadeImagem(file);


			return new ResponseEntity<RetornoImagemDto>(retornoImagemDto, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(value = "/verificar_cnh", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> verificarTextoImagemCNH(@RequestParam("foto") MultipartFile file) {

		try {

			RetornoReciboDto retornoCRLVDto = visionService.verificarImagemCNH(file);

			return new ResponseEntity<RetornoReciboDto>(retornoCRLVDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(value = "/verificar_recibo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> verificarTextoImagemRecibo(@RequestParam("foto") MultipartFile file) {

		try {

			RetornoReciboDto retornoCRLVDto = visionService.verificarImagemRecibo(file);

			return new ResponseEntity<RetornoReciboDto>(retornoCRLVDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(value = "/verificar_crlv", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> verificarTextoImagemCRLV(@RequestParam("foto") MultipartFile file) {

		try {

			RetornoCRLVDto retornoCRLVDto = visionService.verificarImagemCRLV(file);

			return new ResponseEntity<RetornoCRLVDto>(retornoCRLVDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
