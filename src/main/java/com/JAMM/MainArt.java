package com.JAMM;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import java.io.File;
import java.util.Locale;

public class Main {
    public static void main(String[] args) throws Exception {


        // === The code is executed in a new terminal window, drawing a graph ===

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        CentralProcessor processor = hal.getProcessor();
        GlobalMemory memory = hal.getMemory();
        final int graphWidth = 50, graphHeight = 10;
        double[] cpuHistory = new double[graphWidth];
        long[] prevTicks = processor.getSystemCpuLoadTicks();

        while (true) {
            double load = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            prevTicks = processor.getSystemCpuLoadTicks();
            System.arraycopy(cpuHistory, 1, cpuHistory, 0, graphWidth - 1);
            cpuHistory[graphWidth - 1] = load;

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=================================");
            System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
            System.out.println("Cores: " + processor.getPhysicalProcessorCount() +
                    " (Physical) / " + processor.getLogicalProcessorCount() + " (Logical)");
            System.out.println("Memory:  " +
                String.format("%.2f GiB / %.2f GiB",
                    (memory.getTotal() - memory.getAvailable()) / 1073741824.0,
                    memory.getTotal() / 1073741824.0)
            );
            System.out.println("=================================");
            System.out.printf("CPU Usage:   %.1f%%\n\n", load);

            // CPU graph
            for (int h = graphHeight-1; h >= 0; h--) {
                double threshold = ((double)h / (graphHeight-1)) * 100.0;
                for (int x = 0; x < graphWidth; x++)
                    System.out.print(cpuHistory[x] >= threshold ? "█" : " ");
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));
            System.out.println();
            Thread.sleep(500);
        }
    }
}
