-- Datos de prueba
-- Tabla de usuarios
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('RMS','root','DIFC','1999-01-01');
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('PLAYER1','pwd1','1WET','1999-01-01');
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('PLAYER2','pwd2','1WET','1999-01-01');
INSERT INTO usuario (nick,pwd,grupo,fechaUC) VALUES('PLAYER3','pwd3','1WET','1999-01-01');

-- Tabla de escenarios

INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('MOON',1.62,2.0,3500.0);
INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('MARS',3.71,0.0,2500.0);
INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('CALLISTO',1.235,4.3,3500.0);
INSERT INTO escenario (nombre,gravedad,ve,he) VALUES ('PLUTO',0.62,9.8,1750.0);

-- Tabla de Landers

INSERT INTO lander (nombre,t_a,fuel) VALUES ('APOLLO-XIII',24.0,2800.0);
INSERT INTO lander (nombre,t_a,fuel) VALUES ('PATHFINDER',48.0,5600.0);
INSERT INTO lander (nombre,t_a,fuel) VALUES ('MARS-V',24.0,2800.0);
INSERT INTO lander (nombre,t_a,fuel) VALUES ('EUROPA-VII',24.0,2800.0);

-- Periles de  Potencia
-- Perfil 1
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES  (0,0,0.0),
			(0,1, 1.0),
            (0,2, 2.0),
            (0,3, 3.0),
            (0,4, 4.0),
            (0,5, 5.0),
            (0,6, 6.0),
            (0,7, 7.0),
            (0,8, 8.50),
            (0,9, 20.0);
/*
-- Perfil 2
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES (1,0,0.0, 1,1,1.0,1,2, 2.0,1, 3,3.0,1,4, 4.0,1,5, 5.0,1,6, 6.0,1, 7,7.0,1,8,1,  8.50,1,9, 20.0);
-- Perfil 3
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES (2,0,0.0, 2,1,1.0,2,2, 2.0,2, 3,3.0,2,4, 4.0,5,2, 5.0,2,6, 6.0,2, 7,7.0,2,8, 8.50,2,9, 20.0);
-- Perfil 4
INSERT INTO perfil_pot (id_perfil, nivel, valor) 
	VALUES (3,0,0.0,3, 1,1.0,3,2, 2.0,3, 3,3.0,3,4, 4.0,3,5, 5.0,3,6, 6.0, 3,7,7.0,3,8,  8.50,3,9, 20.0);
    */