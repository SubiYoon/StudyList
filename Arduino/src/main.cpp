#include "Arduino.h"
int analog = 0;
void setup() { Serial.begin(9600); }
void loop() {
  analog = analogRead(A0);
  Serial.println(analog);
  delay(1000);
}
