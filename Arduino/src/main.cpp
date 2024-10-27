#include "Arduino.h"

int val = 0;

void setup() {
  Serial.begin(9600);
  pinMode(8, OUTPUT);
}
void loop() {
  val = analogRead(A0);
  if (val < 200) {
    Serial.println("ON");
    digitalWrite(8, HIGH);
  } else {
    Serial.println("OFF");
    digitalWrite(8, LOW);
  }
  Serial.println(val);
  delay(100);
}
