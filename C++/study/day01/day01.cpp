#include "stdio.h"

// java에서 class내 상수를 정하듯이 정하는 함수
// 보통 16진수로 표현시 규칙이 있다.
#define HUNGRY 0x001
#define THIRSTY 0x002
#define TIRED 0x004
#define FIRE 0x008

#define STATUS05 0x010
#define STATUS06 0x020
#define STATUS07 0x040
#define STATUS08 0x080

#define STATUS09 0x100
#define STATUS10 0x200
#define STATUS11 0x400
#define STATUS12 0x800

int main(int argc, char *argv[]) {
  // unsigned는 0을 포함한 자연수만 사용하겠다는 표시
  // 따라서 unsigned일 때와 아닐 때의 비트값에 유의해야 함
  // unsigned의 1111 1111은 255이지만, 그냥 1111 1111은 -1이다.
  unsigned char c = 255;

  printf("hello world!");

  int test = HUNGRY;
  int x = 3;

  printf("%d", x);

  // 비트연산
  // & 둘다 1이면 1
  // | 하나가 1이면 1
  // ^ 같으면 0, 다르면 1
  // ~ 1은 0으로, 0은 1로

  // 상태값이 2바이트라고 생각 0000 0000
  // HUNGRY : 0000 0001, THIRSTY : 0000 0010, TIRED : 0000 0100으로써 1을 비트에
  // 장착하는 개념 따라서 비트연산자를 사용

  short myStatus = 0;
  myStatus |= HUNGRY;  // 배고픈 상태 추가
  myStatus |= THIRSTY; // 피곤한 상태 추가

  // 상태 해제하는 방법
  // 비크 연사에서는 모든 비트가 0이면 false, 하나이상 1이면 true로 판단
  if (myStatus & THIRSTY) { // 0000 0011 & 0000 0010 = 0000 0010
    myStatus &= ~THIRSTY;   // 0000 0011과 1111 1101과의 곱연산
    myStatus &= ~THIRSTY;   // 0000 0011과 1111 1101과의 곱연산
  }

  // sancf : 터미널에서 입력받은 데이터를 바인딩
  int iInput = 0;
  scanf("%d", &iInput);

  // 배열
  // 선언 C/C++은 new 키워드를 사용하지 않는다??
  int iArray[10] = {};

  // C/C++의 경우 배열의 index를 정말 조심해야한다.
  // 다음과 같이 넣었을 경우 myStatus에 20이 바인딩 될수도 있다. (우연치 않게..)
  // why?? 하필 14번째 index의 좌표가 myStatus랑 겹쳐서...
  iArray[13] = 20;
  return 0;
}
