import serial.ArduinoSerialMonitor;
import serial.PORT_SPEED;
import pc.SystemResourcesMonitor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        String portName = args[0];
        ArduinoSerialMonitor arduinoSerialMonitor = new ArduinoSerialMonitor(portName, PORT_SPEED.BAUD_57600);

        SystemResourcesMonitor systemResourcesMonitor
                = new SystemResourcesMonitor(m -> {
            try {
                arduinoSerialMonitor.getOutputStream().write(m.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        systemResourcesMonitor.start();
    }

}
