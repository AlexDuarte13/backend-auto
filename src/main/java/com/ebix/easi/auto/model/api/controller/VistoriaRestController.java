package com.ebix.easi.auto.model.api.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ebix.easi.auto.model.api.enums.FotoType;
import com.ebix.easi.auto.model.api.enums.StatusType;
import com.ebix.easi.auto.model.entities.FotoVeiculo;
import com.ebix.easi.auto.model.entities.Vistoria;
import com.ebix.easi.auto.model.entities.dtos.RetornoCRLVDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoReciboDto;
import com.ebix.easi.auto.model.service.FotoDocumentoService;
import com.ebix.easi.auto.model.service.FotoVeiculoService;
import com.ebix.easi.auto.model.service.VisionService;
import com.ebix.easi.auto.model.service.VistoriaService;

@RestController
@RequestMapping("/vistoria")
public class VistoriaRestController {

	@Autowired
	private VistoriaService vistoriaService;

	@Autowired
	private VisionService visionService;

	@Autowired
	private FotoVeiculoService fotoVeiculoService;

	@Autowired
	private FotoDocumentoService fotoDocumentoService;

	@RequestMapping(value = "/salvar_cnh", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> verificarTextoImagemCNH(@RequestParam("foto") MultipartFile file) {

		try {

			RetornoReciboDto retornoCRLVDto = visionService.registrarCNH(file, FotoType.CNH);

			fotoDocumentoService.saveCNH(retornoCRLVDto);


			return new ResponseEntity<RetornoReciboDto>(retornoCRLVDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(value = "/salvar_crlv", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> verificarTextoImagemCRLV(@RequestParam("foto") MultipartFile file) {

		try {

			RetornoCRLVDto retornoCRLVDto = visionService.registrarCRLV(file, FotoType.CRLV);

			fotoDocumentoService.saveCRLV(retornoCRLVDto);


			return new ResponseEntity<RetornoCRLVDto>(retornoCRLVDto, HttpStatus.ACCEPTED);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(value = "/salvarFotoVeiculo", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> registrarImagem(@RequestParam("foto") MultipartFile file, @RequestParam("tipo") FotoType tipoImagem ) {

		try {

			FotoVeiculo retornoImagemDto = visionService.registrarImagem(file, tipoImagem);

			FotoVeiculo fotoVeiculo = fotoVeiculoService.save(retornoImagemDto);

			//TODO continuar o salvamento da imagem daqui

			return new ResponseEntity<FotoVeiculo>(fotoVeiculo, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<?> salvarVistoria(@RequestBody @Valid Vistoria vistoria) {

		try {

			Vistoria vistoriaSaved = vistoriaService.save(vistoria);
			//exampleService.save(example);
			
			if (vistoriaSaved.getId()!=null && vistoriaSaved.getId()>0) {
				return new ResponseEntity<Vistoria>(vistoriaSaved, HttpStatus.CREATED);
			}else {
				return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);
			}


		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}


	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> listVistorias(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "sort", defaultValue = "asc") String sort,
			@RequestParam(value = "objOrd", defaultValue = "data") String objOrd,
			@RequestParam(value = "status", defaultValue = "ALL") StatusType statusType) {


		try {

			String obj = verificaCamposSort(objOrd);

			Pageable paging = null;

			if ("asc".equalsIgnoreCase(sort)) {
				paging = PageRequest.of(page, pageSize, Sort.by(obj).ascending());
			} else {
				paging = PageRequest.of(page, pageSize, Sort.by(obj).descending());
			}

			Page<Vistoria> examples = null;
			if (statusType != null && !"".equals(statusType)) {
				examples = vistoriaService.findAllByStatus(statusType, paging);
			}else {
				examples = vistoriaService.findAll(paging);
			}


			if (examples != null && examples.getContent().size() > 0) {

				return new ResponseEntity<Page<Vistoria>>(examples, HttpStatus.OK);

			} else {

				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

			}

		} catch (Exception e) {

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}
	
	@RequestMapping(value = "/image/{TOKEN}/{NAME}", method = RequestMethod.GET)
	public ResponseEntity<?> getImageAsByteArray(@PathVariable("NAME") String url, @PathVariable("TOKEN") String token, HttpServletResponse response) throws IOException {
		
		byte[] bFile = null;
		
		try {
			
		bFile = Files.readAllBytes(new File("/home/braddock/Documents/dev/ebix/imagens/"+url).toPath());
		//InputStream imagemByteArray = getClass().getResourceAsStream(fotoVeiculo.getUrl());
		
		
		} catch (IOException e) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		
		return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bFile);
	}
	
    @RequestMapping(value = "/{ID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> buscarVistoria(@PathVariable("ID") Long id) {

        try {

        	Vistoria vistoria = vistoriaService.findById(id);

            if (vistoria != null) {

                return new ResponseEntity<Vistoria>(vistoria, HttpStatus.OK);

            } else {

                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

            }

        } catch (Exception e) {

            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
    
    @RequestMapping(value = "/{ID}/laudo", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE, MediaType.TEXT_HTML_VALUE})
    public ResponseEntity<?> atualizarlaudoVistoria(@PathVariable("ID") Long id, @RequestBody Vistoria vistoria) {

        try {

            Vistoria vistoriaBase = vistoriaService.findById(id);

            if (vistoriaBase == null) {

                return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);

            } else if (vistoriaBase.getStatus().equals(StatusType.CONCLUIDA)) {

            	vistoriaBase = vistoriaService.updateLaudoVistoria(vistoriaBase, vistoria);

            } else {

                return new ResponseEntity<Object>(HttpStatus.NOT_MODIFIED);

            }

            return new ResponseEntity<Vistoria>(vistoriaBase, HttpStatus.ACCEPTED);

        } catch (Exception e) {

            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

	/**
	 * Método responsavel por verificar a ordem do filtro
	 *
	 * @param objOrd
	 * @return
	 */
	private String verificaCamposSort(String objOrd) {
		String obj;
		if ("id".equals(objOrd) || "data".equals(objOrd)) {

			obj = objOrd;

		} else {

			obj = "id";

		}
		return obj;
	}

}
