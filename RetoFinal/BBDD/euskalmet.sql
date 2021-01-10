create database euskalmet collate utf8mb4_spanish_ci;

use euskalmet;

create table USUARIOS
(idUser varchar(20) primary key,
password varchar(10) not null);

create table PROVINCIAS
(id int(2) primary key,
nombre varchar(10) not null);

create table MUNICIPIOS
(nombre varchar(50) primary key,
idProvincia int(2),
constraint fk_idProvincia foreign key(idProvincia) references PROVINCIAS(id) on delete cascade on update cascade);

create table ESPACIOS_NATURALES
(nombre varchar(50) primary key,
descripcion varchar(2000),
tipo varchar(50),
web varchar(200),
nomMunicipio varchar(50),
constraint fk_nomMunicipio1 foreign key(nomMunicipio) references MUNICIPIOS(nombre) on update cascade);

create table ESTACIONES_METEREOLOGICAS
(nombre varchar(50) primary key,
direccion varchar(200),
latitud double,
longitud double,
nomMunicipio varchar(50),
constraint fk_nomMunicipio2 foreign key(nomMunicipio) references MUNICIPIOS(nombre) on delete cascade on update cascade);

create table FOTOS
(id varchar(200) primary key,
nomMunicipio varchar(50),
constraint fk_nomMunicipio3 foreign key(nomMunicipio) references MUNICIPIOS(nombre) on delete cascade on update cascade);

create table FOTOS_EUSKALMET
(id varchar(200) primary key,
constraint fk_FotosEuskalmet foreign key(id) references FOTOS(id) on delete cascade on update cascade);

create table FOTOS_USUARIOS
(id varchar(200) primary key,
idUser varchar(20),
constraint fk_FotosUsuarios foreign key(id) references FOTOS(id) on delete cascade on update cascade,
constraint fk_idUser foreign key(idUser) references USUARIOS(idUser) on update cascade);