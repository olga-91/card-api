insert into user (id, email, password, role) values
(1, 'admin@test.com', 1234, 'ADMIN'),
(2, 'member@test.com', 1234, 'MEMBER');

insert into card (name, created_by, status, created_at) values
('card1', 1, 'TODO', CURRENT_TIMESTAMP),
('card2', 2, 'TODO', CURRENT_TIMESTAMP),
('card3', 2, 'IN_PROGRESS', CURRENT_TIMESTAMP),
('card4', 2, 'DONE', CURRENT_TIMESTAMP);

