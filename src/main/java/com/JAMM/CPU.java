package com.JAMM; 

import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.ProcessorCache;
import oshi.hardware.HardwareAbstractionLayer;

public class CPU {
    private static CentralProcessor processor;

    public static void cpuMenu() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        processor = hal.getProcessor();

        while (true) {
            
            System.out.println("\n==== CPU Information ====");
            System.out.println("1. General info");
            System.out.println("2. Cores");
            System.out.println("3. Cache");
            System.out.println("4. Frequency");
            System.out.println("5. CPU load");
            System.out.println("6. Individual Core load");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");

            String choice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
            System.out.println("");

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
            }
        }
    }

    // Displays general info about the CPU
    private static void showGeneralInfo() {
        System.out.println("=== Processor Information ===");
        System.out.println(" Processor: " + processor.getProcessorIdentifier().getName());
        System.out.println(" Identifier: " + processor.getProcessorIdentifier().getIdentifier());
        System.out.println(" Microarchitecture: " + processor.getProcessorIdentifier().getMicroarchitecture());

        // Divide the max frequency by 10^9 to convert it to GigaHertz
        System.out.println(" Max Frequency: " + (processor.getMaxFreq() / Math.pow(10, 9)) + " GHz");
    
    }

    // How many physical cores and then extra (hyper-threaded) logical cores there
    private static void showCoreInfo() {
            System.out.println("=== Core Information ===");
            System.out.println(" Physical Cores: " + processor.getPhysicalProcessorCount());
            System.out.println(" Logical Cores: " + processor.getLogicalProcessorCount());
    }

    // Sub menu for showing Cache information
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
        
        // While loop which takes an input and iterates through each cache in the chosen level to display data
        while (true) {
            System.out.print("\n=== Which cache level would you like to know about? ===\nLevel 1 \nLevel 2  \nLevel 3 \nTo exit, type 0 or 'exit': ");
            String choice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();

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

            } else if (choice.contains("0")  || 
                    choice.contains("exit") || 
                    choice.contains("quit")) {
                System.out.println("Exiting cache information menu...");
                return;

            }  else { 
                System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
            } 
        }  
    }   

    // Live graph of the frequency the CPU is currently running at
    public static void showCpuFrequencyGraph() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        // Get the maximum frequency in Hz to be our 100% metric
        long maxFreqHz = processor.getMaxFreq();

        // If we couldn't get max frequency from the CPU itself, get the vendor frequency
        if (maxFreqHz <= 0) {
            maxFreqHz = processor.getProcessorIdentifier().getVendorFreq();
        }
        // If we couldn't get a fallback vendor frequency either, return
        if (maxFreqHz <= 0) {
            System.out.println("Could not determine max CPU frequency.");
            return; 
        }

        // Use our max frequency as a constant double (converted to double for calculations)
        final double MAX_FREQ_HZ_DOUBLE = (double) maxFreqHz; 

        // Graph setup
        final int graphWidth = 50, graphHeight = 10;
        double[] freqHistory = new double[graphWidth]; 

        double currentFreqGHz = 0;

        while (true) {
            // Get current frequency for all cores (returns long[])
            long[] currentFreqsHz = processor.getCurrentFreq();

            // Use the average frequency for the graph
            long avgFreqHz = 0;
            if (currentFreqsHz.length > 0) {
                long sum = 0;
                for (long freq : currentFreqsHz) {
                    sum += freq;
                }
                avgFreqHz = sum / currentFreqsHz.length;
            }
            // Convert it to GHz for display purposes
            currentFreqGHz = avgFreqHz / 1_000_000_000.0; 

            // Scale Frequency from 0-100% for the graph 
            double freqPercent = (avgFreqHz / MAX_FREQ_HZ_DOUBLE) * 100.0;
            // Clamp it between 0 and 100 incase something went wrong 
            freqPercent = Math.min(100.0, Math.max(0.0, freqPercent)); 

            // Shift data history left and add new data value
            System.arraycopy(freqHistory, 1, freqHistory, 0, graphWidth - 1);
            freqHistory[graphWidth - 1] = freqPercent;

            // Draw the graph by clearing the screen and displaying information
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=".repeat(graphWidth)); 
            System.out.println("CPU Frequency Graph"); 
            System.out.println(processor.getProcessorIdentifier().getName());
            System.out.println("-".repeat(graphWidth));

            // For loop to determine whether each "spot" in the graph should be filled or not
            for (int h = graphHeight - 1; h >= 0; h--) {
                double threshold = ((double) h / (graphHeight - 1)) * 100.0;
                for (int x = 0; x < graphWidth; x++) {
                    System.out.print(freqHistory[x] >= threshold ? "█" : " ");
                }
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));

            // Display actual Frequency 
            System.out.printf("CPU Frequency: %.2f GHz (%.1f%% of max)\n\n", currentFreqGHz, freqPercent);
            System.out.println("=".repeat(graphWidth));
            System.out.println("\nPress Enter to exit...");

            // Wait / Exit 
            try {
                if (System.in.available() > 0) {
                    // Clear the input buffer before breaking (so input doesn't carry over to the next menu)
                    System.in.read(new byte[System.in.available()]);
                    break;
                }
                // Update the graph every half a second
                Thread.sleep(500); 
            }
            // Catch if something tries to interrupt this current thread 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } 
            // Catch any other exceptions such as IO 
            catch (Exception e) {
                e.printStackTrace();
            }
        } // while ends
    } // showCpuFrequencyGraph ends  

    // Live graph of the CPU's load
    private static void showCpuLoad() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        final int graphWidth = 50, graphHeight = 10;
        
        // Get the current CPU load percentage
        double[] cpuHistory = new double[graphWidth];
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        
        while (true) {
            // Counts how many "ticks" have passed between the current cpuLoadTicks and the previous cpuLoadTicks, returning a double for CPU load
            double load = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            // Update for the next loop
            prevTicks = processor.getSystemCpuLoadTicks();

            // Shift history left and add new load value
            System.arraycopy(cpuHistory, 1, cpuHistory, 0, graphWidth - 1);
            cpuHistory[graphWidth - 1] = load;

            // Draw the graph by clearing the screen and displaying information
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=".repeat(50));
            System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
            System.out.println("-".repeat(50));
            
            // For loop to determine whether each "spot" in the graph should be filled or not
            for (int h = graphHeight-1; h >= 0; h--) {
                double threshold = ((double)h / (graphHeight-1)) * 100.0;
                // Print each column based on whether usage exceeds threshold
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
                    // Clear the input buffer before breaking (so input doesn't carry over to the next menu)
                    System.in.read(new byte[System.in.available()]);
                    break;
                }
                // Update the graph every half a second
                Thread.sleep(500);
            } 
            // Catch if something tries to interrupt this current thread 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } 
            // Catch any other exceptions (like IO exceptions)
            catch (Exception e) {
                e.printStackTrace();
            }
        } //while ends
    } //CpuGraph ends

    // Live graph of the load of each individual Core in the CPU
    private static void showPerCoreLoad() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        // Get ticks once before the loop
        long[][] prevCoreTicks = processor.getProcessorCpuLoadTicks();

        while (true) {
            // Clear the screen to draw the graph
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=".repeat(50));
            System.out.println("Live Per-Core CPU Load");
            System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
            System.out.println("=".repeat(50));

            // Get the load for each core
            double[] coreLoads = processor.getProcessorCpuLoadBetweenTicks(prevCoreTicks);
            // Update for the next loop
            prevCoreTicks = processor.getProcessorCpuLoadTicks(); 

            // For each core load, use the createProgressBar helper function to display it's load as a percentage
            for (int i = 0; i < coreLoads.length; i++) {
                double load = coreLoads[i] * 100;
                System.out.printf("  Core %-2d: %s %.1f%%\n", i, createProgressBar(load / 100, 40), load);
            }

            System.out.println("\nPress Enter to return to menu...");
            // Wait 500 ms, exit the loop if key pressed
            try {
                // System.in.available() allows checking if user pressed ENTER
                 if (System.in.available() > 0) {
                    // Clear the input buffer before breaking (so input doesn't carry over to the next menu)
                    System.in.read(new byte[System.in.available()]);
                    break;
                }
                // Update the graph every half a second
                Thread.sleep(500);
            } 
            // Catch if something tries to interrupt this current thread 
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } 
            // Catch any other exceptions (like IO exceptions)
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }    

    // Helper function for graphing the per core load
    public static String createProgressBar(double percent, int barLength) {
        int filledLength = (int) (barLength * percent);
        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            bar.append(i < filledLength ? "█" : " ");
        }
        bar.append("]");
        return bar.toString();
    }
}
        

