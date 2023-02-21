\c postgres





DROP DATABASE veterinaria;





CREATE DATABASE veterinaria;


\c veterinaria


CREATE TABLE Razas(
                      id_raza SERIAL PRIMARY KEY,
                      nombre CHAR(30) NOT NULL
);

--otro dia con mas calma a;adimos lo de adopcion
CREATE TABLE Dueños(
                       rfc_dueño CHAR(13) PRIMARY KEY,
                       nombre CHAR(30) NOT NULL,
                       apellido_p CHAR(30) NOT NULL,
                       apellido_m CHAR(30)
);

CREATE TABLE Mascotas(
                         curp_mascota CHAR(13) PRIMARY KEY,
                         nombre CHAR(30) NOT NULL,
                         fecha_nacimiento DATE NOT NULL,
                         edad INTEGER NOT NULL,
                         sexo BOOLEAN,
                         rfc_dueño CHAR(13) REFERENCES dueños,
                         id_raza INTEGER REFERENCES razas
);

CREATE TABLE Alimentos(
                          id_al SERIAL PRIMARY KEY,
                          nombre CHAR(30) NOT NULL,
                          costo REAL NOT NULL,
                          gramaje REAL NOT NULL,
                          descripcion CHAR(100) DEFAULT ''
);

CREATE TABLE Tipo_Producto(
                              id_tp SERIAL PRIMARY KEY,
                              tipo CHAR(30) NOT NULL
);

CREATE TABLE Productos(
                          id_p SERIAL NOT NULL PRIMARY KEY,
                          id_tp INTEGER NOT NULL REFERENCES tipo_producto,
                          nombre CHAR(30) NOT NULL,
                          costo REAL NOT NULL,
                          descripcion CHAR(100) DEFAULT ''
);

CREATE TABLE Medicamentos(
                             id_m text PRIMARY KEY,
                             nombre CHAR(30) NOT NULL,
                             costo REAL NOT NULL,
                             gramaje REAL NOT NULL,
                             laboratorio CHAR(30) NOT NULL,
                             descripcion CHAR(100) DEFAULT '',
                             tipo varchar(25) NOT NULL
);

CREATE TABLE Empleados(
                          rfc CHAR(13) NOT NULL PRIMARY KEY,
                          nombre CHAR(30) NOT NULL,
                          apellido_p CHAR(30) NOT NULL,
                          apellido_m CHAR(30) NOT NULL,
                          fecha_ini DATE NOT NULL,
                          jor_ini TIME NOT NULL,
                          jor_fin TIME NOT NULL
);

--como enn el git
CREATE TABLE Veterinarios(
                             rfc_veterinario CHAR(13) NOT NULL REFERENCES empleados,
                             PRIMARY KEY(rfc_veterinario)
);

CREATE TABLE Especialidades(
                               id_especialidad serial not null primary key,
                               nombre varchar(30) not null
);

CREATE TABLE Veterinarios_especialiadades(
                                             rfc char(13) REFERENCES veterinarios,
                                             especialidad integer REFERENCES especialidades,
                                             PRIMARY KEY(rfc,especialidad)
);

CREATE TABLE Nominas(
                        id_nomina SERIAL NOT NULL,
                        rfc varchar(13) NOT NULL REFERENCES empleados,
                        fecha DATE NOT NULL,
                        total_horas smallint NOT NULL,
                        salario REAL NOT NULL,
                        total_bono REAL DEFAULT 0,
                        PRIMARY KEY(id_nomina, rfc)
);

CREATE TABLE proveedores(
                            id_proveedores SERIAL PRIMARY KEY,
                            nombre varchar(30) NOT NULL,
                            direccion varchar(50),
                            telefono INTEGER not null,
                            descripcion varchar(40) not null
);

CREATE TABLE facturas(
                         id_factura SERIAL PRIMARY KEY,
                         id_proveedor INTEGER NOT NULL REFERENCES proveedores,
                         fecha DATE NOT NULL,
                         monto_total REAL NOT NULL
);


