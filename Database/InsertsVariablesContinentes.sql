-- Please, keep in mind to use case sensitive
USE HadoopClimate;

-- Inserts for Variable Table
INSERT INTO Variable(variable, description) VALUES ('T', 'Average annual temperature');
INSERT INTO Variable(variable, description) VALUES ('TM', 'Annual average maximum temperature');
INSERT INTO Variable(variable, description) VALUES ('Tm', 'Average annual minimum temperature');
INSERT INTO Variable(variable, description) VALUES ('PP', 'Rain or snow precipitation total annual');
INSERT INTO Variable(variable, description) VALUES ('V', 'Annual average wind speed');
INSERT INTO Variable(variable, description) VALUES ('RA', 'Number of days with rain');
INSERT INTO Variable(variable, description) VALUES ('SN', 'Number of days with snow');
INSERT INTO Variable(variable, description) VALUES ('TS', 'Number of days with storm');
INSERT INTO Variable(variable, description) VALUES ('FG', 'Number of foggy days');
INSERT INTO Variable(variable, description) VALUES ('TN', 'Number of days with tornado');
INSERT INTO Variable(variable, description) VALUES ('GR', 'Number of days with hail');

-- Inserts for Continent Table
INSERT INTO Continent(continent) VALUES ('Africa');
INSERT INTO Continent(continent) VALUES ('Antarctica');
INSERT INTO Continent(continent) VALUES ('Asia');
INSERT INTO Continent(continent) VALUES ('Europe');
INSERT INTO Continent(continent) VALUES ('North America');
INSERT INTO Continent(continent) VALUES ('Oceania');
INSERT INTO Continent(continent) VALUES ('South America');