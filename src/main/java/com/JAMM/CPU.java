package com.JAMM; 

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorCache;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;

public class CPU {

    private static Scanner ise;


    public static CentralProcessor getCpuCores() {
       
        // creates a syateminfo object and hardwareabstract object
        SystemInfo si = new SystemInfo();
         HardwareAbstractionLayer hal = si.getHardware();
          
        // returns the processor object 
         return hal.getProcessor();
    }

    public static void cpuMenu() {
        boolean inCpuMenu = true; // Flag to control CPU menu loop
        ise = new Scanner(System.in);
        
        while (inCpuMenu) {
            
            System.out.println("\n========== CPU INFORMATION ==========");
            System.out.println("1. View CPU Information (Interactive)");
            System.out.println("2. View  CPU Load Graph");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = ise.nextInt();
                ise.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Call the existing CPU.displayinfo() method
                        System.out.println("\n--- Launching CPU Information Module ---\n");
                        System.out.println("\n--- all / cores / frequency / cache ---\n");
                        //Call the CPU info method
                        CPU.displayinfo();
                        break;
                    case 2:
                        // Call CPU Load Graph method
                        CpuGraph();
                        break;
                    case 0:
                        inCpuMenu = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                ise.nextLine(); // Clear invalid input
            }
        }
    }

    public static void displayinfo() {

        String input1;

        // processor of type centralproceesor
         CentralProcessor processor = getCpuCores();
        
            System.out.print(" What would you like to know about the CPU? ");
             input1 = ise.nextLine().toLowerCase();

        if ((input1.contains("cores"))) {
            
            System.out.println("=== Core Information ===");
            System.out.println(" Physical Cores: " + processor.getPhysicalProcessorCount());
            System.out.println(" Logical Cores: " + processor.getLogicalProcessorCount());
        } 

        else if ((input1.contains("frequency")) || (input1.contains("freq"))) {
         

            long[] freqs = processor.getCurrentFreq();
            double avrgFreq = 0.0;

            long total = 0;
             for (long f : freqs) {
                total += f;
            }
            avrgFreq = (total / (double) freqs.length) / 1_000_000_000.0;
       

            System.out.println("=== Frequency Information ===");
            System.out.println(" Max Frequency: " + (processor.getMaxFreq() / 1000000000.0)+ " GHz");
            System.out.printf(" Current Frequency: %.3f GHz%n", avrgFreq);
        } 
       
        else if ((input1.contains("cache")) || (input1.contains("caches"))) {
           
            List<ProcessorCache> caches = (List<ProcessorCache>) processor.getProcessorCaches();
            
            int size = caches.size();
             
             int c3 = 0;
             int c2 = 0;
             int c1 = 0;
           
              
             
               for (ProcessorCache cache : caches) {
                System.out.println(cache);

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
              
                    
                    System.out.print(" Which cache level would you like to know about? \n 1 \n 2  \n 3 \n 4 to exit: ");
                      option = ise.nextInt();

                
                       switch (option) {
                          
                         case 1:
                         for (int i = 0; i < c1; i ++) {
                            int index = (size - 1) - i;
                             System.out.println(" Cache Level: " + ((List<ProcessorCache>) caches).get(index).getLevel());
                             System.out.println(" Cache Type: " + ((List<ProcessorCache>) caches).get(index).getType());
                             System.out.println(" Cache Size: " + ((List<ProcessorCache>) caches).get(index).getCacheSize() + " bytes");
                         }
                        break;
                         
                         case 2: 

                         for (int i = 0; i < c2; i ++) {
                            int index = (size - 1) - i - c1;

                             System.out.println(" Cache Level: " + ((List<ProcessorCache>) caches).get(index).getLevel());
                             System.out.println(" Cache Type: " + ((List<ProcessorCache>) caches).get(index).getType());
                             System.out.println(" Cache Size: " + ((List<ProcessorCache>) caches).get(index).getCacheSize() + " bytes");  
                         } 
                              break;

                         case 3:        
                         for (int i = 0; i < c3; i ++) {
                            int index = (size - 1) - i - c1 - c2;
                             System.out.println(" Cache Level: " + ((List<ProcessorCache>) caches).get(index).getLevel());
                             System.out.println(" Cache Type: " + ((List<ProcessorCache>) caches).get(index).getType());
                             System.out.println(" Cache Size: " + ((List<ProcessorCache>) caches).get(index).getCacheSize() + " bytes"); 
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
            System.out.println(" Identifier: " + processor.getProcessorIdentifier().getIdentifier());
            System.out.println(" Microarchitecture: " + processor.getProcessorIdentifier().getMicroarchitecture());
            System.out.println(" Vendor ID: " + processor.getProcessorIdentifier().getVendor());
            System.out.println(" Physical Cores: " + processor.getPhysicalProcessorCount());
            System.out.println(" Logical Cores: " + processor.getLogicalProcessorCount());
            System.out.println(" Max Frequency: " + (processor.getMaxFreq() / 1000000000.0) + " GHz");
            System.out.println(" Caches: " + processor.getProcessorCaches());

            long[] prevTicks = processor.getSystemCpuLoadTicks();
            System.out.println(" Usage: " + String.format("%.1f%%", processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100));

        } 
       
        else {
            System.out.println("Invalid input. Please enter 'cores', 'frequency', 'processor', 'all' or 'exit'.");

         }
           
     } // displayinfo

     public static void CpuGraph() {
        // Local instances to avoid interfering with the global ones
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        CentralProcessor processor = hal.getProcessor();
        final int graphWidth = 50, graphHeight = 10;
        // Compute current CPU load percentage
        double[] cpuHistory = new double[graphWidth];
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        
        while (true) {
            double load = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            prevTicks = processor.getSystemCpuLoadTicks();
            // Shift history left and add new load value
            System.arraycopy(cpuHistory, 1, cpuHistory, 0, graphWidth - 1);
            cpuHistory[graphWidth - 1] = load;

            // Clear console (ANSI escape codes)
            /*To clear the screen to a new frame, the ANSI code "\033[H\033[2J" is used, 
            * which does not work in all Windows terminals, 
            * but works fine in VS Code or WSL and in classic UNIX terminals.
            */
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=".repeat(50));
            System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
            System.out.println("-".repeat(50));
            
            // Draw dynamic CPU graph from bottom (high %) to top (low %)
            // Each row represents a level of CPU usage threshold
            for (int h = graphHeight-1; h >= 0; h--) {
                double threshold = ((double)h / (graphHeight-1)) * 100.0;
                // Print each column based on whether usage exceeds threshold
                /*The graph is built from the bottom up: in each line of the graph, 
                * the symbol '█' is displayed if the corresponding history value
                * exceeds % for that line (the higher the row, the higher the “threshold”). 
                */
                for (int x = 0; x < graphWidth; x++)
                    System.out.print(cpuHistory[x] >= threshold ? "█" : " ");
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));
            System.out.printf("CPU Usage:   %.1f%%\n\n", load);
            System.out.println("=".repeat(50));
            System.out.println("\nPress Enter to exit...");
            // Wait 500 ms, exit the loop if key pressed
            try {
                // System.in.available() allows checking if user pressed ENTER
                if (System.in.available() > 0) {
            break;  // Exiting the loop when a key is pressed
        }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //An InterruptedException error causes 
                //a forced exit (a graceful termination of the thread when stopped).
                Thread.currentThread().interrupt();
            break;
            } catch (Exception e) {
                // Catch any other exceptions (like IO exceptions)
                e.printStackTrace();
            }
        } //while ends
    } //CpuGraph ends

 } // cpu
        

