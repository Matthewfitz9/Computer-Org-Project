package com.JAMM;

import java.util.InputMismatchException;
import java.util.Scanner;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;

/**
 * Main menu system for hardware information viewer
 * Uses existing library classes: CPU, Memory, Disk, Disk2, USB, Pci
 */
public class MainArtem {
    private static Scanner scanner;
    private static SystemInfo si;
    private static HardwareAbstractionLayer hal;

    public static void main(String[] args) {
        // Initialize system information objects
        si = new SystemInfo();
        hal = si.getHardware();
        scanner = new Scanner(System.in);

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
            System.out.println("0. Exit");
            System.out.println("========================================");
            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        cpuMenu();
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
                    case 0:
                        System.out.println("\n========================================");
                        System.out.println("  Exiting System Information Viewer");
                        System.out.println("           Goodbye! :)");
                        System.out.println("========================================\n");
                        running = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    /**
     * CPU submenu - displays processor information options
     */
    private static void cpuMenu() {
        boolean inCpuMenu = true;

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
                        //
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
        
        // Call the existing Memory.displayMemory() method
        Memory.displayMemory(hal);
        
        System.out.println("\n========================================");
        System.out.println("\nPress Enter to return to main menu...");
        scanner.nextLine();
    }

    /**
     * Disk information menu
     */
    private static void diskMenu() {
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
                        // Call the existing Disk.displayDiscInfo() method
                        System.out.println("\n--- Launching Disk Information Module ---");
                        Disk diskObj = new Disk();
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
                        
                        Disk2 disk2Obj = new Disk2();
                        disk2Obj.displayDiscInfo(keyword);
                        
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 0:
                        inDiskMenu = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
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
                        // Call the existing USB.displayUSBInfo() method
                        System.out.println("\n--- Launching USB Information Module ---\n");
                        USB usbObj = new USB();
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
        }
    }

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
                        // Call the existing Pci.displayPci() method
                        System.out.println("\n--- PCI Device Information ---");
                        Pci.displayPci(hal);
                        System.out.println("\nPress Enter to continue...");
                        scanner.nextLine();
                        break;
                    case 2:
                        pciSubmenu();
                        break;
                    case 0:
                        inPciMenu = false;
                        break;
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter a number between 1 and 3.");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n[ERROR] Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

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
                        System.out.println("\n========== NETWORK DEVICES ==========");
                        hal.getNetworkIFs().forEach(netDevice -> {
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
        }
    }

    /**
     * CpuGraph display method
     */
    public static void CpuGraph() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        CentralProcessor processor = hal.getProcessor();
        final int graphWidth = 50, graphHeight = 10;
        double[] cpuHistory = new double[graphWidth];
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        
        while (true) {
            double load = processor.getSystemCpuLoadBetweenTicks(prevTicks) * 100;
            prevTicks = processor.getSystemCpuLoadTicks();
            System.arraycopy(cpuHistory, 1, cpuHistory, 0, graphWidth - 1);
            cpuHistory[graphWidth - 1] = load;

            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("=".repeat(50));
            System.out.println("CPU: " + processor.getProcessorIdentifier().getName());
            System.out.println("-".repeat(50));
            
            // CPU graph
            for (int h = graphHeight-1; h >= 0; h--) {
                double threshold = ((double)h / (graphHeight-1)) * 100.0;
                for (int x = 0; x < graphWidth; x++)
                    System.out.print(cpuHistory[x] >= threshold ? "█" : " ");
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));
            System.out.printf("CPU Usage:   %.1f%%\n\n", load);
            System.out.println("=".repeat(50));
            System.out.println("\nPress Enter to exit...");
            
            try {
                if (System.in.available() > 0) {
            break;  // Exiting the loop when a key is pressed
        }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
