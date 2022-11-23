SQL> -- 새로운 계정생성하기
SQL> create user test identified by 1234;
User created.

SQL> select username from dba_users;

USERNAME
------------------------------------------------------------
SCOTT
TEST
SYS
SYSTEM
ANONYMOUS
APEX_PUBLIC_USER
FLOWS_FILES
APEX_040000
OUTLN
DIP
ORACLE_OCM

USERNAME
------------------------------------------------------------
XS$NULL
MDSYS
CTXSYS
DBSNMP
XDB
APPQOSSYS
HR
                                             
18 rows selected.

SQL> -- 생성한 test계정 접속하기
SQL> -- 현재 system에 접속된 상태에서 다른계정 접속하기
SQL> connect test/1234;
ERROR:
ORA-01045: user TEST lacks CREATE SESSION privilege; logon denied 


Warning: You are no longer connected to ORACLE.
SQL> select username from dba_users;
SP2-0640: Not connected
SQL> -- conn test/1234의 접속실패로 원래접속된 system계정 접속이 끊어짐
SQL> -- 관리자 계정 다시 접속
SQL> conn system/master1234
Connected.
SQL> -- test에게 접속권한 설정하기
SQL> grant create session to test;

Grant succeeded.

SQL> conn test/1234
Connected.
SQL> spool off