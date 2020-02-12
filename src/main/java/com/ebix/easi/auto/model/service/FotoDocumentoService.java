package com.ebix.easi.auto.model.service;

import com.ebix.easi.auto.model.entities.FotoDocumento;
import com.ebix.easi.auto.model.entities.dtos.RetornoCRLVDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoReciboDto;

public interface FotoDocumentoService {

	public FotoDocumento saveCNH(RetornoReciboDto retornoCRLVDto);

	public FotoDocumento saveCRLV(RetornoCRLVDto retornoCnh);


}
