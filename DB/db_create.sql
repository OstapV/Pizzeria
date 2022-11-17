
CREATE DATABASE PizzaSystem;
USE PizzaSystem;

DROP TABLE IF EXISTS Pizza;
DROP TABLE IF EXISTS Order_Status;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Pizza_Order_Status;
DROP TABLE IF EXISTS Pizza_Order;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Roles;
DROP TABLE IF EXISTS User_Status;


CREATE TABLE Roles (
    id integer NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE User_Status (
    id integer NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
); 

CREATE TABLE Users (
    id integer NOT NULL AUTO_INCREMENT,
    first_name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    login varchar(255) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    avatar_url varchar(255) NOT NULL DEFAULT('../imgs/avatar.png'),
    role_id integer REFERENCES Roles(id),
    status_id integer REFERENCES User_Status(id),
    PRIMARY KEY (id)
);

CREATE TABLE Pizza (
    id integer NOT NULL AUTO_INCREMENT,
    avatar_url varchar(255) NOT NULL DEFAULT('../imgs/pizza.png'),
    name varchar(255) NOT NULL,
    description varchar(255) NOT NULL,
    price integer NOT NULL,
    massa integer NOT NULL,
    cooking_time integer NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Pizza_Order_Status (
    id integer NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
); 

CREATE TABLE Order_Status (
    id integer NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Orders (
    id integer NOT NULL AUTO_INCREMENT,
    user_id  integer REFERENCES Users(id),
    order_date DATETIME NOT NULL,
    status_id  integer REFERENCES Order_Status(id),
    PRIMARY KEY (id)
); 

CREATE TABLE Pizza_Order (
    id integer NOT NULL AUTO_INCREMENT,
    order_id  integer REFERENCES Orders(id),
    pizza_id  integer REFERENCES Pizza(id),
    adding_date DATETIME NOT NULL,
    status_id  integer REFERENCES Pizza_Order_Status(id),
    count integer NOT NULL,
    PRIMARY KEY (id)
); 







