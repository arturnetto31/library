CREATE TABLE tb_purchase (
    id int8 NOT NULL PRIMARY KEY,
    customer_id int8 NOT NULL,
    shoppingList_id int8 NOT NULL,
    priceToPay float8 NOT NULL,
    purchaseDate VARCHAR(10) NOT NULL,
    purchaseCompleted BIT NOT NULL
);
ALTER TABLE tb_purchase ADD CONSTRAINT customer_id_fk FOREIGN KEY (customer_id) REFERENCES tb_user(id);

ALTER TABLE tb_purchase ADD CONSTRAINT shoppingList_id_fk FOREIGN KEY (shoppingList_id) REFERENCES tb_book(id);