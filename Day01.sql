-- 커서가 있는 줄의 쿼리문이 실행됨
-- 테이블목록을 확인
SELECT * FROM tab;
-- 테이블 구조 확인


SELECT * FROM emp;
-- 필드명 : 영어, 한글 가능
SELECT ename 사원명, sal, sal*0.1 bonus from emp;

-- where 절: 조건에 맞는 레코드 선택하기 (from절 다음에 where절)
SELECT * FROM emp WHERE DEPTNO=10;
SELECT ename 사원명, sal 급여, deptno "부서 코드" FROM emp WHERE JOB='MANAGER';

-- emp테이블의 사원중 급여가 2000이상이고, 부서코드가 30번인 사원을 선택하라
SELECT empno, ename, sal, deptno FROM emp WhERE SAL>=2000 AND DEPTNO=30;

-- emp 테이블의 사원중 담당업무가 manager 이거나 salesman인 사원중 급여가 2000~4000사이인 사원을 선택하라.
SELECT empno, ename, job, sal FROM emp WHERE (job='MANAGER' OR JOB='SALESMAN') AND (SAL>=2000 AND  sal<=4000);

-- between 연산자
-- 조건절 : 필드명 between a and b(필드명의 값이 a와 b사이이면)
-- 급여가 2000 ~ 4000사이인 사원을 선택
SELECT ename, job, sal FROM EMP e WHERE sal BETWEEN 2000 AND 4000;
-- 겹여가 2000 ~ 4000사이가 아닌 사원 선택
SELECT ename, job, sal FROM EMP e WHERE sal NOT BETWEEN 2000 AND 4000;

-- in 연산자 : or
-- 급여가 1250, 1500, 1300인 사원을 선택하라.
SELECT empno, ename, sal FROM emp WHERE sal=1250 or sal=1500 or sal=1300;

SELECT empno, ename, sal FROM emp WHERE sal IN (1250, 1500, 1300);

SELECT empno, ename, sal FROM emp WHERE sal NOT IN (1250, 1500, 1300);

-- like 연산자
--		문자열에서 일부 문자가 있는 레코드 선택할때
--		% : 여러개의 문자
--		_ : 1개의 문자
--
-- 사원명이 A로 시작하는 사원은??
SELECT empno, ename, sal, comm FROM emp
WHERE ename LIKE 'A%';

-- 사원명이 S로 끝나는 사원은??
SELECT empno, ename FROM emp
WHERE ename LIKE '%S';

-- 사원명에 a가 포함되어 있으면 선택하라
SELECT empno, ename FROM emp
WHERE ename LIKE '%A%';

-- A 다음 D
SELECT empno, ename FROM emp
WHERE ename like '%A%D%';

-- 사원명중 두번째 문자가 L인사원을 선택하라
SELECT empno, ename FROM emp
WHERE ename like '_L%';

SELECT empno, ename FROM emp
WHERE ename NOT like '_L%';

--is null, is not null
SELECT ename, sal, comm, comm+sal FROM emp;
SELECT ename, sal, comm FROM emp WHERE comm = null;
SELECT ename, sal, comm FROM emp WHERE comm IS null;
SELECT ename, sal, comm FROM emp WHERE comm IS NOT null;

-- 사원번호, 사원명, 담당업무, 급여, 보너스, 지급액을 선택하라.
-- nvl : Null Value 값이 null인 데이터를 원하는 데이터로 변환하여 처리
SELECT empno, ename, job, sal, nvl(comm, 0) comm, sal+nvl(comm, 0) payment FROM emp;

-- 보너스를 받는 사원의 사원명, 급여, 보너스를 선택하라.
SELECT ename, sal, comm FROM emp WHERE comm IS NOT NULL AND comm != 0;

-- order by : 정렬하기 (asc:오름차순, desc:내림차순)
-- 급여가 높은 순으로 정렬하라.
SELECT ename, sal FROM emp ORDER BY sal ASC; -- asc 생략가능
SELECT ename, sal FROM emp ORDER BY sal DESC;

-- 업무별 급여순으로 정렬하라.
SELECT ename, job, sal FROM emp ORDER BY job ASC, sal ASC;

-- 81년도에 입사한 사원을 부서별 급여순으로 정렬하라.
SELECT ename, job, hiredate, sal FROM emp
WHERE HIREDATE LIKE '%81%' ORDER BY job ASC, sal DESC;





SELECT * FROM emp;