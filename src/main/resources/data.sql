INSERT INTO passport(id,number,issued_country)
VALUES (201,'L1234567','India');
INSERT INTO passport(id,number,issued_country)
VALUES (202,'L1234568','India');


INSERT INTO student(id, name, passport_id, email ) 
VALUES (101,'Jane', 201, 'jane@doe.com');
INSERT INTO student(id, name, passport_id, email )
VALUES (102,'Doe', 202, 'doe@doe.com');

