-- MySQL Script generated by MySQL Workbench
-- 11/02/16 10:42:30
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema FerreteriaDatos
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema FerreteriaDatos
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `FerreteriaDatos` DEFAULT CHARACTER SET utf8 ;
USE `FerreteriaDatos` ;

-- -----------------------------------------------------
-- Table `FerreteriaDatos`.`Producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FerreteriaDatos`.`Producto` (
  `Codigo` VARCHAR(45) NOT NULL,
  `Descripcion` VARCHAR(45) NULL,
  `UnidadMedida` VARCHAR(15) NULL,
  `Precio` DOUBLE NULL,
  `isActivo` TINYINT(1) NULL,
  PRIMARY KEY (`Codigo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FerreteriaDatos`.`Inventario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FerreteriaDatos`.`Inventario` (
  `Fecha` DATETIME NOT NULL,
  `Producto` VARCHAR(45) NOT NULL,
  `Cantidad` INT NULL,
  `isActivo` TINYINT(1) NULL,
  PRIMARY KEY (`Fecha`, `Producto`),
  INDEX `FK1_INVENTARIO_idx` (`Producto` ASC),
  CONSTRAINT `FK1_INVENTARIO`
    FOREIGN KEY (`Producto`)
    REFERENCES `FerreteriaDatos`.`Producto` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FerreteriaDatos`.`Empleado`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FerreteriaDatos`.`Empleado` (
  `ID_EMPLEADO` VARCHAR(10) NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Clave` VARCHAR(45) NULL,
  `isActivo` TINYINT(1) NULL,
  `isAdministrador` TINYINT(1) NULL,
  `isCajero` TINYINT(1) NULL,
  `isVendedor` TINYINT(1) NULL,
  `isDespachador` TINYINT(1) NULL,
  `isBodeguero` TINYINT(1) NULL,
  PRIMARY KEY (`ID_EMPLEADO`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FerreteriaDatos`.`Cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FerreteriaDatos`.`Cliente` (
  `Cedula` VARCHAR(45) NOT NULL,
  `Nombre` VARCHAR(45) NULL,
  `Telefono` VARCHAR(15) NULL,
  `Email` VARCHAR(45) NULL,
  `Descuento` INT NULL,
  PRIMARY KEY (`Cedula`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FerreteriaDatos`.`Factura`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FerreteriaDatos`.`Factura` (
  `Codigo` INT NOT NULL AUTO_INCREMENT,
  `Cliente` VARCHAR(45) NULL,
  `Vendedor` VARCHAR(10) NULL,
  `Fecha` DATE NULL,
  `isPagado` TINYINT(1) NULL,
  `isDespachado` TINYINT(1) NULL,
  PRIMARY KEY (`Codigo`),
  INDEX `FK1_FACTURA_idx` (`Cliente` ASC),
  INDEX `FK2_FACTURA_idx` (`Vendedor` ASC),
  CONSTRAINT `FK1_FACTURA`
    FOREIGN KEY (`Cliente`)
    REFERENCES `FerreteriaDatos`.`Cliente` (`Cedula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK2_FACTURA`
    FOREIGN KEY (`Vendedor`)
    REFERENCES `FerreteriaDatos`.`Empleado` (`ID_EMPLEADO`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FerreteriaDatos`.`LineaDetalle`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FerreteriaDatos`.`LineaDetalle` (
  `Factura` INT NOT NULL,
  `Producto` VARCHAR(45) NOT NULL,
  `Cantidad` INT NULL,
  `Precio` DOUBLE NULL,
  `isDespachado` TINYINT(1) NULL,
  PRIMARY KEY (`Factura`, `Producto`),
  INDEX `FK2_LINEA_DETALLE_idx` (`Producto` ASC),
  CONSTRAINT `FK1_LINEA_DETALLE`
    FOREIGN KEY (`Factura`)
    REFERENCES `FerreteriaDatos`.`Factura` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK2_LINEA_DETALLE`
    FOREIGN KEY (`Producto`)
    REFERENCES `FerreteriaDatos`.`Producto` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `FerreteriaDatos`.`Pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `FerreteriaDatos`.`Pago` (
  `Factura` INT NOT NULL AUTO_INCREMENT,
  `TipoPago` TINYINT(1) NULL,
  `Pago` DOUBLE NULL,
  `CuentaCheque` INT NULL,
  `CuentaTarjeta` INT NULL,
  PRIMARY KEY (`Factura`),
  CONSTRAINT `FK1_PAGO`
    FOREIGN KEY (`Factura`)
    REFERENCES `FerreteriaDatos`.`Factura` (`Codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
