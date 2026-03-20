CREATE TYPE ROLES AS ENUM ('NONE', 'DRIVER', 'FORWARDER', 'ADMIN');
CREATE TYPE STATES AS ENUM (
    'PENDING',
    'ACCEPTED',
    'IN_WAREHOUSE',
    'ASSIGNED',
    'IN_TRANSIT',
    'OUT_FOR_DELIVERY',
    'DELIVERED',
    'FAILED_ATTEMPT',
    'RETURNED',
    'CANCELLED'
    );
CREATE TYPE SIZES AS ENUM (
    'SMALL',
    'MEDIUM',
    'LARGE'
    );

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
    name VARCHAR(100) NOT NULL,
    sender_address VARCHAR(255) NOT NULL,
    delivery_address VARCHAR(255) NOT NULL,
    client_id BIGINT NOT NULL,
    CONSTRAINT fk_cntr_client FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE delivery_states (
    id BIGSERIAL PRIMARY KEY,
    location VARCHAR(255) NOT NULL,
    delivery_state STATES NOT NULL,
    comment TEXT,
    last_updated TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE loads (
    id BIGSERIAL PRIMARY KEY,
    identifier VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    delivery_time VARCHAR(50),
    size SIZES,
    weight DECIMAL,
    send_date TIMESTAMPTZ,
    delivery_date TIMESTAMPTZ,
    worth DECIMAL,
    contract_id BIGINT NOT NULL,
    state_id BIGINT NOT NULL,
    CONSTRAINT fk_ld_contract FOREIGN KEY (contract_id) REFERENCES contracts(id),
    CONSTRAINT fk_ld_state FOREIGN KEY (state_id) REFERENCES delivery_states(id)
);

CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    date TIMESTAMPTZ NOT NULL DEFAULT now(),
    description TEXT,
    cost DECIMAL,
    load_id BIGINT NOT NULL,
    driver_id BIGINT,
    CONSTRAINT fk_crs_load FOREIGN KEY (load_id) REFERENCES loads(id),
    CONSTRAINT fk_crs_driver FOREIGN KEY (driver_id) REFERENCES drivers(id)
);

CREATE TABLE forwarders (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(12) NOT NULL
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ROLES NOT NULL DEFAULT 'NONE',
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    forwarder_id BIGINT UNIQUE,
    driver_id BIGINT UNIQUE,
    CONSTRAINT fk_usr_forwarder FOREIGN KEY (forwarder_id) REFERENCES forwarders(id),
    CONSTRAINT fk_usr_driver FOREIGN KEY (driver_id) REFERENCES drivers(id)
)