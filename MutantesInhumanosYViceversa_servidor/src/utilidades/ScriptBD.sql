--Crear la BD
CREATE DATABASE citas

--Usamos la BD
USE citas

-----------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------
--Tablas de informacion para la aplicación
-----------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------Sexo
CREATE TABLE [SEXO](
	[CODE] [int] NOT NULL,
	[DESCRIPTION] [varchar](50) NOT NULL,
PRIMARY KEY ([CODE] ASC))

insert into SEXO (CODE,DESCRIPTION) values
(1,'Hombre'),
(2,'Mujer');

select * from SEXO;

-----------------------------------------------------------------------------------------------------Rol
CREATE TABLE [ROL](
	[CODE] [int] NOT NULL,
	[DESCRIPTION] [varchar](50) NOT NULL,
PRIMARY KEY ([CODE] ASC))

insert into ROL (CODE,DESCRIPTION) values
(1,'SuperAdmin'),
(2,'Admin'),
(3,'Usuario');

select * from ROL;

-----------------------------------------------------------------------------------------------------Relacion
CREATE TABLE [RELACION](
	[CODE] [int] NOT NULL,
	[DESCRIPTION] [varchar](50) NOT NULL,
PRIMARY KEY ([CODE] ASC))

insert into RELACION (CODE,DESCRIPTION) values
(1,'Seria'),
(2,'Esporádica');

select * from RELACION;

-----------------------------------------------------------------------------------------------------Interes
CREATE TABLE [INTERES](
	[CODE] [int] NOT NULL,
	[DESCRIPTION] [varchar](50) NOT NULL,
PRIMARY KEY ([CODE] ASC))

insert into INTERES (CODE,DESCRIPTION) values
(1,'Hombres'),
(2,'Mujeres'),
(3,'Ambos');

select * from INTERES;

-----------------------------------------------------------------------------------------------------Hijos
CREATE TABLE [HIJOS](
	[CODE] [int] NOT NULL,
	[DESCRIPTION] [varchar](50) NOT NULL,
PRIMARY KEY ([CODE] ASC))

insert into HIJOS (CODE,DESCRIPTION) values
(1,'Tengo hijos'),
(2,'No tengo hijos'),
(3,'Me gustaría tener hijos'),
(4,'No quiero tener hijos');

select * from HIJOS;


-----------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------
--Tablas para la gestion de la aplicación
-----------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------Usuario
CREATE TABLE [USUARIO](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[EMAIL] [varchar](100) NOT NULL,
	[PWD] [varchar](100) NOT NULL,
	[ROL] [int] NOT NULL,
	[ACTIVO] [bit] NOT NULL default 0,
PRIMARY KEY ([ID] ASC))

ALTER TABLE [USUARIO]  WITH CHECK ADD FOREIGN KEY([ROL])
REFERENCES [ROL] ([CODE])


insert into USUARIO (EMAIL,PWD,ROL,ACTIVO) values
('admin@admin.es','admin',1,1);

select * from USUARIO;


--PRUEBAS
DELETE FROM USUARIO WHERE ID = 2
DELETE FROM INFORMACION_USUARIO WHERE ID_USUARIO = 3;

SELECT * FROM USUARIO WHERE EMAIL = 'admin@admin.es' AND PWD = 'admin'
SELECT * FROM INFORMACION_USUARIO

INSERT INTO INFORMACION_USUARIO VALUES (11,'ver',50,50,50,1,1,1,1,null,null)

-----------------------------------------------------------------------------------------------------Informacion del usuario

CREATE TABLE [INFORMACION_USUARIO](
	[ID_USUARIO] [int] NOT NULL,
	[NICK] [varchar](100) NOT NULL,
	[DEPORTE] [int] NOT NULL,
	[ARTE] [int] NOT NULL,
    [POLITICA] [int] NOT NULL,
	[RELACION] [int] NOT NULL,
	[HIJOS] [int] NOT NULL,
    [SEXO] [int] NOT NULL,
	[INTERES] [int] NOT NULL,
	[FOTO] [varbinary](max) NULL,
	[FECHA_ACCESO] [varchar](16) NULL,
PRIMARY KEY ([ID_USUARIO] ASC))

ALTER TABLE [INFORMACION_USUARIO]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO])
REFERENCES [USUARIO] ([ID])

ALTER TABLE [INFORMACION_USUARIO]  WITH CHECK ADD FOREIGN KEY([RELACION])
REFERENCES [RELACION] ([CODE])

ALTER TABLE [INFORMACION_USUARIO]  WITH CHECK ADD FOREIGN KEY([HIJOS])
REFERENCES [HIJOS] ([CODE])

ALTER TABLE [INFORMACION_USUARIO]  WITH CHECK ADD FOREIGN KEY([SEXO])
REFERENCES [SEXO] ([CODE])

ALTER TABLE [INFORMACION_USUARIO]  WITH CHECK ADD FOREIGN KEY([INTERES])
REFERENCES [INTERES] ([CODE])

-----------------------------------------------------------------------------------------------------Conexion

CREATE TABLE [CONEXION](
	[ID_USUARIO] [int] NOT NULL,
	[FECHA_CONEXION] [varchar](16) NOT NULL,
PRIMARY KEY ([ID_USUARIO] ASC))

ALTER TABLE [CONEXION]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO])
REFERENCES [USUARIO] ([ID])

-----------------------------------------------------------------------------------------------------Me gusta
CREATE TABLE [ME_GUSTA](
	[ID_USUARIO] [int] NOT NULL,
	[ID_USUARIO_GUSTA] [int] NOT NULL,
PRIMARY KEY ([ID_USUARIO], [ID_USUARIO_GUSTA] ASC))

ALTER TABLE [ME_GUSTA]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO])
REFERENCES [USUARIO] ([ID])

ALTER TABLE [ME_GUSTA]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO_GUSTA])
REFERENCES [USUARIO] ([ID])

-----------------------------------------------------------------------------------------------------Amigos

CREATE TABLE [AMIGOS](
	[ID_USUARIO] [int] NOT NULL,
	[ID_USUARIO_AMIGO] [int] NOT NULL,
PRIMARY KEY ([ID_USUARIO],[ID_USUARIO_AMIGO] ASC))

ALTER TABLE [AMIGOS]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO])
REFERENCES [USUARIO] ([ID])

ALTER TABLE [AMIGOS]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO_AMIGO])
REFERENCES [USUARIO] ([ID])


-----------------------------------------------------------------------------------------------------Mensajes
CREATE TABLE [MENSAJE](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_USUARIO_EMISOR] [int] NOT NULL,
	[ID_USUARIO_RECEPTOR] [int] NOT NULL,
	[MENSAJE] [varchar](max) NOT NULL,	
	[FECHA_ACCESO] [datetime] NOT NULL,
	[LEIDO] [bit] NOT NULL default 0,
PRIMARY KEY ([ID] ASC))

ALTER TABLE [MENSAJE]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO_EMISOR])
REFERENCES [USUARIO] ([ID])

ALTER TABLE [MENSAJE]  WITH CHECK ADD FOREIGN KEY([ID_USUARIO_RECEPTOR])
REFERENCES [USUARIO] ([ID])


-----------------------------------------------------------------------------------------------------Documentacion Mensajes

CREATE TABLE [DOCUMENTO_MENSAJE](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[ID_MENSAJE] [int] NOT NULL,	
	[FOTO] [varbinary](max) NOT NULL,
PRIMARY KEY ([ID] ASC))

ALTER TABLE [DOCUMENTO_MENSAJE]  WITH CHECK ADD FOREIGN KEY([ID_MENSAJE])
REFERENCES [MENSAJE] ([ID])