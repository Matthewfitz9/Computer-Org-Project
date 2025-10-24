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
           Scanner ise = new Scanner(System.in);
       
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
            String input1 = ise.nextLine().toLowerCase();

        if ((input1.contains("cores"))) {
         //  System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
           
         System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());
             System.out.println("Logical Cores: " + processor.getLogicalProcessorCount());
        } 
        
        else if ((input1.contains("frequency")) || (input1.contains("freq"))) {
           
            System.out.println("Max Frequency: " + processor.getMaxFreq() + " GHz");
        } 
       
        else if ((input1.contains("cache")) || (input1.contains("caches"))) {
           
            List<ProcessorCache> caches = processor.getProcessorCaches();
            for (ProcessorCache cache : caches) {

                System.out.println(cache);
                continue;
            }
            for (ProcessorCache cache : caches) {

               /*  System.out.println("Cache Level: " + cache.getLevel());
                 System.out.println("Cache Type: " + cache.getType());
                  System.out.println("Cache Size: " + cache.getCacheSize() + " bytes");  */

               //   int lvl = cache.getLevel();

                  int option = 0;
                

                  while (option != 4) {
                    
                    System.out.print("Which cache information would you like to know about? 1, 2, 3: ");
                      option = ise.nextInt();

                      switch (option) {
                          
                        case 1:

                        
                             System.out.println("Cache Level: " + cache.getLevel());
                              System.out.println("Cache Type: " + cache.getType());
                               System.out.println("Cache Size: " + cache.getCacheSize() + " bytes");
                               break;
                         
                         case 2:
                            
                             System.out.println("Cache Level: " + cache.getLevel());
                              System.out.println("Cache Type: " + cache.getType());
                               System.out.println("Cache Size: " + cache.getCacheSize() + " bytes");
                                break;

                         case 3:
                            
                            System.out.println("Cache Level: " + cache.getLevel());
                              System.out.println("Cache Type: " + cache.getType());
                               System.out.println("Cache Size: " + cache.getCacheSize() + " bytes");
                                break;

                         case 4:
                              
                            System.out.println("Exiting cache information menu.");
                               break;

                          default:
                              System.out.println("Invalid option. Please choose 1, 2, or 3.");
                               break;     
                      } // switch
                  } // while

            } // for
        } // else if

        else if ((input1.contains("all")) || (input1.contains("cpu"))) {

            System.out.println("=== CPU Information ===");
             System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
              System.out.println("Physical Cores: " + processor.getPhysicalProcessorCount());
               System.out.println("Logical Cores: " + processor.getLogicalProcessorCount());
                System.out.println("Max Frequency: " + processor.getMaxFreq() + " GHz");

        } 

        else if ((input1.contains("processor")) || (input1.contains("name"))) {

            System.out.println("Processor: " + processor.getProcessorIdentifier().getName());
          //c   System.out.println("ID Number: " + processor.getPhysicalProcessorNumber());
      
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
