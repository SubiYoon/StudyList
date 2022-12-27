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
                    p_text LONGBLOB NOT NULL,
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
INSERT INTO user(id, pwd, name, identity_num) VALUES('abc1234','1234','abc','111111-1111111');
INSERT INTO user(id, pwd, name, identity_num) VALUES('abc5678','5678','def','222222-2222222');
INSERT INTO category(c_num, c_name) VALUES(10,'가구');
INSERT INTO category(c_num, c_num2, c_name) VALUES(11, 10,'소파');
INSERT INTO category(c_num, c_num2, c_name) VALUES(12, 10,'침대');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(11,'가죽소파',1000000,'가죽소파입니다.');
INSERT INTO product(c_num, p_name, p_price, p_text) VALUES(12,'라텍스침대',1000000,'라텍스침대입니다.');
INSERT INTO cart(id, p_num, cnt) VALUES('abc1234', 1, 1);
INSERT INTO cart(id, p_num, cnt) VALUES('abc5678', 2, 5);
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('abc1234', '카드', '한강');
INSERT INTO orderr(id, method_payment, detailaddr) VALUES('abc5678', '무통장', '멀티캠퍼스');
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(1, 1, 2);
INSERT INTO orderr_detail(order_num, p_num, cnt) VALUES(2, 2, 4);

select * from user;
select * from orderr;
select * from category;
select * from product;
select * from cart;
select * from orderr_detail;

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
