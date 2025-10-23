package com.JAMM; 

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing OSHI...");

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        // 1. Get CPU Info
        CentralProcessor processor = hal.getProcessor();
        System.out.println("---------------------------------");
        System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
        System.out.println("Cores: " + processor.getPhysicalProcessorCount() + " (Physical) / " +
                           processor.getLogicalProcessorCount() + " (Logical)");

        // 2. Get Memory Info
        GlobalMemory memory = hal.getMemory();
        System.out.println("---------------------------------");
        System.out.println("Memory (RAM):");
        System.out.println(String.format("  Available: %.2f GiB", (double)memory.getAvailable() / 1073741824));
        System.out.println(String.format("  Total:     %.2f GiB", (double)memory.getTotal() / 1073741824));

        System.out.println("---------------------------------");
    }
}