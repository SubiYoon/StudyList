SQL> conn test/1234
Connected.
SQL> select * from tab;

no rows selected

SQL> create table aaa(
  2  f1 number not null,
  3  f2 varchar2(20));
create table aaa(
*
ERROR at line 1:
ORA-01031: insufficient privileges 


SQL> conn system/master1234
Connected.
SQL> -- test에게 테이블 생성 또는 변경할 수 있는 권한 설정하기
SQL> grant create table to test;

Grant succeeded.

SQL> conn test/1234
Connected.
SQL> create table aaa(
  2  f1 number not null,
  3  f2 varchar2(20));
create table aaa(
*
ERROR at line 1:
ORA-01950: no privileges on tablespace 'SYSTEM' 


SQL> conn system/master1234
Connected.
SQL> -- 롤 : 권한 묶음
SQL> -- connect : 접속
SQL> -- resource : 테이블 객체관련 권한 설정
SQL> -- dba : 관리자 권한 설정
SQL> grant connect, resource to test;

Grant succeeded.

SQL> conn test/1234
Connected.
SQL> create table aaa(
  2  f1 number not null,
  3  f2 varchar2(20));

Table created.

SQL> select * from tab;

TNAME                                                        TABTYPE            
------------------------------------------------------------ --------------     
 CLUSTERID                                                                      
----------                                                                      
AAA                                                          TABLE              
                                                                                
                                                                                

SQL> insert into aaa values(1, 'DDDD');

1 row created.

SQL> select * from aaa;

        F1 F2                                                                   
---------- ----------------------------------------                             
         1 DDDD                                                                 

SQL> conn system/master1234
Connected.
SQL> -- 관리자 권한이 있는 계정 만들기
SQL> create user test2 identified by 1234;

User created.

SQL> -- 권한 설정하기
SQL> grant connect, resource, dba to test2;

Grant succeeded.

SQL> conn test2/1234
Connected.
SQL> -- test2계정에서 사용자 계정생성하기
SQL> create user test3 identified by 1234;

User created.

SQL> conn test/1234
Connected.
SQL> create user test4 identified by 1234;
create user test4 identified by 1234
                                *
ERROR at line 1:
ORA-01031: insufficient privileges 


SQL> conn test2/1234
Connected.
SQL> grant resource, connect to test3;

Grant succeeded.

SQL> conn test3/1234
Connected.
SQL> create table BBBB(
  2  xxx number(3),
  3  yyy varchar2(10));

Table created.

SQL> insert into BBBB values(123, 'ABCD');

1 row created.

SQL> select * from BBBB;

       XXX YYY                                                                  
---------- --------------------                                                 
       123 ABCD                                                                 

SQL> -- 권한 회수
SQL> conn test2/1234
Connected.
SQL> revoke dba to test2;
revoke dba to test2
       *
ERROR at line 1:
ORA-00990: missing or invalid privilege 


SQL> conn system/master1234
Connected.
SQL> revoke dba to test2;
revoke dba to test2
       *
ERROR at line 1:
ORA-00990: missing or invalid privilege 


SQL> revoke dba from test2;

Revoke succeeded.

SQL> conn test2/1234
Connected.
SQL> create user samid identified by 1234;
create user samid identified by 1234
                                *
ERROR at line 1:
ORA-01031: insufficient privileges 


SQL> conn system/master1234
Connected.
SQL> select username from dba_users;

USERNAME                                                                        
------------------------------------------------------------                    
TEST2                                                                           
SCOTT                                                                           
TEST                                                                            
TEST3                                                                           
SYS                                                                             
SYSTEM                                                                          
ANONYMOUS                                                                       
APEX_PUBLIC_USER                                                                
FLOWS_FILES                                                                     
APEX_040000                                                                     
OUTLN                                                                           

USERNAME                                                                        
------------------------------------------------------------                    
DIP                                                                             
ORACLE_OCM                                                                      
XS$NULL                                                                         
MDSYS                                                                           
CTXSYS                                                                          
DBSNMP                                                                          
XDB                                                                             
APPQOSSYS                                                                       
HR                                                                              

20 rows selected.

SQL> -- test계정 비밀번호를 5678로 변경
SQL> alter user test identified by 5678;

User altered.

SQL> conn test/5678
Connected.
SQL> select * from tab;

TNAME                                                        TABTYPE            
------------------------------------------------------------ --------------     
 CLUSTERID                                                                      
----------                                                                      
AAA                                                          TABLE              
                                                                                
                                                                                

SQL> conn system/master1234
Connected.
SQL> -- 계정 잠그기
SQL> -- lock(잠그기), unlock(풀기)
SQL> alter user test account lock;

User altered.

SQL> conn test/5678
ERROR:
ORA-28000: the account is locked 


Warning: You are no longer connected to ORACLE.
SQL> conn system/master1234
Connected.
SQL> alter user test account unlock;

User altered.

SQL> conn test/5678
Connected.
SQL> select * from tab;

TNAME                                                        TABTYPE            
------------------------------------------------------------ --------------     
 CLUSTERID                                                                      
----------                                                                      
AAA                                                          TABLE              
                                                                                
                                                                                

SQL> conn system/master1234
Connected.
SQL> -- 계정 삭제하기
SQL> drop user test;
drop user test
*
ERROR at line 1:
ORA-01922: CASCADE must be specified to drop 'TEST' 


SQL> conn system/master1234
Connected.
SQL> drop user test2;

User dropped.

SQL> drop user test cascade;

User dropped.

SQL> select username from dba_users;

USERNAME                                                                        
------------------------------------------------------------                    
SCOTT                                                                           
TEST3                                                                           
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

SQL> drop user test3 cascade;

User dropped.

SQL> select username from dba_users;

USERNAME                                                                        
------------------------------------------------------------                    
SCOTT                                                                           
SYS                                                                             
SYSTEM                                                                          
ANONYMOUS                                                                       
APEX_PUBLIC_USER                                                                
FLOWS_FILES                                                                     
APEX_040000                                                                     
OUTLN                                                                           
DIP                                                                             
ORACLE_OCM                                                                      
XS$NULL                                                                         

USERNAME                                                                        
------------------------------------------------------------                    
MDSYS                                                                           
CTXSYS                                                                          
DBSNMP                                                                          
XDB                                                                             
APPQOSSYS                                                                       
HR                                                                              

17 rows selected.

SQL> spool off
