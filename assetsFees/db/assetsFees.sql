CREATE DATABASE assetsFees;

GRANT ALL ON assetsFees.* TO assetsFeesuser@'%' IDENTIFIED BY 'passetsFeesuser';
GRANT ALL ON assetsFees.* TO assetsFeesuser@localhost IDENTIFIED BY 'passetsFeesuser';

USE assetsFees;

CREATE TABLE loan (
  id INTEGER PRIMARY KEY,
  initialCapital decimal(15,2),
  interest decimal(15,2),
  amortizationTime INTEGER
);