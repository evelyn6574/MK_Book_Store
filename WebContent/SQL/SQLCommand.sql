/** bid: unique identifier of Book (like ISBN)
* title: title of Book
* price: unit price WHEN ordered
* author: name of authors
* category: as specified
*/
CREATE TABLE Book (
bid VARCHAR(20) NOT NULL,
title VARCHAR(60) NOT NULL,
price INT NOT NULL,
category VARCHAR(20) NOT NULL,
PRIMARY KEY(bid)
);

INSERT INTO Book (bid, title, price, category) VALUES ('b001', 'Little Prince', 20, 'Fiction');
INSERT INTO Book (bid, title, price, category) VALUES ('b002','Physics', 201, 'Science');
INSERT INTO Book (bid, title, price, category) VALUES ('b003','Mechanics' ,100,'Engineering');

Select * from BOOK where upper(title) like upper('THE%')

/* Address
* id: address id
*
*/
DROP TABLE Address;

CREATE TABLE Address (
a_id INT  NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
username VARCHAR(20)  NOT NULL,
street VARCHAR(100) NOT NULL,
province VARCHAR(20) NOT NULL,
country VARCHAR(20) NOT NULL,
zip VARCHAR(20) NOT NULL,
phone VARCHAR(20),
addrType VARCHAR(15) NOT NULL,
PRIMARY KEY(a_id),
FOREIGN KEY(username) REFERENCES Customer(username) ON DELETE CASCADE
);

INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('Roger', '123 Yonge St', 'ON',
'Canada', 'K1E 6T5' ,'647-123-4567', 'Shipping');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('Roger', '123 Yonge St', 'ON',
'Canada', 'K1E 6T5' ,'647-123-4567', 'Billing');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('Andy', '789 Keele St.', 'ON',
'Canada', 'K3C 9T5' ,'416-123-9568', 'Billing');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('Andy', '789 Keele St.', 'ON',
'Canada', 'K3C 9T5' ,'416-123-9568', 'Shipping');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('John', '345 John St.', 'ON',
'Canada', 'M1C 5X3' ,'416-321-3456', 'Billing');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('John', '345 John St.', 'ON',
'Canada', 'M1C 5X3' ,'416-321-3456', 'Shipping');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('Peter', '321 McCown St.', 'ON',
'Canada', 'L5T 1R7' ,'416-456-4567', 'Billing');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('Peter', '321 McCown St.', 'ON',
'Canada', 'L5T 1R7' ,'416-456-4567', 'Shipping');
INSERT INTO Address (username, street, province, country, zip, phone, addrType) VALUES ('Roger', '124 Yonge St', 'ON',
'Canada', 'K1E 6T5' ,'647-123-4567', 'Shipping');

/* Purchase Order
* lname: last name
* fname: first name
* id: purchase order id
* status:status of purchase
*/
DROP TABLE PO;

CREATE TABLE PO (
po_id 		VARCHAR(15) NOT NULL,
username 	VARCHAR(20)  NOT NULL,
status 		VARCHAR(20) NOT NULL,
a_id 		INT NOT NULL,
po_date 	VARCHAR(10) NOT NULL,
lname		VARCHAR(20) NOT NULL,
fname		VARCHAR(20) NOT NULL,
PRIMARY KEY(po_id),
FOREIGN KEY (a_id) REFERENCES Address(a_id)
);

INSERT INTO PO VALUES ( '201506071545332', 'Roger', 'PROCESSED', 2, '20150607', 'Rose', 'Wendy');
INSERT INTO PO VALUES ( '201511212359082', 'John', 'DENIED', 6, '20151121', 'John', 'Willie');
INSERT INTO PO VALUES ( '201512041403527', 'Andy', 'ORDERD', 4, '20151204', 'Andy', 'Olly');
INSERT INTO PO VALUES ( '201604010203634', 'Roger', 'ORDERD', 10, '20160401', 'Andy', 'Sherry');

SELECT * from PO order by PO_date desc;
select * from PO where PO_date > '20150710' and po_date < '20151203';

/* Items on order
* id : purchase order id
* bid: unique identifier of Book
* price: unit price
*/
DROP TABLE POItem;

CREATE TABLE POItem (
po_id 		VARCHAR(15) NOT NULL,
bid 		VARCHAR(20) NOT NULL,
quantity	INT NOT NULL,
PRIMARY KEY(po_id,bid),
FOREIGN KEY(po_id) REFERENCES PO(po_id) ON DELETE CASCADE,
FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE
);

INSERT INTO POItem (po_id, bid, quantity) VALUES ('201506071545332', 'b001', 1);
INSERT INTO POItem (po_id, bid, quantity) VALUES ('201511212359082', 'b002', 2);
INSERT INTO POItem (po_id, bid, quantity) VALUES ('201512041403527', 'b003', 1);
INSERT INTO POItem (po_id, bid, quantity) VALUES ('201512041403527', 'b004', 1);
INSERT INTO POItem (po_id, bid, quantity) VALUES ('201512041403527', 'b001', 3);
INSERT INTO POItem (po_id, bid, quantity) VALUES ('201604010203634', 'b001', 3);
INSERT INTO POItem (po_id, bid, quantity) VALUES ('201604010203634', 'b002', 2);
INSERT INTO POItem (po_id, bid, quantity) VALUES ('201604010203634', 'b003', 1);


