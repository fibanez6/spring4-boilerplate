INSERT INTO Country (id, country, code) VALUES (1, 'United Kingdom', 'GB');
INSERT INTO Country (id, country, code) VALUES (2, 'United States of America', 'US');
INSERT INTO Country (id, country, code) VALUES (3, 'China', 'CN');

INSERT INTO Country_Code (id, country, code) VALUES (1, 1, 'UK');
INSERT INTO Country_Code (id, country, code) VALUES (2, 1, 'gb');
INSERT INTO Country_Code (id, country, code) VALUES (3, 2, 'USA');
INSERT INTO Country_Code (id, country, code) VALUES (4, 2, 'us');
INSERT INTO Country_Code (id, country, code) VALUES (5, 3, 'CHN');
INSERT INTO Country_Code (id, country, code) VALUES (6, 3, 'ch');

INSERT INTO Account (account_id, name) VALUES (1, 'Amazing Apps');
INSERT INTO Account (account_id, name) VALUES (2, 'Game studio');

INSERT INTO Network (id, name) VALUES (1, 'network_A');
INSERT INTO Network (id, name) VALUES (2, 'network_B');

INSERT INTO Network_Countrycodes(network_id, country_code_id)  VALUES (1, 1);
INSERT INTO Network_Countrycodes(network_id, country_code_id)  VALUES (1, 3);
INSERT INTO Network_Countrycodes(network_id, country_code_id)  VALUES (2, 2);
INSERT INTO Network_Countrycodes(network_id, country_code_id)  VALUES (2, 4);

INSERT INTO Ad_format (id, format) VALUES (1, 'banner');
INSERT INTO Ad_format (id, format) VALUES (2, 'video');

INSERT INTO Network_Token_Auth (net_Account_id, network, token, account_id) VALUES (1, 1, 'aaa', 1);
INSERT INTO Network_Token_Auth (net_Account_id, network, token, account_id) VALUES (2, 2, 'bbb', 1);

INSERT INTO Application (id, name, account) VALUES (1, 'Flappy bird', 1);
INSERT INTO Application (id, name, account) VALUES (2, 'Fast cars', 1);
INSERT INTO Application (id, name, account) VALUES (3, 'Happy cards', 1);
INSERT INTO Application (id, name, account) VALUES (4, 'Tap tap frenzy', 1);

INSERT INTO Mediation (id, network, application) VALUES (1, 1, 1);
INSERT INTO Mediation (id, network, application) VALUES (2, 2, 1);
INSERT INTO Mediation (id, network, application) VALUES (3, 1, 2);
INSERT INTO Mediation (id, network, application) VALUES (4, 2, 2);
INSERT INTO Mediation (id, network, application) VALUES (5, 1, 3);

INSERT INTO Mediation (id, network, application) VALUES (6, 1, 4);
INSERT INTO Mediation (id, network, application) VALUES (7, 2, 4);

INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (1, true, 1, 1);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (2, true, 2, 1);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (3, true, 1, 2);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (4, true, 2, 2);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (5, true, 1, 3);

INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (6, false, 1, 4);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (7, true, 2, 4);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (8, true, 2, 5);

INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (9, true, 1, 6);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (10, true, 2, 6);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (11, true, 1, 7);
INSERT INTO Ad_Config (id, enable, format, mediation) VALUES (12, true, 2, 7);