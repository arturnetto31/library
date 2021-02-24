CREATE TABLE tb_book (
    id int8 NOT NULL PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    synopsis VARCHAR(500) NOT NULL,
    isbn VARCHAR(17) NOT NULL UNIQUE,
    author VARCHAR(50) NOT NULL,
    price float8 NOT NULL,
    quantityAvailable INTEGER NOT NULL,
    category_id int8 NOT NULL
);

ALTER TABLE tb_book ADD CONSTRAINT category_id_fk FOREIGN KEY (category_id) REFERENCES tb_category(id);