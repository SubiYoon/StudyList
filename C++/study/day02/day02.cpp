#include <stdio.h>

// NOTE: 구조체 : 사용자 정의 자료형(Java의 Class같은 느낌??)
typedef struct _tagMyST {
  int a;
  float f;

  // C에서는 함수를 선언 할 수는 없다!
  int test(int a, int b) { return a + b; }
} MYST;

struct _tagBig {
  MYST k;
  int i;
  char c;
} BIG;

// typedef : 타입을 재정의 하는 것
typedef int INT; // int를 INT로 재정의해서 사용하겠다!
// 예시
// "struct _tagMyStyle을 재정의 한다. MYSTYLE"로 라는 뜻!
typedef struct _tagMyStyle {
  int a;
  float f;

  int test(int a, int b) { return a + b; }
} MYSTYLE;

int main(int argc, char *argv[]) {

  // NOTE: 구조체
  MYST t;

  int iSize = sizeof(t);

  // C++에서는 _tagBig으로 자료형을 선언해도 사용이 가능하지만
  _tagBig a01;
  // C에서는 struct를 앞에 선언하지 않으면 _tagMyST를 사용 할 수 없다.
  struct _tagBig a02;
  // _tagMyST 같이 typedef로 재정의해서 MYST를 사용하면 두 언어 호환가능

  // NOTE: 변수와 메모리

  //  변수의 종류
  // 1. 지역변수
  // 2. 전역변수
  // 3. 정적변수
  // 4. 외부변수

  // 메모리의 종류
  // 스택 영역 : 지역
  // 데이터 영역(프로그램 시작 시 생성, 종료시 삭제) : 정적, 외부
  // 읽기 전용(코드, Read Olny Memory)
  // 힙 영역
  return 0;
}
