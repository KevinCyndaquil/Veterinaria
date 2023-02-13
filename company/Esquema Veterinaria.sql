\c postgres





DROP DATABASE veterinaria;





CREATE DATABASE veterinaria;





\c veterinaria





CREATE TABLE animales(
	ida SERIAL NOT NULL PRIMARY KEY,
	nombre CHAR(20) NOT NULL,
	nombre_cientifico CHAR(50) NOT NULL
);





CREATE TABLE razas(
	idr SERIAL NOT NULL PRIMARY KEY,
	nombre CHAR(30) NOT NULL,
	total_adopcion INTEGER
);





CREATE TABLE dueños(
	rfc CHAR(13) NOT NULL PRIMARY KEY,
	nombre CHAR(30) NOT NULL,
	apellidos CHAR(40) NOT NULL
);





CREATE TABLE mascotas(
	curpm CHAR(13) NOT NULL PRIMARY KEY,
	nombre CHAR(30) NOT NULL,
	fecha_nacimiento DATE NOT NULL,
	edad INTEGER NOT NULL,
	sexo BOOLEAN,
	rfc_dueño CHAR(13) NOT NULL REFERENCES dueños,
	idr INTEGER NOT NULL REFERENCES razas
);





CREATE TABLE alimentos(
	idal SERIAL NOT NULL PRIMARY KEY,
	nombre CHAR(30) NOT NULL,
	costo REAL NOT NULL,
	gramaje REAL NOT NULL,
	descripcion CHAR(100) DEFAULT ''
);





CREATE TABLE tipo_producto(
	idtp SERIAL NOT NULL PRIMARY KEY,
	tipo CHAR(30) NOT NULL
);





CREATE TABLE productos(
	idp SERIAL NOT NULL PRIMARY KEY,
	idtp INTEGER NOT NULL REFERENCES tipo_producto,
	nombre CHAR(30) NOT NULL,
	costo REAL NOT NULL,
	descripcion CHAR(100) DEFAULT ''
);





CREATE TABLE medicamentos(
	idm SERIAL NOT NULL PRIMARY KEY,
	nombre CHAR(30) NOT NULL,
	costo REAL NOT NULL,
	gramaje REAL NOT NULL,
	laboratorio CHAR(30) NOT NULL,
	descripcion CHAR(100) DEFAULT ''
);





CREATE TABLE empleados(
	rfc CHAR(13) NOT NULL PRIMARY KEY,
	nombre CHAR(20) NOT NULL,
	apellidos CHAR(30) NOT NULL,
	fecha_ini DATE NOT NULL,
	jor_ini TIME NOT NULL,
	jor_fin TIME NOT NULL
);





CREATE TABLE veterinarios(
	rfc CHAR(13) NOT NULL REFERENCES empleados,
	PRIMARY KEY(rfc),
	especialidad CHAR(30) NOT NULL
);





CREATE TABLE nominas(
	rfc CHAR(13) NOT NULL REFERENCES empleados,
	cnsn SERIAL NOT NULL,
	PRIMARY KEY(rfc, cnsn),
	fecha DATE NOT NULL,
	total_horas INTEGER NOT NULL,
	salario REAL NOT NULL,
	total_bono REAL DEFAULT 0
);





CREATE TABLE proveedores(
	idpr SERIAL NOT NULL PRIMARY KEY,
	nombre CHAR(30) NOT NULL
);





CREATE TABLE facturas(
	idf SERIAL NOT NULL PRIMARY KEY,
	idpr INTEGER NOT NULL REFERENCES proveedores,
	fecha DATE NOT NULL,
	monto_total REAL NOT NULL
);





CREATE TABLE alimentos_factura(
	idal INTEGER NOT NULL REFERENCES alimentos,
	cnsaf INTEGER NOT NULL,
	PRIMARY KEY(idal, cnsaf),
	subtotal REAL NOT NULL,
	cantidad INTEGER NOT NULL
);





CREATE TABLE productos_factura(
	idp INTEGER NOT NULL REFERENCES productos,
	cnspf INTEGER NOT NULL,
	PRIMARY KEY(idp, cnspf),
	subtotal REAL NOT NULL,
	cantidad INTEGER NOT NULL
);





CREATE TABLE medicamentos_factura(
	idm INTEGER NOT NULL REFERENCES medicamentos,
	cnsmf INTEGER NOT NULL,
	PRIMARY KEY(idm, cnsmf),
	subtotal REAL NOT NULL,
	cantidad INTEGER NOT NULL
);





