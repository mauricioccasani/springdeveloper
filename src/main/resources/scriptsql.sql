CREATE TABLE paciente(
	id smallserial,
	nombres VARCHAR(50)NOT NULL,
	apellidos VARCHAR(50)NOT NULL,
	dni VARCHAR(9)NOT NULL UNIQUE,
	numero_historial_clinico VARCHAR(10)NOT NULL,
	
	CONSTRAINT fk_proveedores PRIMARY KEY (id)
);

CREATE TABLE consulta_medica(
	id smallserial,
	fecha DATE NOT NULL,
	id_paciente smallint NOT NULL,
	id_doctor smallint NOT NULL,
	CONSTRAINT fk_consulta_medica PRIMARY KEY (id),
	CONSTRAINT fk_consulta_medica_doctor FOREIGN KEY(id_doctor)
	REFERENCES doctor(id)ON UPDATE RESTRICT ON DELETE RESTRICT,
	CONSTRAINT fk_consulta_medica_consulta_detalle FOREIGN KEY(id_paciente)
	REFERENCES paciente(id)ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE consulta_detalle(
	id smallserial,
	diagnostico VARCHAR(50)NOT NULL,
	tratamiento VARCHAR(50)NOT NULL,
	id_consulta_medica smallint NOT NULL,
	CONSTRAINT fk_consulta_detalle PRIMARY KEY (id),
	CONSTRAINT fk_consulta_detalle_consulta_medica FOREIGN KEY(id_consulta_medica)
	REFERENCES consulta_medica(id)ON UPDATE RESTRICT ON DELETE RESTRICT
);

CREATE TABLE especialidad(
	id smallserial,
	nombre VARCHAR(50)NOT NULL,
	CONSTRAINT fk_especialidad PRIMARY KEY (id)
);


CREATE TABLE doctor(
	id smallserial,
	nombres VARCHAR(50)NOT NULL,
	apellidos VARCHAR(50)NOT NULL,
	dni VARCHAR(9)NOT NULL UNIQUE,
	clave VARCHAR(100)NOT NULL,
	rol VARCHAR(20)NOT NULL,
	id_especialidad smallint NOT NULL,
	CONSTRAINT fk_doctor PRIMARY KEY (id),
	CONSTRAINT fk_doctor_especialidad FOREIGN KEY(id_especialidad)
	REFERENCES especialidad(id)ON UPDATE RESTRICT ON DELETE RESTRICT
);
