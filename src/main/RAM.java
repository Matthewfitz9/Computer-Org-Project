package com.JAMM; 

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class RAM {

   public static CentralProcessor GetRam() {
   
    SystemInfo si = new SystemInfo(); 
       HardwareAbstractionLayer hal = si.getHardware();
           

         return hal.getProcessor();
     }
    }

     System.out.println("Memory (RAM):");
      
     System.out.println(String.format("  Available: %.2f GiB", (double)memory.getAvailable() / 1073741824));
     
     System.out.println(String.format("  Total:     %.2f GiB", (double)memory.getTotal() / 1073741824));

       

}
