#include "stdio.h"

// java에서 class내 상수를 정하듯이 정하는 함수(enum같기도 하고..?)
#define HUNGRY 3

int main(int argc, char *argv[]) {
  unsigned char c = 255;

  printf("hello world!");

  int test = HUNGRY;
  int x = 3;

  printf("%d", x);
  return 0;
}
