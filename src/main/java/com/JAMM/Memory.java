package com.JAMM; 

import java.text.DecimalFormat;

import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class Memory {
    
    public static void displayMemory(HardwareAbstractionLayer inHal) {
        GlobalMemory memory = inHal.getMemory();

        // Get the ram specs in longs because ram is always gonna be a massive whole number (bytes)
        long totalRam = memory.getTotal();
        long availableRam = memory.getAvailable();

        long usedRam = totalRam - availableRam;
        
        // The ram specs we have are currently in bytes, but we want to convert them to GibiBytes (NOT GB)
        // Divide it by (1024^3) (because we're converting to GiB not GB)
        // Use doubles for this because the division can give decimals
        double totalGiB = (double) totalRam / 1073741824;
        double usedGiB = (double) usedRam / 1073741824;

        double percentUsed = (double) usedRam / totalRam;

        // Format the ram percentage used to round to two decimal places
        String formattedPercentUsed = new DecimalFormat("0.##").format(percentUsed * 100);
        
        System.out.printf("%nYou have %fGiB of ram in total.", totalGiB);
        System.out.printf("%nYou are currently using %s%% of your ram.", formattedPercentUsed);
    }
}

