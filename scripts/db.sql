CREATE USER yanov WITH ENCRYPTED PASSWORD 'YANOV';
CREATE DATABASE yanov_corevalue with owner yanov;

CREATE TABLE employees
    ( employee_id    SERIAL,
    first_name     VARCHAR(20),
    last_name      VARCHAR(25) NOT NULL,
    email          VARCHAR(25) NOT NULL,
    phone_number   VARCHAR(20),
    manager_id     INTEGER,
    UNIQUE (email),
	PRIMARY KEY (employee_id),
	FOREIGN KEY (manager_id)
                      REFERENCES employees
    ) ;

