package com.JAMM; 

import java.io.IOException;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class Memory {
    
    
    public static void showMemoryGraph() {
        
        // same setup as others to accses hardware info
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory memory = hal.getMemory();

        final int graphWidth = 50, graphHeight = 10;
        double[] memHistory = new double[graphWidth];

        // infinite loop to update graph
        while (true) {
            // get current RAM usage
            long totalRam = memory.getTotal();
            long availableRam = memory.getAvailable();
            long usedRam = totalRam - availableRam;
            // calculate load percentage (0-100)
            double load = (double) usedRam / totalRam * 100.0;

            // update graph history
            System.arraycopy(memHistory, 1, memHistory, 0, graphWidth - 1);
            memHistory[graphWidth - 1] = load;

            // drawing section
            System.out.print("\033[H\033[2J"); // clear terminal
            System.out.flush();
            System.out.println("=".repeat(graphWidth)); // Match graph width
            System.out.println("Live Memory (RAM) Usage Graph");
            System.out.println("-".repeat(graphWidth));

            // draw the graph bars
            for (int h = graphHeight - 1; h >= 0; h--) {
                double threshold = ((double) h / (graphHeight - 1)) * 100.0;
                for (int x = 0; x < graphWidth; x++) {
                    System.out.print(memHistory[x] >= threshold ? "█" : " ");
                }
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));

            // summary Ssction 
            // convert bytes to GiB
            double totalGiB = (double) totalRam / 1073741824.0;
            double usedGiB = (double) usedRam / 1073741824.0;

            // print the summary including total RAM
            System.out.printf("RAM Usage: %.1f%% (%.2f / %.2f GiB)\n\n", load, usedGiB, totalGiB);
            System.out.println("=".repeat(graphWidth));
            System.out.println("\nPress Enter to return to menu...");

            //  wait / exit logic 
            try {
                if (System.in.available() > 0) {
                    // clear the input buffer before breaking
                    System.in.read(new byte[System.in.available()]);
                    break; // exit the loop when Enter is pressed
                }
                Thread.sleep(500); // update twice per second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // restore interrupt status
                break; // exit loop on interrupt
            } catch (IOException e) { // catch the IOException from System.in.read
                e.printStackTrace(); // print error if input fails
                break; // exit loop on IO error
            }
        } // while ends
    } // showMemoryGraph ends
} // memory ends

