package com.JAMM; 

import java.io.IOException;

import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;

public class Memory {
    
    // Make it public and static so Main can call it
    public static void showMemoryGraph() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory memory = hal.getMemory();

        final int graphWidth = 50, graphHeight = 10;
        double[] memHistory = new double[graphWidth];

        while (true) {
            // Get current RAM usage
            long totalRam = memory.getTotal();
            long availableRam = memory.getAvailable();
            long usedRam = totalRam - availableRam;
            // Calculate load percentage (0-100)
            double load = (double) usedRam / totalRam * 100.0;

            // Update graph history
            System.arraycopy(memHistory, 1, memHistory, 0, graphWidth - 1);
            memHistory[graphWidth - 1] = load;

            // --- Drawing Section ---
            System.out.print("\033[H\033[2J"); // Clear console
            System.out.flush();
            System.out.println("=".repeat(graphWidth)); // Match graph width
            System.out.println("Live Memory (RAM) Usage Graph");
            System.out.println("-".repeat(graphWidth));

            // Draw the graph bars
            for (int h = graphHeight - 1; h >= 0; h--) {
                double threshold = ((double) h / (graphHeight - 1)) * 100.0;
                for (int x = 0; x < graphWidth; x++) {
                    System.out.print(memHistory[x] >= threshold ? "█" : " ");
                }
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));

            // --- Summary Section ---
            // Calculate GiB values for display
            double totalGiB = (double) totalRam / 1073741824.0;
            double usedGiB = (double) usedRam / 1073741824.0;

            // Print the summary including total RAM
            System.out.printf("RAM Usage: %.1f%% (%.2f / %.2f GiB)\n\n", load, usedGiB, totalGiB);
            System.out.println("=".repeat(graphWidth));
            System.out.println("\nPress Enter to return to menu...");

            // --- Wait / Exit Logic ---
            try {
                if (System.in.available() > 0) {
                    // Clear the input buffer before breaking
                    System.in.read(new byte[System.in.available()]);
                    break; // Exit the loop when Enter is pressed
                }
                Thread.sleep(500); // Update twice per second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupt status
                break; // Exit loop on interrupt
            } catch (IOException e) { // Catch the IOException from System.in.read
                e.printStackTrace(); // Print error if input fails
                break; // Exit loop on IO error
            }
        } // while ends
    } // showMemoryGraph ends
}

