-- Inserting sample authors
INSERT INTO author (name, birth_date, country)
VALUES ('Margaret Atwood', 1939, 'Canada'),
       ('Salman Rushdie', 1947, 'India'),
       ('Toni Morrison', 1931, 'United States'),
       ('Kazuo Ishiguro', 1954, 'United Kingdom'),
       ('Chimamanda Ngozi Adichie', 1977, 'Nigeria'),
       ('Milan Kundera', 1929, 'Czech Republic'),
       ('Orhan Pamuk', 1952, 'Turkey'),
       ('Zadie Smith', 1975, 'United Kingdom'),
       ('Haruki Murakami', 1949, 'Japan'),
       ('Isabel Allende', 1942, 'Chile');

-- Inserting sample publishers
INSERT INTO publisher (name, establishment_year, address)
VALUES ('Vintage Books', 1954, '1745 Broadway, New York, United States'),
       ('Faber & Faber', 1929, '74-77 Great Russell Street, London, United Kingdom'),
       ('Knopf Doubleday', 1897, '1745 Broadway, New York, United States'),
       ('Bloomsbury Publishing', 1986, '50 Bedford Square, London, United Kingdom'),
       ('Grove Atlantic', 1917, '154 West 14th Street, New York, United States');

-- Inserting sample categories
INSERT INTO category (name, description)
VALUES ('Literary Fiction', 'Fictional works of high artistic merit'),
       ('Dystopian', 'Books set in imagined societies with great suffering or injustice'),
       ('Magical Realism', 'Fiction that blends magical elements with the real world'),
       ('Historical Fiction', 'Fiction set in a particular period of history'),
       ('Contemporary Fiction', 'Fiction set in modern times'),
       ('Postcolonial Literature', 'Literature dealing with post-colonial experience'),
       ('Philosophical Fiction', 'Fiction exploring philosophical ideas'),
       ('Psychological Fiction', 'Fiction focusing on the interior lives of characters'),
       ('Experimental Fiction', 'Fiction that breaks traditional literary conventions'),
       ('Multicultural Fiction', 'Fiction exploring multiple cultural perspectives');

-- Inserting sample books
INSERT INTO book (name, publication_year, stock, author_id, publisher_id)
VALUES ('The Handmaid''s Tale', 1985, 10, 1, 1),
       ('Midnight''s Children', 1981, 8, 2, 2),
       ('Beloved', 1987, 7, 3, 3),
       ('Never Let Me Go', 2005, 9, 4, 2),
       ('Half of a Yellow Sun', 2006, 6, 5, 4),
       ('The Unbearable Lightness of Being', 1984, 5, 6, 1),
       ('Snow', 2002, 7, 7, 3),
       ('White Teeth', 2000, 8, 8, 4),
       ('1Q84', 2009, 10, 9, 3),
       ('The House of the Spirits', 1982, 6, 10, 5),
       ('Oryx and Crake', 2003, 7, 1, 1),
       ('The Satanic Verses', 1988, 5, 2, 1),
       ('Song of Solomon', 1977, 6, 3, 3),
       ('The Remains of the Day', 1989, 8, 4, 2),
       ('Americanah', 2013, 9, 5, 4);

-- Inserting sample book-category relationships
INSERT INTO book_category (book_id, category_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 3),
       (3, 1),
       (3, 4),
       (4, 1),
       (4, 8),
       (5, 1),
       (5, 4),
       (6, 1),
       (6, 7),
       (7, 1),
       (7, 6),
       (8, 1),
       (8, 5),
       (9, 1),
       (9, 9),
       (10, 1),
       (10, 3),
       (11, 1),
       (11, 2),
       (12, 1),
       (12, 3),
       (13, 1),
       (13, 6),
       (14, 1),
       (14, 4),
       (15, 1),
       (15, 10);

-- Inserting sample book borrowings
INSERT INTO book_borrowing (borrower_name, borrowing_date, return_date, book_id)
VALUES ('Emma Thompson', '2023-01-10', '2023-01-25', 1),
       ('Daniel Radcliffe', '2023-01-15', '2023-01-30', 2),
       ('Lupita Nyong''o', '2023-02-01', '2023-02-16', 3),
       ('Tom Hiddleston', '2023-02-10', '2023-02-25', 4),
       ('Viola Davis', '2023-03-05', '2023-03-20', 5),
       ('Benedict Cumberbatch', '2023-03-15', '2023-03-30', 6),
       ('Meryl Streep', '2023-04-01', '2023-04-16', 7),
       ('Idris Elba', '2023-04-10', '2023-04-25', 8),
       ('Cate Blanchett', '2023-05-01', '2023-05-16', 9),
       ('Ken Watanabe', '2023-05-10', '2023-05-25', 10),
       ('Gael García Bernal', '2023-06-01', '2023-06-16', 11),
       ('Judi Dench', '2023-06-10', '2023-06-25', 12),
       ('Chiwetel Ejiofor', '2023-07-01', '2023-07-16', 13),
       ('Helen Mirren', '2023-07-10', '2023-07-25', 14),
       ('Dev Patel', '2023-08-01', '2023-08-16', 15);