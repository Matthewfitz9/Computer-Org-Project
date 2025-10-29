package com.JAMM; 

<<<<<<< HEAD

import java.util.InputMismatchException;
=======
>>>>>>> 63a22ea7a12a7752aee554b3192b73da990b5b80
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorCache;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.Sensors;

public class CPU {
<<<<<<< HEAD

    private static Scanner ise;


    public static CentralProcessor getCpuCores() {
       
        // creates a syateminfo object and hardwareabstract object
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        // returns the processor object
         return processor;
    }
=======
    private static CentralProcessor processor;
>>>>>>> 63a22ea7a12a7752aee554b3192b73da990b5b80

    public static void cpuMenu() {
        // Get a reference to the processor from the abstracted hardware layer
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
          
        processor = hal.getProcessor();

        while (true) {
            
<<<<<<< HEAD
            System.out.println("\n ==== CPU INFORMATION ====");
            System.out.println(" 1. View CPU Information (Interactive)");
            System.out.println(" 2. View  CPU Load Graph");
            System.out.println(" 0. Back to Main Menu");
            System.out.println(" =========================");
            System.out.print(" Enter your choice: ");
=======
            System.out.println("\n==== CPU Information ====");
            System.out.println("1. General info");
            System.out.println("2. Cores");
            System.out.println("3. Cache");
            System.out.println("4. Frequency");
            System.out.println("5. CPU load");
            System.out.println("6. Individual Core load");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");
>>>>>>> 63a22ea7a12a7752aee554b3192b73da990b5b80

            String choice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
            System.out.println("");

<<<<<<< HEAD
                switch (choice) {
                    case 1:
                        // Call the existing CPU.displayinfo() method
                        System.out.println("\n--- Launching CPU Information Module ---\n");
                        System.out.println("\n--- all / cores / frequency / cache / processor ---\n");
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
=======
            if (choice.contains("1") || 
                choice.contains("general info") || 
                choice.contains("general")) {
                showGeneralInfo();

            } else if (choice.contains("2") || 
                    choice.contains("core") || 
                    choice.contains("cores")) {
                showCoreInfo();

            } else if (choice.contains("3")  || 
                    choice.contains("cache")) {
                showCacheInfo();

            } else if (choice.contains("4")  || 
                    choice.contains("frequency") || 
                    choice.contains("hertz") ||
                    choice.contains("ghz") ||
                    choice.contains("freq")) {
                showCpuFrequencyGraph();

            } else if (choice.contains("5") || 
                    choice.contains("cpu load") || 
                    choice.contains("cpus load") || 
                    choice.contains("cpu's load")) {
                showCpuLoad();

            } else if (choice.contains("6") || 
                    choice.contains("core load") || 
                    choice.contains("individual core load")) {
                showPerCoreLoad();

            } else if (choice.contains("0") || 
                    choice.contains("exit") || 
                    choice.contains("quit")) {
                System.out.println("Returning to main menu...\n");
                return;

            } else { // This replaces the 'default' case
                System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
>>>>>>> 63a22ea7a12a7752aee554b3192b73da990b5b80
            }
        }
    }

    private static void showGeneralInfo() {
        System.out.println("=== Processor Information ===");
        System.out.println(" Processor: " + processor.getProcessorIdentifier().getName());
        System.out.println(" Identifier: " + processor.getProcessorIdentifier().getIdentifier());
        System.out.println(" Microarchitecture: " + processor.getProcessorIdentifier().getMicroarchitecture());
        System.out.println(" Max Frequency: " + (processor.getMaxFreq() / Math.pow(10, 9)) + " GHz");
    
    }

<<<<<<< HEAD
        String input1;

        // processor of type centralproceesor
         CentralProcessor processor = getCpuCores();
        
            System.out.print(" What would you like to know about the CPU? ");
             input1 = ise.nextLine().toLowerCase();

        if ((input1.contains("cores"))) {
            
            System.out.println(" === Core Information ===");
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
       

            System.out.println(" === Frequency Information ===");
            System.out.println(" Max Frequency: " + (processor.getMaxFreq() / 1000000000.0)+ " GHz");
            System.out.printf(" Current Frequency: %.3f GHz%n", avrgFreq);
        } 
       
        else if ((input1.contains("cache")) || (input1.contains("caches"))) {
           
            System.out.println(" === Cache Information ===");
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

            System.out.println(" === Processor Information ===");
            System.out.println(" Processor: " + processor.getProcessorIdentifier().getName());
            System.out.println(" Identifier: " + processor.getProcessorIdentifier().getIdentifier());
            System.out.println(" Microarchitecture: " + processor.getProcessorIdentifier().getMicroarchitecture());
    
        }

            
        
            

      else if ((input1.contains("all")) || (input1.contains("cpu"))) {

            System.out.println(" === CPU Information ===");
            System.out.println(" Processor: " + processor.getProcessorIdentifier().getName());
            System.out.println(" Identifier: " + processor.getProcessorIdentifier().getIdentifier());
            System.out.println(" Microarchitecture: " + processor.getProcessorIdentifier().getMicroarchitecture());
            System.out.println(" Vendor ID: " + processor.getProcessorIdentifier().getVendor());
            System.out.println(" Physical Cores: " + processor.getPhysicalProcessorCount());
            System.out.println(" Logical Cores: " + processor.getLogicalProcessorCount());
            System.out.println(" Max Frequency: " + (processor.getMaxFreq() / 1000000000.0) + " GHz");
            System.out.println(" Caches: " + processor.getProcessorCaches());

        }
        
       
        else {
            System.out.println(" Invalid input. Please enter 'cores', 'freq', 'processor', 'cache', 'all' or 'exit'.");
=======
    private static void showCoreInfo() {
            System.out.println("=== Core Information ===");
            System.out.println(" Physical Cores: " + processor.getPhysicalProcessorCount());
            System.out.println(" Logical Cores: " + processor.getLogicalProcessorCount());
    }

    private static void showCacheInfo() {
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
        } 
    
        while (true) {
            System.out.print("\n=== Which cache level would you like to know about? ===\nLevel 1 \nLevel 2  \nLevel 3 \nTo exit, type 4 or 'exit': ");
            String choice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
>>>>>>> 63a22ea7a12a7752aee554b3192b73da990b5b80

            if (choice.contains("1") || 
                choice.contains("level 1")) {
                System.out.println("Your CPU has " + c1 + " Level 1 caches.");
                for (int i = 0; i < c1; i ++) {
                    int index = (size - 1) - i;
                        System.out.println("\nThis L1 cache type is " + ((List<ProcessorCache>) caches).get(index).getType());
                        System.out.println("This L1 cache size is " + ((List<ProcessorCache>) caches).get(index).getCacheSize() + " bytes");
                }

            } else if (choice.contains("2") || 
                    choice.contains("level 2")) {
                System.out.println("Your CPU has " + c2 + " Level 2 caches.");
                for (int i = 0; i < c2; i ++) {
                    int index = (size - 1) - i - c1;
                        System.out.println("\nThis L2 cache type is " + ((List<ProcessorCache>) caches).get(index).getType());
                        System.out.println("This L2 cache size is " + ((List<ProcessorCache>) caches).get(index).getCacheSize() + " bytes");  
                } 

            } else if (choice.contains("3")  || 
                    choice.contains("level 3")) {
                System.out.println("Your CPU has " + c3 + " Level 3 caches.");
                for (int i = 0; i < c3; i ++) {
                        int index = (size - 1) - i - c1 - c2;
                            System.out.println("\nThis L3 cache type is " + ((List<ProcessorCache>) caches).get(index).getType());
                            System.out.println("This L3 cache size is " + ((List<ProcessorCache>) caches).get(index).getCacheSize() + " bytes"); 
                } 

            } else if (choice.contains("4")  || 
                    choice.contains("exit") || 
                    choice.contains("quit")) {
                System.out.println("Exiting cache information menu...");
                return;

            }  else { // This replaces the 'default' case
                System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
            } 
        }  
    } 

    public static void showCpuFrequencyGraph() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        // --- Determine Max Frequency ---
        // Get the maximum frequency in Hz (this is our 100% mark)
        long maxFreqHz = processor.getMaxFreq();
        if (maxFreqHz <= 0) {
            // Fallback: Use vendor frequency if max isn't available
            maxFreqHz = processor.getProcessorIdentifier().getVendorFreq();
        }
        if (maxFreqHz <= 0) {
            System.out.println("Could not determine max CPU frequency.");
            return; // Can't proceed without a max value
        }
        final double MAX_FREQ_HZ_DOUBLE = (double) maxFreqHz; // Use double for calculations

        // --- Graph Setup (same as before) ---
        final int graphWidth = 50, graphHeight = 10;
        double[] freqHistory = new double[graphWidth]; // Renamed array

        double currentFreqGHz = 0; // Variable to store current freq for display

        // --- Live Loop ---
        while (true) {
            // --- Get Current Frequency ---
            // Get current frequency for *all* cores (returns long[])
            long[] currentFreqsHz = processor.getCurrentFreq();
            // Use the average frequency for the graph (or just the first core: currentFreqsHz[0])
            long avgFreqHz = 0;
            if (currentFreqsHz.length > 0) {
                long sum = 0;
                for (long freq : currentFreqsHz) {
                    sum += freq;
                }
                avgFreqHz = sum / currentFreqsHz.length;
            }
            currentFreqGHz = avgFreqHz / 1_000_000_000.0; // For display

            // --- Scale Frequency to 0-100% for the graph ---
            double freqPercent = (avgFreqHz / MAX_FREQ_HZ_DOUBLE) * 100.0;
            freqPercent = Math.min(100.0, Math.max(0.0, freqPercent)); // Clamp between 0 and 100

            // --- Update History ---
            // Shift history left and add new scaled frequency value
            System.arraycopy(freqHistory, 1, freqHistory, 0, graphWidth - 1);
            freqHistory[graphWidth - 1] = freqPercent;

            // --- Draw Graph (same logic, different data array) ---
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=".repeat(graphWidth)); // Adjusted repeat width
            System.out.println("CPU Frequency Graph"); // Updated title
            System.out.println(processor.getProcessorIdentifier().getName());
            System.out.println("-".repeat(graphWidth));

            for (int h = graphHeight - 1; h >= 0; h--) {
                double threshold = ((double) h / (graphHeight - 1)) * 100.0;
                for (int x = 0; x < graphWidth; x++) {
                    System.out.print(freqHistory[x] >= threshold ? "█" : " ");
                }
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));

            // --- Display Actual Frequency ---
            System.out.printf("CPU Frequency: %.2f GHz (%.1f%% of max)\n\n", currentFreqGHz, freqPercent);
            System.out.println("=".repeat(graphWidth));
            System.out.println("\nPress Enter to exit...");

            // --- Wait / Exit (same logic) ---
            try {
                if (System.in.available() > 0) {
                    // Clear the input buffer before breaking
                    System.in.read(new byte[System.in.available()]);
                    break;
                }
                Thread.sleep(500); // Update frequency twice a second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } // while ends
    } // showCpuFrequencyGraph ends  

    private static void showCpuLoad() {
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

    private static void showPerCoreLoad() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        // Get ticks once before the loop
        long[][] prevCoreTicks = processor.getProcessorCpuLoadTicks();

        while (true) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=".repeat(50));
            System.out.println("Live Per-Core CPU Load");
            System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
            System.out.println("=".repeat(50));

            // Get the load for each core
            double[] coreLoads = processor.getProcessorCpuLoadBetweenTicks(prevCoreTicks);
            prevCoreTicks = processor.getProcessorCpuLoadTicks(); // Update for next loop

            for (int i = 0; i < coreLoads.length; i++) {
                double load = coreLoads[i] * 100;
                System.out.printf("  Core %-2d: %s %.1f%%\n", i, Main.createProgressBar(load / 100, 40), load);
            }

            System.out.println("\nPress Enter to return to menu...");
            try {
                if (System.in.available() > 0) {
                    System.in.read(new byte[System.in.available()]); // Clear buffer
                    break;
                }
                Thread.sleep(1000); // 1-second-update is fine here
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }    
}
        

