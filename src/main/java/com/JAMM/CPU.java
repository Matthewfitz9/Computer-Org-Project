package com.JAMM; 

import java.util.List;
import java.util.Scanner;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorCache;
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

        String input1;

        // processor of type centralproceesor
         CentralProcessor processor = getCpuCores();
        
         Scanner ise = new Scanner(System.in);
        
            System.out.print(" What would you like to know about the CPU? ");
             input1 = ise.nextLine().toLowerCase();

        if ((input1.contains("cores"))) {
            
            System.out.println("=== Core Information ===");
            System.out.println(" Physical Cores: " + processor.getPhysicalProcessorCount());
            System.out.println(" Logical Cores: " + processor.getLogicalProcessorCount());
        } 
        
        else if ((input1.contains("frequency")) || (input1.contains("freq"))) {
           
            System.out.println("=== CPU Frequency Information ===");
            System.out.println(" Max Frequency: " + processor.getMaxFreq() + " GHz");
        } 
       
        else if ((input1.contains("cache")) || (input1.contains("caches"))) {
           
            List<ProcessorCache> caches = (List<ProcessorCache>) processor.getProcessorCaches();
            
            int size = caches.size();
             
             int c3 = 0;
             int c2 = 0;
             int c1 = 0;
           
              
             
               for (ProcessorCache cache : caches) {

                String change = String.valueOf(cache.getLevel());
                  
               if (change.contains("3")) {    
                   c3++;
               } else if (change.contains("2")) {   
                   c2++;
               } else if (change.contains("1")) {   
                   c1++;
               }
            } // for
            
                int option = 0;
                  while (option != 4) {
              
                    
                    System.out.print(" Which cache information would you like to know about? \n L1 \n L2  \n L3 \n 4 to exit: ");
                      option = ise.nextInt();

                    
                       switch (option) {
                          
                         case 1:
                         int limit1 = c1; // limit is number of level 1 caches ie 3
                         c1 = size - c1; // this creates index

                         while (limit1 < size) {
                             System.out.println(" Cache Level: " + ((List<ProcessorCache>) caches).get(limit1).getLevel());
                             System.out.println(" Cache Type: " + ((List<ProcessorCache>) caches).get(limit1).getType());
                             System.out.println(" Cache Size: " + ((List<ProcessorCache>) caches).get(limit1).getCacheSize() + " bytes");
                               limit1++; // increase until all level 1 caches are printed
                               c1++;
                         }
                              break;
                         
                         case 2:  
                         int limit2 = c2; // limit is number of level 2 caches ie 2
                         c2 = size - (c1 + c2);
                         int start2 = 0;

                         while (start2 < limit2) {
                             System.out.println(" Cache Level: " + ((List<ProcessorCache>) caches).get(c2).getLevel());
                             System.out.println(" Cache Type: " + ((List<ProcessorCache>) caches).get(c2).getType());
                             System.out.println(" Cache Size: " + ((List<ProcessorCache>) caches).get(c2).getCacheSize() + " bytes"); 
                              start2++; //  increase until all level 2 caches are printed
                              c2++;   
                         } 
                              break;

                         case 3:        
                         int limit3 = c3; // limit is number of level 3 caches ie 1
                         c3 = size - (c1 + c2 + c3); // should always equal 0 as first one will always be level 3 cache

                         while (c3 < limit3) {
                             System.out.println(" Cache Level: " + ((List<ProcessorCache>) caches).get(c3).getLevel());
                             System.out.println(" Cache Type: " + ((List<ProcessorCache>) caches).get(c3).getType());
                             System.out.println(" Cache Size: " + ((List<ProcessorCache>) caches).get(c3).getCacheSize() + " bytes"); 
                              c3++;  
                         } 

                              break;

                         case 4:
                              
                            System.out.println(" Exiting cache information menu.");
                              break;

                         default:
                              
                             System.out.println(" Invalid option. Please choose 1, 2, or 3.");
                              break;

                 } // switch
             } // while 
         } // else if

      else if ((input1.contains("processor")) || (input1.contains("processors"))) {

            System.out.println("=== Processor Information ===");
            System.out.println(" Processor: " + processor.getProcessorIdentifier().getName());
            System.out.println(" Identifier: " + processor.getProcessorIdentifier().getIdentifier());
            System.out.println(" Microarchitecture: " + processor.getProcessorIdentifier().getMicroarchitecture());
    
        }
            

      else if ((input1.contains("all")) || (input1.contains("cpu"))) {

            System.out.println("=== CPU Information ===");
            System.out.println(" Processor: " + processor.getProcessorIdentifier().getName());
            System.out.println(" Physical Cores: " + processor.getPhysicalProcessorCount());
            System.out.println(" Logical Cores: " + processor.getLogicalProcessorCount());
            System.out.println(" Max Frequency: " + processor.getMaxFreq() + " GHz");

        } 
       
        else {
            System.out.println("Invalid input. Please enter 'cores', 'frequency', 'processor', 'all' or 'exit'.");

         }
           
     } // displayinfo
 } // cpu
        

