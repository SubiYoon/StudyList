-- 내장함수 - 숫자

-- ABS(n) -> 절대값
SELECT ABS(-90) Abolute FROM dual;

-- CEIL(n) -> 올림(큰 정수값)
SELECT CEIL(10.1), CEIL(-12.1) FROM dual;

-- floor(n) -> 내림(작은 정수값)
SELECT floor(10.9), floor(-12.9) FROM dual;

-- mod(n, m) -> 나머지 구하기
SELECT MOD(12, 5) FROM dual;

-- round(n, m) -> 반올림
SELECT ROUND(26.3658, 1), ROUND(523.3658, -2) FROM dual; 

-- trunc(n, m) -> 반내림
SELECT TRUNC(7.559, 2), TRUNC(5254.26, -2) FROM dual;

-- 사원테이블에서 부서번호가 10인 사원의 급여를 30으로 나눈 나머지 출력
SELECT sal, MOD(sal, 30) FROM emp WHERE deptno=10;


-- 내장함수 - 문자
-- concat(c1, c2)
SELECT empno, CONCAT(ename, '님')  AS ename FROM emp;

-- INITCAP(char) -> 각 단어의 첫번째 문자를 대문자로 변경
SELECT INITCAP('oracle test') FROM dual;
-- lower(char) -> 모든 영문자를 소문자로 변경
SELECT ename, job, LOWER(ename), LOWER(job) FROM emp; 
-- upper(char) -> 모든 영문자를 대문자로 변경
SELECT UPPER('oracle function test') FROM dual;

-- lpad(data, n, 'char') -> 왼쪽에 남는 자리를 특정문자로 채움
SELECT LPAD(ename, 8, '*') FROM emp; 
-- rpad() -> 오른쪽에 남는 자리를 특정문자로 채움
SELECT RPAD(ename, 8, '&') FROM emp;

-- substr(char, n, m) -> 문자열에서 일부 문자 얻어오기
--						 n : 시작위치(index는 1부터), m: 갯
SELECT SUBSTR('hong gildong', 3, 5) FROM dual;
-- 문자의 뒤에서 5번째 글자부터 3개 선택
SELECT SUBSTR('hong gildong', -5, 3) FROM dual;

-- length(char) -> 글자수 구하기
SELECT ename, LENGTH(ename) FROM emp;

-- 이름을 글자길이의 50%만큼 출력하고 나머지 문자는 '*'로 표시하라
SELECT RPAD(SUBSTR(ename, 1, CEIL(LENGTH(ename)/2)), LENGTH(ename), '*') ename FROM emp;

-- replace(char1, str1, str2) -> 특정 문자를 다른문자로 변경
--								 char의 값 중에 str1을 str2로 치환
SELECT ename, REPLACE(ename, 'A', '에이') FROM emp;

-- instr(char, str, n, m) -> 특정문자의 위치를 얻어준다.
--							 해당문자 없을시 0이 반환
SELECT ename, instr(ename, 'A') FROM emp;
SELECT ename, instr(ename, 'A', 2) FROM emp;
SELECT ename, instr(ename, 'T', 1, 2) FROM emp;

SELECT SUBSTR('goguma@nate.com', 1, INSTR('goguma@nate.com', '@')-1)  id,
SUBSTR('goguma@nate.com', INSTR('goguma@nate.com', '@')+1, INSTR('goguma@nate.com', 'm', -1)-INSTR('goguma@nate.com', '@')) domain FROM dual;

--	 TRIM : 양쪽사이드의 특정문자 제거
--	LTRIM : 왼쪽의 특정문자 제거
--	RTRIM : 오른쪽의 특정문자 제거
SELECT  sal, TRIM(0 FROM sal) FROM emp; 
SELECT ename, LTRIM(ename, 'S') FROM emp;
SELECT ename, RTRIM(ename, 'S') FROM emp;

-- general function
-- decode : 조건문
SELECT ename, deptno, decode(deptno, 10,'기획부', 20, '총괄부', 30, '인사부') FROM emp;

-- 날짜 함수
-- 오늘 현재 날짜 구하기
SELECT SYSDATE FROM dual;

-- to_char() -> 날짜를 문자로 변환
SELECT SYSDATE, TO_CHAR(sysdate, 'MONTH') FROM dual;

-- last_day() -> 마지막날
SELECT SYSDATE , LAST_DAY(SYSDATE) FROM dual;
SELECT ename, hiredate, LAST_DAY(hiredate) FROM emp;

-- add_month() -> 월을 더하기
SELECT ADD_MONTHS(SYSDATE, 30) FROM dual; 
SELECT TO_DATE('05122020', 'MMDDYYYY') FROM dual; 

