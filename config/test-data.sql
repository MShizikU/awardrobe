INSERT INTO users (id, is_disposable, status, username, email, password, role_id) VALUES
(2, FALSE, 'active', 'admin', '<EMAIL>', '$2a$10$f9z47vKxd6BqQlYZyCkNh.b8RMUuXJPmj2Gzjv4E47VWwUqgT4ZOi', 1),
(3, TRUE, 'inactive', 'user1', '<EMAIL>', '$2a$10$f9z47vKxd6BqQlYZyCkNh.b8RMUuXJPmj2Gzjv4E47VWwUqgT4ZOi', 2),
(4, FALSE, 'blocked', 'user2', '<EMAIL>', '$2a$10$f9z47vKxd6BqQlYZyCkNh.b8RMUuXJPmj2Gzjv4E47VWwUqgT4ZOi', 2);

INSERT INTO companies (status, name, inn, physical_address, legal_address, manager_id) VALUES
('active', 'Company A', '1234567890', 'Address A', 'Legal Address A', 2),
('inactive', 'Company B', '9876543210', 'Address B', 'Legal Address B', 3);

INSERT INTO branches (status, name, manager_id, company_id) VALUES
('active', 'Branch A', 3, 1),
('deleted', 'Branch B', 2, 1),
('blocked', 'Branch C', 2, 2);

INSERT INTO agrs (status, open_time, close_time, executor_id, branch_id) VALUES
('active', '2023-01-01', '2023-01-31', 3, 1),
('inactive', '2023-02-01', '2023-02-28', 2, 2),
('deleted', '2023-03-01', '2023-03-31', 2, 2);

INSERT INTO cell (status, sequence_number, agr_id, user_id) VALUES
('active', 1, 1, 3),
('inactive', 2, 2, 2),
('blocked', 3, 3, 2);

INSERT INTO visit (start_time, end_time, cell_id, user_id) VALUES
('2023-01-01T09:00:00', '2023-01-01T10:00:00', 1, 3),
('2023-02-01T10:00:00', '2023-02-01T11:00:00', 2, 2),
('2023-03-01T11:00:00', '2023-03-01T12:00:00', 3, 2);