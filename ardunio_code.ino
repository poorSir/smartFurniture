#include <SoftwareSerial.h>
#include <ArduinoJson.h>
SoftwareSerial mySerial(2, 3); // RX, TX 
//发送的数据
String json="";
//发送数据长度
int sendlength;
void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
  }
  Serial.println("Goodnight moon!");
  mySerial.begin(115200);
  delay(2000);
  mySerial.print("AT+RST\r\n");
  delay(8000);
  clearCash();
  Serial.println("rst finish");
  mySerial.print("AT+CWMODE=3\r\n");
  delay(5000);
  clearCash();
   Serial.println("mode finish");
   mySerial.print("AT+CWJAP=\"debug5a621\",\"debug5a621\"\r\n");
  delay(8000);
  clearCash();
  Serial.println("CWJAP finish");
  mySerial.print("AT+CIPSTART=\"TCP\",\"192.168.1.143 \",8888\r\n" );
  delay(8000);
  clearCash();
  json ="{\"id\":1,\"name\":\"szh\"}";
  delay(300);
  String text1 ="AT+CIPSEND=";
  sendlength =json.length()+2;
  text1.concat(String(sendlength));
  Serial.println(text1);
  delay(300);
  mySerial.println(text1);
  delay(1000);
  clearCash();
  Serial.print("Send String:");
  Serial.println(json);
  mySerial.println(json);
  delay(1000);
  clearCash();
  Serial.println("setup end");
}

void loop() { // run over and over
    String inString="";
    boolean start =false;
    mySerial.listen();
   while(mySerial.available()){
    char inChar = (char)mySerial.read();
    Serial.write(inChar);
    if(start){
       if(inChar == '}'){
          start =false;
          Serial.println("start false");
       }
       if(start){
        inString +=(char)inChar;
       }
       Serial.println(inString);
    }
    if(inChar == '{'){
      start =true;
      Serial.println("start true");
      delay(10);
    }
    delay(10);
   }
   if(inString != ""){
    Serial.print("InputString:");
    Serial.println(inString);
    if(inString == "off"){
        digitalWrite(7, LOW);
        json ="{\"state\":\"off\"}";
        delay(300);
        sendResult();
    }else if(inString == "on"){
      digitalWrite(7, HIGH);
      json ="{\"state\":\"on\"}";
      delay(300);
      sendResult();
    }else{
      json ="{\"state\":\"error\"}";
      delay(300);
      sendResult();
    }
    inString="";
   }
}
//清空缓存
void clearCash(){
   while(mySerial.available()){
    mySerial.read();
  }
}
//发送结果数据
void sendResult(){
   String text1 ="AT+CIPSEND=";
   sendlength =json.length()+2;
   text1.concat(String(sendlength));
   mySerial.println(text1);
   delay(1000);
   clearCash();
   mySerial.println(json);
   delay(1000);
   clearCash();
}



