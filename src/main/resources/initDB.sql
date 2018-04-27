create database weatherDB;
create user 'weatherUser'@'localhost' identified by 'ThePassword';
grant all on weatherDB.* to 'weatherUser'@'localhost'; 