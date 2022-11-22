SELECT * FROM tab;
SELECT * FROM employee;
SELECT * FROM department;
SELECT * FROM POSITION;
SELECT * FROM user_constraints WHERE table_name IN (upper('employee'), upper('department'), upper('position'));

SELECT * FROM emp;
DELETE FROM emp WHERE empno in(777,8000);


-- emp테이블의 사원명, 급여 dept테이블의 부서명을 선택하라.
-- Equijoin, 내부조인 -> 필드의 값이 같은 데이터를 조인해줌
--					   조인대싱이 없으면 레코드 제외함
SELECT ename, sal, dname FROM emp, dept WHERE emp.deptno=dept.deptno;
SELECT emp.ename, emp.sal, dept.dname FROM emp, dept WHERE emp.deptno = dept.deptno;
SELECT e.ename, e.sal, d.dname FROM emp e, dept d WHERE e.deptno=d.deptno;

-- Join절 이용하여 조인하기 -> 조인기준은 on절에 표시
SELECT ename, sal, dname FROM emp JOIN dept ON emp.deptno=dept.deptno;

-- 사원번호, 사원명, 부서코드(emp), 부서코드(dept), 부서명, 위치를 선택하라
SELECT empno, ename, e.deptno, d.deptno, dname, loc
FROM emp e join dept d ON e.deptno=d.deptno;

-- where, order by
-- 사원번호, 사원명, 부서코드(emp), 부서코드(dept), 부서명, 위치를 선택하라
-- 단, 20부서의 사원은 제외
SELECT e.empno, e.ename, e.deptno, d.deptno, d.dname, d.loc
FROM emp e JOIN dept d ON e.deptno=d.deptno
WHERE e.deptno!=20;

-- 사원명, 담당업무, 급여, 부서코드(emp), 부서명을 선택하라
-- 단, 20부서의 사원은 제외하고, 이름순으로 정렬하라.
SELECT e.ename, e.job, e.sal, e.deptno, d.dname
FROM emp e JOIN dept d ON e.deptno=d.deptno
WHERE  e.deptno!=20
ORDER BY e.ename; -- ename, emp.ename, e.ename

-- and연산자
SELECT ename, job, sal, emp.deptno, dname
FROM emp JOIN dept ON emp.deptno=dept.deptno
AND emp.deptno!=20 ORDER BY emp.ename ASC;

-- 사원정보에서 사용할 시퀀스
-- 시퀀스 목록 확인하기 : user_sequences
SELECT * FROM user_sequences;

-- 사원번호 1000부터 1000씩 증가
CREATE SEQUENCE emp_sq
START WITH 1000
INCREMENT BY 1000;

CREATE SEQUENCE dept_sq
START WITH 1
INCREMENT BY 1;

CREATE SEQUENCE posi_sq
START WITH 1
INCREMENT BY 1;


-- 부서정보 추가
INSERT INTO department (dept_code, dept_name, dept_local)
VALUES (dept_sq.nextval, '인사부', '2층 203');

UPDATE department SET dept_local = '2층 203호' 
WHERE dept_code = 1;

INSERT INTO department (dept_code, dept_name)
VALUES (dept_sq.nextval, '기획부')

-- 직급정보 추가
INSERT INTO POSITION (posi_code, posi_name)
VALUES (posi_sq.nextval, '대표');

INSERT INTO POSITION (posi_code, posi_name, posi_desc)
VALUES (posi_sq.nextval, '사원', '신입1년이내');

-- 사원등록
INSERT INTO employee (empno, username, password, dept_code, posi_code)
VALUES (emp_sq.nextval, '홍길동', '1234', 1, 2);
INSERT INTO employee (empno, username, password, tel, gender, hiredate, dept_code, posi_code)
VALUES (emp_sq.nextval, '이순신', '1234', '010-1234-5678', 'M', sysdate, 2, 2);



UPDATE employee SET posi_code = 1
WHERE  empno = 1000;

-- 테이블3개 조인하기
-- 사원번호, 사원명, 직급, 직급설명, 부서명, 부서위치를 선택
SELECT e.empno 사원번호, e.username 사원명, p.posi_name 직급, p.posi_desc 부서설명, d.dept_name 부서, d.dept_local 부서위치 FROM employee e
JOIN department d ON e.dept_code=d.dept_code
JOIN POSITION p ON e.posi_code=p.posi_code;

