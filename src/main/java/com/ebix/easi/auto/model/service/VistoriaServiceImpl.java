package com.ebix.easi.auto.model.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ebix.easi.auto.model.api.enums.CampoAvaliacaoEnum;
import com.ebix.easi.auto.model.api.enums.ComparacaoCamposEnum;
import com.ebix.easi.auto.model.api.enums.FotoType;
import com.ebix.easi.auto.model.api.enums.StatusType;
import com.ebix.easi.auto.model.api.enums.TipoCampoEnum;
import com.ebix.easi.auto.model.api.util.CheckSimilaridadeUtil;
import com.ebix.easi.auto.model.entities.Automovel;
import com.ebix.easi.auto.model.entities.Checklist;
import com.ebix.easi.auto.model.entities.CoresImagem;
import com.ebix.easi.auto.model.entities.DocumentosPessoais;
import com.ebix.easi.auto.model.entities.Endereco;
import com.ebix.easi.auto.model.entities.FotoDocumento;
import com.ebix.easi.auto.model.entities.FotoVeiculo;
import com.ebix.easi.auto.model.entities.Laudo;
import com.ebix.easi.auto.model.entities.PropriedadesImagem;
import com.ebix.easi.auto.model.entities.PropriedadesTextoImagem;
import com.ebix.easi.auto.model.entities.User;
import com.ebix.easi.auto.model.entities.Vistoria;
import com.ebix.easi.auto.model.repository.FotoDocumentoRepository;
import com.ebix.easi.auto.model.repository.FotoVeiculoRepository;
import com.ebix.easi.auto.model.repository.VistoriaRepository;
import com.google.api.client.util.IOUtils;

@Service
public class VistoriaServiceImpl implements VistoriaService {

	@Autowired
	private VistoriaRepository vistoriaRepository;

	@Autowired
	private FotoDocumentoRepository fotoDocumentoRepository;

	@Autowired
	private FotoVeiculoRepository fotoVeiculoRepository;

	@Override
	public Page<Vistoria> findAll(Pageable paging) {
		Page<Vistoria> regionsPage = vistoriaRepository.findAll(paging);
		List<Vistoria> examples = regionsPage.getContent();

		return new PageImpl<Vistoria>(examples, paging, regionsPage.getTotalElements());
	}

	@Override
	@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
	public Vistoria save(Vistoria vistoria) {

			// Vistoria vistoriaSaved = vistoriaRepository.save(mockVistoria());

			ArrayList<FotoVeiculo> fotoVeiculos = montarObjetoFotos(vistoria);

			ArrayList<FotoDocumento> fotoDocumentos = montarObjetoFotosDocumento(vistoria);

			vistoria.setFotoVeiculo(fotoVeiculos);

			vistoria.setFotoDocumento(fotoDocumentos);

			vistoria.setStatus(StatusType.CONCLUIDA);

			Laudo laudovistoria = this.montarLaudo(vistoria, fotoVeiculos, fotoDocumentos);

			vistoria.setLaudo(laudovistoria);
			
			vistoria.setId(null);

			Vistoria vistoriaSaved = vistoriaRepository.save(vistoria);

			return vistoriaSaved;


	}

	private Laudo montarLaudo(Vistoria vistoria, ArrayList<FotoVeiculo> fotoVeiculos,
			ArrayList<FotoDocumento> fotoDocumentos) {

		// Iniciar Laudo
		Laudo laudo = new Laudo();
		laudo.setDataVistoria(new Date());

		ArrayList<Checklist> checklists = this.montarChecklist(vistoria, fotoVeiculos, fotoDocumentos);

		laudo.setChecklist(checklists);

		// laudo.setChecklist(checklist);

		return laudo;
	}

