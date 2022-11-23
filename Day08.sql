-- view 테이블 : 가상테이블

-- view생성
-- create or replace : 뷰가 존재하지 않으면 새로 생성
-- 					   이미 뷰가 존재하면 새로 만듬
-- SQL> --scott에게 view권한 설정(grant create view to scott;)
CREATE OR REPLACE VIEW emp_view
AS SELECT empno, ename, job, sal FROM emp;

SELECT * FROM emp_view;
-- view를 이용한 레코드 추가
INSERT INTO emp_view VALUES (5555, 'AAAA', '행정', 3500);
-- view(가상테이블)에 레코드를 추가,수정,삭제 등은 기존테이블에 영향을 줌
SELECT * FROM emp;
UPDATE emp_view SET job='회계' WHERE empno=7369;
DELETE FROM emp_view WHERE job='MANAGER';

-- view에서 SELECT만 가능하도록 설정
-- emp테이블에서 사원번호 사원명, 담당업무, 입사일, 급여를 이용하여 읽기 전용 뷰 생성
CREATE VIEW emp_view_read
AS
SELECT empno, ename, job, hiredate, sal FROM EMP e WITH READ ONLY;

SELECT * FROM emp_view_read;

-- emp_view_read 뷰는 읽기전용 뷰이므로 추가,수정,삭제 등은 불가능
INSERT INTO emp_view_read VALUES (3333, 'BBBB', '기획', sysdate, 4000) -- 에러발생

-- 업무별 급여의 합계 view를 이용하여 생성
SELECT job, sum(sal) FROM emp_view GROUP BY job;

-- 사원번호, 사원명, 담당업무, 부서코드, 부서명을 이용하여 뷰생성
-- 서브쿼리를 조인하여 사용도 가능
CREATE VIEW emp_dept
AS (SELECT e.empno, e.ename, e.job, d.deptno, d.dname FROM emp e
JOIN dept d ON e.deptno=d.deptno);

-- with check option : 조건에 해당하는 레코드만 추가, 수정할 수 있도록 뷰테이블 생성
CREATE OR REPLACE VIEW emp_check
AS
SELECT empno, ename, sal, deptno FROM emp
WHERE deptno=20
WITH CHECK OPTION;

INSERT INTO emp_check VALUES (2222, 'CCCC', 4200, 20);
INSERT INTO emp_check VALUES (6666, 'DDDD', 3800, 10);

-- 뷰확인
SELECT * FROM user_views;

-- 뷰삭제
DROP VIEW emp_view_read;

DELETE FROM emp WHERE empno=6666;

SELECT * FROM emp_check;
SELECT * FROM emp;


-- pl/sql을 이용한 프로시저 만들기
CREATE OR REPLACE PROCEDURE emp_info(p_empno IN emp.empno%TYPE) --매개변수는 IN을 사용해야함
IS 
	-- 변수 선언
	s_empno emp.empno%TYPE;
	s_ename varchar2(10);
	s_sal emp.sal%TYPE; 
BEGIN 
	-- 실행문
	SELECT empno, ename, sal INTO s_empno, s_ename, s_sal FROM emp WHERE empno=p_empno;
	-- 출력
	dbms_output.put_line('사원번호 : '||s_empno);
	dbms_output.put_line('사원이름 : '||s_ename);
	dbms_output.put_line('사원급여 : '||s_sal);
END;

-- 프로시저 확인
SELECT * FROM user_source;


-- 실행결과를 질의 결과에 출력하기 위해 set해줌
SET serveroutput ON;

-- 프로시저 실행하기
call emp_info(7788);


-- 사원번호를 입력받아 사원번호, 사원명, 담당업무, 입사일, 급여를 선택하여 출력하는 프로시저 만들기
CREATE OR REPLACE PROCEDURE emp_list(i_empno IN emp.empno%TYPE)
IS 
	--변수선언 : %rowtype 테이블내의 모든 필드의 변수와 데이터형을 한번에 선언
	r_emp emp%rowtype;
BEGIN 
	SELECT empno, ename, job, hiredate, sal
	INTO r_emp.empno, r_emp.ename, r_emp.job, r_emp.hiredate, r_emp.sal
	FROM emp
	WHERE empno=i_empno;

	dbms_output.put_line(r_emp.empno || ', ' || r_emp.ename || ', ' ||  r_emp.job || ', ' ||  r_emp.hiredate || ', ' ||  r_emp.sal);
END;

CALL emp_list(7839);

-- 사원등록하는 프로시저 만들기
-- 사원번호, 이름, 부서번호들 입력받아 사원등록하기

CREATE OR REPLACE PROCEDURE emp_insert(i_empno emp.empno%TYPE, i_ename emp.ename%TYPE, i_deptno emp.deptno%TYPE)
IS	--변수선언이 꼭 필요한것은 아님
BEGIN 
	INSERT INTO emp (empno, ename, deptno) VALUES (i_empno, i_ename, i_deptno);
	COMMIT;	-- 자동커밋을 위해 삽입
	dbms_output.put_line('사원이 등록되었습니다.');
END;

CALL emp_insert(8888, 'tttt', 40);

SELECT * FROM emp;