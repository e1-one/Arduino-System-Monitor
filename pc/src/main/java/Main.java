import serial.ArduinoSerialMonitor;
import serial.PORT_SPEED;
import pc.SystemResourcesMonitor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws Exception {
        ArduinoSerialMonitor arduinoSerialMonitor = new ArduinoSerialMonitor("COM5", PORT_SPEED.BAUD_57600);

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
