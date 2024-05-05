drop database if exists lander2024;
create database lander2024;

use lander2024;

-- Usuarios
create table usuario(
id_usuario int auto_increment primary key,
nick varchar(8) not null,
pwd varchar(16) not null,
grupo varchar(4) not null,
fechaUC date not null,
num_com int default 0,
estado tinyint default 0  -- 0 no esta jugando y 1 esta jugando
);

-- Lander
create table lander(
id_lander int auto_increment primary key,
nombre varchar(32) not null,
t_a float default 0, -- tren de ate
fuel int not null -- deposito combustible
);

create table perfil_pot(
id_perfil int primary key ,
nivel int not null,
valor int not null
);

create table confg_pot(
id_perfil int not null,-- fk
id_la int not null, -- fk
id_config int auto_increment primary key
);

create table escenario(
id_escenario int auto_increment primary key,
nombre varchar(24) not null, -- Nombre de la estrella/planeta
gravedad float not null, -- Gravedad del cuerpo astral
ve float not null, -- velocida de entrada
he int not null -- altura de entrada
);

create table simulacion(
id_sim int auto_increment primary key,
id_usuario int not null, -- fk
id_lander int not null,
id_escenario int not null,
fecha date not null
);



create table datos_sim(
tiempo int not null,
vel float not null,
fuel float not null,
dist float not null,
id_sim int not null
);

create table puntuacion(
id_usuario int not null,
tiempo int not null,
fuel float not null,
fecha timestamp);

-- CONTRIBUCIONES AL DESARROLLO
create table contrib(
name VARCHAR(64),
phase VARCHAR(64)
);

-- ----------- CLAVES FORÁNEAS -----------

ALTER TABLE simulacion ADD FOREIGN KEY (id_usuario)   references usuario(id_usuario);
ALTER TABLE simulacion ADD FOREIGN KEY (id_lander)    references lander(id_lander);
ALTER TABLE simulacion ADD FOREIGN KEY (id_escenario) references escenario(id_escenario);
ALTER TABLE puntuacion ADD FOREIGN KEY (id_usuario)   references usuario(id_usuario);
ALTER TABLE confg_pot  ADD FOREIGN KEY (id_la)        references lander(id_lander);
ALTER TABLE confg_pot  ADD FOREIGN KEY (id_perfil) references perfil_pot(id_perfil);
ALTER TABLE datos_sim  ADD FOREIGN KEY  (id_sim)      references simulacion(id_sim);

-- FUNCIONES Y PROCEDIMIENTOS

CREATE DEFINER=`root`@`%` FUNCTION `check_access`(_NICK varchar(8), _PWD varchar(16)) RETURNS int
    DETERMINISTIC
BEGIN
	DECLARE ID int DEFAULT -1;		-- Usuario no encontrado
    DECLARE FECHA date;
    DECLARE NC int;
    DECLARE _ESTADO int DEFAULT 0;	-- Usuario no está jugando

    SELECT COUNT(*) INTO ID FROM usuario
            WHERE nick = _NICK AND pwd = _PWD;

	IF ID=1 THEN 	-- Usuario encontrado
    call debug("Encontrado, hago select");
		SELECT id_usuario, fechaUC, num_com,estado INTO ID,FECHA, NC, _ESTADO
		FROM usuario
        WHERE nick = _NICK AND pwd = _PWD;

		IF _ESTADO = 0 THEN 
            call debug(concat("Entro con fecha =",FECHA));
			IF FECHA <> current_date() THEN

				UPDATE usuario  SET fechaUC = current_date(),
								num_com = 1, estado = 1
						WHERE id_usuario = ID; -- Actualizar la fecha de conexión
			ELSE	-- Es el mismo día, comprobar numero de conexiones
				IF NC > 3 THEN 
					SET ID = -2 ; -- Superado el límite de conexiones diarias
				END IF;
			END IF;
		ELSE
            SET ID= -1;   -- Usuario existe pero está jugando
		END IF;  -- _ESTADO = 0   
    END IF;
RETURN ID;
END