SELECT TO_DATE('20-10-12', 'YY-MM-DD') FROM dual;
SELECT TO_DATE('2020-10-12', 'YYYY-MM-DD') FROM dual;
SELECT TO_DATE('2020-10-12 08:24:12', 'YYYY-MM-DD HH24:MI:SS') FROM DUAL;
SELECT TO_DATE('20160901151212', 'YYYY-MM-DDHH24MISS') AS ONE,
	TO_DATE('20160901091212', 'YYYYMMDDHHMISS') AS TWO,
	TO_DATE('2016090115', 'YYYYMMDDHH24') AS THREE,
	TO_DATE('2016', 'YYYY') AS FOUR
FROM dual;

-- month_between(d1, d2) -> d1과 d2사이의 개월수를 구해준다.
SELECT MONTHS_BETWEEN(TO_DATE('2021-05-10', 'YYYY-MM-DD'), SYSDATE) FROM dual; 
SELECT ROUND(MONTHS_BETWEEN(TO_DATE('2021-05-10', 'YYYY-MM-DD'), SYSDATE)) FROM dual; 

-- emp테이블 근속 개월수를 계산하라
SELECT ename, FLOOR(MONTHS_BETWEEN(SYSDATE, hiredate)) "근속 개월수" FROM emp; 

-- 날짜에 대한 반올림
-- year, month, day
SELECT SYSDATE , ROUND(SYSDATE, 'YEAR') FROM dual;
SELECT SYSDATE , ROUND(SYSDATE, 'MONTH') FROM dual;
SELECT SYSDATE , ROUND(SYSDATE, 'DAY') FROM dual;
SELECT SYSDATE , ROUND(SYSDATE, 'DD') FROM dual;

-- 날짜 산술 연산
--		날짜 - 날짜 : 숫자로 반환
--		날짜 - 숫자 : 날짜로 반환
-- 오늘을 기준으로 100일 후
SELECT SYSDATE+100 , SYSDATE -100 FROM dual;
-- 입사한지 몇일인지 구하여
SELECT ename, hiredate, SYSDATE-hiredate FROM emp;

-- 그룹함수
-- count() : 갯수 -> null유무를 확인해야함
--			 조건절까지 인용되어 계산됨
SELECT COUNT(ename) 사원수 FROM emp;
SELECT COUNT(ename), sal FROM emp; -- 문법적으로 오류가 있다. 실행 안됨
SELECT COUNT(ename) FROM emp WHERE job='MANAGER';

-- max(), min() : 해당컬럼의 값중에 최댓값, 최솟값을 구함
SELECT MAX(sal), MIN(sal) FROM emp WHERE job='SALESMAN';

-- sum(), avg()
SELECT SUM(sal), ROUND(AVG(sal), 1) FROM emp;  

-- stddev() : 표준편차
SELECT ROUND(STDDEV(sal), 1) FROM emp;


-- group by : 비교하고자 하는 자료들을 그룹화 하여 보여줌
SELECT  deptno, SUM(sal) 합, ROUND(AVG(sal), 2) 평균 FROM emp GROUP BY deptno ORDER BY deptno;

-- 급여가 1500불 이상인 사원 중 업무별 사원수, 급여의 합계, 급여의 최대값을 구하라.
SELECT job, COUNT(empno), SUM(sal), MAX(sal) FROM emp WHERE sal>=1500 GROUP BY job ORDER BY job;

-- 업무별 사원수를 구하라. 사원수가 3명이상인 업무를 선택하라
-- group by의 조건절은 having 이후에 작성
SELECT job, COUNT(empno) FROM emp  GROUP BY job HAVING COUNT(empno)>=3;

-- 전체 월급이 5000을 초과하는 각 업무에 대하여 업무와 월급여 합계를 출력
-- 단, 판매원은 제외하고 월 급여합계로 내림차순
SELECT job, SUM(sal) FROM emp WHERE job NOT IN 'SALESMAN' GROUP BY job HAVING SUM(sal)>5000 ORDER BY SUM(sal) DESC; 

-- rollup : 그룹함수처리 결과 각 필드의 통계를 구함
SELECT job, SUM(sal), ROUND(AVG(sal), 2) FROM emp
GROUP BY ROLLUP(job);

-- 부서를 1차분류하고 부서내에서 담당업무를 2차분류하여 사원수, 급여의 합계와 평균을 선택하라.
-- 분류 기준을 2개갖는 것
SELECT deptno, job, COUNT(empno), SUM(sal), AVG(sal) FROM emp GROUP BY(deptno, job) ORDER BY deptno, job;
-- 분류 기준을 2개이상으로 둘 때는, 1차로 분류한 기준으로 rollup하고 전체통계 작업
SELECT deptno, job, COUNT(empno), SUM(sal), AVG(sal) FROM emp GROUP BY ROLLUP(deptno, job) ORDER BY deptno, job;

-- 문제 1
SELECT empno, ename, job, hiredate, sal, comm, sal+NVL(comm, 0) 지급액 FROM emp;
-- 문제 2
SELECT ename, job, sal, NVL(comm, 0) bonus FROM emp WHERE (sal BETWEEN 2500 AND 4000) OR job='SALESMAN' ORDER BY sal DESC;


SELECT * FROM emp;					