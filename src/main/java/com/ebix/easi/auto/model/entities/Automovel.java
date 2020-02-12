package com.ebix.easi.auto.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "automovel")
public class Automovel implements Serializable{

	private static final long serialVersionUID = 8147254190610322447L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_automovel", unique = true, nullable = false)
	private Long id;

	// Dados do Veiculo
	private String tipoVeiculo;
	private String placa;
	private String chassi;
	private String renavam;

	// Dados para vistorias de veiculos
	private String regiaoGeografica;
	private String pais;
	private String fabricante;
	private String combustivel;
	private String cilindradas;
	private String anoFabricacao;
	private String localizacaoFabrica;

	// Informações detran
	// chassi
	// Placa

	private String ufPlaca;
	private String cor;
	private String marcaModelo;
	private String anoModelo;
	private String especie;
	private String potencia;
	private String capacidade_passageiros;
	private String procedencia;

	private String km;
	private boolean veiculo_usado_como_taxi;
	private boolean restricao_roubo_furto;
	private boolean restrito_judicialmente;
	private boolean pertence_consulado_autoridades_especiais;
	private boolean existe_recall;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipoVeiculo() {
		return tipoVeiculo;
	}
	public void setTipoVeiculo(String tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getChassi() {
		return chassi;
	}
	public void setChassi(String chassi) {
		this.chassi = chassi;
	}
	public String getRenavam() {
		return renavam;
	}
	public void setRenavam(String renavam) {
		this.renavam = renavam;
	}
	public String getRegiaoGeografica() {
		return regiaoGeografica;
	}
	public void setRegiaoGeografica(String regiaoGeografica) {
		this.regiaoGeografica = regiaoGeografica;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getCombustivel() {
		return combustivel;
	}
	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}
	public String getCilindradas() {
		return cilindradas;
	}
	public void setCilindradas(String cilindradas) {
		this.cilindradas = cilindradas;
	}
	public String getAnoFabricacao() {
		return anoFabricacao;
	}
	public void setAnoFabricacao(String anoFabricacao) {
		this.anoFabricacao = anoFabricacao;
	}
	public String getLocalizacaoFabrica() {
		return localizacaoFabrica;
	}
	public void setLocalizacaoFabrica(String localizacaoFabrica) {
		this.localizacaoFabrica = localizacaoFabrica;
	}
	public String getUfPlaca() {
		return ufPlaca;
	}
	public void setUfPlaca(String ufPlaca) {
		this.ufPlaca = ufPlaca;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public String getMarcaModelo() {
		return marcaModelo;
	}
	public void setMarcaModelo(String marcaModelo) {
		this.marcaModelo = marcaModelo;
	}
	public String getAnoModelo() {
		return anoModelo;
	}
	public void setAnoModelo(String anoModelo) {
		this.anoModelo = anoModelo;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public String getPotencia() {
		return potencia;
	}
	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}
	public String getCapacidade_passageiros() {
		return capacidade_passageiros;
	}
	public void setCapacidade_passageiros(String capacidade_passageiros) {
		this.capacidade_passageiros = capacidade_passageiros;
	}
	public String getProcedencia() {
		return procedencia;
	}
	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}
	public String getKm() {
		return km;
	}
	public void setKm(String km) {
		this.km = km;
	}
	public boolean isVeiculo_usado_como_taxi() {
		return veiculo_usado_como_taxi;
	}
	public void setVeiculo_usado_como_taxi(boolean veiculo_usado_como_taxi) {
		this.veiculo_usado_como_taxi = veiculo_usado_como_taxi;
	}
	public boolean isRestricao_roubo_furto() {
		return restricao_roubo_furto;
	}
	public void setRestricao_roubo_furto(boolean restricao_roubo_furto) {
		this.restricao_roubo_furto = restricao_roubo_furto;
	}
	public boolean isRestrito_judicialmente() {
		return restrito_judicialmente;
	}
	public void setRestrito_judicialmente(boolean restrito_judicialmente) {
		this.restrito_judicialmente = restrito_judicialmente;
	}
	public boolean isPertence_consulado_autoridades_especiais() {
		return pertence_consulado_autoridades_especiais;
	}
	public void setPertence_consulado_autoridades_especiais(boolean pertence_consulado_autoridades_especiais) {
		this.pertence_consulado_autoridades_especiais = pertence_consulado_autoridades_especiais;
	}
	public boolean isExiste_recall() {
		return existe_recall;
	}
	public void setExiste_recall(boolean existe_recall) {
		this.existe_recall = existe_recall;
	}

}
