-- Datos de prueba
-- Tabla de usuarios
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('RMS','root','DIFC','1999-01-01');
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('PLAYER1','pwd1','1WET','1999-01-01');
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('PLAYER2','pwd2','1WET','1999-01-01');
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('PLAYER3','pwd3','1WET','1999-01-01');

-- Tabla de escenarios

INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('MOON',1.62,2.0,3500);
INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('MARS',3.71,0.0,2500);
INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('CALLISTO',1.235,4.3,3500);
INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('PLUTO',0,62,9.8,1750);

-- Tabla de Landers

INSERT INTO lander (nombre,t_a,fuel) VALUES ('APOLLO-XIII',24,2800);
INSERT INTO lander (nombre,t_a,fuel) VALUES ('PATHFINDER',48,5600);
INSERT INTO lander (nombre,t_a,fuel) VALUES ('MARS-V',24,2800);
INSERT INTO lander (nombre,t_a,fuel) VALUES ('EUROPA-VII',24,2800);

-- Periles de  Potencia
-- Perfil 1
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES (0,0,0,0,1,10,0,2,20,0,3,30,0,4,40,0,5,50,0,6,60,0,7,70,0,8,80,0,9,90);
-- Perfil 2
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES (1,0,0,1,1,10,1,2,20,1,3,30,1,4,40,1,5,50,1,6,60,1,7,70,1,8,80,1,9,90);
-- Perfil 3
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES (2,0,0,2,1,10,2,2,20,2,3,30,2,4,40,2,5,50,2,6,60,2,7,70,2,8,80,2,9,90);
-- Perfil 4
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES (3,0,0,3,1,10,3,2,20,3,3,30,3,4,40,3,5,50,3,6,60,3,7,70,3,8,80,3,9,90);