CREATE TABLE alimentos_factura(
                                  id_alimento INTEGER REFERENCES alimentos,
                                  id_factura INTEGER REFERENCES facturas,
                                  cantidad INTEGER NOT NULL,
                                  subtotal REAL NOT NULL,
                                  PRIMARY KEY(id_alimento, id_factura)
);



CREATE TABLE productos_factura(
                                  id_producto INTEGER REFERENCES productos,
                                  id_factura INTEGER REFERENCES facturas,
                                  cantidad INTEGER NOT NULL,
                                  subtotal REAL NOT NULL,
                                  PRIMARY KEY(id_producto, id_factura)
);


CREATE TABLE medicamentos_factura(
                                     id_medicamento text REFERENCES medicamentos,
                                     id_factura INTEGER REFERENCES facturas,
                                     cantidad INTEGER NOT NULL,
                                     subtotal REAL NOT NULL,
                                     PRIMARY KEY(id_medicamento, id_factura)
);

CREATE TABLE alimentos_stock(
                                id_alimento INTEGER REFERENCES alimentos,
                                caducidad DATE NOT NULL,
                                cantidad INTEGER NOT NULL,
                                PRIMARY KEY(id_alimento,caducidad)
);

CREATE TABLE productos_stock(
                                id_producto INTEGER REFERENCES productos,
                                caducidad DATE ,
                                cantidad INTEGER NOT NULL,
                                PRIMARY KEY(id_producto, caducidad)
);

CREATE TABLE medicamentos_stock(
                                   id_medicamento text REFERENCES medicamentos,
                                   caducidad DATE ,
                                   cantidad INTEGER NOT NULL,
                                   PRIMARY KEY(id_medicamento, caducidad)
);


CREATE TABLE formas_pago(
                            id_forma_pago SERIAL PRIMARY KEY,
                            forma CHAR(30) NOT NULL
);


CREATE TABLE tickets(
                        id_ticket SERIAL PRIMARY KEY,
                        id_forma_pago INTEGER REFERENCES formas_pago,
                        monto_total REAL NOT NULL,
                        fecha_cobro DATE NOT NULL,
                        hora_cobro TIME NOT NULL
);

CREATE TABLE alimentos_vendidos(
                                   id_ticket INTEGER REFERENCES tickets,
                                   id_registro serial,
                                   id_alimento INTEGER REFERENCES alimentos,
                                   cantidad INTEGER NOT NULL,
                                   subtotal REAL NOT NULL,
                                   PRIMARY KEY(id_ticket, id_registro)
);

CREATE TABLE productos_vendidos(
                                   id_ticket INTEGER REFERENCES tickets,
                                   id_registro serial,
                                   id_producto INTEGER REFERENCES productos,
                                   cantidad INTEGER NOT NULL,
                                   subtotal REAL NOT NULL,
                                   PRIMARY KEY(id_ticket, id_registro)
);

CREATE TABLE vacunas_expediente(
                                   id_registro serial,
                                   curp_mascota CHAR(13) REFERENCES mascotas,
                                   fecha_vacuna DATE NOT NULL,
                                   id_vacuna text NOT NULL REFERENCES medicamentos
                                       primary key(id_registro, curp_mascota)
);

CREATE TABLE estatus(
                        id_estatus SERIAL PRIMARY KEY,
                        estatus CHAR(20) NOT NULL
);

CREATE TABLE citas(
                      id_cita serial,
                      curp_mascota CHAR(13) REFERENCES mascotas,
                      rfc_veterinario CHAR(13) REFERENCES veterinarios,
                      fecha DATE NOT NULL,
                      hora TIME NOT NULL,
                      monto REAL NOT NULL,
                      detalle CHAR(100) DEFAULT '',
                      id_ticket INTEGER REFERENCES tickets,
                      id_estatus INTEGER REFERENCES estatus,
                      PRIMARY KEY(id_cita, curp_mascota)
);