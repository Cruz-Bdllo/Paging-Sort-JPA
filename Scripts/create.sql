CREATE DATABASE products_db;

USE products_db;

CREATE OR REPLACE TABLE brands(
	brand_id int(2) not null auto_increment,
	name varchar(60) not null,
	primary key (brand_id),
	constraint uq_brand_name unique(name)
);

CREATE OR REPLACE TABLE madein(
	madein_id int(2) not null auto_increment,
	name varchar(60) not null,
	primary key(madein_id),
	constraint uq_madein_name unique(name)
);

CREATE OR REPLACE TABLE products(
	product_id int not null auto_increment,
	name varchar(50) not null,
	brand_id int(2) not null,
	madein_id int(2) not null,
	price float not null,
	stock int not null,
	primary key (product_id),
	constraint ck_product_stock check(stock >= 0),
	constraint fk_product_brand_id foreign key (brand_id) references brands(brand_id),
	constraint fk_product_made_id foreign key (madein_id) references madein (madein_id)
);

INSERT INTO brands (name) VALUES ('LG'), ('SAMSUNG'), ('APPLE'), ('MOTOROLA'), ('ZTE'),
	('NOKIA'), ('LANIX');
	
INSERT INTO madein (name) VALUES ('MEXICO'), ('USA'), ('CHINA');


select * from brands order by brand_id ASC;
select * from madein order by madein_id ASC;

INSERT INTO products (name, brand_id, madein_id, price, stock) VALUES 
	('LG K22', 7, 2, 3599, 3), ('LG VELVET', 7, 2, 17999, 7), ('Qstylus Alpha', 7, 2, 3999, 10),
	('Galaxy A31', 8, 3, 7209, 13), ('Galaxy A01 Core', 8, 3, 2699, 7), ('Galaxy A20s', 8, 3, 4999, 9),
	('LG iPhone XR', 9, 2, 16499, 13), ('LG iPhone 12 Pro', 9, 2, 27499, 17), ('iPhone XS MAx', 9, 2, 18499, 4),
	('Moto G8 Power Lite', 10, 2, 3999, 3), ('One Vision', 10, 2, 6999, 7), ('Moto G9 Plus', 10, 2, 7299, 10),
	('Blade A5 2020', 11, 3, 2599, 13), ('Blade V10', 11, 3, 2999, 8), ('ZTE Axon 11', 11, 3, 6999, 5),
	('Nokia 5.3', 12, 2, 5999, 0), ('Nokia 5.1 Plus', 12, 2, 4859, 3), ('Nokia 2.3', 12, 2, 3699, 2),
	('Ilium Alpha 7', 13, 1, 3499, 3), ('Ilium M5', 13, 1, 1999, 7), ('U210', 13, 1, 329, 10);

SELECT p.product_id as ID, p.name as NOMBRE, b.name as MARCA, m.name as 'Hecho en', p.price as PRECIO, p.stock as STOCK FROM products p
INNER JOIN brands b ON p.brand_id = b.brand_id 
INNER JOIN madein m ON p.madein_id=m.madein_id;

-- Obtener productos de MEXICO ordenados por ID de manera DESCENDENTE
SELECT p.product_id as ID, p.name as NOMBRE, b.name as MARCA, m.name as 'Hecho en', p.price as PRECIO, p.stock as STOCK FROM products p
INNER JOIN brands b ON p.brand_id = b.brand_id 
INNER JOIN madein m ON p.madein_id=m.madein_id
WHERE m.name='MEXICO' ORDER BY p.product_id DESC;

-- Obtener productos de la marca NOKIA ordenados por ID de manera ASCENDENTE que tengan mas de 2 elementos en STOCK
SELECT p.product_id as ID, p.name as NOMBRE, b.name as MARCA, m.name as 'Hecho en', p.price as PRECIO, p.stock as STOCK FROM products p
INNER JOIN brands b ON p.brand_id = b.brand_id 
INNER JOIN madein m ON p.madein_id=m.madein_id
WHERE b.name='NOKIA' AND p.stock > 2 ORDER BY p.product_id ASC;

-- Obtener los productos que cuesten menos de $ 5000 y esten ordenados de mayor a menor precio
SELECT p.product_id as ID, p.name as NOMBRE, b.name as MARCA, m.name as 'Hecho en', p.price as PRECIO, p.stock as STOCK FROM products p
INNER JOIN brands b ON p.brand_id = b.brand_id 
INNER JOIN madein m ON p.madein_id=m.madein_id
WHERE p.price < 5000 order by p.price DESC;
	

show tables;
