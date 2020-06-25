USE warehouse;
CREATE TABLE transaction (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
date datetime,
barcode  INT(6) unsigned,
sold_price DOUBLE(6,2),
FOREIGN KEY (barcode) REFERENCES inventory(barcode)
);


DELIMITER //
CREATE PROCEDURE Selling(
	IN barcode int
)
BEGIN
 SET @state = (SELECT state from inventory
WHERE inventory.barcode=barcode);
 SET @total =(SELECT price+surcharge from inventory
LEFT JOIN product ON inventory.product = product.id
LEFT JOIN size ON inventory.size = size.id
WHERE inventory.barcode=barcode);


IF(@state<=>'available' OR @state<=>'returned')
THEN 
BEGIN
INSERT INTO transaction(date, barcode, sold_price)
VALUES (NOW(), barcode, @total);
UPDATE inventory
SET inventory.sold_price = @total,
	inventory.sold_date = NOW(),
	inventory.state = 3
WHERE inventory.barcode=barcode;
-- END IF; 
END;
END IF;
END //

DELIMITER ;

SELECT * from inventory
WHERE inventory.barcode=62;

CALL Selling(50); 

select * from transaction;
select * from inventory;

-- DROP TABLE transaction;
-- DROP procedure Selling;


DELIMITER //
CREATE PROCEDURE Profit()
BEGIN
SELECT SUM(inventory.sold_price-product.cost) AS profit from inventory
LEFT JOIN product ON inventory.product = product.id
WHERE inventory.state<=>3 AND sold_date>=NOW()-INTERVAL 1 MONTH;
END //

DELIMITER ;

CALL Profit();
