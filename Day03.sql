-- DML : SELECT(선택), INSERT(삽입), UPDATE(수정), DELETE(삭제)
-- 사원추가
-- 모든 필드의 값이 존재할때 : 레코드 선택했을때 표시되는 필드 순서와 일치해야 함
INSERT INTO emp VALUES(2222, '홍길동', '기획부', 7499, SYSDATE, 1000, 500, 40);

-- 일부의 데이터가 존재할때 레코드 추가
-- 사원번호(3333) not null 이기때문에 반드시 있어야함
-- 사원명(이순신), 급여(2000)
INSERT INTO emp (empno, sal, ename)
VALUES(333, 2000, '이순신');

-- 문제 dept테이블에 부서코드(50), 부서명(기획부), 위치(서울 강남구)
INSERT INTO dept VALUES(50, '기획부', '강남구');

-------------------------------------------------------------------------------------------------------------------------------------------
-- 테이블 복사
-- 기존테이블의 구조만 복사 가능
-- 기존테이블의 모든 레코드 or 일부 레코드 복사 가능
-------------------------------------------------------------------------------------------------------------------------------------------
-- emp테이블의 모든 필드와 모든 레코드를 복사하기
CREATE TABLE emp2 
AS
SELECT * FROM emp;
-- emp테이블의 일부 필드와 레코드 복사하기(사원번호, 사원명, 업무, 급여)
CREATE TABLE emp3
AS
SELECT empno, ename, job, sal FROM emp;
-- emp테이블의 일부 레코드(job이=MANEGER or ANAYST)를 복사하기
CREATE TABLE emp4
AS
SELECT * FROM emp
WHERE job IN('MANAGER', 'ANALYST');
--emp테이블의 테이블구조만 복사하기
CREATE TABLE emp5
AS
SELECT empno, ename, job, sal FROM emp
WHERE 1=2; -- 일어날 수 없는 조건을 입력

SELECT * FROM tab;


-- emp 모든필드 * 레코드
-- emp5 레코드는 없고 필드는 empno, ename, job, sal이 존재 (INSERT)
INSERT INTO emp5 (empno, ename, job, sal)
SELECT empno, ename, job, sal FROM emp;

-- UPDATE -> 테이블내의 데이터를 1개 이상 수정
-- 이순신사원의 업무를 인사부로 수정
UPDATE emp2 SET job='인사부' WHERE empno=333;

-- MANAGER의 급여를 10% 인상
UPDATE emp2 SET sal=sal*1.1 WHERE job='MANAGER';

-- [문제] 보너스를 받지 않는 사원의 보너스를 급여의 10%로 수정
UPDATE emp2 SET comm=sal*0.1 WHERE nvl(comm, 0)=0;

-- DELETE -> 레코드를 전체 or 일부 삭제한다.
DELETE FROM emp2 WHERE comm<200;

commit;

SELECT * FROM emp;
SELECT * FROM emp2;
SELECT * FROM emp3;
SELECT * FROM emp4;
SELECT * FROM emp5;
SELECT * FROM dept;