// File: PigeonGUI.java
package PigeonScareProject;

import com.fazecast.jSerialComm.SerialPort;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PigeonGUI extends JFrame {
    private JTextArea logArea;
    private SerialPort serialPort;
    private JComboBox<String> portList;
    private JButton connectButton, disconnectButton;
    private boolean isConnected = false;

    public PigeonGUI() {
        setTitle("Pigeon Scaring System - Advanced");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setBackground(Color.BLACK);
        logArea.setForeground(Color.GREEN);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(logArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        portList = new JComboBox<>();
        for (SerialPort port : SerialPort.getCommPorts()) {
            portList.addItem(port.getSystemPortName());
        }

        connectButton = new JButton("Connect");
        disconnectButton = new JButton("Disconnect");
        disconnectButton.setEnabled(false);

        connectButton.addActionListener(this::connectToESP);
        disconnectButton.addActionListener(e -> disconnect());

        bottomPanel.add(new JLabel("Port:"));
        bottomPanel.add(portList);
        bottomPanel.add(connectButton);
        bottomPanel.add(disconnectButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void connectToESP(ActionEvent e) {
        if (isConnected) return;
        String selectedPort = (String) portList.getSelectedItem();
        serialPort = SerialPort.getCommPort(selectedPort);
        serialPort.setComPortParameters(9600, 8, 1, 0);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);

        if (serialPort.openPort()) {
            log("Connected to " + selectedPort + " ✅");
            isConnected = true;
            connectButton.setEnabled(false);
            disconnectButton.setEnabled(true);
            portList.setEnabled(false);

            Thread thread = new Thread(() -> {
                Scanner scanner = new Scanner(serialPort.getInputStream());
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    log("ESP: " + line);
                    if (line.toLowerCase().contains("motion")) {
                        log("⚠️ Motion Detected!");
                    }
                }
                scanner.close();
            });
            thread.start();
        } else {
            log("❌ Failed to connect!");
        }
    }

    private void disconnect() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
            log("Disconnected from ESP8266.");
        }
        isConnected = false;
        connectButton.setEnabled(true);
        disconnectButton.setEnabled(false);
        portList.setEnabled(true);
    }

    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            logArea.append("[" + timestamp + "] " + message + "
");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PigeonGUI().setVisible(true);
        });
    }
}