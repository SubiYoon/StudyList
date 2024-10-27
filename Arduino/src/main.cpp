#include "Arduino.h"

void setup() { pinMode(5, OUTPUT); }
void loop() {
  tone(5, 261.6);
  delay(200);
  noTone(5);
  delay(200);
  tone(5, 311.1);
  delay(200);
  noTone(5);
  delay(200);
  tone(5, 269.6);
  delay(200);
  noTone(5);
  delay(200);
  tone(5, 349.2);
  delay(200);
  noTone(5);
  delay(200);
}
