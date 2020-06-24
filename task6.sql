CREATE database warehouse;
USE warehouse;

CREATE TABLE product (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL,
description VARCHAR(1000) NOT NULL,
cost DOUBLE(10,2),
price DOUBLE(10,2),
comission DOUBLE(10,2)
);

CREATE TABLE size (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(20) NOT NULL,
surcharge DOUBLE(10,2)
);

CREATE TABLE inventory (
barcode INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
state ENUM ('available','lost','sold','returned'),
product  INT(6) unsigned,
size  INT(6) unsigned,
sold_date datetime,
sold_price DOUBLE(6,2),
FOREIGN KEY (product) REFERENCES product(id),
FOREIGN KEY (size) REFERENCES size(id)
);

SHOW tables;
DESCRIBE inventory;
 DESCRIBE product;
DESCRIBE size;

INSERT INTO size (name, surcharge)
VALUES ('s',0.0),
	   ('m',0.0),
       ('l',0.0),
       ('xs',1.0),
       ('xxs',3.0),
       ('xxxs',5.0),
       ('xl',2.0),
       ('xxl',3.0),
       ('xxxl',4.0);
SELECT * FROM size;



INSERT INTO product (name, description, cost, price, comission)
VALUES ('t-shirt', 'blue girly t-shirt', 5.0, 10.0, 1.0),
       ('shirt', 'green formal shirt', 10.0, 20.0, 1.2),
       ('trousers', 'loose khaki trousers ', 14.0, 32.0, 2.0),
       ('leggins', 'black long workout leggings', 3.0, 8.0, 1.3),
       ('hat', 'beach hat', 4.0, 15.0, 2.1),
       ('belt', 'plain normal belt', 1.0, 4.0, 0.5),
       ('socks', 'comfy socks', 1.0, 3.0, 0.4),
       ('earrings', 'evening earrings', 1.2, 9.0, 0.9),
       ('sneakers', 'white sneakers', 15.0, 100.0, 2.9),
       ('crop-top', 'summer crop-top', 3.0, 14.0, 1.7)
;

SELECT * FROM product;


INSERT INTO inventory (state, product, size, sold_date,sold_price)
VALUES (3, 3, 3, NOW(), 19),
	   (3, 1, 1, NOW(), 20),
       (3, 8, 2, NOW(), 21),
       (3, 2, 4, NOW(), 12),
       (3, 5, 5, NOW(), 15),
       (3, 4, 5, NOW(), 14),
       (3, 8, 4, NOW(), 23),
       (3, 4, 5, NOW(), 14),
       (3, 9, 3, NOW(), 23),
       (3, 9, 4, NOW(), 14),
       (3, 7, 4, NOW(), 23)
;

INSERT INTO inventory (state, product, size)
VALUES (2, 1, 3),
(2, 3, 2),
(2, 9, 1),
(2, 4, 7),
(2, 7, 6)
;
INSERT INTO inventory (state, product, size)
VALUES (4, 1, 3),
(4, 2, 6),
(4, 5, 2),
(4, 2, 1),
(4, 8, 3)
;
INSERT INTO inventory (state, product, size)
VALUES (1, 1, 1),
	   (1, 1, 2),
	   (1, 1, 3),
       (1, 1, 3),
	   (1, 1, 4),
       (1, 1, 4),
	   (1, 1, 5),
	   (1, 1, 6),
	   (1, 2, 3),
	   (1, 2, 3),
	   (1, 2, 1),
	   (1, 2, 2),
	   (1,2, 8),
       (1, 3, 3),
       (1, 4, 3),
	   (1, 4, 2),
	   (1, 4, 1),
       (1, 4, 7),
       (1, 5, 3),
       (1, 6, 3),
       (1, 7, 3),
       (1, 7, 3),
	   (1, 7, 3),
       (1, 8, 2),
       (1, 8, 2),
       (1, 8, 2),
       (1, 9, 6),
       (1, 9, 5),
       (1, 9, 3),
       (1, 9, 7),
       (1, 9, 5),
       (1, 10, 3),
       (1, 10, 3),
       (1, 10, 2),
       (1, 10, 2),
       (1, 10, 4),
       (1, 10, 1),
       (1, 10, 3),
       (1, 10, 6),
       (1, 10, 7),
       (1, 10, 6)
;
SELECT * FROM inventory;
       
SELECT * FROM product;
SELECT * FROM size;

-- 1
SELECT product.name,product.price,size.name,size.surcharge,price+surcharge AS 'total'
FROM product
CROSS JOIN size
order by total;
-- 2
SELECT barcode, product.name, product.price,size, size.surcharge, product.price+size.surcharge AS 'total'from inventory
LEFT JOIN product ON inventory.product = product.id
LEFT JOIN size ON inventory.size = size.id
-- LEFT JOIN size ON size.id
order by total
;
-- 3
SELECT product.name, 
CASE size.name
        WHEN 's' THEN 'small'
        WHEN 'm' THEN 'medium'
		WHEN 'l' THEN 'large'
        WHEN 'xl' THEN 'x-large'
        ELSE size.name
    end as size
FROM product
CROSS JOIN size;
-- 4
SELECT COUNT(barcode),product.name
FROM inventory
LEFT JOIN product ON inventory.product = product.id
GROUP BY product.id
ORDER BY COUNT(barcode) DESC;

-- 5
SELECT COUNT(barcode),product.name,size.name
FROM inventory
LEFT JOIN product ON inventory.product = product.id
LEFT JOIN size ON inventory.size = size.id
GROUP BY product.id, size.id
ORDER BY COUNT(barcode) DESC;

-- 6
SELECT COUNT(barcode),product.name,size.name
FROM inventory
LEFT JOIN product ON inventory.product = product.id
LEFT JOIN size ON inventory.size = size.id
WHERE size.name<>'xl'
GROUP BY product.id, size.id
ORDER BY COUNT(barcode) DESC;

-- 7
SELECT COUNT(barcode),product.name,size.name
FROM inventory
LEFT JOIN product ON inventory.product = product.id
LEFT JOIN size ON inventory.size = size.id
WHERE product.name<>'hat'
GROUP BY product.id, size.id
ORDER BY COUNT(barcode) DESC;

-- 8
SELECT COUNT(barcode) AS 'number' ,product.name,size.name
FROM inventory
LEFT JOIN product ON inventory.product = product.id
LEFT JOIN size ON inventory.size = size.id
GROUP BY product.id, size.id
HAVING COUNT(barcode)<25
ORDER BY COUNT(barcode) DESC;

CREATE TABLE jewelry (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(50) NOT NULL,
description VARCHAR(1000) NOT NULL,
price DOUBLE(10,2)
);

DESCRIBE jewelry;
INSERT INTO jewelry (name, description,price)
VALUES ('earrings','long earrings', 40.0),
       ('ring','golden ring', 70.0),
       ('bracalet','pretty bracalet', 25.0),
       ('necklace','diamond necklace', 2000.0);

-- 9
SELECT product.name FROM product
UNION
SELECT jewelry.name FROM jewelry;





