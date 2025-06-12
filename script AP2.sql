SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema restaurante_avaliacao_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `restaurante_avaliacao_db` ;
CREATE SCHEMA IF NOT EXISTS `restaurante_avaliacao_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `restaurante_avaliacao_db` ;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`cliente` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(14) NOT NULL UNIQUE,
  `nome` VARCHAR(150) NOT NULL,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `senha` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`local_restaurante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`local_restaurante` (
  `id_local` INT NOT NULL AUTO_INCREMENT,
  `cidade` VARCHAR(100) NOT NULL,
  `bairro` VARCHAR(100) NOT NULL,
  `rua` VARCHAR(150) NOT NULL,
  `numero` INT NOT NULL,
  `cep` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id_local`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`restaurante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`restaurante` (
  `id_restaurante` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(150) NOT NULL,
  `data_criacao` DATE NOT NULL,
  `id_local` INT NOT NULL,
  PRIMARY KEY (`id_restaurante`),
  INDEX `fk_restaurante_local_idx` (`id_local` ASC) VISIBLE,
  CONSTRAINT `fk_restaurante_local`
    FOREIGN KEY (`id_local`)
    REFERENCES `restaurante_avaliacao_db`.`local_restaurante` (`id_local`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`avaliacao_atendimento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`avaliacao_atendimento` (
  `id_avaliacao_atendimento` INT NOT NULL AUTO_INCREMENT,
  `nota_atendimento` FLOAT NOT NULL,
  `fk_restaurante` INT NOT NULL,
  `fk_cliente` INT NOT NULL,
  PRIMARY KEY (`id_avaliacao_atendimento`),
  INDEX `fk_atendimento_restaurante_idx` (`fk_restaurante` ASC) VISIBLE,
  INDEX `fk_atendimento_cliente_idx` (`fk_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_atendimento_restaurante`
    FOREIGN KEY (`fk_restaurante`)
    REFERENCES `restaurante_avaliacao_db`.`restaurante` (`id_restaurante`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_atendimento_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `restaurante_avaliacao_db`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`avaliacao_ambiente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`avaliacao_ambiente` (
  `id_avaliacao_ambiente` INT NOT NULL AUTO_INCREMENT,
  `nota_ambiente` FLOAT NOT NULL,
  `fk_restaurante` INT NOT NULL,
  `fk_cliente` INT NOT NULL,
  PRIMARY KEY (`id_avaliacao_ambiente`),
  INDEX `fk_ambiente_restaurante_idx` (`fk_restaurante` ASC) VISIBLE,
  INDEX `fk_ambiente_cliente_idx` (`fk_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_ambiente_restaurante`
    FOREIGN KEY (`fk_restaurante`)
    REFERENCES `restaurante_avaliacao_db`.`restaurante` (`id_restaurante`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_ambiente_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `restaurante_avaliacao_db`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`avaliacao_comida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`avaliacao_comida` (
  `id_avaliacao_comida` INT NOT NULL AUTO_INCREMENT,
  `nota_comida` FLOAT NOT NULL,
  `fk_restaurante` INT NOT NULL,
  `fk_cliente` INT NOT NULL,
  PRIMARY KEY (`id_avaliacao_comida`),
  INDEX `fk_comida_restaurante_idx` (`fk_restaurante` ASC) VISIBLE,
  INDEX `fk_comida_cliente_idx` (`fk_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_comida_restaurante`
    FOREIGN KEY (`fk_restaurante`)
    REFERENCES `restaurante_avaliacao_db`.`restaurante` (`id_restaurante`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comida_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `restaurante_avaliacao_db`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`avaliacao_localizacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`avaliacao_localizacao` (
  `id_avaliacao_localizacao` INT NOT NULL AUTO_INCREMENT,
  `nota_localizacao` FLOAT NOT NULL,
  `fk_restaurante` INT NOT NULL,
  `fk_cliente` INT NOT NULL,
  PRIMARY KEY (`id_avaliacao_localizacao`),
  INDEX `fk_localizacao_restaurante_idx` (`fk_restaurante` ASC) VISIBLE,
  INDEX `fk_localizacao_cliente_idx` (`fk_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_localizacao_restaurante`
    FOREIGN KEY (`fk_restaurante`)
    REFERENCES `restaurante_avaliacao_db`.`restaurante` (`id_restaurante`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_localizacao_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `restaurante_avaliacao_db`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `restaurante_avaliacao_db`.`classificacao_final`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `restaurante_avaliacao_db`.`classificacao_final` (
  `id_classificacao` INT NOT NULL AUTO_INCREMENT,
  `fk_restaurante` INT NOT NULL,
  `fk_cliente` INT NOT NULL,
  `nota_final` FLOAT NOT NULL,
  `data_classificacao` DATE NOT NULL,
  PRIMARY KEY (`id_classificacao`),
  INDEX `fk_classificacao_restaurante_idx` (`fk_restaurante` ASC) VISIBLE,
  INDEX `fk_classificacao_cliente_idx` (`fk_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_classificacao_restaurante`
    FOREIGN KEY (`fk_restaurante`)
    REFERENCES `restaurante_avaliacao_db`.`restaurante` (`id_restaurante`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_classificacao_cliente`
    FOREIGN KEY (`fk_cliente`)
    REFERENCES `restaurante_avaliacao_db`.`cliente` (`id_cliente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;