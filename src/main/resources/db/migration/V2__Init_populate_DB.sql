INSERT INTO contact_info (email, phone, telegram) VALUES ('jobmail28@email.ua', '+380 95 257 29 39', '@looove');
INSERT INTO contact_info (email, phone, telegram) VALUES (null, '+380 95 447 23 34', null);
INSERT INTO contact_info (email, phone, telegram) VALUES ('watcher332@email.ua', null, null);
INSERT INTO contact_info (email, phone, telegram) VALUES ('strategist2002@email.ua', null, '@magick');
INSERT INTO contact_info (email, phone, telegram) VALUES (null, '+380 68 777 39 39', '@duckiness');
INSERT INTO contact_info (email, phone, telegram) VALUES (null, null, null);

INSERT INTO cards (balance, currency) VALUES (9000.00, 'USD');
INSERT INTO cards (balance, currency) VALUES (10000.00, 'UAH');
INSERT INTO cards (balance, currency) VALUES (300.00, 'EUR');
INSERT INTO cards (balance, currency) VALUES (39000.00, 'UAH');
INSERT INTO cards (balance, currency) VALUES (4700.00, 'USD');
INSERT INTO cards (balance, currency) VALUES (50.00, 'USD');
INSERT INTO cards (balance, currency) VALUES (50.00, 'EUR');

INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Money Transfer', current_date, 300.00, 3);
INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Paid products ATB', current_date, 500.00, 4);
INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Job Salary', current_date, 370.00, 5);
INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Money Transfer', current_date, 10.00, 4);
INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Scholarship', current_date, 150.00, 3);
INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Money Transfer', current_date, 1500.00, 3);
INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Scholarship', current_date, 33.00, 3);
INSERT INTO bills (header, paid_date, total, card_id) VALUES ('Job Salary', current_date, 49.90, 4);

INSERT INTO users (name, role, contact_info_id) VALUES ('Boris', 'ADMIN', 1);
INSERT INTO users (name, role, contact_info_id) VALUES ('Mary', 'VENDOR', 2);
INSERT INTO users (name, role, contact_info_id) VALUES ('James', 'CLIENT', 3);
INSERT INTO users (name, role, contact_info_id) VALUES ('John', 'CLIENT', 4);
INSERT INTO users (name, role, contact_info_id) VALUES ('Moris', 'CLIENT', 5);
INSERT INTO users (name, role, contact_info_id) VALUES ('Carl', 'CLIENT', 6);

INSERT INTO users_cards (user_id, card_id) VALUES (1, 1);
INSERT INTO users_cards (user_id, card_id) VALUES (2, 2);
INSERT INTO users_cards (user_id, card_id) VALUES (3, 3);
INSERT INTO users_cards (user_id, card_id) VALUES (4, 4);
INSERT INTO users_cards (user_id, card_id) VALUES (5, 5);
INSERT INTO users_cards (user_id, card_id) VALUES (6, 6);
INSERT INTO users_cards (user_id, card_id) VALUES (3, 7);