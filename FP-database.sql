CREATE SCHEMA IF NOT EXISTS `Fellesprosjekt`;
USE `Fellesprosjekt`;

-- -----------------------------------------------------
-- Table `Bruker`
-- -----------------------------------------------------
CREATE TABLE `Bruker` (
  `Brukernavn` VARCHAR(20) NOT NULL ,
  `Passord` VARCHAR(45) NOT NULL ,
  `Fornavn` VARCHAR(45) NOT NULL ,
  `Etternavn` VARCHAR(45) NOT NULL ,
  `E-post` VARCHAR(45) NOT NULL ,
  `Telefon` VARCHAR(8) NOT NULL ,
  PRIMARY KEY (`Brukernavn`));


-- -----------------------------------------------------
-- Table `Møterom`
-- -----------------------------------------------------
CREATE  TABLE `Møterom` (
  `Romnavn` VARCHAR(40) NOT NULL ,
  `Størrelse` INT NOT NULL ,
  PRIMARY KEY (`Romnavn`));

-- -----------------------------------------------------
-- Table`Gruppe`
-- -----------------------------------------------------

CREATE  TABLE `Gruppe` (
  `GruppeID` INT NOT NULL ,
  `Tillegsgruppe` VARCHAR(45) NULL ,
  `Brukernavn` VARCHAR(20) NULL,
  PRIMARY KEY (`GruppeID`),
  FOREIGN KEY (`Brukernavn`) REFERENCES Bruker(`Brukernavn`)
);
   
-- -----------------------------------------------------
-- Table`Gruppemedlem`
-- -----------------------------------------------------

CREATE TABLE `Gruppemedlem` (
  `GruppeID` INT NOT NULL ,
  `Brukernavn` varchar(20) NOT NULL,
  PRIMARY KEY (`Brukernavn`,`GruppeID`),
  FOREIGN KEY (`Brukernavn`) REFERENCES Bruker(`Brukernavn`),
  FOREIGN KEY (`GruppeID`) REFERENCES Gruppe(`GruppeID`)
);

-- -----------------------------------------------------
-- Table`Avtale`
-- -----------------------------------------------------

CREATE  TABLE `Avtale` (
  `AvtaleID` INT NOT NULL ,
  `Dato` DATE NOT NULL ,
  `Starttid` TIME NOT NULL ,
  `Slutttid` TIME NOT NULL ,
  `Beskrivelse` VARCHAR(45) NOT NULL,
  `Romnavn` VARCHAR(40) NOT NULL,
  `Brukernavn` VARCHAR(45),
  `GruppeID` INT,
  PRIMARY KEY (`AvtaleID`),
  FOREIGN KEY (`Romnavn`) REFERENCES Møterom(`Romnavn`),
  FOREIGN KEY (`Brukernavn`) REFERENCES Bruker(`Brukernavn`),
  FOREIGN KEY (`GruppeID`) REFERENCES Gruppe(`GruppeID`) 
);


-- -----------------------------------------------------
-- Table`Alarm`
-- -----------------------------------------------------

CREATE  TABLE `Alarm` (
  `Brukernavn` VARCHAR(20) NOT NULL,
  `AvtaleID` INT NOT NULL,
  `Tidspunkt` DATETIME NOT NULL,
  PRIMARY KEY (`Brukernavn`, `AvtaleID`),
  FOREIGN KEY (`Brukernavn`) REFERENCES Bruker(`Brukernavn`),
  FOREIGN KEY (`AvtaleID`) REFERENCES Avtale(`AvtaleID`)
);