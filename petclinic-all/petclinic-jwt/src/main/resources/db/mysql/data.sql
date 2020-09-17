INSERT IGNORE INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT IGNORE INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT IGNORE INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT IGNORE INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT IGNORE INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT IGNORE INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT IGNORE INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT IGNORE INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT IGNORE INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT IGNORE INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT IGNORE INTO pets VALUES (1, 'Leo', '2000-09-07', 1);
INSERT IGNORE INTO pets VALUES (2, 'Basil', '2002-08-06', 2);
INSERT IGNORE INTO pets VALUES (3, 'Rosy', '2001-04-17', 3);
INSERT IGNORE INTO pets VALUES (4, 'Jewel', '2000-03-07', 3);
INSERT IGNORE INTO pets VALUES (5, 'Iggy', '2000-11-30', 4);
INSERT IGNORE INTO pets VALUES (6, 'George', '2000-01-20', 5);
INSERT IGNORE INTO pets VALUES (7, 'Samantha', '1995-09-04', 6);
INSERT IGNORE INTO pets VALUES (8, 'Max', '1995-09-04', 6);
INSERT IGNORE INTO pets VALUES (9, 'Lucky', '1999-08-06', 7);
INSERT IGNORE INTO pets VALUES (10, 'Mulligan', '1997-02-24', 8);
INSERT IGNORE INTO pets VALUES (11, 'Freddy', '2000-03-09', 9);
INSERT IGNORE INTO pets VALUES (12, 'Lucky', '2000-06-24', 10);
INSERT IGNORE INTO pets VALUES (13, 'Sly', '2002-06-08', 10);

INSERT IGNORE INTO visits VALUES (1, 7, '2010-03-04', 'rabies shot');
INSERT IGNORE INTO visits VALUES (2, 8, '2011-03-04', 'rabies shot');
INSERT IGNORE INTO visits VALUES (3, 8, '2009-06-04', 'neutered');
INSERT IGNORE INTO visits VALUES (4, 7, '2008-09-04', 'spayed');

-- password - petclinic
INSERT IGNORE INTO users VALUES (1, 'petclinic', '$2a$10$mWmWbwt6mDRikhJbPKmQDewJ8Of2BtdBd6xzvdGN8yKo06gFGb/p2');
-- password - user
INSERT IGNORE INTO users VALUES (2, 'user', '$2a$10$UDW6q2eTpOdLG24crN/FgOdzxB03CM/fT0T84IU6f8elbZqxSXc2i');

INSERT IGNORE INTO roles VALUES (1, 'ADMIN');
INSERT IGNORE INTO roles VALUES (2, 'USER');

INSERT IGNORE INTO user_role VALUES(1, 1);
INSERT IGNORE INTO user_role VALUES(2, 2);