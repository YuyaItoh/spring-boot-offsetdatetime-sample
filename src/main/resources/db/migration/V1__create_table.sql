CREATE TABLE customers(
    name VARCHAR(50) NOT NULL PRIMARY KEY,
    address VARCHAR(50) NOT NULL,
    registered_date_time TIMESTAMP NOT NULL,
    create_date_time TIMESTAMP NOT NULL,
    update_date_time TIMESTAMP NOT NULL
);
