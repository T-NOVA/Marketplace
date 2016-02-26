-- UMAA init database
CREATE DATABASE IF NOT EXISTS umaa_db;
CREATE USER 'umaa_db_usr'@'%' IDENTIFIED BY 'HeVBz6T5';
GRANT ALL PRIVILEGES ON umaa_db.* TO 'umaa_db_usr'@'%';
FLUSH PRIVILEGES;
