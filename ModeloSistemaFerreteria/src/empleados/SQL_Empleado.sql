/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Michael Chen W.
 * Created: Oct 26, 2016
 */

CREATE DATABASE Empleado

use Empleado;

CREATE TABLE Empleado (
  ID_Empleado INT(11) NOT NULL,
  Nombre VARCHAR(50) NULL,
  Clave VARCHAR(45) NULL,
  isAdministrador BIT NULL,
  isBodeguero BIT NULL,
  isDespachador BIT NULL,
  isCajero BIT NULL,
  isVendedor BIT NULL,
  PRIMARY KEY (ID_Cliente)
);

INSERT INTO Empleado (ID_Empleado, Nombre, isAdministrador, isBodeguero, isDespachador, isCajero, isVendedor) 
            VALUES   (123456, Pedro, 1, 1, 1, 1, 1);

    