#include "Arduino.h"

void setup() { pinMode(3, OUTPUT); }
void loop() {
  for (int i = 0; i < 255; i += 10) {
    analogWrite(3, i);
    delay(100);
  }
}
