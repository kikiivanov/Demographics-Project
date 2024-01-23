/*CREATE TABLE demographics_db;*/

USE demographics_db;

/*CREATE TABLE demographics (
    id INT AUTO_INCREMENT PRIMARY KEY,
    POPULATION INT,
    STATE_NAME VARCHAR(255)
);*/

SELECT * FROM demographics;

/*SELECT Db, User FROM mysql.db WHERE Db = 'demographics_db';

GRANT ALL PRIVILEGES ON demographics_db.* TO 'ivanov'@'localhost';
FLUSH PRIVILEGES;

/*ALTER USER 'ivanov'@'localhost' IDENTIFIED BY '!2zA#9pBqRvT';
