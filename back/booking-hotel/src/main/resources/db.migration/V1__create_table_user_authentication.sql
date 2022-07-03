create table credential(
    id serial PRIMARY KEY,
    password VARCHAR(80) NOT NULL,
    email VARCHAR(50) NOT NULL
);

create table customer(
   id serial PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   credential_id SMALLINT references credential(id)
);

create table organization(
    id serial PRIMARY KEY,
    name VARCHAR(50),
    cnpj VARCHAR(14),
    city VARCHAR(100)
);

create table company(
    id serial PRIMARY KEY,
    organization_id SMALLINT references organization(id),
    credential_id SMALLINT references credential(id)
);

create table hotel(
    id serial PRIMARY KEY,
    number_bedrooms SMALLINT NOT NULL,
    organization_id SMALLINT REFERENCES organization(id),
    company_id SMALLINT REFERENCES company(id)
);

create table Room(
    id serial PRIMARY KEY,
    type varchar(50) NOT NULL,
    rent_value varchar(10) NOT NULL,
    quantity SMALLINT NOT NULL,
    description VARCHAR NOT NULL,
    hotel_id SMALLINT references hotel(id)
);

create table booking(
    id serial PRIMARY KEY,
    start_date DATE NOT NULL DEFAULT CURRENT_DATE,
    end_date DATE NOT NULL,
    description VARCHAR(255),
    status varchar(100),
    customer_id SMALLINT REFERENCES credential(id),
    room_id SMALLINT REFERENCES room(id)
);

create table check_point(
    id serial PRIMARY KEY,
    type VARCHAR(100),
    hotel_id SMALLINT REFERENCES hotel(id),
    customer_id SMALLINT REFERENCES credential(id),
    booking_id SMALLINT REFERENCES booking(id)
);