package com.JAMM;

import java.util.Comparator; 
import java.util.List;

import oshi.SystemInfo; 
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess; 
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.OSVersionInfo;

public class SystemInfoModule {

    private static OperatingSystem os;
    private static HardwareAbstractionLayer hal;
    private static ComputerSystem computerSystem;

    public static void systemMenu() {
        // Get System Info
        SystemInfo si = new SystemInfo();
        os = si.getOperatingSystem();
        hal = si.getHardware();
        computerSystem = hal.getComputerSystem();

        // Main selection menu
        while (true) {
            System.out.println("\n===== System Information Menu =====");
            System.out.println("1. Current processes");
            System.out.println("2. Operating System info");
            System.out.println("3. Machine/Computer info");
            System.out.println("4. Firmware info");
            System.out.println("5. Motherboard info");
            System.out.println("0. Return to Main Menu");
            System.out.print("\nEnter your choice: ");
            String choice = Main.scanner.nextLine().trim().toLowerCase();

            if (choice.contains("1") ||
                choice.contains("process")) {
                showTopProcesses(); 

            } else if (choice.contains("2") ||
                       choice.contains("os")) {
                showOsInfo();

            } else if (choice.contains("3") ||
                       choice.contains("machine") ||
                       choice.contains("computer")) {
                showMachineInfo();

            } else if (choice.contains("4") ||
                       choice.contains("firmware") ||
                       choice.contains("bios")) {
                showFirmwareInfo();

            } else if (choice.contains("5") ||
                       choice.contains("motherboard") ||
                       choice.contains("baseboard")) {
                showMotherboardInfo();

            } else if (choice.contains("0") ||
                       choice.contains("exit") ||
                       choice.contains("quit") ||
                       choice.contains("return") ||
                       choice.contains("back")) {
                System.out.println("Returning to main menu...\n");
                return; 

            } else {
                System.out.println("\n[ERROR] Invalid choice. Please enter a number or keyword.");
            }
        }
    }

    // Live chart displaying the top 15 processes taking up ram, aswell as the current system uptime
    private static void showTopProcesses() {
        SystemInfo si = new SystemInfo();
        os = si.getOperatingSystem();
        
        // Get Total Memory once to calculate %
        final long totalMem = si.getHardware().getMemory().getTotal();

        while (true) {
            // Clear the screen
            System.out.print("\033[H\033[2J");
            System.out.flush();

            long uptimeSeconds = os.getSystemUptime();
            
            // Format the total uptime seconds into Days, Hours, Minutes, and Seconds
            long days = uptimeSeconds / (24 * 3600);
            long hours = (uptimeSeconds % (24 * 3600)) / 3600;
            long minutes = (uptimeSeconds % 3600) / 60;
            long seconds = uptimeSeconds % 60;
            
            // Display the uptime
            System.out.println("\n\n\n\n\n");
            System.out.println("=== System Uptime ===");
            System.out.printf("%d Days, %02d Hours, %02d Minutes, %02d Seconds\n\n", days, hours, minutes, seconds);

            System.out.println("=== Live Processes ===");

            // Get the current list of processes
            List<OSProcess> processes = os.getProcesses();
            
            // Sort the list by RAM usage (Resident Set Size), from high to low
            processes.sort(Comparator.comparing(OSProcess::getResidentSetSize).reversed());

            // Print a formatted table header
            System.out.printf("%-8s | %-10s | %-8s | %s\n", 
                    "PID", "USER", "%MEM", "NAME");
            System.out.println("-".repeat(80));

            // Loop and print the top 15
            for (int i = 0; i < 15 && i < processes.size(); i++) {
                OSProcess p = processes.get(i);
                
                double memPercent = (double) p.getResidentSetSize() / totalMem * 100.0;
                
                System.out.printf("%-8d | %-10s | %-8.2f | %s\n",
                        p.getProcessID(),
                        p.getUser(),
                        memPercent,
                        p.getName()
                );
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
                // Update the graph every half second
                Thread.sleep(1000);
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

    // Shows static OS information
    private static void showOsInfo() {
        OSVersionInfo versionInfo = os.getVersionInfo();

        System.out.println("\n=== Operating System (OS) Info ===");
        System.out.println("  Family: " + os.getFamily());
        System.out.println("  Manufacturer: " + os.getManufacturer());
        System.out.println("  Version: " + versionInfo.getVersion());
        System.out.println("  Code Name: " + versionInfo.getCodeName());
        System.out.println("  Build Number: " + versionInfo.getBuildNumber());
        System.out.println("  Architecture: " + os.getBitness() + "-bit");
        System.out.println("\nPress Enter to return...");
        Main.scanner.nextLine(); // Wait
    }

    // Shows general information about the machines
    private static void showMachineInfo() {
        System.out.println("\n=== Machine/Computer Info ===");
        System.out.println("  Manufacturer: " + computerSystem.getManufacturer());
        System.out.println("  Model: " + computerSystem.getModel());
        System.out.println("  Serial Number: " + computerSystem.getSerialNumber());
        System.out.println("  Hardware UUID: " + computerSystem.getHardwareUUID());
        System.out.println("\nPress Enter to return...");
        Main.scanner.nextLine(); // Wait
    }

    /// Shows general information about the Firmware/BIOS
    private static void showFirmwareInfo() {
        Firmware firmware = computerSystem.getFirmware();
        System.out.println("\n=== Firmware (BIOS/UEFI) Info ===");
        System.out.println("  Manufacturer: " + firmware.getManufacturer());
        System.out.println("  Name: " + firmware.getName());
        System.out.println("  Description: " + firmware.getDescription());
        System.out.println("  Version: " + firmware.getVersion());
        System.out.println("  Release Date: " + firmware.getReleaseDate());
        System.out.println("\nPress Enter to return...");
        Main.scanner.nextLine(); // Wait
    }

    // Shows static information about the Motherboard
    private static void showMotherboardInfo() {
        Baseboard baseboard = computerSystem.getBaseboard();
        System.out.println("\n=== Motherboard (Baseboard) Info ===");
        System.out.println("  Manufacturer: " + baseboard.getManufacturer());
        System.out.println("  Model: " + baseboard.getModel());
        System.out.println("  Version: " + baseboard.getVersion());
        System.out.println("  Serial Number: " + baseboard.getSerialNumber());
        System.out.println("\nPress Enter to return...");
        Main.scanner.nextLine(); // Wait
    }
}
