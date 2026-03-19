CREATE TABLE drivers (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(12) NOT NULL
);

CREATE TABLE clients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nip VARCHAR(10) NOT NULL,
    phone_number VARCHAR(12) NOT NULL,
    email VARCHAR(255)
);

CREATE TABLE contracts (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100),
    sender_address VARCHAR(255),
    delivery_address VARCHAR(255),
    client_id BIGINT,
    CONSTRAINT fk_cntr_client FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE loads (
    id BIGSERIAL PRIMARY KEY,
    identifier BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    delivery_time INT,
    size VARCHAR(20),
    weight DECIMAL,
    send_date TIMESTAMPTZ,
    delivery_date TIMESTAMPTZ,
    worth DECIMAL,
    transport_cost DECIMAL,
    contract_id BIGINT,
    CONSTRAINT fk_ld_contract FOREIGN KEY (contract_id) REFERENCES contracts(id)
);

CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL,
    date TIMESTAMPTZ NOT NULL DEFAULT now(),
    description TEXT,
    cost DECIMAL,
    load_id BIGINT,
    driver_id BIGINT,
    CONSTRAINT fk_crs_load FOREIGN KEY (load_id) REFERENCES loads(id),
    CONSTRAINT fk_crs_driver FOREIGN KEY (driver_id) REFERENCES drivers(id)
);