-- employee테이블의 사원명, 연락처, 입사일
-- department테이블의 부서코드, 부서명, 부서위치
-- position테이블의 직급코드, 직급명
-- 사원명, 연락처, 부서명, 부서위치, 직급명, 입사일, 부서코드, 직급코드로 선택하고 사원명 오름차순 정렬
SELECT e.username 사원명, e.tel 연락처, d.dept_name 부서, d.dept_local 부서위치,
p.posi_name 직급, hiredate 입사일, d.dept_code 부서코드, p.posi_code 직급코드 FROM employee e
JOIN department d ON e.dept_code=d.dept_code JOIN position p ON e.posi_code=p.posi_code ORDER BY e.username;

SELECT e.empno, e.ename, e.sal, d.dname, d.loc FROM emp e
JOIN dept d ON e.deptno=d.deptno WHERE job='SALESMAN';

-- 서브쿼리를 이용한 조인
SELECT e.empno, e.ename, e.sal, d.dname, d.loc
FROM (SELECT empno, ename, sal, deptno FROM emp WHERE job='SALESMAN') e
JOIN dept d ON e.deptno = d.deptno;

-- NON Equi Join : 학점, 등급, 호봉 등 범위가 있는 데이터를 조인할 때
--				   1:1 매칭이 되지 않는 데이터 조인
-- 사원명, 급여, 호봉(grade)을 선택
SELECT e.ename, e.sal, s.grade FROM emp e
JOIN salgrade s ON e.sal BETWEEN s.losal AND s. hisal;

-- 10번과 20번 부서의 사원중 사원명, 담당업무, 급여, 부서명, 부서위치, 호봉을 선택하라.
SELECT e.ename, e.job, e.sal, d.dname, d.loc, s.grade FROM emp e
JOIN dept d ON e.deptno=d.deptno JOIN salgrade s ON e.sal BETWEEN s.losal AND s.hisal WHERE e.deptno!=30;


-- [과제물]
CREATE SEQUENCE book_sq
START WITH 1
INCREMENT BY 1;
SELECT * FROM user_sequences;
SELECT * FROM tab;
-- 작가정보테이블에 작가 최소 3명 등록
SELECT * FROM author_tbl;
INSERT INTO author_tbl VALUES (author_sq.nextval, '홍길동', 'hong@naver.com', '홍길동전', sysdate, sysdate);
INSERT INTO author_tbl VALUES (author_sq.nextval, '허준', 'joon@naver.com', '동의보감', sysdate, sysdate);
INSERT INTO author_tbl VALUES (author_sq.nextval, '정약용', 'jung@naver.com', '목민심서', '1920-04-2', sysdate);
-- 출판사정보테이블에 출판사 최소 3곳 등록
SELECT * FROM pub_tbl;
INSERT INTO pub_tbl VALUES (pub_sq.nextval, '의적', '02-123-4567', 'juck@yahoo.con', '각시탈', sysdate);
INSERT INTO pub_tbl VALUES (pub_sq.nextval, '약쟁이', '043-567-1379', 'drug@yahoo.con', '이선생', sysdate);
INSERT INTO pub_tbl VALUES (pub_sq.nextval, '용가리', '063-159-6487', 'dragon@yahoo.con', '잠자리', sysdate);
-- 책정보테이블에 책 최소 6권 등록
SELECT * FROM book_tbl;
INSERT INTO book_tbl (isbn, book_name, price, gerne, author_code, pub_code, writedate)
VALUES (book_sq.nextval, '빨간맛', 35000, 'food', 2, 2, sysdate);
INSERT INTO book_tbl (isbn, book_name, price, gerne, author_code, pub_code, writedate)
VALUES (book_sq.nextval, '궁금해', 30000, 'poem', 1, 1, sysdate);
INSERT INTO book_tbl (isbn, book_name, price, gerne, author_code, pub_code, writedate)
VALUES (book_sq.nextval, '허니', 55000, 'novel', 3, 3, sysdate);
INSERT INTO book_tbl (isbn, book_name, price, gerne, author_code, pub_code, writedate)
VALUES (book_sq.nextval, '자바', 25000, 'language', 1, 2, sysdate);
INSERT INTO book_tbl (isbn, book_name, price, gerne, author_code, pub_code, writedate)
VALUES (book_sq.nextval, '파이썬', 15000, 'language', 2, 3, sysdate);
INSERT INTO book_tbl (isbn, book_name, price, gerne, author_code, pub_code, writedate)
VALUES (book_sq.nextval, '몽고DB', 80000, 'database', 3, 1, sysdate);

