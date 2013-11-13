CREATE TABLE stock_holders(id int not null,
       name varchar(255),
       surname varchar(255),
       factory decimal(1,0),
       phone varchar(13),
       simpleStockItem decimal(10),
       personalStockItem decimal(10),
       libertyStockItem decimal(10),
       adress varchar(255),
       areaCode varchar(30),
       personalCodeOrRegistraionNumber varchar(12),
       bankCode varchar(1000),
       citizen BOOLEAN,
       primary key(id));
       
CREATE TABLE users(id int not null,
       login varchar(255),
       password varchar(255),
       primary key(id));

CREATE SEQUENCE stock_holders_id START WITH 1;