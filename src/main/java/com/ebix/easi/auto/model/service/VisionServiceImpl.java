package com.ebix.easi.auto.model.service;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.hibernate.secure.spi.IntegrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gcp.vision.CloudVisionTemplate;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ebix.easi.auto.model.api.enums.FotoType;
import com.ebix.easi.auto.model.api.util.CheckSimilaridadeUtil;
import com.ebix.easi.auto.model.api.util.ColorUtils;
import com.ebix.easi.auto.model.entities.CoresImagem;
import com.ebix.easi.auto.model.entities.FotoVeiculo;
import com.ebix.easi.auto.model.entities.PropriedadesImagem;
import com.ebix.easi.auto.model.entities.dtos.RetornoCRLVDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoImagemDto;
import com.ebix.easi.auto.model.entities.dtos.RetornoReciboDto;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.ColorInfo;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature.Type;

@Service
public class VisionServiceImpl implements VisionService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private CloudVisionTemplate cloudVisionTemplate;

	@Override
	public RetornoCRLVDto registrarCRLV(MultipartFile file, FotoType tipo) {

		File folder = null;
		File tempFile = null;
		BufferedImage img = null;

		try {

			tempFile = guardarImagem(file, false);

			String response = this.cloudVisionTemplate
					.extractTextFromImage(this.resourceLoader.getResource("file:" + tempFile.getAbsolutePath()));

			RetornoCRLVDto retornoCRVL = extraitRetornoCRLV(response);

			retornoCRVL.setImageName(tempFile.getName());
			retornoCRVL.setTipoFoto(tipo);
			retornoCRVL.setUrl(tempFile.getAbsolutePath());

			return retornoCRVL;

		} catch (Exception e) {

			System.out.println("Ocorreu um erro na verificação da imagem: " + e.getMessage());
			throw new IntegrationException(e.getMessage());

		} finally {

			deletarArquivoTemporario(file, tempFile);

		}

	}

	@Override
	public RetornoReciboDto registrarCNH(MultipartFile file, FotoType tipo) {

		File folder = null;
		File documentoLocal = null;
		BufferedImage img = null;

		try {

			documentoLocal = guardarImagem(file, false);

			String response = this.cloudVisionTemplate
					.extractTextFromImage(this.resourceLoader.getResource("file:" + documentoLocal.getAbsolutePath()));

			RetornoReciboDto retornoCRVL = extrairMontarObjetoCNH(response);

			retornoCRVL.setImageName(documentoLocal.getName());
			retornoCRVL.setTipoFoto(tipo);
			retornoCRVL.setUrl(documentoLocal.getAbsolutePath());


			return retornoCRVL;

		} catch (Exception e) {

			System.out.println("Ocorreu um erro na verificação da imagem: " + e.getMessage());
			throw new IntegrationException(e.getMessage());

		} finally {

			deletarArquivoTemporario(file, documentoLocal);

		}

	}

	@Override
	public FotoVeiculo registrarImagem(MultipartFile file, FotoType tipo) {

		File imagemVeiculo = null;

		try {

			imagemVeiculo = guardarImagem(file, false);

			AnnotateImageResponse response = this.cloudVisionTemplate
					.analyzeImage(this.resourceLoader.getResource("file:" + imagemVeiculo.getAbsolutePath()), Type.LABEL_DETECTION);

			FotoVeiculo fotoVeiculo = new FotoVeiculo();

			fotoVeiculo.setPropriedadesImagems(extrairPropriedatesImagem(response));

			//Testar cores
			AnnotateImageResponse response2 = this.cloudVisionTemplate
					.analyzeImage(this.resourceLoader.getResource("file:" + imagemVeiculo.getAbsolutePath()), Type.IMAGE_PROPERTIES);

			ArrayList<CoresImagem> coresImagems = extrairCoresImagem(response2);

			fotoVeiculo.setCoresImagem(coresImagems);

			fotoVeiculo.setName(imagemVeiculo.getName());
			fotoVeiculo.setTipoFoto(tipo);
			fotoVeiculo.setUrl(imagemVeiculo.getAbsolutePath());

			//System.out.println(response2.toString());

			return fotoVeiculo;

		} catch (Exception e) {

			System.out.println("Ocorreu um erro na verificação da imagem: " + e.getMessage());
			throw new IntegrationException(e.getMessage());

		} finally {

			try {
				file.getInputStream().close();
			} catch (IOException e1) {
				System.out.println("Error to close file: " + e1.getMessage());
			}

		}

	}

	@Override
	public RetornoImagemDto verificarPropriedadeImagem(MultipartFile file) {

		File tempFile = null;

		try {

			tempFile = guardarImagem(file, true);

			AnnotateImageResponse response = this.cloudVisionTemplate
					.analyzeImage(this.resourceLoader.getResource("file:" + tempFile.getAbsolutePath()), Type.LABEL_DETECTION);

			RetornoImagemDto imagemDto = new RetornoImagemDto();

			imagemDto.setPropriedadesImagems(extrairPropriedatesImagem(response));

			//Testar cores
			AnnotateImageResponse response2 = this.cloudVisionTemplate
					.analyzeImage(this.resourceLoader.getResource("file:" + tempFile.getAbsolutePath()), Type.IMAGE_PROPERTIES);

			ArrayList<CoresImagem> coresImagems = extrairCoresImagem(response2);

			imagemDto.setCoresImagem(coresImagems);

			//System.out.println(response2.toString());

			return imagemDto;

		} catch (Exception e) {

			System.out.println("Ocorreu um erro na verificação da imagem: " + e.getMessage());
			throw new IntegrationException(e.getMessage());

		} finally {

			deletarArquivoTemporario(file, tempFile);

		}

	}

	private ArrayList<CoresImagem> extrairCoresImagem(AnnotateImageResponse response2) {
		List<ColorInfo> colors = response2.getImagePropertiesAnnotation().getDominantColors().getColorsList();

		ArrayList<CoresImagem> coresImagems = new ArrayList<>();

		for (ColorInfo colorInfo : colors) {

			CoresImagem coresImagem = new CoresImagem();


			coresImagem.setRgbBlue(colorInfo.getColor().getBlue());
			coresImagem.setRgbGreen(colorInfo.getColor().getGreen());
			coresImagem.setRgbRed(colorInfo.getColor().getRed());
			coresImagem.setScore(colorInfo.getScore());

			//Util pegar nome da cor
			Color color = new Color(colorInfo.getColor().getRed()/255, colorInfo.getColor().getGreen()/255, colorInfo.getColor().getBlue()/255);

			coresImagem.setDescricaoCor(ColorUtils.getColorNameFromHex(ColorUtils.colorToHex(color)));

			coresImagems.add(coresImagem);
		}

		Collections.sort(coresImagems);
		return coresImagems;
	}

	private List<PropriedadesImagem> extrairPropriedatesImagem(AnnotateImageResponse response) {

		ArrayList<PropriedadesImagem> propriedadesImagensDtos = new ArrayList<PropriedadesImagem>();

		Map<String, Float> imageLabels =
			    response.getLabelAnnotationsList()
			        .stream()
			        .collect(
			            Collectors.toMap(
			                EntityAnnotation::getDescription,
			                EntityAnnotation::getScore,
			                (u, v) -> {
			                  throw new IllegalStateException(String.format("Duplicate key %s", u));
			                },
			                LinkedHashMap::new));

		for (Entry<String, Float> pair : imageLabels.entrySet()) {

			PropriedadesImagem propriedadeImagem = new PropriedadesImagem();

			propriedadeImagem.setDescricao(pair.getKey());
			propriedadeImagem.setPorcentagem(pair.getValue());

			propriedadesImagensDtos.add(propriedadeImagem);

		}

		//System.out.println(imageLabels.toString());

		return propriedadesImagensDtos;
	}

	@Override
	public RetornoReciboDto verificarImagemCNH(MultipartFile file) {

		File folder = null;
		File tempFile = null;
		BufferedImage img = null;

		try {

			tempFile = guardarImagem(file, true);

			String response = this.cloudVisionTemplate
					.extractTextFromImage(this.resourceLoader.getResource("file:" + tempFile.getAbsolutePath()));

			RetornoReciboDto retornoCRVL = extrairMontarObjetoCNH(response);


			/*
			 * System.out.println("/n/n");
			 *
			 * String lines[] = response.split("\\r?\\n");
			 *
			 * for (String string : lines) {
			 *
			 * System.out.println(string);
			 *
			 * }
			 */


			return retornoCRVL;

		} catch (Exception e) {

			System.out.println("Ocorreu um erro na verificação da imagem: " + e.getMessage());
			throw new IntegrationException(e.getMessage());

		} finally {

			deletarArquivoTemporario(file, tempFile);

		}

	}

	private RetornoReciboDto extrairMontarObjetoCNH(String response) {
		RetornoReciboDto retornoCRVL = new RetornoReciboDto();

		try {

			if (!"".equals(response)) {

				Pattern cpfCnpjPattern = Pattern.compile(
						"(^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2})|(^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2})");

				Pattern cepPattern = Pattern.compile(
						"^[cC]{1}[eE]{1}[pP]{1}\\s{1,}[0-9]{8}");

				Pattern cepPuroPattern = Pattern.compile(
						"[0-9]{8}$");

				String lines[] = response.split("\\r?\\n");

				boolean texto_nome_endereco = false;

				for (String string : lines) {

					if (null == retornoCRVL.getCpfCnpj() || "".equals(retornoCRVL.getCpfCnpj())) {

						Matcher cpfCnpjMatcher = cpfCnpjPattern.matcher(string);
						if (cpfCnpjMatcher.find()) {
							retornoCRVL.setCpfCnpj(cpfCnpjMatcher.group());
						}

					}

					if (checkNotNull(string)
							&& (0.80 <= CheckSimilaridadeUtil.checkSimilarity("NOME", string)|| 0.80 <= CheckSimilaridadeUtil.checkSimilarity("nome", string))) {
						texto_nome_endereco = true;
						continue;
					}

					if (texto_nome_endereco) {
						retornoCRVL.setNome(string);
						texto_nome_endereco = false;
						continue;
					}


				}

				/*
				 * System.out.println("/n/n");
				 *
				 * for (String string : lines) {
				 *
				 * System.out.println(string);
				 *
				 * }
				 */

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retornoCRVL;
	}

	@Override
	public RetornoReciboDto verificarImagemRecibo(MultipartFile file) {

		File folder = null;
		File tempFile = null;
		BufferedImage img = null;

		try {

			tempFile = guardarImagem(file, true);

			String response = this.cloudVisionTemplate
					.extractTextFromImage(this.resourceLoader.getResource("file:" + tempFile.getAbsolutePath()));

			RetornoReciboDto retornoCRVL = extrairMontarObjetoRecibo(response);

			return retornoCRVL;

		} catch (Exception e) {

			System.out.println("Ocorreu um erro na verificação da imagem: " + e.getMessage());
			throw new IntegrationException(e.getMessage());

		} finally {

			deletarArquivoTemporario(file, tempFile);

		}

	}

	private RetornoReciboDto extrairMontarObjetoRecibo(String response) {
		RetornoReciboDto retornoCRVL = new RetornoReciboDto();

		try {

			if (!"".equals(response)) {

				Pattern cpfCnpjPattern = Pattern.compile(
						"(^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2})|(^[0-9]{2}\\.[0-9]{3}\\.[0-9]{3}\\/[0-9]{4}\\-[0-9]{2})");

				Pattern cepPattern = Pattern.compile(
						"^[cC]{1}[eE]{1}[pP]{1}\\s{1,}[0-9]{8}");

				Pattern cepPuroPattern = Pattern.compile(
						"[0-9]{8}$");

				String lines[] = response.split("\\r?\\n");

				boolean texto_nome_endereco = false;
				boolean proximo_bloco = false;
				boolean proximo_bloco_logradouro = false;

				for (String string : lines) {

					if (null == retornoCRVL.getCpfCnpj() || "".equals(retornoCRVL.getCpfCnpj())) {

						Matcher cpfCnpjMatcher = cpfCnpjPattern.matcher(string);
						if (cpfCnpjMatcher.find()) {
							retornoCRVL.setCpfCnpj(cpfCnpjMatcher.group());
						}

					}

					if (checkNotNull(string)
							&& 0.80 <= CheckSimilaridadeUtil.checkSimilarity("NOME/ENDEREÇO", string)) {
						texto_nome_endereco = true;
						continue;
					}

					if (texto_nome_endereco) {
						retornoCRVL.setNome(string);
						proximo_bloco_logradouro = true;
						texto_nome_endereco = false;
						continue;
					}

					if (proximo_bloco_logradouro) {
						retornoCRVL.setLogradouro(string);
						proximo_bloco_logradouro = false;
						continue;
					}


					if (null == retornoCRVL.getCep() || "".equals(retornoCRVL.getCep())) {

						Matcher cepMatcher = cepPattern.matcher(string);
						if (cepMatcher.find()) {
							Matcher cepPuroMatcher = cepPuroPattern.matcher(string);
							if (cepPuroMatcher.find()) {
								retornoCRVL.setCep(cepPuroMatcher.group());
							}
						}

					}

				}

				System.out.println("/n/n");

				for (String string : lines) {

					System.out.println(string);

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retornoCRVL;
	}

	private boolean checkNotNull(String string) {
		return null != string && !"".equals(string);
	}

	@Override
	public RetornoCRLVDto verificarImagemCRLV(MultipartFile file) {

		File folder = null;
		File tempFile = null;
		BufferedImage img = null;

		try {

			tempFile = guardarImagem(file, true);

			String response = this.cloudVisionTemplate
					.extractTextFromImage(this.resourceLoader.getResource("file:" + tempFile.getAbsolutePath()));

			RetornoCRLVDto retornoCRVL = extraitRetornoCRLV(response);

			return retornoCRVL;

		} catch (Exception e) {

			System.out.println("Ocorreu um erro na verificação da imagem: " + e.getMessage());
			throw new IntegrationException(e.getMessage());

		} finally {

			deletarArquivoTemporario(file, tempFile);

		}

	}

	private RetornoCRLVDto extraitRetornoCRLV(String response) {

		RetornoCRLVDto retornoCRVL = new RetornoCRLVDto();
		try {

			if (!"".equals(response)) {

				Pattern chassiPattern = Pattern.compile(
						"^[A0-Z9]{16,}");

				Pattern renavamPattern = Pattern.compile(
						"^[0-9]{11}");

				Pattern placaPattern = Pattern.compile(
						"^[A-Z]{3}[0-9]{4}");

				Pattern placaUfPattern = Pattern.compile(
						"^[A-Z]{3}[0-9]{4}\\/[A-Z]{2}");

				Pattern ufPattern = Pattern.compile(
						"[A-Z]{2}$");


				String lines[] = response.split("\\r?\\n");

				for (String string : lines) {

					if (null == retornoCRVL.getChassi() || "".equals(retornoCRVL.getChassi())) {

						Matcher chassiMatcher = chassiPattern.matcher(string);
						if (chassiMatcher.find()) {
							retornoCRVL.setChassi(chassiMatcher.group());
						}

					}

					if (null == retornoCRVL.getRenavam() || "".equals(retornoCRVL.getRenavam())) {

						Matcher renavamMatcher = renavamPattern.matcher(string);
						if (renavamMatcher.find()) {
							retornoCRVL.setRenavam(renavamMatcher.group());
						}

					}

					if (null == retornoCRVL.getPlaca() || "".equals(retornoCRVL.getPlaca())) {

						Matcher placaMatcher = placaPattern.matcher(string);
						if (placaMatcher.find()) {
							retornoCRVL.setPlaca(placaMatcher.group());
						}

					}

					if (null == retornoCRVL.getUf() || "".equals(retornoCRVL.getUf())) {

						Matcher placaUfMatcher = placaUfPattern.matcher(string);
						if (placaUfMatcher.find()) {

							Matcher ufMatcher = ufPattern.matcher(string);

							if (ufMatcher.find()) {

								retornoCRVL.setUf(ufMatcher.group());

							}

						}

					}

				}

				/*
				 * System.out.println("/n/n");
				 *
				 * for (String string : lines) {
				 *
				 * System.out.println(string);
				 *
				 * }
				 */

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retornoCRVL;

	}

	private File guardarImagem(MultipartFile file, boolean temporaria) throws IOException {
		File folder;
		File tempFile;
		BufferedImage img;
		img = ImageIO.read(file.getInputStream());

		String uniqueImageID = UUID.randomUUID().toString();

		if (temporaria) {
			folder = new File("C:\\EASI\\imagensTemp\\");
		}else {
			folder = new File("C:\\EASI\\imagens\\");
		}

		tempFile = File.createTempFile(uniqueImageID, "." + file.getOriginalFilename().split("\\.")[1], folder);

		ImageIO.write(img, file.getOriginalFilename().split("\\.")[1], tempFile);
		return tempFile;

	}

	private void deletarArquivoTemporario(MultipartFile file, File tempFile) {
		try {
			file.getInputStream().close();
		} catch (IOException e1) {
			System.out.println("Error to close file: " + e1.getMessage());
		}

		try {
			// System.out.println(tempFile.delete()); //Delete on runtime exit
			Files.deleteIfExists(Paths.get(tempFile.getAbsolutePath()));
		} catch (NoSuchFileException e) {
			System.out.println("No such file/directory exists");
		} catch (DirectoryNotEmptyException e) {
			System.out.println("Directory is not empty.");
		} catch (IOException e) {
			System.out.println("Invalid permissions.");
		}
	}

}
