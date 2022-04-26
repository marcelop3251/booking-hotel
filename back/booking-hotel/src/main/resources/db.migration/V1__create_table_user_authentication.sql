create table credential(
    id serial PRIMARY KEY,
    password VARCHAR(80) NOT NULL,
    email VARCHAR(50) NOT NULL
);

create table customer(
   id serial PRIMARY KEY,
   name VARCHAR(50) NOT NULL,
   credential_id SMALLINT references credential(id)
)

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
    rent_value varchar(10),
    quantity SMALLINT NOT NULL,
    description VARCHAR NOT NULL,
    hotel_id SMALLINT references hotel(id)
)