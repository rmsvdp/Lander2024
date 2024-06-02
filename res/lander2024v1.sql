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
t_a double default 0, -- tren de ate
fuel double not null,-- deposito combustible
perfil_pot int default 0  -- Sólo se elige un perfil y no 
-- se guarda la elección. 
);
-- Modificada la clave principal

create table perfil_pot(
id_perfil int not null,
nivel int not null,
valor double not null,
primary key(id_perfil,nivel)
);

/** No usada en esta versión **/
create table confg_pot(
id_perfil int not null,-- fk
id_la int not null, -- fk
id_config int auto_increment primary key
);

create table escenario(
id_escenario int auto_increment primary key,
nombre varchar(24) not null, -- Nombre de la estrella/planeta
gravedad double not null, -- Gravedad del cuerpo astral
ve double not null, -- velocida de entrada
he double not null -- altura de entrada
);

create table simulacion(
id_sim int auto_increment primary key,
id_usuario int not null, -- fk
id_lander int not null,
id_escenario int not null,
fecha datetime not null
);



create table datos_sim(
id_ds int auto_increment primary key,
tiempo int not null,
vel double not null,
fuel double not null,
dist double not null,
id_sim int not null
);
 
create table puntuacion(
id_pun int auto_increment primary key,
id_usuario int not null,
id_simulacion int not null,
tiempo int not null,
fuel double not null,
fecha timestamp);

-- CONTRIBUCIONES AL DESARROLLO
create table contrib(
id_ctr int auto_increment primary key,
name VARCHAR(64),
phase VARCHAR(64)
);

-- ----------- CLAVES FORÁNEAS -----------

ALTER TABLE simulacion ADD FOREIGN KEY (id_usuario)   references usuario(id_usuario);
ALTER TABLE simulacion ADD FOREIGN KEY (id_lander)    references lander(id_lander);
ALTER TABLE simulacion ADD FOREIGN KEY (id_escenario) references escenario(id_escenario);
ALTER TABLE puntuacion ADD FOREIGN KEY (id_usuario)   references usuario(id_usuario);
ALTER TABLE puntuacion ADD FOREIGN KEY (id_simulacion)references simulacion(id_sim);
ALTER TABLE confg_pot  ADD FOREIGN KEY (id_la)        references lander(id_lander);
ALTER TABLE confg_pot  ADD FOREIGN KEY (id_perfil) 	  references perfil_pot(id_perfil);
ALTER TABLE datos_sim  ADD FOREIGN KEY (id_sim)      references simulacion(id_sim);

-- VISTAS
-- Listado de datos de simulación ordenados por simulación y tiempo (ascendente)

create view v_datosSim as 
select id_sim, tiempo as TIEMPO, vel as VELOCIDAD, fuel as FUEL, dist as DISTANCIA
from datos_sim
order by id_sim, tiempo ASC;

-- Listado de los detalles de una simulación

create view v_detalle_sim as 
select s.id_sim, s.fecha as FECHA, s.id_usuario, l.nombre as MODULO ,e.nombre as ESCENARIO
from simulacion s
inner join escenario e on e.id_escenario = s.id_escenario
inner join lander l on l.id_lander = s.id_lander;

-- Tabla de puntuaciones con escenario y lander

create view v_punt_full as 
select u.nick as JUGADOR, e.nombre as ESCENARIO, l.nombre, p.tiempo as TIEMPO, p.fuel as FUEL
from puntuacion p
inner join usuario u on u.id_usuario = p.id_usuario
inner join simulacion s on s.id_sim = p.id_simulacion
inner join escenario e on e.id_escenario = s.id_escenario
inner join lander l on l.id_lander = s.id_lander
;

-- FUNCIONES Y PROCEDIMIENTOS

