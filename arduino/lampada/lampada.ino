int ledPin =  13; //atribui o pino 13 a variável ledPin 
int dado; //variável que receberá os dados da porta serial
int resultado; //variável que receberá o status da porta 
 
void setup(){
  Serial.begin(9600);//frequência da porta serial
   pinMode(ledPin,OUTPUT); //define o pino o ledPin como saída
}
 
void loop(){
  if(Serial.available() > 0){ //verifica se existe comunicação com a porta serial
      dado = Serial.read();//lê os dados da porta serial
      resultado = digitalRead(ledPin);
      switch(dado){
        case 3:
           if (resultado == 0){
             digitalWrite(ledPin,HIGH); //liga o pino ledPin 
           }else {
             Serial.println("Ligado");
           }
        break;
        case 4:
           if (resultado == 1){
             digitalWrite(ledPin,LOW); //desliga o pino ledPin 
           }else {
             Serial.println("Desligado");
           }
         break;
    }
  }
}
