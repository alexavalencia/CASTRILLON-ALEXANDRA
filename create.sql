DROP TABLE ODONTOLOGOS IF EXISTS;
CREATE TABLE ODONTOLOGOS(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    NUMERO_MATRICULA VARCHAR(25) NOT NULL,
    NOMBRE VARCHAR(50) NOT NULL,
    APELLIDO VARCHAR(50) NOT NULL
);

INSERT INTO ODONTOLOGOS VALUES( DEFAULT, '1234567','JUAN CAMILO','AGUIRRE'),
( DEFAULT, '1234568','LUISA','GIRALDO'),
( DEFAULT, '1234569','NORA','VALENCIA'),
( DEFAULT, '1234510','JUAN','AGUIRRE');
