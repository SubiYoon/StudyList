-- 계정 생성
CREATE USER goguma IDENTIFIED BY 1234;

--1. 계정생
-- 	 계정 admin1, admin2, admin3 비번 자율
--2. 권한설정
-- 	 admin1 : connect, resource
-- 	 admin2 : 관리자권한
--	 admin3는 admin2로 생성
--3. admin3계정 잠그기
--4. admin1 계정 지우기

CREATE USER admin1 IDENTIFIED BY 1234;
CREATE USER admin2 IDENTIFIED BY 1234;

GRANT CONNECT, resource TO admin1;
GRANT dba TO admin2;
conn admin2/1234;
CREATE USER admin3 IDENTIFIED BY 1234;
ALTER USER admin3 account LOCK;
DROP USER admin1;
DROP USER admin2;
DROP USER admin3;
DROP USER goguma;

SELECT username FROM dba_users;