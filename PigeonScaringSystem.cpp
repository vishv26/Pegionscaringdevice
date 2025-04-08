#include <ESP8266WiFi.h>

#define pirPin D1
#define relayPin D2

bool motionDetected = false;

void setup() {
  Serial.begin(9600);
  pinMode(pirPin, INPUT);
  pinMode(relayPin, OUTPUT);
  digitalWrite(relayPin, LOW);
  Serial.println("System Ready");
}

void loop() {
  int pirValue = digitalRead(pirPin);

  if (pirValue == HIGH && !motionDetected) {
    Serial.println("Motion Detected");
    digitalWrite(relayPin, HIGH);
    delay(5000); // Siren on for 5 seconds
    digitalWrite(relayPin, LOW);
    motionDetected = true;
    delay(2000); // Cooldown between motion readings
    motionDetected = false;
  }
}
