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

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_sq.NEXTVAL, '비전공자를 위한 이해할 수 있는 IT지식', 'T.W.I.G', 'IT');

INSERT INTO book (bno, bname, b_publish, cate)
VALUES (mem_Sq.NEXTVAL, '오늘부터 개발자 비전공자를 위한 개발자 취업 입문 개론', '천그루숲', 'IT');

-- 서브쿼리는 ( ) 내에 기술해야함
-- 서브쿼리는 괄호로 묶어주어야 한다. 서브쿼리 실행 후 메인 쿼리가 실행됨
-- 단일행 서브 쿼리
-- scott과 같은 부서의 사원은??
SELECT empno, ename, sal, deptno FROM emp WHERE deptno=(SELECT deptno FROM emp WHERE ename='SCOTT'); 
-- 평균급여 이상을 받는 사원은?
SELECT empno, ename, sal, deptno FROM emp WHERE sal>=(SELECT AVG(sal) FROM emp);

-- 7369사원과 같은 업무를 하는 사원은?
SELECT empno, ename, sal, job FROM emp WHERE job=(SELECT job FROM emp WHERE empno=7369);
-- scott보다 급여가 높은 사원은?
SELECT empno, ename, job, sal FROM emp WHERE sal>(SELECT sal FROM emp WHERE ename='SCOTT');
-- 21.서브쿼리(2) 문제 1
SELECT ename, hiredate, job, deptno FROM emp
WHERE job = (SELECT job FROM emp WHERE ename='ADAMS')
OR deptno = (SELECT deptno FROM emp WHERE ename='SCOTT');
-- 21.서브쿼리(2) 문제 2
SELECT empno, ename, job, hiredate, sal FROM emp 
WHERE job=(SELECT job FROM emp WHERE empno=7521) 
AND sal>(SELECT sal FROM emp WHERE empno=7934);
-- 서브쿼리에 그룹함수 사용하기
-- [문제] 담당업무가 'SALESMAN' 사원의 평균급여보다 작게 받는 사원은?
SELECT ename, job, sal FROM emp WHERE sal<(SELECT AVG(sal) FROM emp WHERE job='SALESMAN'); 

-- HAVING절의 서브쿼리 사용하기
-- HAVING은 그룹함수의 결과를 조건절로 사용할 때 사용
SELECT deptno, MIN(sal) FROM emp GROUP BY deptno
HAVING MIN(sal)>(SELECT MIN(sal)  FROM emp WHERE deptno=20);  
-- 21.서브쿼리(2) 문제 1
SELECT job, AVG(sal) FROM emp GROUP BY job 
HAVING AVG(sal)>(SELECT AVG(sal) FROM emp WHERE deptno=10); 

-- 다중행 서브쿼리
SELECT empno, ename, sal FROM emp
WHERE sal>(SELECT sal FROM emp WHERE deptno=10);

-- 급여 3000, 1250, 1300을 받는 사원은?
SELECT * FROM emp WHERE sal=3000 OR sal=1250 OR sal=1300;
SELECT * FROM emp WHERE sal IN(3000, 1250, 1300);

SELECT max(sal) FROM emp GROUP BY job;
-- 부서별 최고 급여를 받는 사원을 출력
-- IN 연산자 : 서브쿼리결과가 다중행일때 or 처리
SELECT empno, ename, job, sal, deptno FROM emp 
WHERE sal IN(SELECT max(sal) FROM emp GROUP BY deptno);

-- [문제] 업무별 최대 급여를 받는 사원의 사원번호, 이름, 업무, 급여를 출력
SELECT empno, ename, job, sal FROM emp 
WHERE sal IN(SELECT max(sal) FROM emp GROUP BY job);

-- ANY 연산자 : 하나의 조건만 만족해도 선택
-- 업무가 'SALESMAN'인 사원의 최소급여보다 많으면서 부서번호가 20번이 아닌 사원의 이름과 급여, 부서코드 출력
SELECT ename, sal, deptno FROM emp WHERE deptno !=20 AND sal > ANY(SELECT sal FROM emp WHERE job='SALESMAN'); 
-- 22.서브쿼리(3) 문제
SELECT ename, job, sal, hiredate FROM emp
WHERE hiredate >ANY(SELECT hiredate FROM emp WHERE deptno=(SELECT deptno FROM emp WHERE ename='KING'));

-- ALL 연산자 : 모든 조건에 만족해야 선택
-- 업무가 salesman인 사원의 급여보다 많고, 부서가 20이 아닌 사원의 이름과 급여 출력
SELECT ename, sal FROM emp 
WHERE deptno != 20 
AND sal>ALL(SELECT sal FROM emp WHERE job='SALESMAN');

-- EXISTS 연산자 : 서버쿼리의 결과값과 같은 값이 존재하면 선택
SELECT empno, ename, sal, mgr FROM emp e
WHERE EXISTS (SELECT empno FROM emp WHERE e.empno = mgr);
SELECT empno, ename, sal, mgr FROM emp e
WHERE empno = ANY(SELECT e.empno FROM emp WHERE e.empno = mgr);

