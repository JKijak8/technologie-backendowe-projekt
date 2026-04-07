CREATE TYPE ROLES AS ENUM ('NONE', 'DRIVER', 'FORWARDER', 'MANAGER', 'ADMIN');
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

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE positions (
    id BIGSERIAL PRIMARY KEY,
    position VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE employees (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(12) NOT NULL,
    position_id BIGINT NOT NULL,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_empl_position FOREIGN KEY (position_id) REFERENCES positions(id),
    CONSTRAINT fk_empl_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE clients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nip VARCHAR(10) NOT NULL UNIQUE,
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

CREATE TABLE courses (
    id BIGSERIAL PRIMARY KEY,
    destination VARCHAR(255) NOT NULL,
    date TIMESTAMPTZ NOT NULL DEFAULT now(),
    description TEXT,
    cost DECIMAL,
    driver_id BIGINT,
    CONSTRAINT fk_crs_driver FOREIGN KEY (driver_id) REFERENCES employees(id)
);

CREATE TABLE loads (
    id BIGSERIAL PRIMARY KEY,
    identifier VARCHAR(255) UNIQUE NOT NULL,
    type VARCHAR(255) NOT NULL,
    delivery_time VARCHAR(50),
    size SIZES,
    weight DECIMAL,
    send_date TIMESTAMPTZ,
    delivery_date TIMESTAMPTZ,
    worth DECIMAL,
    contract_id BIGINT NOT NULL,
    state_id BIGINT NOT NULL,
    course_id BIGINT,
    CONSTRAINT fk_ld_contract FOREIGN KEY (contract_id) REFERENCES contracts(id),
    CONSTRAINT fk_ld_state FOREIGN KEY (state_id) REFERENCES delivery_states(id),
    CONSTRAINT fk_ld_course FOREIGN KEY (course_id) REFERENCES courses(id)
);

CREATE TABLE users_roles (
    user_id BIGINT NOT NULL,
    role ROLES NOT NULL,
    PRIMARY KEY (user_id, role),
    CONSTRAINT fk_role_user FOREIGN KEY (user_id) REFERENCES users(id)
);