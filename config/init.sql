CREATE TABLE user_roles(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    hashcode TEXT NOT NULL UNIQUE,
    is_disposable BOOLEAN NOT NULL,
    status TEXT NOT NULL, -- active, inactive, deleted, blocked
    username TEXT UNIQUE,
    email TEXT UNIQUE, -- email address for login
    password TEXT, -- password for login, hashed with bcrypt
    role_id INTEGER REFERENCES user_roles(id) NOT NULL
);

CREATE TABLE companies(
    id SERIAL PRIMARY KEY,
    status TEXT NOT NULL, -- active, inactive, deleted, blocked
    name TEXT NOT NULL UNIQUE,
    inn TEXT NOT NULL UNIQUE,
    physical_address TEXT NOT NULL,
    legal_address TEXT NOT NULL,
    manager_id INTEGER REFERENCES users(id) NOT NULL
);

CREATE TABLE branches(
    id SERIAL PRIMARY KEY,
    status TEXT NOT NULL, -- active, inactive, deleted, blocked
    name TEXT NOT NULL,
    manager_id INTEGER REFERENCES users(id) NOT NULL,
    company_id INTEGER REFERENCES companies(id) NOT NULL
);

CREATE TABLE agrs(
    id SERIAL PRIMARY KEY,
    status TEXT NOT NULL, -- active, inactive, deleted, blocked
    open_time TEXT NOT NULL,
    close_time TEXT NOT NULL,
    executor_id INTEGER REFERENCES users(id) NOT NULL,
    branch_id INTEGER REFERENCES branches(id) NOT NULL
);

CREATE TABLE cell(
    id SERIAL PRIMARY KEY,
    status TEXT NOT NULL, -- active, inactive, deleted, blocked
    sequence_number INTEGER NOT NULL, -- serial number for cell
    agr_id INTEGER REFERENCES agrs(id) NOT NULL,
    user_id INTEGER REFERENCES users(id)
);

CREATE TABLE visit(
    id SERIAL PRIMARY KEY,
    start_time TIMESTAMP NOT NULL,
    end_time TIMESTAMP NOT NULL,
    cell_id INTEGER REFERENCES cell(id) NOT NULL,
    user_id INTEGER REFERENCES users(id) NOT NULL,
    branch_id INTEGER REFERENCES branches(id) NOT NULL
);

/*
CREATE TABLE action_names(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE,
    related_database_name TEXT NOT NULL
);

CREATE TABLE action_types(
    id SERIAL PRIMARY KEY,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE log(
    id SERIAL PRIMARY KEY,
    status TEXT NOT NULL, -- started, completed, failed
    performed_time TIMESTAMP NOT NULL, -- timestamp of performed action
    related_entity_id BIGINT NOT NULL, -- id of related entity (e.g. user_id, company_id)
    action_name_id INTEGER REFERENCES action_names(id) NOT NULL,
    action_type_id INTEGER REFERENCES action_types(id) NOT NULL,
    performer_id INTEGER REFERENCES users(id) NOT NULL
);*/