select POItem.bid, sum(POItem.quantity) from POItem, PO where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' 
and POItem.PO_id >= (select min(PO_id) from POItem where PO_id like '20150607%') 
and POItem.PO_id <= (select max(PO_id) from POItem where PO_id like '20151204%') group by POItem.bid;

select bid, Q from (select POItem.bid, sum(POItem.quantity) as "Q" from POItem, PO 
where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' group by POItem.bid) as M where Q=
(select max(Q) from (select POItem.bid, sum(POItem.quantity) as "Q" from POItem, PO 
where POItem.PO_id=PO.PO_id and PO.status<>'DENIED' group by POItem.bid) as M)

/* visit to website
* day: date
* bid: unique identifier of Book
* eventtype: status of purchase
*/
CREATE TABLE VisitEvent (
day varchar(8) NOT NULL,
bid varchar(20) not null REFERENCES Book(bid),
eventtype varchar(20) NOT NULL,
FOREIGN KEY(bid) REFERENCES Book(bid)
);

INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12202015', 'b001', 'VIEW');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12242015', 'b001', 'CART');
INSERT INTO VisitEvent (day, bid, eventtype) VALUES ('12252015', 'b001', 'PURCHASE');

/* Customer register
* username: Username to sign in
* password: Password to sign in
*/
DROP TABLE CUSTOMER;

CREATE TABLE Customer (
username	VARCHAR(20) NOT NULL,
password	VARCHAR(20) NOT NULL,
PRIMARY KEY (username)
);

INSERT INTO Customer (username, password) VALUES ('Roger', 'roger123');
INSERT INTO Customer (username, password) VALUES ('Steven', 'steven123');
INSERT INTO Customer (username, password) VALUES ('John', 'john123');
INSERT INTO Customer (username, password) VALUES ('Peter', 'peter123');
INSERT INTO Customer (username, password) VALUES ('Andy', 'Andy123');
INSERT INTO Customer (username, password) VALUES ('Malan', 'malan123');
INSERT INTO Customer (username, password) VALUES ('Emily', 'emily123');
INSERT INTO Customer (username, password) VALUES ('Evelyn', 'evelyn123');

SELECT username, password FROM Customer WHERE username = 'username';
UPDATE CUSTOMER SET "PASSWORD"='roger124' WHERE "USERNAME"='Roger';
DELETE FROM Customer WHERE username = 'Eva';

/* Customer profile
* username: Username to sign in, FK to CUSTOMER
* fname: first name
* lname: last name
* email: email address
*/
CREATE TABLE Profile (
username	VARCHAR(20) NOT NULL,
fname		VARCHAR(20),
lname		VARCHAR(20),
email		VARCHAR(40),
PRIMARY KEY (username),
FOREIGN KEY(username) REFERENCES Customer(username) ON DELETE CASCADE);

INSERT INTO Profile (username, fname, lname, email) VALUES ('Steven', 'Steven', 'Eric', 'steveneric@yorku.ca');
INSERT INTO Profile (username, email) VALUES ('Andy', 'andyabc@yorku.ca');

UPDATE PROFILE SET "FNAME"='Andy', "LNAME"='Tommy' WHERE "USERNAME"='Andy';

/* Credit Card payment information
* username: Username to sign in, FK to CUSTOMER
* cardType: card type
* cardHolder: card holder
* cardNumber: card number
* expireM: expire month
* expireY: expire year
*/
DROP TABLE CreditInfo;

CREATE TABLE CreditInfo (
username	VARCHAR(20) NOT NULL,
cardType	INT NOT NULL,
cardHolder	VARCHAR(50) NOT NULL,
cardNumber	VARCHAR(20) NOT NULL,
expireM		INT NOT NULL,
expireY		INT NOT NULL,
a_id		INT,
PRIMARY KEY (username),
FOREIGN KEY (username) REFERENCES Customer(username) ON DELETE CASCADE,
FOREIGN KEY (a_id) REFERENCES Address(a_id) ON DELETE CASCADE
);

INSERT INTO CreditInfo (username, cardType, CardHolder, CardNumber, expireM, expireY, a_id) 
VALUES ('Roger', 1, 'Roger Sun', '1111 2222 3333 4444', 8, 17, 1);

/*
 * Review comments on books
 * bid: Book ID
 * username: user's username
 * rating: rate from 1-5
 * review: paragraph of review
 */
CREATE TABLE Review(
bid 		VARCHAR(20) NOT NULL,
username	VARCHAR(20) NOT NULL,
rating		INT NOT NULL,
review		VARCHAR(500),
PRIMARY KEY(bid, username),
FOREIGN KEY(bid) REFERENCES Book(bid) ON DELETE CASCADE,
FOREIGN KEY(username) REFERENCES Customer(username) ON DELETE CASCADE
);

INSERT INTO Review (bid, username, rating, review) VALUES ('b001', 'Roger', 5, 'Very good book!');
INSERT INTO Review VALUES ('b002', 'Roger', 4, 'Good book!');