INSERT INTO users (hashcode, is_disposable, status,username,  email, phone, password, role_id) VALUES
    ('abcd1234', false, 'active','Shiz', 'ADMIN@example.com', NULL, 'hashed_password', 1),
    ('xyz9876', false, 'active','CoolManager', 'MANAGER@example.com', NULL, 'hashed_password', 2),
    ('uvw5678', false, 'active','BestObserver111', 'OBSERVER@example.com', NULL, 'hashed_password', 3),
    ('qwe1234', false, 'active','Executor777', 'EXECUTOR@example.com', NULL, 'hashed_password', 4),
    ('rty1234', false, 'active','SimpleUser', 'USER@example.com', NULL, 'hashed_password', 5);

INSERT INTO companies (status, name, inn, company_code, phyzical_address, legal_address, manager_id) VALUES
    ('active', 'Moscow State University', '1234567890', 'MSU77', '??????, ????????? ????, ???, ?. 1', '??????, ????????? ????, ???, ?. 1', 2),
    ('active', 'Moscow Institute of RadioElctronics and Automatization', '0987654321', 'MIREA77', '??????, 5-? ????? ????????? ????, ?. 22', '??????, 5-? ????? ????????? ????, ?. 22', 2);

INSERT INTO branches (status, name, branch_code, manager_id, company_id) VALUES
    ('active', '??????????? ??????', 'CENTRALBLOCK77', 2, 1),
    ('active', '?????? ?? ?????????', 'STROMINKA77', 2, 2),
    ('active', '?????? ?? ??????????? 78', 'VERNAD78', 2, 2);

INSERT INTO agrs (status, open_time, close_time, executor_id, branch_id) VALUES
    ('active', '08:00:00', '22:00:00', 4, 2),
    ('active', '08:00:00', '22:00:00', 4, 3);

INSERT INTO cell (status, sequence_number, agr_id, user_id) VALUES
    ('free', 1, 1, NULL),
    ('free', 2, 2, NULL);