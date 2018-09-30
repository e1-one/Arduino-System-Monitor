#include <LiquidCrystal.h>

LiquidCrystal lcd(4, 6, 10, 11, 12, 13);

void setup()
{
  Serial.begin(57600);
  lcd.begin(16, 2);  // set up the LCD's number of columns and rows:

  lcd.setCursor(0, 0);
  lcd.print("Waiting for");
  lcd.setCursor(0, 1);
  lcd.print("connection ...");
}

void loop()
{
  while (Serial.available() > 0)
  {
    String recieved = Serial.readString();
    String line1 = recieved.substring(0, 16);
    String line2 = recieved.substring(16, 32);
    lcd.setCursor(0, 0);
    lcd.print(line1);
    lcd.setCursor(0, 1);
    lcd.print(line2);
  }

}
