#include "Arduino.h"

int val = 0;

void setup() {
  // 프로그램 시작시 최초 1번 실행
  pinMode(2, INPUT);
  pinMode(3, OUTPUT);
  Serial.begin(9600);
}

void loop() {
  // 프로그램 후 반복적으로 실행
  val = digitalRead(2);

  if (val == HIGH) {
    digitalWrite(3, HIGH);
  } else {
    digitalWrite(3, LOW);
  }

  Serial.println(val);
}
