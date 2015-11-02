#include <IRremote.h>

IRsend irsend;

void setup()
{
  Serial.begin(9600);
}

void loop() {
  switch (Serial.read()) {
        case 'l':{
        irsend.sendPanasonic(0x28C6, 0x8087F);
        Serial.println("Ligando");
        delay(100);
        break;
        }
        case 'd':{
        irsend.sendPanasonic(0x28C6, 0x80840);
        Serial.println("Desligando");
        delay(100);
        break;
        }        
  }
  
}
