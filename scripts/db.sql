CREATE USER yanov WITH PASSWORD 'YANOV';
CREATE DATABASE yanov_corevalue WITH OWNER yanov;

CREATE TABLE employees
    ( employee_id    SERIAL,
    first_name     VARCHAR(20),
    last_name      VARCHAR(25) NOT NULL,
    email          VARCHAR(25) NOT NULL,
    manager_id     INTEGER,
    UNIQUE (email),
	PRIMARY KEY (employee_id),
	FOREIGN KEY (manager_id)
                      REFERENCES employees
    ) ;

INSERT INTO employees (first_name, last_name, email, manager_id)  VALUES ('Zinedine', 'Zidane', 'zidane@gmail.com', null);
INSERT INTO employees (first_name, last_name, email, manager_id)  VALUES ('Gareth', 'Bale', 'bale@gmail.com', 1);
INSERT INTO employees (first_name, last_name, email, manager_id)  VALUES ('Cristiano', 'Ronaldo', 'cristiano@gmail.com', 1);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Alex', 'Chamberlain', 'chamberlain@gmail.com', 2);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Killian', 'Mbappe', 'mbappe@gmail.com', 2);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Antony', 'Martial', 'martial@gmail.com', 3);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('David', 'Beckham', 'beckham@gmail.com', 4);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('David', 'DeGea', 'degea@gmail.com', 7);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Arturo', 'Vidal', 'vidal@gmail.com', 8);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Maxi', 'Lopez', 'lopez@gmail.com', 9);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Lionel', 'Messi', 'messi@gmail.com', 6);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Ngolo', 'Kante', 'kante@gmail.com', 10);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Robin', 'VanPersie', 'vanpersie@gmail.com', 11);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Raul', 'Gonzales', 'raul@gmail.com', 12);
INSERT INTO employees (first_name, last_name, email, manager_id) VALUES ('Roberto', 'Mancini', 'mancini@gmail.com', 12);

