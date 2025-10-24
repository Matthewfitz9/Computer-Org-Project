package com.JAMM; 

import java.util.List;
import java.util.Scanner;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorCache;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class CPU {

    public static CentralProcessor getCpuCores() {
       
        // creates a syateminfo object and hardwareabstract object
        SystemInfo si = new SystemInfo();
         HardwareAbstractionLayer hal = si.getHardware();
          
        // returns the processor object 
         return hal.getProcessor();
    }

    public static void displayinfo() {
        
        // processor of type centralproceesor
        CentralProcessor processor = getCpuCores();
        
       Scanner ise = new Scanner(System.in);
        
        System.out.print("What would you like to know about the CPU? ");
            String input = ise.nextLine().toLowerCase();

        if ((input.contains("cores"))) {
         //  System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
            System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());
             System.out.println("Logical Cores: " + processor.getLogicalProcessorCount());
        } 
        else if ((input.contains("frequency")) || (input.contains("freq"))) {
            System.out.println("Max Frequency: " + processor.getMaxFreq() + " GHz");
        } 
        else if ((input.contains("cache")) || (input.contains("caches"))) {
            List<ProcessorCache> caches = processor.getProcessorCaches();
            for (ProcessorCache cache : caches) {
                System.out.println("Cache Level: " + cache.getLevel());
                 System.out.println("Cache Type: " + cache.getType());
                  System.out.println("Cache Size: " + cache.getCacheSize() + " bytes");      
            }
        }
        else if ((input.contains("all")) || (input.contains("cpu"))) {
            System.out.println("=== CPU Information ===");
             System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
              System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());
               System.out.println("Logical Cores: " + processor.getLogicalProcessorCount());
                System.out.println("Max Frequency: " + processor.getMaxFreq() + " GHz");

        } 
        else if ((input.contains("processor")) || (input.contains("name"))) {
            System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
          //   System.out.println("ID Number: " + processor.getPhysicalProcessorNumber());
         



        }
        else {
            System.out.println("Invalid input. Please enter 'cores', 'frequency', or 'all'.");

        }


   /*  List<ProcessorCache> caches = processor.getProcessorCaches();
        for (ProcessorCache cache : caches) {
            System.out.println("Cache Level: " + cache.getLevel());cache
            System.out.println("Cache Type: " + cache.getType());
            System.out.println("Cache Size: " + cache.getSize() + " bytes");
            System.out.println();
        }
*/


/* cores

        System.out.println("=== CPU Information ===");
        System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
        System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());
        System.out.println("Logical Cores: " + processor.getLogicalProcessorCount());
        System.out.println("Max Frequency: " + processor.getMaxFreq() + " GHz");
       */
    }
        
    
}
