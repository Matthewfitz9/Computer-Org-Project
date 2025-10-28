package com.JAMM; //Main package for the application

/*
 * InputMismatchException is a type of exception (error)
 *  that occurs when an input stream (usually via a Scanner)
 *  receives data that does not match the expected type.
 */
import java.util.InputMismatchException;
import java.util.Scanner;
// Uses OSHI library to retrieve system information.
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

import oshi.software.os.OSFileStore;
import java.util.List;

/**
 * Main menu system for hardware information viewer
 * Uses existing library classes: CPU, Memory, Disk, Disk2, USB, Pci
 */
public class MainArtem {
    // Static fields for shared access throughout the class
    private static Scanner scanner;
    private static SystemInfo si; // SystemInfo instance for hardware access
    private static HardwareAbstractionLayer hal; // HAL instance for hardware details

    public static void main(String[] args) {
        // Initialize system information objects
        si = new SystemInfo(); // SystemInfo instance
        /*
        * Initialize scanner for user input
         */

        hal = si.getHardware(); // Hardware Abstraction Layer instance
        
        scanner = new Scanner(System.in); // Initialize scanner for user input

        // Display welcome message
        System.out.println("========================================");
        System.out.println("   SYSTEM INFORMATION VIEWER v1.0");
        System.out.println("========================================\n");

        // Start main menu loop
        mainMenu();

        // Close scanner when exiting
        scanner.close();
    }

