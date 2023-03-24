USE myshop;

-- 테이블 삭제
DROP TABLE cart;
DROP TABLE orderr_detail;
DROP TABLE orderr;
DROP TABLE product;
DROP TABLE category;
DROP TABLE user;

-- 테이블 생성
CREATE TABLE user(id VARCHAR(10) NOT NULL,
					pwd VARCHAR(20) NOT NULL,
                    name VARCHAR(10) NOT NULL,
                    identity_num VARCHAR(14) not NULL );
CREATE TABLE orderr(order_num INT NOT NULL,
					id VARCHAR(10) NOT null,
					method_payment VARCHAR(10) NOT NULL,
                    rdate DATETIME NOT NULL DEFAULT NOW(),
                    detailaddr varchar(100) NOT null );
CREATE TABLE orderr_detail(od_num INT NOT NULL,
					order_num INT NOT NULL,
					p_num INT NOT NULL,
                    cnt INT NOT NULL);
CREATE TABLE category(c_num INT NOT NULL ,
						c_num2 INT NULL,
						c_name VARCHAR(30) NOT NULL);
CREATE TABLE product(p_num INT NOT NULL,
					c_num INT NOT NULL,
                    p_name VARCHAR(30) NOT NULL,
                    p_price INT NOT NULL,
                    p_text LONGTEXT NOT NULL,
                    p_date DATETIME NOT NULL DEFAULT NOW());
CREATE TABLE cart(cart_num INT NOT NULL,
					id VARCHAR(10) NOT NULL,
					p_num INT NOT NULL,
                    cnt INT NOT NULL);
-- 기본키 생성                    
ALTER TABLE user ADD CONSTRAINT PRIMARY KEY(id);
ALTER TABLE orderr ADD CONSTRAINT PRIMARY KEY(order_num);
ALTER TABLE orderr_detail ADD CONSTRAINT PRIMARY KEY(od_num);
ALTER TABLE category ADD CONSTRAINT PRIMARY KEY(c_num);
ALTER TABLE product ADD CONSTRAINT PRIMARY KEY(p_num);
ALTER TABLE cart ADD CONSTRAINT PRIMARY KEY(cart_num);

-- 초기값 설정
ALTER TABLE orderr MODIFY order_num INT AUTO_INCREMENT;
ALTER TABLE orderr_detail MODIFY od_num INT AUTO_INCREMENT;
ALTER TABLE product MODIFY p_num INT AUTO_INCREMENT;
ALTER TABLE cart MODIFY cart_num INT AUTO_INCREMENT;

-- 왜래키 생성
ALTER TABLE orderr ADD FOREIGN KEY(id) REFERENCES user(id);
ALTER TABLE orderr_detail ADD FOREIGN KEY(order_num) REFERENCES orderr(order_num);
ALTER TABLE orderr_detail ADD FOREIGN KEY(p_num) REFERENCES product(p_num);
ALTER TABLE category ADD FOREIGN KEY(c_num2) REFERENCES category(c_num);
ALTER TABLE product ADD FOREIGN KEY(c_num) REFERENCES category(c_num);
ALTER TABLE cart ADD FOREIGN KEY(id) REFERENCES user(id);
ALTER TABLE cart ADD FOREIGN KEY(p_num) REFERENCES product(p_num);

