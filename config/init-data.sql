INSERT INTO user_roles (name) VALUES
    ('ADMIN'),
    ('MANAGER'),
    ('OBSERVER'),
    ('EXECUTOR'),
    ('USER');

INSERT INTO action_names (name, related_database_name) VALUES
    ('create user', 'users'),
    ('update user', 'users'),
    ('delete user', 'users');

INSERT INTO action_names (name, related_database_name) VALUES
    ('create company', 'companies'),
    ('update company', 'companies'),
    ('delete company', 'companies');

INSERT INTO action_names (name, related_database_name) VALUES
    ('create branch', 'branches'),
    ('update branch', 'branches'),
    ('delete branch', 'branches');

INSERT INTO action_names (name, related_database_name) VALUES
    ('create agreement', 'agrs'),
    ('update agreement', 'agrs'),
    ('delete agreement', 'agrs');

INSERT INTO action_names (name, related_database_name) VALUES
    ('create cell', 'cell'),
    ('update cell', 'cell'),
    ('delete cell', 'cell');

INSERT INTO action_names (name, related_database_name) VALUES
    ('create visit', 'visit'),
    ('update visit', 'visit'),
    ('delete visit', 'visit');

INSERT INTO action_names (name, related_database_name) VALUES
    ('create action name', 'action_names'),
    ('update action name', 'action_names'),
    ('delete action name', 'action_names'),
    ('create action type', 'action_types'),
    ('update action type', 'action_types'),
    ('delete action type', 'action_types');


INSERT INTO action_types (name) VALUES
    ('CREATE'),
    ('UPDATE'),
    ('DELETE');

