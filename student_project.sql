DROP TABLE IF EXISTS st_child;
DROP TABLE IF EXISTS st_student_request;
DROP TABLE IF EXISTS st_passport_office;
DROP TABLE IF EXISTS st_register_office;
DROP TABLE IF EXISTS st_country_struct;
DROP TABLE IF EXISTS st_street;


CREATE TABLE st_street (
	street_code integer,
	street_name varchar(300),
	PRIMARY KEY(street_code)
);

CREATE TABLE st_country_struct(
	area_id char(12) not null,
	area_name varchar(200),
	PRIMARY KEY(area_id)
);

CREATE TABLE st_passport_office(
	p_office_id integer not null,
	p_office_area_id char(12) not null,
	p_office_name varchar(200),
	PRIMARY KEY(p_office_id),
	FOREIGN KEY(p_office_area_id) REFERENCES st_country_struct(area_id)
		ON DELETE RESTRICT
);

CREATE TABLE st_register_office(
	r_office_id integer not null,
	r_office_area_id char(12) not null,
	r_office_name varchar(200),
	PRIMARY KEY(r_office_id),
	FOREIGN KEY(r_office_area_id) REFERENCES st_country_struct(area_id)
		ON DELETE RESTRICT
);

CREATE TABLE st_student_request (
    student_request_id SERIAL,
    h_name varchar(100) not null,
    h_surname varchar(100) not null,
    h_patronymic varchar(100) not null,
    h_birth_data date not null,
    h_passport_series varchar(10) not null,
    h_passport_number varchar(20) not null,
    h_passport_date date not null,
    h_passport_office_id integer not null,
    h_postal_code varchar(10),
    h_street_code integer not null,
    h_building varchar(10) not null,
    h_corpus varchar(10),
    h_apartment varchar(10),
    w_name varchar(100) not null,
    w_surname varchar(100) not null,
    w_patronymic varchar(100) not null,
    w_birth_data date not null,
    w_passport_series varchar(10) not null,
    w_passport_number varchar(20) not null,
    w_passport_date date not null,
    w_passport_office_id integer not null,
    w_postal_code varchar(10),
    w_street_code integer not null,
    w_building varchar(10) not null,
    w_corpus varchar(10),
    w_apartment varchar(10),
    certificate_id varchar(20) not null,
    register_office_id integer not null,
    marriage_data date not null,
    PRIMARY KEY(student_request_id),
    FOREIGN KEY(h_street_code) REFERENCES st_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY(h_passport_office_id) REFERENCES st_passport_office(p_office_id) ON DELETE RESTRICT,
    FOREIGN KEY(w_street_code) REFERENCES st_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY(w_passport_office_id) REFERENCES st_passport_office(p_office_id) ON DELETE RESTRICT,
    FOREIGN KEY(register_office_id) REFERENCES st_register_office(r_office_id) ON DELETE RESTRICT
);

CREATE TABLE st_child (
    child_id SERIAL,
    student_request_id integer not null,
    c_name varchar(100) not null,
    c_surname varchar(100) not null,
    c_patronymic varchar(100) not null,
    c_birth_data date not null,
    c_certificate_number varchar(20) not null,
    c_certificate_date date not null,
    c_register_office_id integer not null,
    c_postal_code varchar(10),
    c_street_code integer not null,
    c_building varchar(10) not null,
    c_corpus varchar(10),
    c_apartment varchar(10),
    PRIMARY KEY(child_id),
    FOREIGN KEY(c_street_code) REFERENCES st_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY(c_register_office_id) REFERENCES st_register_office(r_office_id) ON DELETE RESTRICT
)
