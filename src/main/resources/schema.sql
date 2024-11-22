CREATE
DATABASE product_order_rest_api2 TEMPLATE template0;

CREATE TABLE email
(
    id    SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE customer
(
    customer_id   SERIAL PRIMARY KEY,
    customer_name VARCHAR(100) NOT NULL,
    address       VARCHAR(200) NOT NULL,
    phone_number  VARCHAR(50)  NOT NULL,
    email      VARCHAR,
    CONSTRAINT fk_email FOREIGN KEY (email) REFERENCES email (email) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE order_tb
(
    order_id     SERIAL PRIMARY KEY,
    order_date   TIMESTAMP   NOT NULL,
    total_amount FLOAT       NOT NULL,
    status       VARCHAR(30) NOT NULL,
    customer_id  INT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE product
(
    product_id   SERIAL PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    unit_price   FLOAT        NOT NULL,
    description  VARCHAR(200) NOT NULL
);

CREATE TABLE product_order
(
    id         SERIAL PRIMARY KEY,
    product_id INT,
    order_id   INT,
    quantity   INT NOT NULL,
    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES product (product_id) ON UPDATE CASCADE ON DELETE CASCADE ,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES order_tb (order_id) ON UPDATE CASCADE ON DELETE CASCADE
);
-- email
SELECT *FROM email;

SELECT *FROM email WHERE id =1;

SELECT *FROM email WHERE email = 'lengpanha789@gmail.com';

INSERT INTO email(email)
VALUES ('lengpanha@gmail.com') returning *;

UPDATE email
SET email = 'panha789@gmail.com'
WHERE id = 1
returning * ;

DELETE FROM email WHERE  id = 1 ;

-- customer
SELECT * FROM customer;

SELECT *FROM customer WHERE customer_id = 1;

INSERT INTO customer(customer_name, address, phone_number, email_id)
VALUES ('panha','kompong cham','0974821711',2) returning *;

UPDATE customer
SET customer_name = 'khuoch' , address = 'pp' , phone_number = '09876543' , email_id = 5
WHERE customer_id = 2
returning * ;

DELETE FROM customer WHERE customer_id =2;

-- product

SELECT *FROM product;

SELECT *FROM product WHERE product_id =1;

INSERT INTO product(product_name, unit_price, description)
VALUES ('coca',200.0,'coca') returning *;

UPDATE product
SET product_name = 'koko' , unit_price = 300.00 , description = 'kkoko'
WHERE product_id = 1
returning * ;

DELETE FROM product WHERE  product_id = 1 ;


INSERT INTO order_tb(order_date, total_amount, status, customer_id)
VALUES ('2024-06-21 15:35:03.000000',2000,'SHIPPED',2) returning *;



INSERT INTO product_order(product_id, order_id, quantity)
VALUES (2,7,20);

SELECT p.product_id,p.product_name,p.unit_price,p.description FROM product p
JOIN product_order po on p.product_id = po.product_id WHERE po.order_id = 12;

SELECT * FROM order_tb WHERE order_id = 8;

UPDATE order_tb
SET total_amount = 23
WHERE order_id = 1
returning * ;

UPDATE order_tb
SET status = 'SHIPPED'
WHERE order_id = 2 AND customer_id =2
returning * ;