    /**
     * Main menu - displays all hardware categories
     */
    private static void mainMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n========================================");
            System.out.println("           MAIN MENU");
            System.out.println("========================================");
            System.out.println("1. CPU Information");
            System.out.println("2. Memory Information");
            System.out.println("3. Disk Information");
            System.out.println("4. USB Devices");
            System.out.println("5. PCI Devices");
            System.out.println("6. About JAMM project");
            System.out.println("0. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                /*
                 * Calls to other classes (CPU, Memory, Disk, Disk2, USB, Pci):
                 * These classes are expected to implement the corresponding methods
                 *  (e.g., CPU.displayinfo(), Disk.displayDiscInfo(), etc.).
                 */
                switch (choice) {
                    /*
                     * After calling, for example, cpuMenu(), 
                     * control is completely transferred to this method. 
                     * It again implements an internal menu and mechanics similar to the main one: 
                     * the user selects the next step, and again, 
                     * the required logic (e.g., a graph, detailed information) is called via a switch statement.
                     */
                    case 1:
                        cpuMenu(); // Enter CPU submenu
                        break;
                    case 2:
                        memoryMenu();
                        break;
                    case 3:
                        diskMenu();
                        break;
                    case 4:
                        usbMenu();
                        break;
                    case 5:
                        pciMenu();
                        break;
                    case 6:
                        System.out.println("\n========== ABOUT JAMM PROJECT ==========");
                        System.out.println("JAMM (Java Advanced Monitoring & Management) — this is an educational project,");
                        System.out.println("designed to analyze and display hardware characteristics");
                        System.out.println("software using the OSHI library. The program allows");
                        System.out.println("view information about CPU, memory, disks, USB and PCI devices.");
                        System.out.println("\nProject developers:");
                        System.out.println("1. Joshua Corcoran");
                        System.out.println("2. Artem Bosyi");
                        System.out.println("3. Matthew Fitzgerald");
                        System.out.println("4. Mathieu Gril");
                        System.out.println("========================================");
                        System.out.println("\nPress Enter to return to the main menu...");
                        scanner.nextLine();
                        break;
                    case 0:
                    // Exit condition
                        System.out.println("\n========================================");
                        System.out.println("  Exiting System Information Viewer");
                        System.out.println("           Goodbye! :)");
                        System.out.println("========================================\n");
                        running = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number between 1 and 6.");
                } //switch ends
                /*
                 * Handle errors using try/catch InputMismatchException. 
                 * If the user enters something other than a number, the program doesn't crash, 
                 * but displays a message and waits for valid input.
                 */
            } catch (InputMismatchException e) {
                // Input wasn't an integer, handle gracefully
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                /*
                 * After each scan of a number, 
                 * scanner.nextLine() is performed to remove unnecessary
                 *  line feeds and avoid input confusion.
                 */
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * CPU submenu - displays processor information options
     */
    private static void cpuMenu() {
        boolean inCpuMenu = true; // Flag to control CPU menu loop

        while (inCpuMenu) {
            
            System.out.println("\n========== CPU INFORMATION ==========");
            System.out.println("1. View CPU Information (Interactive)");
            System.out.println("2. View  CPU Load Graph");
            System.out.println("0. Back to Main Menu");
            System.out.println("=====================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

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
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * Memory information menu
     */
    private static void memoryMenu() {
        System.out.println("\n========== MEMORY INFORMATION ==========");
        
        // Calls static method in Memory class for RAM info (hal passed in)
        Memory.displayMemory(hal);
        displayMemory(hal); // Display memory with progress bars
        
        System.out.println("\n========================================");
        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }
        public static void displayMemory(HardwareAbstractionLayer hal) {
        // Getting memory information
        oshi.hardware.GlobalMemory mem = hal.getMemory();

        long totalMem = mem.getTotal();
        long usedMem = totalMem - mem.getAvailable();
        long freeMem = mem.getAvailable();

        long totalSwap = mem.getVirtualMemory().getSwapTotal();
        long usedSwap = mem.getVirtualMemory().getSwapUsed();
        long freeSwap = totalSwap - usedSwap;

        // Visualization: Progress bars for RAM and swap
        int barWidth = 40;
        System.out.println("========== MEMORY USAGE ==========");

        System.out.printf("RAM Total:      %,d MB\n", totalMem / 1024 / 1024);
        System.out.printf("RAM Used:       %,d MB\n", usedMem / 1024 / 1024);
        System.out.printf("RAM Free:       %,d MB\n", freeMem / 1024 / 1024);

        // RAM bar
        System.out.print("RAM:     [");
        int ramBars = (int)(usedMem * barWidth / totalMem);
        for (int i = 0; i < barWidth; i++)
            System.out.print(i < ramBars ? "█" : " ");
        System.out.printf("] %.1f%%\n", (100.0 * usedMem / totalMem));

        System.out.printf("\nSwap Total:     %,d MB\n", totalSwap / 1024 / 1024);
        System.out.printf("Swap Used:      %,d MB\n", usedSwap / 1024 / 1024);
        System.out.printf("Swap Free:      %,d MB\n", freeSwap / 1024 / 1024);

        // Swap bar
        System.out.print("Swap:    [");
        int swapBars = totalSwap == 0 ? 0 : (int)(usedSwap * barWidth / totalSwap);
        for (int i = 0; i < barWidth; i++)
            System.out.print(i < swapBars ? "█" : " ");
        System.out.printf("] %.1f%%\n", totalSwap == 0 ? 0.0 : (100.0 * usedSwap / totalSwap));

        System.out.println("==================================");
    }


    /**
     * Disk information menu
     */
    private static void diskMenu() {

        displayDiscUsage(); // Display disk usage with progress bars

        boolean inDiskMenu = true;
        while (inDiskMenu) {
            System.out.println("\n========== DISK INFORMATION ==========");
            System.out.println("1. View All Disk Information");
            System.out.println("2. Search Disk Information (Keyword-based)");
            System.out.println("0. Back to Main Menu");
            System.out.println("======================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\n--- Launching Disk Information Module ---");
                        Disk diskObj = new Disk(); // Create Disk instance
                        // Call the existing Disk.displayDiscInfo() method
                        diskObj.displayDiscInfo();
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 2:
                        // Call the existing Disk2.displayDiscInfo() method with user input
                        System.out.println("\n--- Keyword-based Disk Search ---");
                        System.out.println("Try keywords like: name, model, size, serial, partition, all");
                        System.out.print("Enter search keyword: ");
                        String keyword = scanner.nextLine();
                        //
                        Disk2 disk2Obj = new Disk2(); // Create Disk2 instance
                        disk2Obj.displayDiscInfo(keyword);
                        
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 0:
                        inDiskMenu = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number between 1 and 3.");
                    } //switch ends
                //
                } catch (InputMismatchException e) {
                    System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                    scanner.nextLine(); // Clear invalid input
                }
        }
    }



    public static void displayDiscUsage() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        List<OSFileStore> fileStores = si.getOperatingSystem().getFileSystem().getFileStores();

        int barWidth = 40;
        System.out.println("======== DISK PARTITION USAGE ========");

        for (OSFileStore store : fileStores) {
            long totalSpace = store.getTotalSpace();
            long usableSpace = store.getUsableSpace();
            long usedSpace = totalSpace - usableSpace;
            double usagePercent = totalSpace == 0 ? 0.0 : (100.0 * usedSpace / totalSpace);

            System.out.println("\nPartition: " + store.getName() + " (" + store.getMount() + ")");
            System.out.printf("  Total: %,d GB\n", totalSpace / 1024 / 1024 / 1024);
            System.out.printf("  Used:  %,d GB\n", usedSpace / 1024 / 1024 / 1024);
            System.out.printf("  Free:  %,d GB\n", usableSpace / 1024 / 1024 / 1024);

            // Progress bar
            System.out.print("  Usage: [");
            int bars = totalSpace == 0 ? 0 : (int)(usedSpace * barWidth / totalSpace);
            for (int i = 0; i < barWidth; i++)
                System.out.print(i < bars ? "█" : " ");
            System.out.printf("] %.1f%%\n", usagePercent);
        }
        System.out.println("\n======================================");
    }

    /**
     * USB devices menu
     */
    private static void usbMenu() {
        boolean inUsbMenu = true;

        while (inUsbMenu) {
            System.out.println("\n========== USB DEVICES ==========");
            System.out.println("1. View USB Information (Interactive Menu)");
            System.out.println("0. Back to Main Menu");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Display all USB device info
                        System.out.println("\n--- Launching USB Information Module ---\n");
                        //
                        USB usbObj = new USB();
                        // Call the existing USB.displayUSBInfo() method
                        usbObj.displayUSBInfo();
                        break;
                    case 0:
                        inUsbMenu = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter 1 or 2.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        } //while ends
    } //usbMenu ends

    /**
     * PCI devices menu
     */
    private static void pciMenu() {
        boolean inPciMenu = true;

        while (inPciMenu) {
            System.out.println("\n========== PCI DEVICES ==========");
            System.out.println("1. View All PCI Devices");
            System.out.println("2. View PCI Device Details (Submenu)");
            System.out.println("0. Back to Main Menu");
            System.out.println("=================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Calls static method to print all PCI devices
                        System.out.println("\n--- PCI Device Information ---");
                        Pci.displayPci(hal);
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 2:
                    // Enter extended submenu for more specific PCI info
                        pciSubmenu();
                        break;
                    case 0:
                        inPciMenu = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number between 1 and 3.");
                } //switch ends
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        } //while ends
    } //pciMenu ends

    /**
     * PCI devices submenu - provides detailed categorization
     */
    private static void pciSubmenu() {
        boolean inPciSubmenu = true;

        while (inPciSubmenu) {
            System.out.println("\n========== PCI DEVICE DETAILS ==========");
            System.out.println("1. Graphics Cards");
            System.out.println("2. Network Devices");
            System.out.println("3. Storage Devices");
            System.out.println("4. Sound Cards");
            System.out.println("0. Back to PCI Menu");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.println("\n========== GRAPHICS CARDS ==========");
                        hal.getGraphicsCards().forEach(gpu -> {
                            System.out.println("\n--- Graphics Card ---");
                            System.out.println("Device ID: " + gpu.getDeviceId());
                            System.out.println("Name: " + gpu.getName());
                            System.out.println("Vendor: " + gpu.getVendor());
                            System.out.println("Version Info: " + gpu.getVersionInfo());
                            System.out.println("VRAM: " + gpu.getVRam() + " bytes");
                        });
                        System.out.println("====================================");
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 2:
                        // List all real (non-VM) network devices and their IP/MAC
                        System.out.println("\n========== NETWORK DEVICES ==========");
                        hal.getNetworkIFs().forEach(netDevice -> {
                            // Skips "virtual" MACs used by VM devices
                            //
                            if (!netDevice.isKnownVmMacAddr()) {
                                System.out.println("\n--- Network Device ---");
                                System.out.println("Name: " + netDevice.getDisplayName());
                                System.out.println("MAC Address: " + netDevice.getMacaddr());
                                System.out.print("IPv4 Addresses: ");
                                String[] ipv4Addresses = netDevice.getIPv4addr();
                                    for (String addr : ipv4Addresses) {
                                        System.out.print(addr + " ");
                                    }
                                System.out.println();
                                System.out.println("Speed: " + netDevice.getSpeed() + " bps");
                            }
                        });
                        System.out.println("=====================================");
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 3:
                        // List all storage devices with model, size, and serial
                        System.out.println("\n========== STORAGE DEVICES ==========");
                        hal.getDiskStores().forEach(storage -> {
                            System.out.println("\n--- Storage Device ---");
                            System.out.println("Model: " + storage.getModel());
                            System.out.println("Size: " + storage.getSize() + " bytes");
                            System.out.println("Serial: " + storage.getSerial());
                        });
                        System.out.println("=====================================");
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 4:
                        // List all detected sound cards
                        System.out.println("\n========== SOUND CARDS ==========");
                        hal.getSoundCards().forEach(soundCard -> {
                            System.out.println("\n--- Sound Card ---");
                            System.out.println("Driver Version: " + soundCard.getDriverVersion());
                            System.out.println("Codec: " + soundCard.getCodec());
                            System.out.println("Name: " + soundCard.getName());
                        });
                        System.out.println("=================================");
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 0:
                        inPciSubmenu = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        } //while ends
    } //pciSubmenu ends

    /**
     * CpuGraph display method
     */
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
} //Main ends