-- 다중 열 서브쿼리 : 서브쿼리에서 선택한 레코드의 컬림이 2개 이상일 때
-- 쌍비교
SELECT empno, sal, comm, deptno FROM emp WHERE (sal, deptno)
IN (SELECT sal, deptno FROM emp WHERE deptno=30 AND NVL(comm, 0) > 0);

-- 23.서브쿼리(4) 문제
-- 업무별 최소급여
SELECT job, MIN(sal)  FROM emp GROUP BY job;
-- 업무별 최소급여 받는 사원 출력(업무별로 정렬)
SELECT empno, ename, job, deptno FROM emp 
WHERE (job, sal) IN (SELECT job, MIN(sal) FROM emp GROUP BY job) ORDER BY job;

-- 비쌍비교
SELECT empno, sal, job, deptno FROM emp
WHERE sal IN(SELECT sal FROM emp WHERE deptno = 30 AND comm IS NOT NULL)
AND deptno IN (SELECT deptno FROM emp WHERE deptno = 30 AND comm IS NOT NULL);

-- FROM절에 사용하는 서브쿼리
SELECT ename, sal
FROM (SELECT empno, ename, job, sal FROM emp WHERE deptno IN (10, 20)) e;

-- UNION : 합집합 - 중복데이터 제외한 합집합
SELECT deptno FROM emp
UNION
SELECT deptno FROM dept;

-- UNIONALL : 합집합 - 중복데이터 포함 합집합
SELECT deptno FROM emp
UNION ALL
SELECT deptno FROM dept;

-- INTERSECT : 교집합 - 중복데이터만 추출
SELECT deptno FROM emp
INTERSECT
SELECT deptno FROM dept;

-- MINUS : 차집합 - 첫번째 SELECT 에서 두번째 SELECT 뺴
SELECT deptno FROM dept
MINUS
SELECT deptno FROM emp;


-- MERGE : 레코드가 존재하면 UPDATE 실행, 존재하지 않으면 INSERT 실행

-- 테이블 복사
CREATE TABLE emp2
AS
SELECT * FROM emp WHERE deptno=10;
-- 병합
MERGE INTO emp2 a USING emp b
ON (a.empno = b.empno) --()필수
WHEN MATCHED THEN
	UPDATE SET sal = sal+2000
WHEN NOT MATCHED THEN
	INSERT VALUES(b.empno, b.ename, b.job, b.mgr, b.hiredate, b.sal, b.comm, b.deptno);


-- CASE : DECODE와 비슷 -> 조건문
SELECT empno, ename, deptno,
	CASE DEPTNO 
	WHEN 10 THEN '기획부'
	WHEN 20 THEN '인사부'
	WHEN 30 THEN '총괄부'
	ELSE '총무부'
	END as "부서명"
FROM emp;

SELECT ename,
	CASE
		WHEN ename LIKE 'AD%' THEN '10%'
		WHEN ename LIKE 'S%' THEN '20%'
		WHEN ename LIKE '%B%' THEN '30%'
		ELSE '40%'
	END as 결과
FROM emp;
	

-- NULLIF(a, b) -> a와 b가 같으면 NULL을 반환
--				-> a와 b가 다르면 a를 반환
SELECT NULLIF (123,123) FROM dual;
SELECT NULLIF (123,456) FROM dual;
SELECT CASE WHEN 'ADAMS' = 'SMITH'
			THEN NULL
			ELSE 'ADAMS'
		END kkk
FROM dual;

-- COALESCE -> NULL 데이터 처리
SELECT comm, COALESCE(comm, 1) FROM emp;
SELECT comm, case when comm is not null
					then 100
					else coalesce(comm, 50)
				end shit
FROM emp;

CREATE Table book_tbl(
	isbn NUMBER(13) primary key,
	book_name VARCHAR2(100) not null,
	price number(7) not null,
	genre varchar2(20) null,
	first_pub date,
	pages number(4),
	author_code number(5) not null,
	pub_code number(5) not null,
	writedate date default sysdate
);

CREATE table pub_tbl(
	pub_code number(5) primary key,
	pub_tel varchar2(15),
	pub_email varchar2(100),
	pub_owner varchar2(30),
	publish varchar2(50) not null,
	writedate date default sysdate
);

CREATE table author_tbl(
	author_code number(5) primary key,
	author varchar2(30) not null,
	author_email varchar2(100),
	debue varchar2(100),
	debue_year date,
	writedate date default sysdate
);



SELECT * FROM emp;
SELECT * FROM emp2;
INSERT INTO emp(empno, ename) VALUES(7900, 'DDDD');
SELECT * FROM book_tbl;
SELECT * FROM PUB_tbl;
SELECT * FROM AUTHOR_tbl;
SELECT * FROM book;