-- noinspection SqlResolveForFile
INSERT INTO author (id, last_name, first_name)
VALUES (1, 'Rowling', 'J.K');
INSERT INTO author (id, first_name, last_name)
VALUES (2, 'Hawking', 'Stephen');
INSERT INTO author (id, first_name, last_name)
VALUES (3, 'Delaney', 'Joseph');

INSERT INTO book (id, title, page_count, author_id)
VALUES (1, 'Harry Potter and philosophical stone', 321, 1);
INSERT INTO book (id, title, page_count, author_id)
VALUES (2, 'A short story of time', 256, 2);
INSERT INTO book (id, title, page_count, author_id)
VALUES (3, 'The spook''s apprentice', 432, 3);
INSERT INTO book (id, title, page_count, author_id)
VALUES (4, 'Harry Potter and the chamber of secrets', 538, 1);