	private ArrayList<Checklist> montarChecklist(Vistoria vistoria, ArrayList<FotoVeiculo> fotoVeiculos,
			ArrayList<FotoDocumento> fotoDocumentos) {

		// checklist
		ArrayList<Checklist> checklists = new ArrayList<Checklist>();

		try {
			CampoAvaliacaoEnum[] camposEnums = CampoAvaliacaoEnum.values();

			// FOTOS DOCUMENTOS
			if (fotoDocumentos != null && fotoDocumentos.size() > 0) {

				for (FotoDocumento fotoDocumento : fotoDocumentos) {
					if (fotoDocumento != null && fotoDocumento.getPropriedadesTextoImagems() != null
							&& fotoDocumento.getPropriedadesTextoImagems().size() > 0) {

						for (PropriedadesTextoImagem proImagem : fotoDocumento.getPropriedadesTextoImagems()) {

							for (CampoAvaliacaoEnum campoAvaliacaoEnum : camposEnums) {

								if (campoAvaliacaoEnum.equals(proImagem.getAtributo())) {

									Checklist checklist = new Checklist();

									checklist.setAtributo(campoAvaliacaoEnum);
									// checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.CORRETO);

									checklist.setValorIA(proImagem.getDescricao());
									checklist.setDataInclusaoIA(proImagem.getDataInclusao());

									checklist.setValorSegurado(
											this.retornaGetVistoria(vistoria, campoAvaliacaoEnum, checklist));
									checklist.setDataInclusaoSegurado(vistoria.getData());

									if ((checklist.getValorIA() != null && checklist.getValorIA().length() > 0)
											&& (checklist.getValorSegurado() != null
													&& checklist.getValorSegurado().length() > 0)) {

										try {

											float similaridade = CheckSimilaridadeUtil.checkSimilarity(
													checklist.getValorIA(), checklist.getValorSegurado());

											if (1.00 == similaridade) {
												checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.CORRETO);
												checklist.setPorcentagemAssertividade(similaridade);
											} else if (0.80 <= similaridade) {
												checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.DIVERGENCIA);
												checklist.setPorcentagemAssertividade(similaridade);
											} else {
												checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.INCORRETO);
												checklist.setPorcentagemAssertividade(similaridade);
											}
										} catch (Exception e) {
											System.out.println(
													"Erro na verificação dos dados similares: " + e.getMessage());
											e.printStackTrace();
										}

									} else {

										checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.NAOAVALIADO);
										checklist.setPorcentagemAssertividade(0f);

									}

									checklists.add(checklist);

								}

							}

						}

					}

				}

			}

			boolean veiculoBatidoGrave = false;
			boolean veiculoColisao = false;
			
			// FOTOS VEICULOS
			if (fotoVeiculos != null && fotoVeiculos.size() > 0) {

				for (FotoVeiculo fotoVeiculo : fotoVeiculos) {

					if (fotoVeiculo != null && fotoVeiculo.getPropriedadesImagems() != null
							&& fotoVeiculo.getPropriedadesImagems().size() > 0) {

						boolean possuiVeiculo = false;
						boolean possuiParteCarro = false;

						for (PropriedadesImagem proImagem : fotoVeiculo.getPropriedadesImagems()) {

							if ("Crash".equalsIgnoreCase(proImagem.getDescricao()) && !veiculoBatidoGrave) {
								Checklist checklist = new Checklist();
								checklist.setValorIA("O veiculo com avaria devido a batida/acidente grave");
								checklist.setDataInclusaoIA(new Date());
								checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
								checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.ATENCAO);
								checklist.setValorSegurado("");
								checklist.setDataInclusaoSegurado(null);
								checklist.setPorcentagemAssertividade(proImagem.getPorcentagem());
								checklist.setAtributo(CampoAvaliacaoEnum.BATIDAGRAVE);
								checklists.add(checklist);
								veiculoBatidoGrave=true;
							} else if ("Collision".equalsIgnoreCase(proImagem.getDescricao()) && !veiculoColisao) {
								Checklist checklist = new Checklist();
								checklist.setValorIA("O veiculo com possivel avaria devido a batida");
								checklist.setDataInclusaoIA(new Date());
								checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
								checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.ATENCAO);
								checklist.setValorSegurado("");
								checklist.setDataInclusaoSegurado(null);
								checklist.setPorcentagemAssertividade(proImagem.getPorcentagem());
								checklist.setAtributo(CampoAvaliacaoEnum.COLISAO);
								checklists.add(checklist);
								veiculoColisao = true;
							}

							if ("Motor vehicle".equalsIgnoreCase(proImagem.getDescricao())
									|| "Vehicle".equalsIgnoreCase(proImagem.getDescricao())
									|| "Car".equalsIgnoreCase(proImagem.getDescricao())) {

								possuiVeiculo = true;

							}

							if ("Motor vehicle".equalsIgnoreCase(proImagem.getDescricao())
									|| "Vehicle".equalsIgnoreCase(proImagem.getDescricao())
									|| "Car".equalsIgnoreCase(proImagem.getDescricao())) {

								possuiParteCarro = true;

							}

						}

						if (!possuiParteCarro && !possuiVeiculo) {

							Checklist checklist = new Checklist();
							checklist.setValorIA("A foto" + this.auxiliarTipoFoto(fotoVeiculo.getTipoFoto())
									+ "pode não ser um veiculo ou parte do veiculo!");
							checklist.setDataInclusaoIA(new Date());
							checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
							checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.ATENCAO);
							checklist.setValorSegurado("");
							checklist.setDataInclusaoSegurado(null);
							checklist.setPorcentagemAssertividade(0f);
							checklist.setAtributo(CampoAvaliacaoEnum.FOTONAOIDENTIFICADA);
							checklists.add(checklist);

						}

					}

					if (fotoVeiculo != null && fotoVeiculo.getCoresImagem() != null
							&& fotoVeiculo.getCoresImagem().size() > 0) {

						if (vistoria.getAutomovel() != null && !"".equals(vistoria.getAutomovel().getCor())) {

							boolean corEncontrada = false;
							Collections.sort(fotoVeiculo.getCoresImagem());
							int count = 1;
							for (CoresImagem corImagem : fotoVeiculo.getCoresImagem()) {

								if (vistoria.getAutomovel().getCor().equals(corImagem.getDescricaoCor())) {
									corEncontrada = true;
								}

								if (count < 2) {
									break;
								} else {

								}

							}

							if (!corEncontrada) {

								if (!FotoType.CHASSI.equals(fotoVeiculo.getTipoFoto())
										&& !FotoType.MOTOR.equals(fotoVeiculo.getTipoFoto())
										&& !FotoType.PAINEL.equals(fotoVeiculo.getTipoFoto())
										&& !FotoType.PORTAMALA.equals(fotoVeiculo.getTipoFoto())) {

									Checklist checklist = new Checklist();
									checklist.setValorIA(
											"A cor da foto" + this.auxiliarTipoFoto(fotoVeiculo.getTipoFoto())
													+ "não é a mesma digitada pelo usuário!");
									checklist.setDataInclusaoIA(new Date());
									checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
									checklist.setComparacaoCamposEnum(ComparacaoCamposEnum.ATENCAO);
									checklist.setValorSegurado("");
									checklist.setDataInclusaoSegurado(null);
									checklist.setPorcentagemAssertividade(0f);
									checklist.setAtributo(CampoAvaliacaoEnum.CORDIFERENTE);
									checklists.add(checklist);

								}

							}

						}

					}

				}

			}

		} catch (Exception e) {
			System.out.println("Erro ao montar checklist: " + e.getMessage());
			e.printStackTrace();
		}

		return checklists;
	}

	private String auxiliarTipoFoto(FotoType fotoType) {

		if (FotoType.DIANTEIRADIREITA.equals(fotoType)) {
			return " referente a dianteira direita ";
		} else if (FotoType.DIANTEIRAESQUERDA.equals(fotoType)) {
			return " referente a dianteira esquerda ";
		} else if (FotoType.TRASEIRADIREITA.equals(fotoType)) {
			return " referente a traseira direita ";
		} else if (FotoType.TRASEIRAESQUERDA.equals(fotoType)) {
			return " referente a traseira direita ";
		} else if (FotoType.CHASSI.equals(fotoType)) {
			return " referente ao chassi ";
		} else if (FotoType.PAINEL.equals(fotoType)) {
			return " referente ao painel ";
		} else if (FotoType.MOTOR.equals(fotoType)) {
			return " referente ao motor ";
		} else if (FotoType.PORTAMALA.equals(fotoType)) {
			return " referente ao porta mala ";
		}
		return " ";

	}

	/**
	 * Método responsavel por retornar o atributo de vistoria e settar o tipo de
	 * campo que a validação pertence
	 *
	 * @param vistoria
	 * @param campoAvaliacaoEnum
	 * @param checklist
	 * @return
	 */
	private String retornaGetVistoria(Vistoria vistoria, CampoAvaliacaoEnum campoAvaliacaoEnum, Checklist checklist) {

		if (vistoria.getDocumentosPessoais() != null) {

			if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.NOME)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
				return vistoria.getDocumentosPessoais().getNome();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CPFCNPJ)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
				return vistoria.getDocumentosPessoais().getCpfCnpj();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.EMAIL)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
				return vistoria.getDocumentosPessoais().getEmail();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.DATANASCIMENTO)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
				return vistoria.getDocumentosPessoais().getDataNascimento();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.TELEFONE)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
				return vistoria.getDocumentosPessoais().getTelefone();
			}

			if (vistoria.getDocumentosPessoais().getEndereco() != null) {

				if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CEP)) {
					checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
					return vistoria.getDocumentosPessoais().getEndereco().getCep();
				} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.LOGRADOURO)) {
					checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
					return vistoria.getDocumentosPessoais().getEndereco().getLogradouro();
				} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.NUMERO)) {
					checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
					return vistoria.getDocumentosPessoais().getEndereco().getNumero();
				} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.COMPLEMENTO)) {
					checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
					return vistoria.getDocumentosPessoais().getEndereco().getComplemento();
				} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.BAIRRO)) {
					checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
					return vistoria.getDocumentosPessoais().getEndereco().getBairro();
				} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CIDADE)) {
					checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
					return vistoria.getDocumentosPessoais().getEndereco().getCidade();
				} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.UF)) {
					checklist.setTipoCampoEnum(TipoCampoEnum.DOCUMENTO);
					return vistoria.getDocumentosPessoais().getEndereco().getUf();
				}

			}

		}

		if (vistoria.getAutomovel() != null) {

			if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.ANOFABRICACAO)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getAnoFabricacao();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.TIPOVEICULO)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getTipoVeiculo();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.PLACA)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getPlaca();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CHASSI)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getChassi();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.RENAVAM)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getRenavam();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.FABRICANTE)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getFabricante();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.COMBUSTIVEL)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getCombustivel();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.ANOFABRICACAO)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getAnoFabricacao();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.UFPLACA)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getUfPlaca();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.REGIAOGEOGRAFICA)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getRegiaoGeografica();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.PAIS)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getPais();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CILINDRADAS)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getCilindradas();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.LOCALIZACAOFABRICA)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getLocalizacaoFabrica();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.UFPLACA)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getUfPlaca();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.COR)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getCor();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.MARCAMODELO)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getMarcaModelo();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CAPACIDADEPASSAGEIROS)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getCapacidade_passageiros();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.PROCEDENCIA)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getProcedencia();
			} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.KM)) {
				checklist.setTipoCampoEnum(TipoCampoEnum.VEICULO);
				return vistoria.getAutomovel().getKm();
			}

		}

		// uso types
		if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.VEICULOUSADOTAXI)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.USO);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.RESTRICAOROUBO)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.USO);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.RESTRICAOJUDICIAL)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.USO);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.AUTORIDADESESPECIAIS)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.USO);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.RECALL)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.USO);
			return "";
		}

		// Outros types
		if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.EXCESSOAVARIAS)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.IPVAATRASADO)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.DUTATRASADO)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CHASSIREMARCADO)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.RENAVAMINVALIDO)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CHASSIADULTERADO)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.CHASSIILEGIVEL)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.VEICULOFORAMUNICIPIORESIDENCIA)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.HISTORICOACIDENTE)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		} else if (campoAvaliacaoEnum.equals(CampoAvaliacaoEnum.NUMEROMOTORILEGIVEL)) {
			checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
			return "";
		}

		// se não retornou nada entra na categoria outros
		checklist.setTipoCampoEnum(TipoCampoEnum.OUTROS);
		return "";

	}

	private ArrayList<FotoDocumento> montarObjetoFotosDocumento(Vistoria vistoria) {

		ArrayList<FotoDocumento> fotoDocumentos = new ArrayList<FotoDocumento>();

		if (vistoria.getIdCNH() != null && vistoria.getIdCNH() > 0) {

			Optional<FotoDocumento> documento = fotoDocumentoRepository.findById(vistoria.getIdCNH());
//			FotoDocumento documento = new FotoDocumento();
//			documento.setId(vistoria.getIdCNH());
			fotoDocumentos.add(documento.get());
		}

		if (vistoria.getIdCRLV() != null && vistoria.getIdCRLV() > 0) {
			Optional<FotoDocumento> documento = fotoDocumentoRepository.findById(vistoria.getIdCRLV());
//			FotoDocumento documento = new FotoDocumento();
//			documento.setId(vistoria.getIdCRLV());
			fotoDocumentos.add(documento.get());
		}

		return fotoDocumentos;
	}

	private ArrayList<FotoVeiculo> montarObjetoFotos(Vistoria vistoria) {
		ArrayList<FotoVeiculo> fotoVeiculos = new ArrayList<FotoVeiculo>();

		if (vistoria.getIdfotoDianteiraEsquerda() != null && vistoria.getIdfotoDianteiraEsquerda() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoDianteiraEsquerda());
			//FotoVeiculo fotoVeiculoDianteiraEsquerda = new FotoVeiculo();
			//fotoVeiculoDianteiraEsquerda.setId(vistoria.getIdfotoDianteiraEsquerda());
			fotoVeiculos.add(fotoVeiculo.get());

		}

		if (vistoria.getIdfotoDianteiraDireita() != null && vistoria.getIdfotoDianteiraDireita() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoDianteiraDireita());
			//FotoVeiculo fotoVeiculoDianteiraDireita = new FotoVeiculo();
			//fotoVeiculoDianteiraDireita.setId(vistoria.getIdfotoDianteiraDireita());
			fotoVeiculos.add(fotoVeiculo.get());
		}

		if (vistoria.getIdfotoTraseiraEsquerda() != null && vistoria.getIdfotoTraseiraEsquerda() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoTraseiraEsquerda());
			//FotoVeiculo fotoVeiculoTraseiraEsquerda = new FotoVeiculo();
			//fotoVeiculoTraseiraEsquerda.setId(vistoria.getIdfotoTraseiraEsquerda());
			fotoVeiculos.add(fotoVeiculo.get());
		}

		if (vistoria.getIdfotoTraseiraDireita() != null && vistoria.getIdfotoTraseiraDireita() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoTraseiraDireita());
			//FotoVeiculo fotoVeiculoTraseiraDireita = new FotoVeiculo();
			//fotoVeiculoTraseiraDireita.setId(vistoria.getIdfotoTraseiraDireita());
			fotoVeiculos.add(fotoVeiculo.get());
		}

		if (vistoria.getIdfotoChassi() != null && vistoria.getIdfotoChassi() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoChassi());
			//FotoVeiculo fotoVeiculoChassi = new FotoVeiculo();
			//fotoVeiculoChassi.setId(vistoria.getIdfotoChassi());
			fotoVeiculos.add(fotoVeiculo.get());
		}

		if (vistoria.getIdfotoMotor() != null && vistoria.getIdfotoMotor() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoMotor());
			//FotoVeiculo fotoVeiculoMotor = new FotoVeiculo();
			//fotoVeiculoMotor.setId(vistoria.getIdfotoMotor());
			fotoVeiculos.add(fotoVeiculo.get());
		}

		if (vistoria.getIdfotoPainel() != null && vistoria.getIdfotoPainel() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoPainel());
			//FotoVeiculo fotoVeiculoPainel = new FotoVeiculo();
			//fotoVeiculoPainel.setId(vistoria.getIdfotoPainel());
			fotoVeiculos.add(fotoVeiculo.get());
		}

		if (vistoria.getIdfotoPortaMala() != null && vistoria.getIdfotoPortaMala() > 0) {

			Optional<FotoVeiculo> fotoVeiculo = fotoVeiculoRepository.findById(vistoria.getIdfotoPortaMala());
			//FotoVeiculo fotoVeiculoPortaMala = new FotoVeiculo();
			//fotoVeiculoPortaMala.setId(vistoria.getIdfotoPortaMala());
			fotoVeiculos.add(fotoVeiculo.get());
		}

		return fotoVeiculos;
	}

	private Vistoria mockVistoria() {

		Vistoria vistoria = new Vistoria();
		vistoria.setData(new Date());
		vistoria.setNome("Vistoria auto");
		vistoria.setStatus(StatusType.CONCLUIDA);

		DocumentosPessoais documentosPessoais = new DocumentosPessoais();
		documentosPessoais.setCpfCnpj("05808791782");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataNasci = "19-10-1988";
		documentosPessoais.setDataNascimento(dataNasci);
		documentosPessoais.setEmail("segurado@email.com");

		Endereco endereco = new Endereco();
		endereco.setBairro("Centro");
		endereco.setCep("21833320");
		endereco.setCidade("Rio de Janeiro");
		endereco.setComplemento("Predio A");
		endereco.setLogradouro("Rua Visconde de inhauma");
		endereco.setNumero("37");
		endereco.setUf("RJ");
		documentosPessoais.setEndereco(endereco);
		documentosPessoais.setNome("Segurado de Exemplo");
		documentosPessoais.setTelefone("986966062");

		vistoria.setDocumentosPessoais(documentosPessoais);

		Automovel automovel = new Automovel();
		automovel.setAnoFabricacao("2000");
		automovel.setAnoModelo("1999");
		automovel.setCapacidade_passageiros("5");
		automovel.setChassi("45sd456a4da5da");
		automovel.setCilindradas("200");
		automovel.setCombustivel("Gasolina");
		automovel.setCor("Prata");
		automovel.setEspecie("");
		automovel.setExiste_recall(false);
		automovel.setFabricante("Fiat");
		automovel.setKm("30000");
		automovel.setLocalizacaoFabrica("RJ");
		automovel.setMarcaModelo("SIENA");
		automovel.setPais("Brasil");
		automovel.setPertence_consulado_autoridades_especiais(false);
		automovel.setPlaca("LPM5333");
		automovel.setPotencia("500 cavalos");
		automovel.setProcedencia("Leilao");
		automovel.setRegiaoGeografica("Rio de janeiro");
		automovel.setRenavam("7884917489");
		automovel.setRestricao_roubo_furto(false);
		automovel.setTipoVeiculo("passeio");
		automovel.setUfPlaca("RJ");
		automovel.setVeiculo_usado_como_taxi(false);

		vistoria.setAutomovel(automovel);

		User userSegurado = new User();
		userSegurado.setId(1L);

		vistoria.setUsuarioSegurado(userSegurado);

		FotoVeiculo fotoVeiculo = new FotoVeiculo();
		fotoVeiculo.setName("fotoQualquer");
		fotoVeiculo.setUrl("URLFoto://url.com");
		PropriedadesImagem propriedadesImagem = new PropriedadesImagem();
		propriedadesImagem.setPorcentagem(25F);
		propriedadesImagem.setDescricao("Carro");
		CoresImagem coresImagem = new CoresImagem();
		coresImagem.setDescricaoCor("Azul");
		coresImagem.setRgbBlue(0.1911F);
		coresImagem.setRgbGreen(0.1911F);
		coresImagem.setRgbRed(0.1911F);
		coresImagem.setScore(100F);
		ArrayList<PropriedadesImagem> propriedadesImagems = new ArrayList<>();
		propriedadesImagems.add(propriedadesImagem);
		ArrayList<CoresImagem> coresImagems = new ArrayList<>();
		coresImagems.add(coresImagem);
		fotoVeiculo.setCoresImagem(coresImagems);
		fotoVeiculo.setPropriedadesImagems(propriedadesImagems);
		fotoVeiculo.setTipoFoto(FotoType.DIANTEIRADIREITA);

		vistoria.getFotoVeiculo().add(fotoVeiculo);

		return vistoria;

	}

	@Override
	public Page<Vistoria> findAllByStatus(StatusType status, Pageable paging) {
		
		Page<Vistoria> regionsPage = null;
		
		if (status.equals(StatusType.ALL)) {
			regionsPage = vistoriaRepository.findAll(paging);
		}else {
			regionsPage = vistoriaRepository.findAllByStatus(status, paging);
		}
		
		List<Vistoria> examples = regionsPage.getContent();
		
		//this.montarFotosByteArray(examples);

		return new PageImpl<Vistoria>(examples, paging, regionsPage.getTotalElements());
	}

	private void montarFotosByteArray(List<Vistoria> examples) {
		
		for (Vistoria vistoria : examples) {
			
			for (FotoVeiculo fotoVeiculo : vistoria.getFotoVeiculo()) {
				
				try {
				
				byte[] bFile = Files.readAllBytes(new File(fotoVeiculo.getUrl()).toPath());
				//InputStream imagemByteArray = getClass().getResourceAsStream(fotoVeiculo.getUrl());
				
				fotoVeiculo.setImagemByteArray(bFile);
				
				} catch (IOException e) {
					System.out.println("Ocorreu um erro para renderizar a foto do documento para byte array");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Ocorreu um erro para renderizar a foto do documento para byte array");
					e.printStackTrace();
				}
				
			}
			
			for (FotoDocumento fotoDocumento : vistoria.getFotoDocumento()) {
				
				try {
				
				byte[] bFile = Files.readAllBytes(new File(fotoDocumento.getUrl()).toPath());
					
				fotoDocumento.setImagemByteArray(bFile);
				
				
				} catch (IOException e) {
					System.out.println("Ocorreu um erro para renderizar a foto do veiculo para byte array");
					e.printStackTrace();
				} catch (Exception e) {
					System.out.println("Ocorreu um erro para renderizar a foto do veiculo para byte array");
					e.printStackTrace();
				}
				
			}
			
		}
		
	}

	@Override
	public Vistoria findById(Long id) {
		Optional<Vistoria> vistoria = vistoriaRepository.findById(id);
		return vistoria.get();
	}

	@Override
	@Transactional(rollbackFor = {Exception.class, RuntimeException.class})
	public Vistoria updateLaudoVistoria(Vistoria vistoriaBase, Vistoria vistoria) {
				
		vistoriaBase.getLaudo().setDataRevisao(new Date());
		
		for (Checklist checklistBase : vistoriaBase.getLaudo().getChecklist()) {
			
			for (Checklist checklist : vistoria.getLaudo().getChecklist()) {
				
				if (checklistBase.getId().equals(checklist.getId())) {
					
					checklistBase.setValorVistoriador(checklist.getValorVistoriador());
					checklistBase.setDataInclusaoVistoriador(new Date());
					checklistBase.setObservacao(checklist.getObservacao());
					
				}
				
			}
	
		}
		
		vistoriaBase.setStatus(StatusType.FINALIZADA);
		
		Vistoria vistoriaSaved = vistoriaRepository.save(vistoriaBase);
		
		
		return vistoriaSaved;
	}

}