-- 데이터 삽입
INSERT INTO user(id, pwd, name, identity_num) VALUES('aaa5678','ab13','이순신','111111-1111111');
INSERT INTO user(id, pwd, name, identity_num) VALUES('bbb5678','cd46','강감찬','222222-2222222');
INSERT INTO user(id, pwd, name, identity_num) VALUES('ccc5678','ef79','세종대왕','333333-3333333');
INSERT INTO user(id, pwd, name, identity_num) VALUES('ddd5678','gh25','쓰레기','444444-4444444');
INSERT INTO user(id, pwd, name, identity_num) VALUES('eee5678','ij85','유관순','555555-5555555');
INSERT INTO user(id, pwd, name, identity_num) VALUES('fff5678','kl49','안중근','666666-6666666');
INSERT INTO user(id, pwd, name, identity_num) VALUES('ggg5678','mn76','이선생','777777-7777777');
INSERT INTO user(id, pwd, name, identity_num) VALUES('hhh5678','op37','마약왕','888888-8888888');
INSERT INTO user(id, pwd, name, identity_num) VALUES('iii5678','qr96','왕뚜껑','999999-9999999');
INSERT INTO user(id, pwd, name, identity_num) VALUES('jjj5678','st45','껑충껑충','123456-7891598');
INSERT INTO category(c_num, c_name) VALUES(10,'가구');
INSERT INTO category(c_num, c_num2, c_name) VALUES(11, 10, '소파');
INSERT INTO category(c_num, c_num2, c_name) VALUES(12, 10, '침대');
INSERT INTO category(c_num, c_num2, c_name) VALUES(14, null, '가전');
INSERT INTO category(c_num, c_num2, c_name) VALUES(15, 14, 'TV');
INSERT INTO category(c_num, c_num2, c_name) VALUES(16, 14, '청소기');
INSERT INTO category(c_num, c_num2, c_name) VALUES(17, 14, '냉장고');
INSERT INTO category(c_num, c_num2, c_name) VALUES(18, 14, '세탁기');
INSERT INTO category(c_num, c_num2, c_name) VALUES(19, 14, '건조기');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(11,'가죽소파',1000000,'가죽소파입니다.');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(12,'라텍스침대',1000000,'라텍스침대입니다.');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(15,'스마트TV',20000,'스마트tv입니다.');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(16,'청소기',20000,'청소기입니다.');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(17,'냉장고',120000,'냉장고입니다.');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(18,'세탁기',20000,'세탁기입니다.');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(19,'건조기',20000,'건조기입니다.');
INSERT INTO cart(id, p_num, cnt) VALUES('aaa5678', 1, 1);
INSERT INTO cart(id, p_num, cnt) VALUES('bbb5678', 1, 5);
INSERT INTO cart(id, p_num, cnt) VALUES('ccc5678', 2, 2);
INSERT INTO cart(id, p_num, cnt) VALUES('ddd5678', 2, 6);
INSERT INTO cart(id, p_num, cnt) VALUES('eee5678', 2, 10);
INSERT INTO cart(id, p_num, cnt) VALUES('fff5678', 5, 4);
INSERT INTO cart(id, p_num, cnt) VALUES('ggg5678', 3, 56);
INSERT INTO cart(id, p_num, cnt) VALUES('hhh5678', 4, 8);
INSERT INTO cart(id, p_num, cnt) VALUES('iii5678', 4, 6);
INSERT INTO cart(id, p_num, cnt) VALUES('jjj5678', 3, 7);
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('aaa5678', '신용카드', '한강');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('bbb5678', '무통장', '멀티캠퍼스');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('ccc5678', '휴대폰', '학교');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('ddd5678', '카카오페이', '동사무소');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('eee5678', '네이버페이', '학교');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('fff5678', '네이버페이', '멀티캠퍼스');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('ggg5678', '휴대폰', '멀티캠퍼스');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('hhh5678', '신용카드', '한강');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('iii5678', '휴대폰', '동사무소');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('jjj5678', '신용카드', '동사무소');
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(1, 1, 2);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(1, 2, 6);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(1, 5, 1);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(1, 7, 2);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(2, 1, 4);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(2, 2, 5);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(2, 3, 20);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(2, 4, 6);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(2, 7, 12);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(3, 6, 5);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(3, 4, 3);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(4, 5, 10);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(4, 7, 8);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(6, 1, 1);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(6, 2, 2);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(6, 3, 3);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(6, 4, 4);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(7, 5, 5); 
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(7, 6, 6); 
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(7, 7, 7); 
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(7, 7, 8);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(8, 2, 1);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(8, 3, 5);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(8, 4, 14);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(10, 5, 2);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(10, 1, 10);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(10, 2, 3);

select * from user;
select * from orderr;
select * from category;
select * from product;
select * from cart;
select * from orderr_detail;

desc cart;

-- 이름, 상품명, 수량, 총 금액(total)로 표시
select u.name, p.p_name, c.cnt, p.p_price*c.cnt total
from cart c
join product p
on p.p_num=c.p_num
join user u
on u.id=c.id;


-- BLOB 데이터 내용 보기
select CONVERT(p_text USING utf8) from product where p_num=1;

select * from information_schema.table_constraints;
