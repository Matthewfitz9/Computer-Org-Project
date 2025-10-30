package com.JAMM;

import java.io.IOException;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;

public class Disk {
    private static List<HWDiskStore> diskStore;

    public static void diskMenu() {
        // Create SystemInfo and HardwareAbstractionLayer objects to access hardware data
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        // Get the list of all physical disks connected to the system
        diskStore = hal.getDiskStores();

        while (true) {
            
            System.out.println("=== Available Disk Devices ===");
            for (int i = 0; i < diskStore.size(); i++) {
                System.out.println((i + 1) + ". " + diskStore.get(i).getName());
            }

            System.out.println("0. Exit");
            System.out.print("\nEnter the list number of the disk you want to examine: ");
            String choice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
            System.out.println("");

            if (choice.contains("0") || 
                    choice.contains("exit") || 
                    choice.contains("quit")) {
                System.out.println("Returning to main menu...\n");
                return;
            }

            displayDiscInfo(choice);
        }
    }

    private static void displayDiscInfo(String choice) {

        int diskNum;
        
        try {
            diskNum = Integer.valueOf(choice) - 1;
        } catch (Exception e) {
            System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.\n");
            return;
        }

        HWDiskStore disk = diskStore.get(diskNum);

        while (true) {
            
            System.out.println("\n=== " + diskStore.get(diskNum).getName() + " ===\n");
            System.out.println("1. General information");
            System.out.println("2. Partition information");
            System.out.println("3. Disk speed");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");
            String diskChoice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
            System.out.println("");
            
            if (diskChoice.contains("1") || 
                diskChoice.contains("general info") || 
                diskChoice.contains("general")) {
                showGeneralInfo(disk);

            } else if (diskChoice.contains("2") || 
                    diskChoice.contains("partition") || 
                    diskChoice.contains("part")) {
                showPartitionInfo(disk);

            } else if (diskChoice.contains("3")  || 
                    diskChoice.contains("speed") || 
                    diskChoice.contains("disk speed")) {
                showDiskSpeed(disk);

            } else if (diskChoice.contains("0") || 
                    diskChoice.contains("exit") || 
                    diskChoice.contains("quit")) {
                System.out.println("Returning to disk select menu...\n");
                return;

            } else { // This replaces the 'default' case
                System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
            }
        }
    }

    private static void showGeneralInfo(HWDiskStore disk) {
        System.out.println("== Disk Information ==\n");
        System.out.println(" Model: " + disk.getModel());
        System.out.println(" Size: " + (disk.getSize() / Math.pow(1024, 3)) + " GiB");
        System.out.println(" Serial number: " + disk.getSerial());
    
    }

    private static void showPartitionInfo(HWDiskStore disk) {
        List<HWPartition> partitions = disk.getPartitions();

        if (partitions.isEmpty()) {
            System.out.println("\nNo partitions found on this disk.\n");
            return;
        }

        // Loop through partitions on the current disk
        for (HWPartition part : partitions) {
            double sizeGiB = (double) part.getSize() / 1073741824.0; // Convert bytes to GiB

            System.out.println("\nName: " + part.getName());
            System.out.println("ID: " + part.getIdentification());
            System.out.println("Type: " + part.getType());
            System.out.println("Size: " + sizeGiB + " GiB");
            System.out.println("Mount point: " + part.getMountPoint() + "\n");
        }
    }

    private static void showDiskSpeed(HWDiskStore disk) {
        // --- Initial values ---
        long prevReadBytes = disk.getReadBytes();
        long prevWriteBytes = disk.getWriteBytes();
        long prevTimeStamp = disk.getTimeStamp(); // Use OSHI's timestamp

        System.out.println("");
        System.out.println("");
        
        
        // --- Live Loop ---
        while (true) {
            // Wait 1 second before getting new stats
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            // --- IMPORTANT: Refresh disk stats ---
            if (!disk.updateAttributes()) {
                System.out.println("Failed to update disk stats. Exiting.");
                break; // Exit if update fails (e.g., disk removed)
            }

            // --- Get new values ---
            long newReadBytes = disk.getReadBytes();
            long newWriteBytes = disk.getWriteBytes();
            long newTimeStamp = disk.getTimeStamp();

            // --- Calculate delta (difference) ---
            long readDelta = newReadBytes - prevReadBytes;
            long writeDelta = newWriteBytes - prevWriteBytes;
            long timeDeltaMs = newTimeStamp - prevTimeStamp; // Time difference in milliseconds

            // --- Calculate speed (Bytes per Second) ---
            // Avoid division by zero if timeDelta is too small
            double readSpeedBps = (timeDeltaMs > 0) ? (double) readDelta / (timeDeltaMs / 1000.0) : 0;
            double writeSpeedBps = (timeDeltaMs > 0) ? (double) writeDelta / (timeDeltaMs / 1000.0) : 0;

            // --- Convert to Megabytes per Second (MB/s) ---
            double readSpeedMBs = readSpeedBps / 1_000_000.0;
            double writeSpeedMBs = writeSpeedBps / 1_000_000.0;

            // --- Display Live Speeds ---
            // Using \r (carriage return) to overwrite the previous line for a cleaner look
            // 1. Move cursor UP one line (to where the speed line should be)
            System.out.print("\033[F");
            System.out.print("\033[F");

            // 2. Print the speed line, overwriting with spaces using \r and padding
            System.out.printf("\rRead: %8.2f MB/s | Write: %8.2f MB/s%-30s",
                            readSpeedMBs, writeSpeedMBs, ""); // Pad with spaces

            // 4. (Optional) Clear the exit message line before re-printing it
            //    This helps if the terminal size changes.
            //System.out.print("\r\033[K"); // Move to start, clear line
            System.out.println("");
            System.out.println("");

            // 5. Re-print the exit message (without a newline) on the line below the speeds
            System.out.print("Press Enter to exit..."); // Re-print if you cleared it

            System.out.flush(); // Ensure output is immediate
            // --- Update previous values for the next loop ---
            prevReadBytes = newReadBytes;
            prevWriteBytes = newWriteBytes;
            prevTimeStamp = newTimeStamp;

            // --- Check for Exit (Optional - e.g., press Enter to stop) ---
            try {
                if (System.in.available() > 0) {
                    System.out.println("\nExiting disk speed monitor...");
                    // Clear the input buffer
                    System.in.read(new byte[System.in.available()]);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        } // while loop ends
    }
}