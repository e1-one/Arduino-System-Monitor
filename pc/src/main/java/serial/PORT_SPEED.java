package serial;

public enum PORT_SPEED {
    BAUD_300(300),
    BAUD_1200(1200),
    BAUD_(2400),
    BAUD_2400(4800),
    BAUD_9600(9600),
    BAUD_19200(19200),
    BAUD_38400(38400),
    BAUD_57600(57600),
    BAUD_74880(74880),
    BAUD_115200(115200),
    BAUD_230400(230400),
    BAUD_250000(250000),
    BAUD_500000(500000),
    BAUD_1000000(1000000),
    BAUD_2000000(2000000)
    ;

    private final int speedValue;

    private PORT_SPEED(int speedValue) {
        this.speedValue = speedValue;
    }

    public int getSpeedValue() {
        return speedValue;
    }

}
