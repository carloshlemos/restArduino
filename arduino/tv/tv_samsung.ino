#include <IRremote.h>

IRsend irsend;

void setup()
{
  Serial.begin(9600);
}

void loop() {
  switch (Serial.read()) {
        case 'l':{
        irsend.sendNEC(0xF4BA2988, 32);
        Serial.println("Ligando...ou... Desligando");
        delay(100);
        break;
        }
        case 'm':{
        irsend.sendNEC(0x61F4D827, 32);
        Serial.println("Vol -");
        delay(100);
        break;
        }        
        case 'n':{
        irsend.sendNEC(0x61F448B7, 32);
        Serial.println("Vol +");
        delay(100);
        break;
        }
        case 'p':{
        irsend.sendNEC(0x61F4E01F, 32);
        Serial.println("CH+");
        delay(100);
        break;
        }
        case 'q':{
        irsend.sendNEC(0x61F4807F, 32);
        Serial.println("CH-");
        delay(100);
        break;
        }
  }
  
}
