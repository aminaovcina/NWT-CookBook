DROP TABLE IF EXISTS billionaires;
 
CREATE TABLE user (
  IDuser INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  email VARCHAR(250),
  city VARCHAR(250),
  dateOfBirth VARCHAR(250),
  gender VARCHAR(250)

);

CREATE TABLE account (
  IDaccount INT AUTO_INCREMENT  PRIMARY KEY,
  IDuser INT FOREIGN KEY,
  passwordAccount VARCHAR(250) NOT NULL,
  usernameAccount VARCHAR(250) NOT NULL
);
 
INSERT INTO user (first_name, last_name, email, city, dateOfBirth, gender)
VALUES
  ('Azra', 'Ibric', 'akulaglic2@etf.unsa.ba', 'Sarajevo', '3.10.1997.', 'female'),
  ('Amina', 'Ovcina', 'aovcina1@etf.unsa.ba''Sarajevo', '17.11.1997.', 'female'),
  ('Amina', 'Kurtovic', 'akurtovic3@etf.unsa.ba''Sarajevo', '17.2.1997.', 'female');


INSERT INTO account (first_name, last_name, email, city, dateOfBirth, gender)
VALUES
  ('aibric2', 'aibric2')
  ('aovcina1', 'aovcina1'),
  ('akurtovic3', 'akurtovic3');