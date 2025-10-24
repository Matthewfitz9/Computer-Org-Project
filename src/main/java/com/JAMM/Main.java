package com.JAMM; 

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class Main {
    public static void main(String[] args) {
        System.out.println("Initializing OSHI...");

        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        // 1. Get CPU Info
          CPU.displayinfo();
 
    }
}