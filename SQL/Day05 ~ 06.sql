-- 출판사코드 시퀀스
CREATE SEQUENCE pub_sq
START WITH 1
INCREMENT BY 1; 

-- 작가코드 시퀀스
CREATE SEQUENCE author_sq
START WITH 10
INCREMENT BY 10;

-- 시퀀스 객체 확인하기
SELECT * FROM user_sequences;

-- 제약조건
SELECT *FROM tab;

SELECT * FROM book_tbl; 

-- 현재계정에 설정된 제약조건 확인하기
SELECT * FROM user_constraints WHERE table_name='emp';

SELECT * FROM emp;
SELECT * FROM emp2;
DROP TABLE emp2 purge;

-- ename을 not null 제약조건
-- ALTER table emp (constraint emp_nn_ename not null);
ALTER TABLE emp MODIFY (ename varchar2(20) NOT NULL);

-- unique : 유일한값으로 설정
--				테이블명_제약조건_필드명
-- 이미 emp테이블의 deptno은 중복값이 존재하므로 오류발생
-- 반드시 제약조건을 설정해야 할 경우 레코드 지우고 설정해야함
ALTER TABLE emp
ADD CONSTRAINT emp_uk_deptno
unique(deptno);

-- dept 테이블의 deptno 필드를 unique 제약조건
ALTER TABLE dept
ADD CONSTRAINT deptno_uk_deptno
UNIQUE(deptno);

SELECT * FROM user_CONSTRAINTS;

INSERT INTO dept(deptno, dname, loc) VALUES (50, '기획부', '강남구');

-- dept 테이블의 deptno에 설정된 제약조건 삭제하기
SELECT * FROM user_constraints;
ALTER TABLE dept DROP CONSTRAINT deptno_uk_deptno;

INSERT INTO dept (deptno, dname, loc) VALUES (40, '인사부', '송파구');

SELECT * FROM dept;

DELETE FROM dept WHERE dname='인사부';

-- dept테이블의 deptno을 primary key 설정하기
ALTER TABLE dept ADD CONSTRAINT dept_pk_deptno
PRIMARY KEY (deptno);

-- book_tbl의 고유번호
-- pub_tbl의 출판사코드
-- author_tbl의 작가코드
-- 위 세개를 primary key 또는 unique로 설정하라.
ALTER TABLE book_tbl ADD CONSTRAINT book_btl_uk_isbn
UNIQUE(isbn);
ALTER TABLE pub_tbl ADD CONSTRAINT pub_tbl_uk_pub_code
UNIQUE(pub_code);

-- emp테이블의 comm은 800~6000사이의 값만 저장되도록 제약조건을 설정한다.
ALTER TABLE emp ADD CONSTRAINT emp_ck_comm
CHECK (comm>=800 AND comm<=6000);	--기존의 데이터가 check범위를 초과

ALTER TABLE emp ADD CONSTRAINT emp_ck_sal
CHECK (sal>=800 AND sal<=6000);		--기존의 데이터가 check범위에 존재

SELECT * FROM emp;
SELECT * FROM user_constraints;

-- sal 제약범위초과
INSERT INTO emp (empno, ename, sal) VALUES (1111, 'ABCD', 7000);
-- sal 제약범위
INSERT INTO emp (empno, ename, sal) VALUES (1111, 'ABCD', 6000);

-- 보너스는 아래수만 등록가능하도록 제약조건 설정
-- 0, 300, 500, 1400, 1800, 2200
UPDATE emp SET comm = 0 WHERE comm IS NULL;
ALTER TABLE emp ADD CONSTRAINT emp_ck_comm
CHECK (comm IN (0, 300, 500, 1400, 1800, 2200));

UPDATE emp SET comm=1000;

DELETE FROM emp WHERE empno='1111';

-- 오류발생 : constraint로 수정할 수 없다. modify로만 수정가능
ALTER TABLE emp ADD CONSTRAINT emp_df_hiredate
DEFAULT (hiredate DATE DEFAULT sysdate);

ALTER TABLE emp MODIFY (hiredate DATE DEFAULT sysdate);

SELECT * FROM emp;

INSERT INTO emp (empno, ename, sal, comm) VALUES (777, 'DDDD', 3200, 2200);

--default는 초기입력값을 비웠을 때 입력된다.
INSERT INTO emp (empno, ename, sal, comm, hiredate)
VALUES (8000, 'EEEE', 4200, 1400, to_date('2022-08-09', 'yyyy-mm-dd'));

--외래키 -> foreign key
SELECT * FROM user_constraints;

-- emp 테이블의 deptno는 dept테이블의 deptno를 참조
ALTER TABLE emp ADD CONSTRAINT emp_fk_deptno
FOREIGN KEY (deptno) REFERENCES dept(deptno);

-- 제약조건 삭제
ALTER TABLE emp
DROP CONSTRAINT emp_fk_deptno;

-- 삭제가 가능한 forein key
ALTER TABLE emp ADD CONSTRAINT emp_fk_deptno
FOREIGN KEY (deptno) REFERENCES dept(deptno) ON DELETE CASCADE;

-- no action
-- 참조되는 데이터를 삭제할때 참조하고 있는 정보가 있으면 삭제할 수 없다.
-- cascade
-- 참조되는 데이터가 삭제되면 참조하고 있는 정보도 삭제됨

ALTER TABLE book_tbl ADD CONSTRAINT book_fk_author
FOREIGN KEY (author_code) REFERENCES author_tbl(author_code);

ALTER TABLE book_tbl ADD CONSTRAINT book_fk_publish
FOREIGN KEY (pub_code) REFERENCES pub_tbl(pub_code);

-- 외래키가 있는 테이블(book_tbl) 삭제
DROP TABLE book_tbl;
DROP TABLE pub_tbl;
DROP TABLE author_tbl;
DROP TABLE book;


DROP TABLE com_grade;

SELECT * FROM tab;
SELECT * FROM dept;
SELECT * FROM user_constraints;


