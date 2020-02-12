CREATE DATABASE  IF NOT EXISTS `easi_bradesco` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `easi_bradesco`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: localhost    Database: easi_bradesco
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorization`
--

DROP TABLE IF EXISTS `authorization`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `authorization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiration_date` datetime NOT NULL,
  `jwt` varchar(500) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorization`
--

LOCK TABLES `authorization` WRITE;
/*!40000 ALTER TABLE `authorization` DISABLE KEYS */;
INSERT INTO `authorization` VALUES (1,'2020-01-18 03:40:08','eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NzkzMTg4MDcsImlkIjpudWxsLCJ1c2VyIjp7ImlkIjoxLCJuYW1lIjoidGlhZ28iLCJlbWFpbCI6InRpYWdvQGdtYWlsLmNvbSIsInBhc3N3b3JkIjpudWxsLCJwaG9uZSI6bnVsbCwicHJvZmlsZSI6IklOU1VSRUQifSwiand0IjpudWxsLCJleHBpcmF0aW9uRGF0ZSI6MTU3OTMxODgwNzk3Nn0.1uTvQpd-6E4uKDCpuvROCBMOgtklTQxNX8pSKFcUUIWJ75mEdHAjiBr9e-OQIpexIakxXbPaEXzcoMuFph-fyA');
/*!40000 ALTER TABLE `authorization` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `automovel`
--

DROP TABLE IF EXISTS `automovel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `automovel` (
  `id_automovel` bigint(20) NOT NULL AUTO_INCREMENT,
  `ano_fabricacao` varchar(255) DEFAULT NULL,
  `ano_modelo` varchar(255) DEFAULT NULL,
  `capacidade_passageiros` varchar(255) DEFAULT NULL,
  `chassi` varchar(255) DEFAULT NULL,
  `cilindradas` varchar(255) DEFAULT NULL,
  `combustivel` varchar(255) DEFAULT NULL,
  `cor` varchar(255) DEFAULT NULL,
  `especie` varchar(255) DEFAULT NULL,
  `existe_recall` bit(1) NOT NULL,
  `fabricante` varchar(255) DEFAULT NULL,
  `km` varchar(255) DEFAULT NULL,
  `localizacao_fabrica` varchar(255) DEFAULT NULL,
  `marca_modelo` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `pertence_consulado_autoridades_especiais` bit(1) NOT NULL,
  `placa` varchar(255) DEFAULT NULL,
  `potencia` varchar(255) DEFAULT NULL,
  `procedencia` varchar(255) DEFAULT NULL,
  `regiao_geografica` varchar(255) DEFAULT NULL,
  `renavam` varchar(255) DEFAULT NULL,
  `restricao_roubo_furto` bit(1) NOT NULL,
  `restrito_judicialmente` bit(1) NOT NULL,
  `tipo_veiculo` varchar(255) DEFAULT NULL,
  `uf_placa` varchar(255) DEFAULT NULL,
  `veiculo_usado_como_taxi` bit(1) NOT NULL,
  PRIMARY KEY (`id_automovel`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `automovel`
--

LOCK TABLES `automovel` WRITE;
/*!40000 ALTER TABLE `automovel` DISABLE KEYS */;
INSERT INTO `automovel` VALUES (1,'2000','2000','5','BAP17206LA2084594',NULL,'GASOLINA','PRATA',NULL,_binary '\0','5','122',NULL,'SIENA',NULL,_binary '\0','LPM5301',NULL,NULL,NULL,'00193894955',_binary '\0',_binary '\0','AUTOMOVEL','RJ',_binary '\0');
/*!40000 ALTER TABLE `automovel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checklist`
--

DROP TABLE IF EXISTS `checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checklist` (
  `id_checklist` bigint(20) NOT NULL AUTO_INCREMENT,
  `atributo` varchar(255) DEFAULT NULL,
  `comparacao_campos_enum` varchar(255) DEFAULT NULL,
  `data_inclusaoia` datetime DEFAULT NULL,
  `data_inclusao_segurado` datetime DEFAULT NULL,
  `data_inclusao_vistoriador` datetime DEFAULT NULL,
  `observacao` varchar(255) DEFAULT NULL,
  `porcentagem_assertividade` float DEFAULT NULL,
  `recomendado` bit(1) NOT NULL,
  `tipo_campo_enum` varchar(255) DEFAULT NULL,
  `valoria` varchar(255) DEFAULT NULL,
  `valor_segurado` varchar(255) DEFAULT NULL,
  `valor_vistoriador` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_checklist`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checklist`
--

LOCK TABLES `checklist` WRITE;
/*!40000 ALTER TABLE `checklist` DISABLE KEYS */;
INSERT INTO `checklist` VALUES (1,'NOME','CORRETO','2020-01-17 21:40:26',NULL,NULL,NULL,1,_binary '\0','DOCUMENTO','TIAGO ARAUJO DE GOUVEA','TIAGO ARAUJO DE GOUVEA',NULL),(2,'CPFCNPJ','CORRETO','2020-01-17 21:40:26',NULL,NULL,NULL,1,_binary '\0','DOCUMENTO','058.087.917-82','058.087.917-82',NULL),(3,'CHASSI','CORRETO','2020-01-17 21:41:41',NULL,NULL,NULL,1,_binary '\0','VEICULO','BAP17206LA2084594','BAP17206LA2084594',NULL),(4,'MARCAMODELO','INCORRETO','2020-01-17 21:41:41',NULL,NULL,NULL,-0.0000000298023,_binary '\0','VEICULO','LPM5301','SIENA',NULL),(5,'RENAVAM','CORRETO','2020-01-17 21:41:41',NULL,NULL,NULL,1,_binary '\0','VEICULO','00193894955','00193894955',NULL),(6,'UF','CORRETO','2020-01-17 21:41:41',NULL,NULL,NULL,1,_binary '\0','DOCUMENTO','RJ','RJ',NULL),(7,'COLISAO','ATENCAO','2020-01-17 21:42:38',NULL,NULL,NULL,0.744026,_binary '\0','OUTROS','O veiculo com possivel avaria devido a batida','',NULL),(8,'BATIDAGRAVE','ATENCAO','2020-01-17 21:42:38',NULL,NULL,NULL,0.673704,_binary '\0','OUTROS','O veiculo com avaria devido a batida/acidente grave','',NULL),(9,'CORDIFERENTE','ATENCAO','2020-01-17 21:42:38',NULL,NULL,NULL,0,_binary '\0','OUTROS','A cor da foto referente a dianteira esquerda não é a mesma digitada pelo usuário!','',NULL),(10,'BATIDAGRAVE','ATENCAO','2020-01-17 21:42:38',NULL,NULL,NULL,0.910604,_binary '\0','OUTROS','O veiculo com avaria devido a batida/acidente grave','',NULL),(11,'COLISAO','ATENCAO','2020-01-17 21:42:38',NULL,NULL,NULL,0.686465,_binary '\0','OUTROS','O veiculo com possivel avaria devido a batida','',NULL),(12,'CORDIFERENTE','ATENCAO','2020-01-17 21:42:38',NULL,NULL,NULL,0,_binary '\0','OUTROS','A cor da foto referente a dianteira direita não é a mesma digitada pelo usuário!','',NULL);
/*!40000 ALTER TABLE `checklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cores_imagem`
--

DROP TABLE IF EXISTS `cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cores_imagem` (
  `id_cores_imagem` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao_cor` varchar(255) DEFAULT NULL,
  `rgb_blue` float NOT NULL,
  `rgb_green` float NOT NULL,
  `rgb_red` float NOT NULL,
  `score` float NOT NULL,
  PRIMARY KEY (`id_cores_imagem`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cores_imagem`
--

LOCK TABLES `cores_imagem` WRITE;
/*!40000 ALTER TABLE `cores_imagem` DISABLE KEYS */;
INSERT INTO `cores_imagem` VALUES (1,'Silver',192,188,181,0.250246),(2,'DarkSlateGray',49,49,50,0.166932),(3,'DarkGray',164,161,155,0.142501),(4,'Black',24,23,23,0.135472),(5,'WhiteSmoke',242,243,242,0.131871),(6,'DarkSlateGray',82,81,80,0.0721771),(7,'Gray',120,118,115,0.0588262),(8,'RosyBrown',136,159,170,0.01241),(9,'LightSteelBlue',210,192,188,0.0107762),(10,'DarkSlateGray',38,52,67,0.0068847),(11,'Gainsboro',220,223,225,0.181436),(12,'Silver',195,198,199,0.143648),(13,'Black',15,18,22,0.111959),(14,'DarkSlateGray',49,52,55,0.0867706),(15,'DarkSlateGray',80,81,81,0.0806508),(16,'DarkGray',154,158,157,0.0782484),(17,'Gray',116,119,118,0.0771878),(18,'DarkSlateGray',36,53,67,0.0523292),(19,'DarkOliveGreen',57,74,88,0.0282053),(20,'Peru',84,143,191,0.013083),(21,'Gray',120,118,117,0.242428),(22,'Silver',196,194,192,0.219721),(23,'DarkGray',158,156,155,0.203811),(24,'DimGray',90,86,86,0.144734),(25,'Gainsboro',232,230,228,0.0943835),(26,'DarkSlateGray',57,55,54,0.041712),(27,'Silver',163,197,196,0.013539),(28,'DimGray',92,119,120,0.0102119),(29,'Black',30,28,27,0.00710788),(30,'Gray',125,146,152,0.00572314);
/*!40000 ALTER TABLE `cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `documentos_pessoais`
--

DROP TABLE IF EXISTS `documentos_pessoais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `documentos_pessoais` (
  `id_documento_pessoais` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf_cnpj` varchar(255) DEFAULT NULL,
  `data_nascimento` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `telefone` varchar(255) DEFAULT NULL,
  `endereco_id_endereco` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_documento_pessoais`),
  KEY `FKrq9oue5mf1oqv9lyfj8dwa61n` (`endereco_id_endereco`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `documentos_pessoais`
--

LOCK TABLES `documentos_pessoais` WRITE;
/*!40000 ALTER TABLE `documentos_pessoais` DISABLE KEYS */;
INSERT INTO `documentos_pessoais` VALUES (1,'058.087.917-82','19/10/1988','tiago@gmail.ciom','TIAGO ARAUJO DE GOUVEA','3123123131',1);
/*!40000 ALTER TABLE `documentos_pessoais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `id_endereco` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` varchar(255) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_endereco`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
INSERT INTO `endereco` VALUES (1,'senador camara','21833320','rio','casa a','Rua alfredo','160','RJ');
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `example`
--

DROP TABLE IF EXISTS `example`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `example` (
  `example_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`example_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `example`
--

LOCK TABLES `example` WRITE;
/*!40000 ALTER TABLE `example` DISABLE KEYS */;
/*!40000 ALTER TABLE `example` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exemplo`
--

DROP TABLE IF EXISTS `exemplo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exemplo` (
  `example_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`example_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplo`
--

LOCK TABLES `exemplo` WRITE;
/*!40000 ALTER TABLE `exemplo` DISABLE KEYS */;
/*!40000 ALTER TABLE `exemplo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_chassi`
--

DROP TABLE IF EXISTS `foto_chassi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_chassi` (
  `id_foto_chassi` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_chassi`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_chassi`
--

LOCK TABLES `foto_chassi` WRITE;
/*!40000 ALTER TABLE `foto_chassi` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_chassi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_chassi_cores_imagem`
--

DROP TABLE IF EXISTS `foto_chassi_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_chassi_cores_imagem` (
  `foto_chassi_id_foto_chassi` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_nvqk01djw4kjp97nlvnvlu8i` (`cores_imagem_id_cores_imagem`),
  KEY `FKppwutpbbph2te0ck232ptumda` (`foto_chassi_id_foto_chassi`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_chassi_cores_imagem`
--

LOCK TABLES `foto_chassi_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_chassi_cores_imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_chassi_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_chassi_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_chassi_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_chassi_propriedades_imagems` (
  `foto_chassi_id_foto_chassi` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_rvkb0d1ybhdc9uoa78tg012gf` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FKp4bvd0h47gdaty2xmyxcbopjn` (`foto_chassi_id_foto_chassi`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_chassi_propriedades_imagems`
--

LOCK TABLES `foto_chassi_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_chassi_propriedades_imagems` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_chassi_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_dianteira_direita`
--

DROP TABLE IF EXISTS `foto_dianteira_direita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_dianteira_direita` (
  `id_foto_dianteira_direita` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_dianteira_direita`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_dianteira_direita`
--

LOCK TABLES `foto_dianteira_direita` WRITE;
/*!40000 ALTER TABLE `foto_dianteira_direita` DISABLE KEYS */;
INSERT INTO `foto_dianteira_direita` VALUES (1,'fotoQualquer','URLFoto://url.com'),(2,'fotoQualquer','URLFoto://url.com'),(3,'fotoQualquer','URLFoto://url.com'),(4,'fotoQualquer','URLFoto://url.com'),(5,'fotoQualquer','URLFoto://url.com');
/*!40000 ALTER TABLE `foto_dianteira_direita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_dianteira_direita_cores_imagem`
--

DROP TABLE IF EXISTS `foto_dianteira_direita_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_dianteira_direita_cores_imagem` (
  `foto_dianteira_direita_id_foto_dianteira_direita` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_gblpvprerf69xqmm9d7uhllp4` (`cores_imagem_id_cores_imagem`),
  KEY `FK4utni9aa3uban3dt5mjarfh7` (`foto_dianteira_direita_id_foto_dianteira_direita`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_dianteira_direita_cores_imagem`
--

LOCK TABLES `foto_dianteira_direita_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_dianteira_direita_cores_imagem` DISABLE KEYS */;
INSERT INTO `foto_dianteira_direita_cores_imagem` VALUES (3,1),(4,3),(5,5);
/*!40000 ALTER TABLE `foto_dianteira_direita_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_dianteira_direita_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_dianteira_direita_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_dianteira_direita_propriedades_imagems` (
  `foto_dianteira_direita_id_foto_dianteira_direita` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_hs3ofwlasoru6hjwulkeykkab` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FK4dxklo22g8oswga53aydjkt72` (`foto_dianteira_direita_id_foto_dianteira_direita`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_dianteira_direita_propriedades_imagems`
--

LOCK TABLES `foto_dianteira_direita_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_dianteira_direita_propriedades_imagems` DISABLE KEYS */;
INSERT INTO `foto_dianteira_direita_propriedades_imagems` VALUES (3,1),(4,3),(5,5);
/*!40000 ALTER TABLE `foto_dianteira_direita_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_dianteira_esquerda`
--

DROP TABLE IF EXISTS `foto_dianteira_esquerda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_dianteira_esquerda` (
  `id_dianteira_esquerda` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_dianteira_esquerda`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_dianteira_esquerda`
--

LOCK TABLES `foto_dianteira_esquerda` WRITE;
/*!40000 ALTER TABLE `foto_dianteira_esquerda` DISABLE KEYS */;
INSERT INTO `foto_dianteira_esquerda` VALUES (1,'fotoQualquer','URLFoto://url.com'),(2,'fotoQualquer','URLFoto://url.com'),(3,'fotoQualquer','URLFoto://url.com'),(4,'fotoQualquer','URLFoto://url.com'),(5,'fotoQualquer','URLFoto://url.com');
/*!40000 ALTER TABLE `foto_dianteira_esquerda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_dianteira_esquerda_cores_imagem`
--

DROP TABLE IF EXISTS `foto_dianteira_esquerda_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_dianteira_esquerda_cores_imagem` (
  `foto_dianteira_esquerda_id_dianteira_esquerda` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_4xahv33uambe0t799ug5r5nul` (`cores_imagem_id_cores_imagem`),
  KEY `FKlftif69lee16gbvouw5tchjsl` (`foto_dianteira_esquerda_id_dianteira_esquerda`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_dianteira_esquerda_cores_imagem`
--

LOCK TABLES `foto_dianteira_esquerda_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_dianteira_esquerda_cores_imagem` DISABLE KEYS */;
INSERT INTO `foto_dianteira_esquerda_cores_imagem` VALUES (3,2),(4,4),(5,6);
/*!40000 ALTER TABLE `foto_dianteira_esquerda_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_dianteira_esquerda_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_dianteira_esquerda_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_dianteira_esquerda_propriedades_imagems` (
  `foto_dianteira_esquerda_id_dianteira_esquerda` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ebjpberugfxyftlf9973u1u3g` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FK4ug0swaor7jd1xjayswk0inh` (`foto_dianteira_esquerda_id_dianteira_esquerda`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_dianteira_esquerda_propriedades_imagems`
--

LOCK TABLES `foto_dianteira_esquerda_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_dianteira_esquerda_propriedades_imagems` DISABLE KEYS */;
INSERT INTO `foto_dianteira_esquerda_propriedades_imagems` VALUES (3,2),(4,4),(5,6);
/*!40000 ALTER TABLE `foto_dianteira_esquerda_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_documento`
--

DROP TABLE IF EXISTS `foto_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_documento` (
  `id_foto_documento` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `tipo_foto` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_documento`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_documento`
--

LOCK TABLES `foto_documento` WRITE;
/*!40000 ALTER TABLE `foto_documento` DISABLE KEYS */;
INSERT INTO `foto_documento` VALUES (1,'c88fe554-e11f-4fac-9d34-8efd6e19642e3984441373819706707.jpg','CNH','C:\\EASI\\imagens\\c88fe554-e11f-4fac-9d34-8efd6e19642e3984441373819706707.jpg'),(2,'58048332-913a-4871-bc64-0915f61f04398293859976066640371.jpg','CRLV','C:\\EASI\\imagens\\58048332-913a-4871-bc64-0915f61f04398293859976066640371.jpg');
/*!40000 ALTER TABLE `foto_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_documento_cores_imagem`
--

DROP TABLE IF EXISTS `foto_documento_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_documento_cores_imagem` (
  `foto_documento_id_foto_chassi` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  `foto_documento_id_foto_documento` bigint(20) NOT NULL,
  UNIQUE KEY `UK_lv1uvrei3buqc85asko2e16kw` (`cores_imagem_id_cores_imagem`),
  KEY `FKo4k8fwjt0ryrivd1di70gjf1w` (`foto_documento_id_foto_chassi`),
  KEY `FKvjin4i5yu8g96qalopl2gaek` (`foto_documento_id_foto_documento`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_documento_cores_imagem`
--

LOCK TABLES `foto_documento_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_documento_cores_imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_documento_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_documento_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_documento_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_documento_propriedades_imagems` (
  `foto_documento_id_foto_chassi` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  `foto_documento_id_foto_documento` bigint(20) NOT NULL,
  UNIQUE KEY `UK_o474nggngaoi329lyfsfm8f0i` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FKh4e93uid8u3i9bbeh8exsbvy` (`foto_documento_id_foto_chassi`),
  KEY `FKs186uksujeodc36h0x2ljosyj` (`foto_documento_id_foto_documento`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_documento_propriedades_imagems`
--

LOCK TABLES `foto_documento_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_documento_propriedades_imagems` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_documento_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_documento_propriedades_texto_imagems`
--

DROP TABLE IF EXISTS `foto_documento_propriedades_texto_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_documento_propriedades_texto_imagems` (
  `foto_documento_id_foto_documento` bigint(20) NOT NULL,
  `propriedades_texto_imagems_id_propriedade_texto_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_7qw8qn7tjqqhe16hg7s101gm2` (`propriedades_texto_imagems_id_propriedade_texto_imagem`),
  KEY `FK4cre5v7p4hxiqqvwgko1bat0m` (`foto_documento_id_foto_documento`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_documento_propriedades_texto_imagems`
--

LOCK TABLES `foto_documento_propriedades_texto_imagems` WRITE;
/*!40000 ALTER TABLE `foto_documento_propriedades_texto_imagems` DISABLE KEYS */;
INSERT INTO `foto_documento_propriedades_texto_imagems` VALUES (1,1),(1,2),(2,3),(2,4),(2,5),(2,6);
/*!40000 ALTER TABLE `foto_documento_propriedades_texto_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_motor`
--

DROP TABLE IF EXISTS `foto_motor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_motor` (
  `id_documento_pessoais` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_documento_pessoais`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_motor`
--

LOCK TABLES `foto_motor` WRITE;
/*!40000 ALTER TABLE `foto_motor` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_motor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_motor_cores_imagem`
--

DROP TABLE IF EXISTS `foto_motor_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_motor_cores_imagem` (
  `foto_motor_id_documento_pessoais` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_q0gq6mqvk74j2lae9a10ufq5b` (`cores_imagem_id_cores_imagem`),
  KEY `FKbvk3saywkh456ejfcxnfu7g8o` (`foto_motor_id_documento_pessoais`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_motor_cores_imagem`
--

LOCK TABLES `foto_motor_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_motor_cores_imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_motor_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_motor_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_motor_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_motor_propriedades_imagems` (
  `foto_motor_id_documento_pessoais` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_1bhhyt3w561sv5bxxb6thwxyb` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FK9shdgr486o8uvqju9w20q2eec` (`foto_motor_id_documento_pessoais`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_motor_propriedades_imagems`
--

LOCK TABLES `foto_motor_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_motor_propriedades_imagems` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_motor_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_painel`
--

DROP TABLE IF EXISTS `foto_painel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_painel` (
  `id_foto_painel` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_painel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_painel`
--

LOCK TABLES `foto_painel` WRITE;
/*!40000 ALTER TABLE `foto_painel` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_painel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_painel_cores_imagem`
--

DROP TABLE IF EXISTS `foto_painel_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_painel_cores_imagem` (
  `foto_painel_id_foto_painel` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ba4k0fu2abe7p99l8xjvmi27c` (`cores_imagem_id_cores_imagem`),
  KEY `FKn4n0utnedwltmf9kja84fjbjr` (`foto_painel_id_foto_painel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_painel_cores_imagem`
--

LOCK TABLES `foto_painel_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_painel_cores_imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_painel_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_painel_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_painel_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_painel_propriedades_imagems` (
  `foto_painel_id_foto_painel` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_pdg9qxpy07sjjx754upptbu5r` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FK34d1ftopmj4rssb6gdxlqs7pq` (`foto_painel_id_foto_painel`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_painel_propriedades_imagems`
--

LOCK TABLES `foto_painel_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_painel_propriedades_imagems` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_painel_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_porta_mala`
--

DROP TABLE IF EXISTS `foto_porta_mala`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_porta_mala` (
  `id_foto_porta_mala` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_porta_mala`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_porta_mala`
--

LOCK TABLES `foto_porta_mala` WRITE;
/*!40000 ALTER TABLE `foto_porta_mala` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_porta_mala` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_porta_mala_cores_imagem`
--

DROP TABLE IF EXISTS `foto_porta_mala_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_porta_mala_cores_imagem` (
  `foto_porta_mala_id_foto_porta_mala` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_2smgykobsdarhjxbb9tcadjbf` (`cores_imagem_id_cores_imagem`),
  KEY `FKssxl8tg95rybyg7sttfttr0oh` (`foto_porta_mala_id_foto_porta_mala`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_porta_mala_cores_imagem`
--

LOCK TABLES `foto_porta_mala_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_porta_mala_cores_imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_porta_mala_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_porta_mala_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_porta_mala_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_porta_mala_propriedades_imagems` (
  `foto_porta_mala_id_foto_porta_mala` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_gosjkqpr2k0c9jr0usalxnwi1` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FKg5roydx8iu1jns1mc47pgs1pq` (`foto_porta_mala_id_foto_porta_mala`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_porta_mala_propriedades_imagems`
--

LOCK TABLES `foto_porta_mala_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_porta_mala_propriedades_imagems` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_porta_mala_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_traseira_direita`
--

DROP TABLE IF EXISTS `foto_traseira_direita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_traseira_direita` (
  `id_foto_traseira_direita` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_traseira_direita`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_traseira_direita`
--

LOCK TABLES `foto_traseira_direita` WRITE;
/*!40000 ALTER TABLE `foto_traseira_direita` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_traseira_direita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_traseira_direita_cores_imagem`
--

DROP TABLE IF EXISTS `foto_traseira_direita_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_traseira_direita_cores_imagem` (
  `foto_traseira_direita_id_foto_traseira_direita` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_p7rx2jql0gm149t6fhgut403v` (`cores_imagem_id_cores_imagem`),
  KEY `FKnsjuv707mm256dgifib5tntay` (`foto_traseira_direita_id_foto_traseira_direita`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_traseira_direita_cores_imagem`
--

LOCK TABLES `foto_traseira_direita_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_traseira_direita_cores_imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_traseira_direita_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_traseira_direita_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_traseira_direita_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_traseira_direita_propriedades_imagems` (
  `foto_traseira_direita_id_foto_traseira_direita` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_9jrpv1x3w0vyicuu4q3n7p1gn` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FKe89vkt8a5c43p99a8g1giilqx` (`foto_traseira_direita_id_foto_traseira_direita`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_traseira_direita_propriedades_imagems`
--

LOCK TABLES `foto_traseira_direita_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_traseira_direita_propriedades_imagems` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_traseira_direita_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_traseira_esquerda`
--

DROP TABLE IF EXISTS `foto_traseira_esquerda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_traseira_esquerda` (
  `id_foto_traseira_esquerda` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_traseira_esquerda`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_traseira_esquerda`
--

LOCK TABLES `foto_traseira_esquerda` WRITE;
/*!40000 ALTER TABLE `foto_traseira_esquerda` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_traseira_esquerda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_traseira_esquerda_cores_imagem`
--

DROP TABLE IF EXISTS `foto_traseira_esquerda_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_traseira_esquerda_cores_imagem` (
  `foto_traseira_esquerda_id_foto_traseira_esquerda` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_7wtitn38ulmfsw1vymurictq4` (`cores_imagem_id_cores_imagem`),
  KEY `FKqxmqapphew8kdoulfbfc0l4ln` (`foto_traseira_esquerda_id_foto_traseira_esquerda`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_traseira_esquerda_cores_imagem`
--

LOCK TABLES `foto_traseira_esquerda_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_traseira_esquerda_cores_imagem` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_traseira_esquerda_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_traseira_esquerda_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_traseira_esquerda_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_traseira_esquerda_propriedades_imagems` (
  `foto_traseira_esquerda_id_foto_traseira_esquerda` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_p7wij6oc58fkmk4rt04uicyki` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FKd7hfu05bwtqk4q8lhxbila74j` (`foto_traseira_esquerda_id_foto_traseira_esquerda`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_traseira_esquerda_propriedades_imagems`
--

LOCK TABLES `foto_traseira_esquerda_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_traseira_esquerda_propriedades_imagems` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_traseira_esquerda_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_veiculo`
--

DROP TABLE IF EXISTS `foto_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_veiculo` (
  `id_foto_veiculo` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `tipo_foto` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_foto_veiculo`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_veiculo`
--

LOCK TABLES `foto_veiculo` WRITE;
/*!40000 ALTER TABLE `foto_veiculo` DISABLE KEYS */;
INSERT INTO `foto_veiculo` VALUES (1,'8d35dbf0-b9aa-4e69-8ed1-dd14a930d5566078072584349540419.jpg','DIANTEIRAESQUERDA','C:\\EASI\\imagens\\8d35dbf0-b9aa-4e69-8ed1-dd14a930d5566078072584349540419.jpg'),(2,'082fc71f-0777-4e90-91c2-cabdf21510b61139155697382226653.jpg','DIANTEIRADIREITA','C:\\EASI\\imagens\\082fc71f-0777-4e90-91c2-cabdf21510b61139155697382226653.jpg'),(3,'b9353b2a-3184-4f00-9566-dda18e1a24de8587673592510217747.jpg','MOTOR','C:\\EASI\\imagens\\b9353b2a-3184-4f00-9566-dda18e1a24de8587673592510217747.jpg');
/*!40000 ALTER TABLE `foto_veiculo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_veiculo_cores_imagem`
--

DROP TABLE IF EXISTS `foto_veiculo_cores_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_veiculo_cores_imagem` (
  `foto_veiculo_id_foto_veiculo` bigint(20) NOT NULL,
  `cores_imagem_id_cores_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_jxviulae6kfp0asiqyyfm44tf` (`cores_imagem_id_cores_imagem`),
  KEY `FKth48slu1n9qmqw88dysm8430i` (`foto_veiculo_id_foto_veiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_veiculo_cores_imagem`
--

LOCK TABLES `foto_veiculo_cores_imagem` WRITE;
/*!40000 ALTER TABLE `foto_veiculo_cores_imagem` DISABLE KEYS */;
INSERT INTO `foto_veiculo_cores_imagem` VALUES (1,10),(1,9),(1,8),(1,7),(1,6),(1,5),(1,4),(1,3),(1,2),(1,1),(2,20),(2,19),(2,18),(2,17),(2,16),(2,15),(2,14),(2,13),(2,12),(2,11),(3,30),(3,29),(3,28),(3,27),(3,26),(3,25),(3,24),(3,23),(3,22),(3,21);
/*!40000 ALTER TABLE `foto_veiculo_cores_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_veiculo_propriedades_imagems`
--

DROP TABLE IF EXISTS `foto_veiculo_propriedades_imagems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_veiculo_propriedades_imagems` (
  `foto_veiculo_id_foto_veiculo` bigint(20) NOT NULL,
  `propriedades_imagems_id_propriedade_imagem` bigint(20) NOT NULL,
  UNIQUE KEY `UK_odim3fgwwt2brl629wl7gdfe8` (`propriedades_imagems_id_propriedade_imagem`),
  KEY `FKfh7xhayhkaic1y5xi8ug79qtv` (`foto_veiculo_id_foto_veiculo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_veiculo_propriedades_imagems`
--

LOCK TABLES `foto_veiculo_propriedades_imagems` WRITE;
/*!40000 ALTER TABLE `foto_veiculo_propriedades_imagems` DISABLE KEYS */;
INSERT INTO `foto_veiculo_propriedades_imagems` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(2,8),(2,9),(2,10),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(2,17),(3,18),(3,19),(3,20),(3,21),(3,22),(3,23),(3,24);
/*!40000 ALTER TABLE `foto_veiculo_propriedades_imagems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laudo`
--

DROP TABLE IF EXISTS `laudo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laudo` (
  `id_laudo` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_revisao` datetime DEFAULT NULL,
  `data_vistoria` datetime DEFAULT NULL,
  PRIMARY KEY (`id_laudo`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laudo`
--

LOCK TABLES `laudo` WRITE;
/*!40000 ALTER TABLE `laudo` DISABLE KEYS */;
INSERT INTO `laudo` VALUES (1,NULL,'2020-01-17 21:42:38');
/*!40000 ALTER TABLE `laudo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laudo_checklist`
--

DROP TABLE IF EXISTS `laudo_checklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laudo_checklist` (
  `laudo_id_laudo` bigint(20) NOT NULL,
  `checklist_id_checklist` bigint(20) NOT NULL,
  UNIQUE KEY `UK_g38eo335yt3hyo2x7antau14j` (`checklist_id_checklist`),
  KEY `FKa9g4fmwfat2kiljgc9wltow2p` (`laudo_id_laudo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laudo_checklist`
--

LOCK TABLES `laudo_checklist` WRITE;
/*!40000 ALTER TABLE `laudo_checklist` DISABLE KEYS */;
INSERT INTO `laudo_checklist` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12);
/*!40000 ALTER TABLE `laudo_checklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `error` varchar(255) DEFAULT NULL,
  `send_date` datetime NOT NULL,
  `status` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propriedades_imagem`
--

DROP TABLE IF EXISTS `propriedades_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propriedades_imagem` (
  `id_propriedade_imagem` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) DEFAULT NULL,
  `porcentagem` float DEFAULT NULL,
  PRIMARY KEY (`id_propriedade_imagem`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propriedades_imagem`
--

LOCK TABLES `propriedades_imagem` WRITE;
/*!40000 ALTER TABLE `propriedades_imagem` DISABLE KEYS */;
INSERT INTO `propriedades_imagem` VALUES (1,'Vehicle',0.943505),(2,'Car',0.920965),(3,'Collision',0.744026),(4,'Auto part',0.68212),(5,'Crash',0.673704),(6,'Vehicle door',0.630066),(7,'Hood',0.559906),(8,'Motor vehicle',0.981144),(9,'Vehicle',0.963194),(10,'Crash',0.910604),(11,'Car',0.905323),(12,'Mode of transport',0.80817),(13,'Collision',0.686465),(14,'Compact car',0.639261),(15,'City car',0.578033),(16,'Hatchback',0.576491),(17,'Subcompact car',0.535538),(18,'Engine',0.979177),(19,'Auto part',0.954321),(20,'Automotive engine part',0.882958),(21,'Vehicle',0.745259),(22,'Automotive super charger part',0.666133),(23,'Car',0.618885),(24,'Aircraft engine',0.536519);
/*!40000 ALTER TABLE `propriedades_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `propriedades_texto_imagem`
--

DROP TABLE IF EXISTS `propriedades_texto_imagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `propriedades_texto_imagem` (
  `id_propriedade_texto_imagem` bigint(20) NOT NULL AUTO_INCREMENT,
  `atributo` varchar(255) DEFAULT NULL,
  `data_inclusao` datetime DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_propriedade_texto_imagem`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `propriedades_texto_imagem`
--

LOCK TABLES `propriedades_texto_imagem` WRITE;
/*!40000 ALTER TABLE `propriedades_texto_imagem` DISABLE KEYS */;
INSERT INTO `propriedades_texto_imagem` VALUES (1,'NOME','2020-01-17 21:40:26','TIAGO ARAUJO DE GOUVEA'),(2,'CPFCNPJ','2020-01-17 21:40:26','058.087.917-82'),(3,'CHASSI','2020-01-17 21:41:41','BAP17206LA2084594'),(4,'MARCAMODELO','2020-01-17 21:41:41','LPM5301'),(5,'RENAVAM','2020-01-17 21:41:41','00193894955'),(6,'UF','2020-01-17 21:41:41','RJ');
/*!40000 ALTER TABLE `propriedades_texto_imagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(150) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'tiago@gmail.com','tiago','123','2123135646','INSURED');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vistoria`
--

DROP TABLE IF EXISTS `vistoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vistoria` (
  `id_vistoria` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `data_laudo` datetime DEFAULT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `automovel_id_automovel` bigint(20) DEFAULT NULL,
  `documentos_pessoais_id_documento_pessoais` bigint(20) DEFAULT NULL,
  `laudo_id_laudo` bigint(20) DEFAULT NULL,
  `usuario_segurado_id` bigint(20) DEFAULT NULL,
  `usuario_vistoriador_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_vistoria`),
  KEY `FKl09oidws6rfsl00lqbh0bkjrw` (`automovel_id_automovel`),
  KEY `FKd9nkl8hpliecjr2rfxeeo9f3w` (`documentos_pessoais_id_documento_pessoais`),
  KEY `FKml8ux1kdksyio1kvsawne0pv3` (`laudo_id_laudo`),
  KEY `FK2p0y7h016jv6rjdq11x61fgsj` (`usuario_segurado_id`),
  KEY `FKmcwlxhpx4c0v1fi7b0ey9q1uh` (`usuario_vistoriador_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vistoria`
--

LOCK TABLES `vistoria` WRITE;
/*!40000 ALTER TABLE `vistoria` DISABLE KEYS */;
INSERT INTO `vistoria` VALUES (1,NULL,NULL,'TIAGO ARAUJO DE GOUVEA','CONCLUIDA',1,1,1,1,NULL);
/*!40000 ALTER TABLE `vistoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vistoria_foto_documento`
--

DROP TABLE IF EXISTS `vistoria_foto_documento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vistoria_foto_documento` (
  `vistoria_id_vistoria` bigint(20) NOT NULL,
  `foto_documento_id_foto_documento` bigint(20) NOT NULL,
  UNIQUE KEY `UK_antrqo34ex4t55s4vbw33magt` (`foto_documento_id_foto_documento`),
  KEY `FK39lcrmepyfy71ho5w4upoidll` (`vistoria_id_vistoria`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vistoria_foto_documento`
--

LOCK TABLES `vistoria_foto_documento` WRITE;
/*!40000 ALTER TABLE `vistoria_foto_documento` DISABLE KEYS */;
INSERT INTO `vistoria_foto_documento` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `vistoria_foto_documento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vistoria_foto_veiculo`
--

DROP TABLE IF EXISTS `vistoria_foto_veiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vistoria_foto_veiculo` (
  `vistoria_id_vistoria` bigint(20) NOT NULL,
  `foto_veiculo_id_foto_veiculo` bigint(20) NOT NULL,
  UNIQUE KEY `UK_84xuyor2q0r2b13o96cwqlywt` (`foto_veiculo_id_foto_veiculo`),
  KEY `FK66h94h97iuu6ne8l9smea7jf6` (`vistoria_id_vistoria`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vistoria_foto_veiculo`
--

LOCK TABLES `vistoria_foto_veiculo` WRITE;
/*!40000 ALTER TABLE `vistoria_foto_veiculo` DISABLE KEYS */;
INSERT INTO `vistoria_foto_veiculo` VALUES (1,1),(1,2),(1,3);
/*!40000 ALTER TABLE `vistoria_foto_veiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-17 19:18:12
