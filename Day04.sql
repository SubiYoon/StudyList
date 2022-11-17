-- 테이블관리
-- emp테이블에 tel필드를 문자 15자리를 저장하도록 필드에 추가
--		NOT NULL : 반드시 데이터가 있어야 한다.
--			NULL : 데이터가 없어도 생성가능
--	필드명 데이터 타입
ALTER TABLE emp2 ADD (tel VARCHAR2(15));

-- 필드수정하기
ALTER TABLE emp2 MODIFY (ename VARCHAR2(20));
INSERT INTO emp2 (empno, ename) VALUES (1234, '세종대왕');

-- 데이터에 영향을 주면 변경실패
ALTER TABLE emp2 MODIFY (job VARCHAR2(5));

-- 데이터에 영향을 주지 않으면 변경됨
ALTER TABLE emp2 MODIFY (ename VARCHAR2(15)); 

-- 필드 삭제하기
ALTER TABLE emp2 DROP COLUMN mgr;

-- 테이블 생성
-- 회원정보 테이블 생성 : member
-- 필드명 생성 : no, username, phone, addr, writedate
-- primary key는 자동으로 not null로 잡는다.
-- null은 생략가능함
CREATE TABLE member(
	no NUMBER(5) PRIMARY KEY,
	username  VARCHAR2(20) NOT NULL,
	phone VARCHAR2(15) NOT NULL,
	email VARCHAR2(50) NULL,
	addr VARCHAR2(300),
	writedate DATE DEFAULT SYSDATE -- writedate에 값을 설정하지 않으면 자동으로 현재 날짜로 저장
);
-- 테이블 생성 확인
SELECT * FROM member;

-- 시퀀스
-- 시퀀스는 규칙적데이터 자동으로 생성해주는 객체
-- 한번 생성된 정보는 다시 생헝하지 않음
CREATE SEQUENCE mem_sq
START WITH 1
INCREMENT BY 1;

-- 5부터 3씩 증가하는 시퀀스 생성
CREATE SEQUENCE test_sq
START WITH 5
INCREMENT BY 3;

-- 시퀀스 목록 확인하기
SELECT *FROM user_sequences;

-- 시퀀스 객체에서 번호를 얻어오기
-- NEXTVAL -> 다음에 생성될 수
-- CURRVAL -> 현재 사용한 수
-- 5, 8, 11, 14, 17, 20, ...
SELECT test_sq.NEXTVAL FROM dual;
SELECT test_sq.CURRVAL FROM dual;
-- 동시 실행시 두 수는 같다.
SELECT test_sq.NEXTVAL, test_sq.CURRVAL FROM dual;
-- 앞뒤 변경해도 결과는 같음
SELECT test_sq.CURRVAL, test_sq.NEXTVAL FROM dual;

-- member 테이블에 레코드 추가하기
INSERT INTO member (no, username, phone) VALUES(mem_sq.NEXTVAL, 'AAAAA', '010-7894-9658');
INSERT INTO member (no, username, phone) VALUES(mem_sq.NEXTVAL, 'BBBBB', '010-7894-9658');

-- addr필드 삭제
ALTER TABLE member DROP COLUMN addr;

-- 시퀀스 삭제
DROP SEQUENCE test_sq; 

-- 테이블 삭제
DROP TABLE member;

DROP TABLE emp5;

-- 삭제시 쓰레기 남지 않도록 지우기
SELECT * FROM user_sequences;

DROP TABLE book;

-- 도서테이블 만들기
CREATE TABLE book(
	bno NUMBER(5) NOT NULL,
	bname VARCHAR2(100) NOT NULL,
	b_publish VARCHAR2(50),
	cate VARCHAR2(20),
	writedate date DEFAULT SYSDATE
);

ALTER TABLE book MODIFY (bname VARCHAR2(150));

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (1, 'Java의 정석', '도우출판', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (2, '이보다 더 쉬울 수 없는 자바 머신러닝 with Weka', 'BJPUBLIC', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, '코드로 배우는 스프링 웹프로젝트 개정판', '남가람북스', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, 'JavaScript와 함께하는 NoSQL DBMS 맛있는 MongoDB', 'BJPUBLIC', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, 'Do it 한권으로 끝내는 웹 기본 교과서 HTML+CSS+자바스크립트 웹 표준의 정석', '이지스퍼블리싱', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, 'Do it 내 손으로 직접 코딩하여 확인한다 자료구조와 함께 배우는 알고리즘 입문', '이지스퍼블리싱', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, 'Do it 비전공자도 기초부터 확실하게! 오라클로 배우는 데이터베이스 입문', '이지스퍼블리싱', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, 'Do it 개발 10년 강의 10년 명강사의 기초 튼튼 코딩 밥상 자바 프로그래밍 입문', '이지스퍼블리싱', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, '그린전동자동차기사 필기 한권으로 끝내기', 'SD에듀', 'CAR');

SELECT * FROM book;

select mem_sq.CURRVAL FROM dual;


SELECT * FROM book;