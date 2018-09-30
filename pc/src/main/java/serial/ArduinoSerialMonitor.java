package serial;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;

public class ArduinoSerialMonitor {

    private InputStream inputStream;
    private OutputStream outputStream;

    public ArduinoSerialMonitor(String portName, PORT_SPEED portSpeed) throws Exception
    {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if ( portIdentifier.isCurrentlyOwned() )
        {
            throw new PortInUseException();//"Error: Port is currently inputStream use"
        }
        else
        {
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);

            if ( commPort instanceof SerialPort)
            {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(portSpeed.getSpeedValue(),SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);

                 inputStream = serialPort.getInputStream();
                 outputStream = serialPort.getOutputStream();
            }
            else
            {
                throw new RuntimeException("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