CREATE TABLE alimentos_stock(
	idal INTEGER NOT NULL REFERENCES alimentos,
	caducidad DATE NOT NULL,
	PRIMARY KEY(idal, caducidad),
	cantidad INTEGER NOT NULL
);





CREATE TABLE productos_stock(
	idp INTEGER NOT NULL REFERENCES productos,
	caducidad DATE NOT NULL,
	PRIMARY KEY(idp, caducidad),
	cantidad INTEGER NOT NULL
);





CREATE TABLE medicamentos_stock(
	idm INTEGER NOT NULL REFERENCES medicamentos,
	caducidad DATE NOT NULL,
	PRIMARY KEY(idm, caducidad),
	cantidad INTEGER NOT NULL
);





CREATE TABLE formas_pago(
	idfp SERIAL NOT NULL PRIMARY KEY,
	forma CHAR(30) NOT NULL
);





CREATE TABLE tickets(
	idt SERIAL NOT NULL PRIMARY KEY,
	idfp INTEGER NOT NULL REFERENCES formas_pago,
	monto_total REAL NOT NULL,
	fecha_cobro DATE NOT NULL,
	hora_cobro TIME NOT NULL
);





CREATE TABLE alimentos_comprados(
	idt INTEGER NOT NULL REFERENCES tickets,
	cnsac INTEGER NOT NULL,
	PRIMARY KEY(idt, cnsac),
	cantidad INTEGER NOT NULL,
	idal INTEGER NOT NULL REFERENCES alimentos
);





CREATE TABLE productos_comprados(
	idt INTEGER NOT NULL REFERENCES tickets,
	cnspc INTEGER NOT NULL,
	PRIMARY KEY(idt, cnspc),
	cantidad INTEGER NOT NULL,
	idp INTEGER NOT NULL REFERENCES productos
);





CREATE TABLE vacunas_aplicadas(
	curpm CHAR(13) NOT NULL REFERENCES mascotas,
	cnsv INTEGER NOT NULL,
	PRIMARY KEY(curpm, cnsv),
	fecha_apl DATE NOT NULL,
	idm INTEGER NOT NULL REFERENCES medicamentos
);





CREATE TABLE estatus(
	ide SERIAL NOT NULL PRIMARY KEY,
	estatus CHAR(20) NOT NULL
);





CREATE TABLE citas(
	curpm CHAR(13) NOT NULL REFERENCES mascotas,
	cnsc INTEGER NOT NULL,
	PRIMARY KEY(curpm, cnsc),
	fecha DATE NOT NULL,
	hora TIME NOT NULL,
	monto REAL NOT NULL,
	ide INTEGER NOT NULL REFERENCES estatus,
	rfc_doctor CHAR(13) NOT NULL REFERENCES veterinarios,
	idt INTEGER REFERENCES tickets,
	detalle CHAR(100) DEFAULT ''
);





CREATE TABLE alimentos_recetados(
	curpm CHAR(13) NOT NULL,
	cnsc INTEGER NOT NULL,
	FOREIGN KEY(curpm, cnsc) REFERENCES citas,
	cnsare INTEGER NOT NULL,
	PRIMARY KEY(curpm, cnsc, cnsare),
	total INTEGER NOT NULL,
	dias INTEGER NOT NULL,
	frecuencia REAL NOT NULL,
	can_fre INTEGER NOT NULL,
	idal INTEGER NOT NULL REFERENCES alimentos
);





CREATE TABLE medicamentos_recetados(
	curpm CHAR(13) NOT NULL,
	cnsc INTEGER NOT NULL,
	FOREIGN KEY(curpm, cnsc) REFERENCES citas,
	cnsmre INTEGER NOT NULL,
	PRIMARY KEY(curpm, cnsc, cnsmre),
	total INTEGER NOT NULL,
	dias INTEGER NOT NULL,
	frecuencia REAL NOT NULL,
	can_fre INTEGER NOT NULL,
	idm INTEGER NOT NULL REFERENCES medicamentos
);





CREATE TABLE servicios(
	ids SERIAL NOT NULL PRIMARY KEY,
	servicio CHAR(30) NOT NULL
);





CREATE TABLE pago_servicio(
	idps SERIAL NOT NULL PRIMARY KEY,
	ids INTEGER NOT NULL REFERENCES servicios,
	fecha DATE NOT NULL,
	costo REAL NOT NULL
);