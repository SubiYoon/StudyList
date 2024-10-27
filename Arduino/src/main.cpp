#include "Arduino.h"
#include "LiquidCrystal.h"
#include "Wire.h"

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

void setup() {
  lcd.begin(16, 2);
  lcd.print("I'm stil hungry...");
}
void loop() {}