-- 1. 테이블 2개로 조인문제 만들어 풀기
-- 2. 테이블 3개로 조인문제 만들어 풀기

-- self 조인(자기자신을 조인)
SELECT * FROM emp;

SELECT e1.empno 사번, e1.ename 사원명, e1.job 업무, e1.mgr "사수 사번", e2.ename "사수 이름", e2.job "사수 업무"
FROM emp e1 JOIN emp e2
ON e1.mgr=e2.empno;

-- [문제] emp테이블에서 "누구의 관리자는 누구이다."는 내용을 출력
SELECT e1.ename || '의 관리자는 ' || e2.ename || '이다.' FROM emp e1
JOIN emp e2 ON e1.mgr=e2.empno;

-- [문제] 사원명, 급여, 부서명, 관리자명, 입사일, 호봉을 선택
SELECT e1.ename 사원명, e1.sal 급여, d.dname 부서명, e2.ename 관리자명, e1.hiredate 입사일, s.grade 호봉 FROM emp e1
JOIN emp e2 ON e1.mgr=e2.empno JOIN dept d ON e1.deptno=d.deptno JOIN salgrade s ON e1.sal BETWEEN s.LOSAL AND s.hisal;

-- outer join -> 두테이블이 조인된 경우 한쪽테이블에 레코드가 남을 때
-- left outer join -> 두 테이블 중 오른쪽에 null데이터가 발생할 때
-- right outer join -> 두 테이블 중 왼쪽에 null데이터가 발생할 때
SELECT ename, e.deptno 사원부서코드, d.deptno 부서부서코드, dname
FROM emp e RIGHT OUTER JOIN dept d ON e.deptno=d.deptno;

SELECT ename, e.deptno 사원부서코드, d.deptno 부서부서코드, dname
FROM dept d LEFT OUTER JOIN emp e ON e.deptno=d.deptno;

-- where절 이용시 null 데이터가 발생하는 테이블에 (+)을 표시
SELECT e.ename, e.sal, e.deptno, d.dname
FROM emp e, dept d
WHERE e.deptno(+)=d.deptno;


-- Full Outer Join -> null 데이터가 있는 레코드가 left, right 상관없이 무조건 표시됨
SELECT DISTINCT e.deptno, d.deptno
FROM emp e FULL OUTER JOIN dept d ON e.deptno=d.deptno ORDER BY e.deptno;


--[종합문제]--
-- 1. emp테이블에서 사원이름, 부서번호, 부서명 출력 (부서번호순 오름차순)
SELECT e.ename, e.deptno, d.dname FROM emp e
JOIN dept d ON e.deptno=d.deptno ORDER BY e.deptno;

-- 2. emp테이블에서 NEW YORK에 근무하는 사원이름, 업무, 급여, 부서명 출력
SELECT e.ename, e.job, e.sal, d.dname FROM emp e
JOIN dept d ON e.deptno=d.deptno WHERE d.loc='NEW YORK';

-- 3. emp테이블에서 보너스 받는 사원이름, 부서명, 위치 출력
SELECT e.ename, d.dname, d.loc FROM emp e
JOIN dept d ON e.deptno=d.deptno WHERE e.comm>0;

-- 4. emp테이블에서 이름 중 L자가 있는 사원이름, 업무, 부서명, 위치 출력
SELECT e.ename, e.job, d.dname, d.loc FROM emp e
JOIN dept d ON e.deptno=d.deptno WHERE e.ename LIKE '%L%';

-- 5. 해당표 완성
SELECT e1.ename Employee, e1.empno Emp#, e2.ename Magager, e1.mgr Mgr# FROM emp e1
LEFT OUTER JOIN emp e2 ON e1.mgr=e2.empno ORDER BY e2.ename DESC;


SELECT * FROM emp;

