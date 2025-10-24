package com.JAMM;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class Main {
    public static void main(String[] args) throws Exception {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        CentralProcessor processor = hal.getProcessor();
        GlobalMemory memory = hal.getMemory();

        final int graphWidth = 50;       // graph width
        final int graphHeight = 10;      // graph height (lines)
        double[] cpuHistory = new double[graphWidth]; // Download history array

        long[] prevTicks = processor.getSystemCpuLoadTicks();

        while (true) {
            // Getting CPU load
            double load = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            prevTicks = processor.getSystemCpuLoadTicks();

            // Shifting history and adding a new point
            System.arraycopy(cpuHistory, 1, cpuHistory, 0, graphWidth - 1);
            cpuHistory[graphWidth - 1] = load;

            // ANSI Reset Screen Update
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

           // Draw a GRAPH -- drawing by lines (from 100% to 0%)
            for (int h = graphHeight-1; h >= 0; h--) {
                double threshold = ((double)h / (graphHeight-1)) * 100.0;
                for (int x = 0; x < graphWidth; x++) {
                    System.out.print(cpuHistory[x] >= threshold ? "█" : " ");
                }
                System.out.println();
            }

            // Ось X
            System.out.println("_".repeat(graphWidth));
            System.out.println();
            Thread.sleep(500); // Пауза
        }
    }
}
