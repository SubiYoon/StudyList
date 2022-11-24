--사원명,비밀번호, 연락처, 부서코드, 직급코드를 입력받아 employee테이블에 사원을 등록하라.
--프로시저 생성

CREATE OR REPLACE PROCEDURE employee_insert(i_username employee.username%TYPE,
											i_password employee.password%TYPE,
											i_tel employee.tel%TYPE,
											i_dept_code employee.dept_code%TYPE,
											i_posi_code employee.posi_code%TYPE,
											o_result OUT NUMBER) -- insert문 처리 결과 내보내기
IS 
BEGIN 
	o_result := 1; --o_reslut 변수에 1대입
	INSERT INTO employee(empno, username, password, tel, dept_code, posi_code)
	VALUES (emp_sq.nextval, i_username, i_password, i_tel, i_dept_code, i_posi_code);
--예외처리
EXCEPTION 
	WHEN OTHERS THEN
		o_result := 0; --예외가 발생하면 레코드 추가 실패
END;

SELECT * FROM user_sequences;

SELECT * FROM EMPLOYEE ;

SELECT * FROM tab ;


-- 모든 레코드 선택 (사원번호, 이름, 연락처, 성별, 입사일) 프로시저
-- sys_refcursor : 모든 데이터타입사용
CREATE OR REPLACE PROCEDURE emp_all_select(o_result OUT sys_refcursor)
IS 
BEGIN 
	OPEN o_result FOR 
	SELECT empno, username, tel, gender, hiredate FROM employee ORDER BY empno;
END;

-- 이름을 입력받아 해당사원을 선택하는 프로시저
CREATE OR REPLACE PROCEDURE employee_search(name_search IN employee.username%TYPE,
											r_employee OUT sys_refcursor)
IS 
BEGIN 
	OPEN r_employee FOR 
	SELECT empno, username, tel, hiredate, dept_name, posi_name  FROM employee
	JOIN department ON employee.dept_code = department.dept_code
	JOIN POSITION ON employee.posi_code = POSITION.posi_code
	WHERE username LIKE '%'||name_search||'%'; 
END;

-- 사원번호에 해당하는 사원의 비밀번호, 연락처, 퇴사일 수정하는 프로시저
CREATE OR REPLACE PROCEDURE employee_edit(i_empno IN employee.empno%TYPE,
										  i_password IN employee.password%TYPE,
										  i_tel IN employee.tel%TYPE,
										  i_resigndate IN varchar2, -- 입력방식을 정해주어야함 ex)2022-10-10, 20221010, 2022/10/10
										  r_data OUT NUMBER)
IS 
BEGIN 
	r_data :=1;
	UPDATE employee SET password=i_password, tel=i_tel, resigndate=to_date(i_resigndate, 'YYYYMMDD')
	WHERE empno=i_empno;
EXCEPTION 
	WHEN OTHERS THEN 
	r_data := 0;
END;

-- 사원번호를 입력받아 해당사원삭제하는 프로시저 만들기
CREATE OR REPLACE PROCEDURE employee_del(i_empno IN employee.empno%TYPE,
										 r_data OUT NUMBER)
IS 
BEGIN 
	r_data := 1;
	DELETE FROM employee WHERE empno=i_empno;
EXCEPTION 
	WHEN OTHERS THEN
	r_data := 0;
END;


SELECT * FROM EMPLOYEE;
