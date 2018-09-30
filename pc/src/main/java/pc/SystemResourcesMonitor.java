package pc;

import com.jezhumble.javasysmon.CpuTimes;
import com.jezhumble.javasysmon.JavaSysMon;

import java.util.function.Consumer;

public class SystemResourcesMonitor {
    private static final int METRIC_GIGA = 1000 * 1000 * 1000;
    private static final int MEGA_BYTES = 1024 * 1024 * 1024;

    private static final int REFRESH_RATE_MS = 1000; //>1000 is suggested

    private JavaSysMon javaSysMon;
    private Consumer<String> stringConsumer;

    public SystemResourcesMonitor(Consumer<String> consumer) {
        this.stringConsumer = consumer;
        javaSysMon = new JavaSysMon();
    }

    public void start() throws InterruptedException {
        CpuTimes previous = javaSysMon.cpuTimes();
        float cpuGHz = ((float) javaSysMon.cpuFrequencyInHz()) / METRIC_GIGA;
        while (true){
            CpuTimes current = javaSysMon.cpuTimes();
            float cpuUsage = current.getCpuUsage(previous);
            String str1 = String.format("CPU: %2.1f Ghz%3.0f%%", cpuGHz, cpuUsage * 100);

            float free = ((float) javaSysMon.physical().getFreeBytes()) / MEGA_BYTES;
            float total = ((float) javaSysMon.physical().getTotalBytes()) / MEGA_BYTES;
            float used = total - free;
            String str2 = String.format("RAM: %2.1f/%2.1f %2.0f%%", used, total, (used/total)*100);

            stringConsumer.accept(str1+str2);

            previous = current;
            Thread.sleep(REFRESH_RATE_MS);
        }
    }
}
