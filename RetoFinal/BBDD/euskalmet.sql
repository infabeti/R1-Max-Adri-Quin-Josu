create database euskalmet collate utf8mb4_spanish_ci;

use euskalmet;

create table USUARIOS
(idUser varchar(20) primary key,
password varchar(10) not null,
recuperacion varchar(60));

create table PROVINCIAS
(id int(2) primary key,
nombre varchar(10) not null);

create table MUNICIPIOS
(nombre varchar(50) primary key,
idProvincia int(2) not null,
descripcion mediumtext,
latitud double,
longitud double,
constraint fk_idProvincia foreign key(idProvincia) references PROVINCIAS(id) on delete cascade on update cascade);

create table ESPACIOS_NATURALES
(nombre varchar(50) primary key,
descripcion mediumtext,
latitud double,
longitud double,
tipo varchar(50),
web varchar(200));

create table EXISTE
(nomMunicipio varchar(50),
nomEspNat varchar(50),
constraint fk_nomMunicipio foreign key(nomMunicipio) references MUNICIPIOS(nombre) on delete cascade on update cascade,
constraint fk_nomEspNat foreign key(nomEspNat) references ESPACIOS_NATURALES(nombre) on delete cascade on update cascade,
constraint pk_existe primary key (nomMunicipio,nomEspNat));

create table ES_FAVORITO_MUN
(idUser varchar(20),
nomMunicipio varchar(50),
constraint fk_idUser2 foreign key(idUser) references USUARIOS(idUser) on delete cascade on update cascade,
constraint fk_nomMunicipio4 foreign key(nomMunicipio) references MUNICIPIOS(nombre) on delete cascade on update cascade,
constraint pk_es_favorito_mun primary key(idUser,nomMunicipio));

create table ES_FAVORITO_ESP
(idUser varchar(20),
nomEspNat varchar(50),
constraint fk_idUser3 foreign key(idUser) references USUARIOS(idUser) on delete cascade on update cascade,
constraint fk_nomEspNat2 foreign key(nomEspNat) references ESPACIOS_NATURALES(nombre) on delete cascade on update cascade,
constraint pk_es_favorito_esp primary key(idUser,nomEspNat));

create table ESTACIONES_METEREOLOGICAS
(nombre varchar(50) primary key,
direccion varchar(200),
latitud double,
longitud double,
nomMunicipio varchar(50) not null,
constraint fk_nomMunicipio2 foreign key(nomMunicipio) references MUNICIPIOS(nombre) on delete cascade on update cascade);

create table CALIDAD_AIRE
(nomEstMet varchar(50) primary key,
Comgm3 varchar(20), 
CO8hmgm3 varchar(20), 
Nogm3 varchar(20), 
NO2gm3 varchar(20), 
NOXgm3 varchar(20), 
PM10gm3 varchar(20), 
PM25gm3 varchar(20), 
SO2gm3 varchar(20),
fecha_hora varchar(20),
constraint fk_nomEstMet foreign key(nomEstMet) references ESTACIONES_METEREOLOGICAS(nombre) on delete cascade on update cascade);

create table FOTOS
(id int AUTO_INCREMENT primary key,
nomMunicipio varchar(50),
idUser varchar(20),
foto mediumtext,
constraint fk_idUser foreign key(idUser) references USUARIOS(idUser) on update cascade,
constraint fk_nomMunicipio3 foreign key(nomMunicipio) references MUNICIPIOS(nombre) on delete cascade on update cascade);


insert into provincias values (1,'Álava');
insert into provincias values (20,'Gipuzkoa');
insert into provincias values (48,'Bizkaia');