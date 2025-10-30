package com.JAMM;

import java.util.Scanner;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;

public class Main {
    // Static fields for shared access throughout the class
    public static Scanner scanner;
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
        System.out.println("");
        System.out.println("============================");
        System.out.println(" SYSTEM INFORMATION VIEWER ");
        System.out.println("----------------------------\n");


        System.out.println("Welcome to the System Information Viewer!");
        System.out.println("To use this program, you will have to navigate through menus by entering responses in the terminal.");
        System.out.println("Each menu will give you a list of options, and you can select an option in multiple ways.");
        System.out.println("You can enter the number next to it, e.g '1'.");
        System.out.println("You can use a keyword in the option, e.g 'memory'.");
        System.out.println("Or you can submit a phrase with a keyword/number in it, e.g 'I want to see information about the cpu'.\n");

        // Start main menu loop
        mainMenu();

        // Close scanner when exiting
        scanner.close();
    }

    private static void mainMenu() {
        while (true) {
            System.out.println("\n===== Main Menu =====");
            System.out.println("1. CPU Information");
            System.out.println("2. Memory Information");
            System.out.println("3. Disk Information");
            System.out.println("4. USB Devices");
            System.out.println("5. PCI Devices");
            System.out.println("6. System Information");
            System.out.println("7. About JAMM project");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
            
            if (choice.contains("1") || 
                choice.contains("cpu") || 
                choice.contains("processor")) {
                CPU.cpuMenu(); // Assuming CPU.cpuMenu() exists

            } else if (choice.contains("2") || 
                    choice.contains("memory") || 
                    choice.contains("ram")) {
                Memory.showMemoryGraph();

            } else if (choice.contains("3") || 
                    choice.contains("disk")) { // "disk information" is covered by "disk"
                Disk.diskMenu(); 

            } else if (choice.contains("4") || 
                    choice.contains("usb")) { // "usb devices" is covered by "usb"
                USB.usbMenu();

            } else if (choice.contains("5") || 
                    choice.contains("pci")) { // "pci devices" is covered by "pci"
                Pci.pciMenu();

            } else if (choice.contains("6") || 
                    choice.contains("system")) {
                SystemInfoModule.systemMenu();

            } else if (choice.contains("7") || 
                    choice.contains("about")) { // "about jamm project" is covered by "about"
                System.out.println("\n====== About JAMM Project =======");
                System.out.println("Java Advanced Monitoring & Management");
                System.out.println("using OSHI library to display CPU, memory,");
                System.out.println("disks, USB and PCI devices information.");
                System.out.println("");
                System.out.println("\nProject developers:");
                System.out.println("1. Joshua Corcoran");
                System.out.println("2. Artem Bosyi");
                System.out.println("3. Matthew Fitzgerald");
                System.out.println("4. Mathieu Gril");
                System.out.println("---------------------");
                System.out.println("\nPress Enter to return to the main menu...");
                scanner.nextLine(); // Wait for user input

            } else if (choice.contains("0") || 
                    choice.contains("exit") || 
                    choice.contains("quit")) {
                System.out.println("========Exiting=========");
                System.out.println("System Information Viewer");
                System.out.println("------Goodbye! :)-------\n");
                // If this is inside the main loop, you might need 'break;' or 'return;'
                // depending on your program structure. 'return;' is used assuming this exits a method.
                return;

            } else { // This replaces the 'default' case
                System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
            }        
        } // while loop ends
    } // mainMenu ends

    // Used by CPU for individual cores
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
