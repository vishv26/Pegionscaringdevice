# Pegionscaringdevice
Pigeon Scaring System - Java + Arduino (ESP8266)
Project Type: Java Desktop GUI + IoT Hardware (Motion Detection + Sound Trigger)
Developer: [Your Name]
Folder Name: PigeonScareProject
Main Java File: PigeonGUI.java


---

Overview

This is a motion-detection-based pigeon scaring system using:

ESP8266 microcontroller

PIR sensor for motion detection

Relay module to trigger a siren/speaker

Java GUI built with Swing for live monitoring



---

Hardware Components

ESP8266 (NodeMCU or similar)

PIR Motion Sensor

5V Relay Module

Siren/Buzzer (powered by 12V battery or adapter)

Jumper wires + Breadboard

Power Supply (USB/laptop or 12V battery)



---

Circuit Connections

PIR Sensor to ESP8266

VCC → 3.3V (ESP8266)

GND → GND

OUT → D1 (GPIO5)


Relay Module to ESP8266

VCC → VIN (if 5V relay)

GND → GND

IN → D2 (GPIO4)


Relay Output to Siren (External Power)

Siren + → 12V Battery +

Siren - → Relay NO (Normally Open)

Relay COM → Battery -



---

How it works

The PIR sensor detects motion (like a pigeon landing).

The ESP8266 triggers the relay, activating the siren.

The Java GUI displays logs in real-time (connected via COM port).

The user can monitor motion events, status, and logs from the GUI.



---

Java GUI Features

Dark Theme modern layout

COM port selection + connection status

Live serial monitor of messages from ESP8266

Motion alerts shown in GUI

Time-stamped logs

Clean UI with scrollable log area

Safe disconnect button to release COM port before uploading new Arduino code



---

How to Run

1. Flash Arduino Code

Open Arduino IDE

Select Board: NodeMCU 1.0

Port: Select correct COM port

Upload PigeonScaringSystem.ino


2. Run Java GUI

Open project folder PigeonScareProject in VS Code

Compile and run PigeonGUI.java


javac PigeonGUI.java
java PigeonGUI

Click "Connect" to start monitoring



---

Troubleshooting

Can't upload Arduino code?

Close the Java GUI before uploading (disconnects COM port).


No output in GUI?

Make sure COM port is correct.

Check PIR sensor and wiring.


Want better range or sensitivity?

Adjust PIR sensor potentiometers.




---

Planned Improvements

Adjustable motion detection range via GUI

Custom siren sound control

Battery backup indicator

WiFi connectivity for remote alerts (optional)



---
