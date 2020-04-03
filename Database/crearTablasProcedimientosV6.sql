-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema HadoopClimate
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema HadoopClimate
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `HadoopClimate` DEFAULT CHARACTER SET utf8 ;
USE `HadoopClimate` ;

-- -----------------------------------------------------
-- Table `HadoopClimate`.`Continent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`Continent` (
  `idContinent` INT(11) NOT NULL AUTO_INCREMENT,
  `continent` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idContinent`),
  UNIQUE INDEX `idContinent_UNIQUE` (`idContinent` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `HadoopClimate`.`Country`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`Country` (
  `idCountry` INT(11) NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `idContinent` INT(11) NOT NULL,
  PRIMARY KEY (`idCountry`),
  UNIQUE INDEX `idCountry_UNIQUE` (`idCountry` ASC),
  INDEX `Country_X_Continent_idx` (`idContinent` ASC),
  CONSTRAINT `fkCountryContinent`
    FOREIGN KEY (`idContinent`)
    REFERENCES `HadoopClimate`.`Continent` (`idContinent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `HadoopClimate`.`Variable`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`Variable` (
  `idVariable` INT(11) NOT NULL AUTO_INCREMENT,
  `variable` VARCHAR(2) NOT NULL,
  `description` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`idVariable`),
  UNIQUE INDEX `idVariable_UNIQUE` (`idVariable` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `HadoopClimate`.`ContCountryMaxMinVar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`ContCountryMaxMinVar` (
  `idContCountryMaxMinVar` INT(11) NOT NULL AUTO_INCREMENT,
  `idCountry` INT(11) NOT NULL,
  `idVariable` INT(11) NOT NULL,
  `value` DOUBLE NULL,
  `MaxMin` VARCHAR(7) NOT NULL COMMENT 'Max = M\nMin = m',
  PRIMARY KEY (`idContCountryMaxMinVar`),
  UNIQUE INDEX `idContCountryMaxMinVar_UNIQUE` (`idContCountryMaxMinVar` ASC),
  INDEX `fkCountry_idx` (`idCountry` ASC),
  INDEX `fkVariable_idx` (`idVariable` ASC),
  CONSTRAINT `fkContCountryCountry`
    FOREIGN KEY (`idCountry`)
    REFERENCES `HadoopClimate`.`Country` (`idCountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkContCountryVariable`
    FOREIGN KEY (`idVariable`)
    REFERENCES `HadoopClimate`.`Variable` (`idVariable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'Almacena el país por cada continente en el que la variable fue la máxima o la mínima. Puntos H y I.';


-- -----------------------------------------------------
-- Table `HadoopClimate`.`ContinentAverageVar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`ContinentAverageVar` (
  `idContinentAverageVar` INT(11) NOT NULL AUTO_INCREMENT,
  `idContinent` INT(11) NOT NULL,
  `decade` VARCHAR(4) NOT NULL,
  `idVariable` INT(11) NOT NULL,
  `value` DOUBLE NULL,
  PRIMARY KEY (`idContinentAverageVar`),
  INDEX `fkContinentAverageContinent_idx` (`idContinent` ASC),
  INDEX `fkContinentAverageVariable_idx` (`idVariable` ASC),
  CONSTRAINT `fkContinentAverageContinent`
    FOREIGN KEY (`idContinent`)
    REFERENCES `HadoopClimate`.`Continent` (`idContinent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkContinentAverageVariable`
    FOREIGN KEY (`idVariable`)
    REFERENCES `HadoopClimate`.`Variable` (`idVariable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Almacena el promedio de cada variable por continent en grupos de 10 años. Punto E.';


-- -----------------------------------------------------
-- Table `HadoopClimate`.`CountryMaxMinAverage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`CountryMaxMinAverage` (
  `idCountryMaxMinAverage` INT(11) NOT NULL AUTO_INCREMENT,
  `idCountry` INT(11) NOT NULL,
  `idVariable` INT(11) NOT NULL,
  `value` DOUBLE NULL,
  `MaxMin` VARCHAR(7) NOT NULL,
  PRIMARY KEY (`idCountryMaxMinAverage`),
  UNIQUE INDEX `idCountryMaxMinAverage_UNIQUE` (`idCountryMaxMinAverage` ASC),
  INDEX `fkAverageCountry_idx` (`idCountry` ASC),
  INDEX `fkVariable_idx` (`idVariable` ASC),
  CONSTRAINT `fkAverageCountry`
    FOREIGN KEY (`idCountry`)
    REFERENCES `HadoopClimate`.`Country` (`idCountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkAverageVariable`
    FOREIGN KEY (`idVariable`)
    REFERENCES `HadoopClimate`.`Variable` (`idVariable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'Tabla para almacenar el top 10 países los máximos promedios generales. Puntos A y B.';


-- -----------------------------------------------------
-- Table `HadoopClimate`.`Station`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`Station` (
  `idStation` INT(11) NOT NULL AUTO_INCREMENT,
  `stationNumber` VARCHAR(15) NOT NULL,
  `idCountry` INT(11) NOT NULL,
  `stationOrCityName` VARCHAR(100) NOT NULL,
  `Latitude` FLOAT NULL DEFAULT NULL,
  `Longitude` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`idStation`),
  UNIQUE INDEX `idCity_UNIQUE` (`stationNumber` ASC),
  UNIQUE INDEX `idStation_UNIQUE` (`idStation` ASC),
  INDEX `City_X_Country_idx` (`idCountry` ASC),
  CONSTRAINT `fkStationCountry`
    FOREIGN KEY (`idCountry`)
    REFERENCES `HadoopClimate`.`Country` (`idCountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `HadoopClimate`.`StationMaxMinVar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`StationMaxMinVar` (
  `idStationMaxMinVar` INT(11) NOT NULL AUTO_INCREMENT,
  `idCountry` INT(11) NOT NULL,
  `idStation` INT(11) NULL,
  `idVariable` INT(11) NOT NULL,
  `value` DOUBLE NULL,
  `MaxMin` VARCHAR(7) NOT NULL COMMENT 'Max = M\nMin = m',
  PRIMARY KEY (`idStationMaxMinVar`),
  UNIQUE INDEX `idStationMaxMinVar_UNIQUE` (`idStationMaxMinVar` ASC),
  INDEX `fkCountry_idx` (`idCountry` ASC),
  INDEX `fkStation_idx` (`idStation` ASC),
  INDEX `fkVariable_idx` (`idVariable` ASC),
  CONSTRAINT `fkStationMMCountry`
    FOREIGN KEY (`idCountry`)
    REFERENCES `HadoopClimate`.`Country` (`idCountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkStationMMStation`
    FOREIGN KEY (`idStation`)
    REFERENCES `HadoopClimate`.`Station` (`idStation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkStationMMVariable`
    FOREIGN KEY (`idVariable`)
    REFERENCES `HadoopClimate`.`Variable` (`idVariable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8
COMMENT = 'Almacena la estación por cada país en el que la variable fue la máxima o la mínima. Puntos F y G.';


-- -----------------------------------------------------
-- Table `HadoopClimate`.`YearMaxMinVar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `HadoopClimate`.`YearMaxMinVar` (
  `idVarMaxMin` INT(11) NOT NULL AUTO_INCREMENT,
  `idCountry` INT(11) NOT NULL,
  `idVariable` INT(11) NOT NULL,
  `year` INT(11) NOT NULL,
  `value` DOUBLE NULL,
  `MaxMin` VARCHAR(7) NOT NULL COMMENT 'Max M\nMin m',
  PRIMARY KEY (`idVarMaxMin`),
  UNIQUE INDEX `idVarMaxMin_UNIQUE` (`idVarMaxMin` ASC),
  INDEX `fkCountry_idx` (`idCountry` ASC),
  INDEX `fkVariable_idx` (`idVariable` ASC),
  CONSTRAINT `fkYearMMCountry`
    FOREIGN KEY (`idCountry`)
    REFERENCES `HadoopClimate`.`Country` (`idCountry`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkYearMMVariable`
    FOREIGN KEY (`idVariable`)
    REFERENCES `HadoopClimate`.`Variable` (`idVariable`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COMMENT = 'Almacena el año por cada país en el que la variable fue la máxima o la mínima. Puntos C y D.';

USE `HadoopClimate` ;

-- -----------------------------------------------------
-- procedure getContCountryMaxMinVars
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getContCountryMaxMinVars`(pContinent INT, pCountry INT, pVariable INT, pMaxMinOpt VARCHAR(7))
BEGIN
	SELECT continent, country, CONCAT_WS(' - ', Variable.variable, Variable.description) AS var,
		value, MaxMin
	FROM Continent, Country, Variable, ContCountryMaxMinVar
    WHERE (ContCountryMaxMinVar.idCountry = Country.idCountry) and
		(Continent.idContinent = Country.idContinent) and
        (ContCountryMaxMinVar.idVariable = Variable.idVariable) and
		(pVariable is null or ContCountryMaxMinVar.idVariable = pVariable) and
        (pCountry is null or ContCountryMaxMinVar.idCountry = pCountry) and
        (pContinent is null or Continent.idContinent = pContinent) and
        (pMaxMinOpt is null or ContCountryMaxMinVar.MaxMin = pMaxMinOpt)
	ORDER BY Continent ASC, Country ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getContinentCountries
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getContinentCountries`(pIdContinent INT)
BEGIN
	SELECT idCountry, country
    FROM Country
    WHERE idContinent = pIdContinent
    ORDER BY country ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getContinents
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getContinents`()
BEGIN
	SELECT idContinent, continent 
    FROM Continent
    ORDER BY continent ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getCountries
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getCountries`()
BEGIN
	SELECT idCountry, country 
    FROM Country
    ORDER BY country ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getCountryID
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` FUNCTION `getCountryID`(pCountry varchar(45)) RETURNS int(11)
BEGIN
	declare myId int;
	set myId =(select idCountry from HadoopClimate.Country where country = pCountry);
RETURN myId;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getCountryMaxMinAverage
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getCountryMaxMinAverage`(pContinent INT, pCountry INT, pVariable INT, pMaxMinOpt VARCHAR(7))
BEGIN
	SELECT continent, country, CONCAT_WS(' - ', Variable.variable, Variable.description) AS var,
		value, MaxMin
	FROM Continent, Country, Variable, CountryMaxMinAverage
    WHERE (CountryMaxMinAverage.idCountry = Country.idCountry) and
		(Continent.idContinent = Country.idContinent) and
        (CountryMaxMinAverage.idVariable = Variable.idVariable) and
		(pVariable is null or CountryMaxMinAverage.idVariable = pVariable) and
        (pCountry is null or CountryMaxMinAverage.idCountry = pCountry) and
        (pContinent is null or Continent.idContinent = pContinent) and
        (pMaxMinOpt is null or CountryMaxMinAverage.MaxMin = pMaxMinOpt)
	ORDER BY var ASC, value DESC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getCountryStations
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getCountryStations`(pIdCountry INT)
BEGIN
	SELECT idStation, CONCAT_WS(' - ', stationOrCityName, stationNumber) as stationName
    FROM Station
    WHERE idCountry = pIdCountry
    ORDER BY stationName ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getStationID
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` FUNCTION `getStationID`(pStation varchar(100)) RETURNS int(11)
BEGIN
	declare myId int;
	set myId =(select idStation from HadoopClimate.Station where stationOrCityName = Binary pStation);
RETURN myId;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getStationMaxMinVars
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getStationMaxMinVars`(pContinent INT, pCountry INT, pStation INT, pVariable INT, pMaxMinOpt VARCHAR(7))
BEGIN
	SELECT Continent.continent, 
	   Country.country,
       CONCAT_WS(' - ', Station.stationOrCityName, Station.stationNumber) AS stationName,
       CONCAT_WS(' - ', Variable.variable, Variable.description) AS var,
       value, MaxMin
       
		FROM StationMaxMinVar
		
        INNER JOIN Station
		ON StationMaxMinVar.idStation = Station.idStation AND StationMaxMinVar.idStation = IFNULL(pStation, Station.idStation)

		INNER JOIN Country
		ON StationMaxMinVar.idCountry = Country.idCountry AND StationMaxMinVar.idCountry = IFNULL(pCountry, Country.idCountry)
        
        INNER JOIN Continent
		ON Country.idContinent = Continent.idContinent AND Continent.idContinent = IFNULL(pContinent, Continent.idContinent)

		INNER JOIN Variable
		ON StationMaxMinVar.idVariable = Variable.idVariable AND Variable.idVariable = IFNULL(pVariable, Variable.idVariable)

		WHERE pMaxMinOpt is null or maxMin = pMaxMinOpt
	ORDER BY Country ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getStations
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getStations`()
BEGIN
	SELECT idStation, CONCAT_WS(' - ', stationOrCityName, stationNumber) as stationName
    FROM Station
    ORDER BY stationName ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getVariableID
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` FUNCTION `getVariableID`(pVariable varchar(2)) RETURNS int(11)
BEGIN
	declare myId int;
	set myId =(select idVariable from HadoopClimate.Variable where variable = Binary pVariable);
RETURN myId;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getVariables
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getVariables`()
BEGIN
	SELECT idVariable, CONCAT_WS(' - ', variable, description) AS var
    FROM Variable;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getYearMaxMinVars
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getYearMaxMinVars`(pContinent INT, pCountry INT, pVariable INT, pMaxMinOpt VARCHAR(7))
BEGIN
	SELECT continent, country, year, CONCAT_WS(' - ', Variable.variable, Variable.description) AS var,
		value, MaxMin
	FROM Continent, Country, Variable, YearMaxMinVar
    WHERE (YearMaxMinVar.idCountry = Country.idCountry) and
		(Continent.idContinent = Country.idContinent) and
        (YearMaxMinVar.idVariable = Variable.idVariable) and
		(pVariable is null or YearMaxMinVar.idVariable = pVariable) and
        (pCountry is null or YearMaxMinVar.idCountry = pCountry) and
        (pContinent is null or Continent.idContinent = pContinent) and
        (pMaxMinOpt is null or YearMaxMinVar.MaxMin = pMaxMinOpt)
	ORDER BY Continent ASC, Country ASC, year ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertTop
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `insertTop`(country varchar(45), variable varchar(2), myValor double, indicador varchar(7))
BEGIN
	insert into HadoopClimate.CountryMaxMinAverage(idCountry, idVariable, value, MaxMin)
    value((select getCountryID(country)), (select getVariableID(variable)), myValor, indicador);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertVarStation
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `insertVarStation`(country varchar(45), station varchar(100), variable int, myValor double, indicador varchar(7))
BEGIN
	insert into HadoopClimate.StationMaxMinVar(idCountry, idStation, idVariable, value, MaxMin)
    value((select getCountryID(country)), (select getStationID(station)), variable, myValor, indicador);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertVarYear
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `insertVarYear`(country varchar(45), myYear int, myValor double, variable int, indicador varchar(7))
BEGIN
	insert into HadoopClimate.YearMaxMinVar(idCountry, idVariable, year, value, MaxMin)
    value((select getCountryID(country)), variable, myYear, myValor, indicador);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertVarsCountry
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `insertVarsCountry`(country varchar(45), variable int, myValor double, indicador varchar(7))
BEGIN
	insert into HadoopClimate.ContCountryMaxMinVar(idCountry, idVariable, value, MaxMin)
    value((select getCountryID(country)),variable , myValor, indicador);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure insertContinentDecade
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE PROCEDURE `insertContinentDecade`(continent varchar(45), decade varchar(4), variable int, value double)
BEGIN
	INSERT INTO HadoopClimate.ContinentAverageVar(idContinent,decade,idVariable,value)
    value((select getContinentID(continent)), decade, variable,value);
END$$

DELIMITER ;

-- -----------------------------------------------------
-- function getContinentID
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` FUNCTION `getContinentID`(pContinent varchar(45)) RETURNS int(11)
BEGIN
	declare myId int;
	set myId =(select idContinent from HadoopClimate.Continent where continent = pContinent);
RETURN myId;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getContinentAverageVars
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE DEFINER=`hadoop`@`localhost` PROCEDURE `getContinentAverageVars`(pContinent INT, pDecade INT, pVariable INT)
BEGIN
	SELECT continent, decade, CONCAT_WS(' - ', Variable.variable, Variable.description) AS var,
		value
	FROM Continent, Variable, ContinentAverageVar
    WHERE (Continent.idContinent = ContinentAverageVar.idContinent) and
        (ContinentAverageVar.idVariable = Variable.idVariable) and
		(pVariable is null or ContinentAverageVar.idVariable = pVariable) and
        (pContinent is null or Continent.idContinent = pContinent) and
        (pDecade is null or ContinentAverageVar.decade = pDecade)
	ORDER BY continent ASC, decade ASC;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure getContinentStations
-- -----------------------------------------------------

DELIMITER $$
USE `HadoopClimate`$$
CREATE PROCEDURE `getContinentStations`(pContinent INT)
BEGIN
	SELECT idStation, CONCAT_WS(' - ', stationOrCityName, stationNumber) as stationName
    FROM Station
	INNER JOIN Country
    ON Station.idCountry = Country.idCountry AND pContinent = Country.idContinent
    ORDER BY stationName ASC;
END$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
