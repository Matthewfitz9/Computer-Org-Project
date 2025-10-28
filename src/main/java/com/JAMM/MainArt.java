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
public class MainArt {
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
        System.out.println("");
        System.out.println("============================");
        System.out.println(" SYSTEM INFORMATION VIEWER ");
        System.out.println("----------------------------\n");

        // Start main menu loop
        mainMenu();

        // Close scanner when exiting
        scanner.close();
    }

    /**
     * Main menu - displays all hardware categories
     */
    private static void mainMenu() {
        while (true) {
            System.out.println("===== Main Menu =====");
            System.out.println("1. CPU Information");
            System.out.println("2. Memory Information");
            System.out.println("3. Disk Information");
            System.out.println("4. USB Devices");
            System.out.println("5. PCI Devices");
            System.out.println("6. About JAMM project");
            System.out.println("0. Exit");
            System.out.println("---------------------");
            System.out.println("You can enter number or keyword.");
            System.out.print("Enter your choice: ");

            try {
                //int choice = scanner.nextInt();
                String choice = scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();

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
                    case "1":
                    case "cpu":
                    case "cpu information":
                        CPU.cpuMenu(); // Enter CPU submenu
                        break;
                    case "2":
                    case "memory":
                    case "memory information":
                        //memoryMenu();
                        break;
                    case "3":
                    case "disk":
                    case "disk information":
                        //diskMenu();
                        break;
                    case "4":
                    case "usb":
                    case "usb devices":
                        //usbMenu();
                        break;
                    case "5":
                    case "pci":
                    case "pci devices":
                        //pciMenu();
                        break;
                    case "6":
                    case "about":
                    case "about jamm project":
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
                        scanner.nextLine();
                        break;
                    case "0":
                    case "exit":
                    case "quit":
                    // Exit condition
                        System.out.println("========Exiting=========");
                        System.out.println("System Information Viewer");
                        System.out.println("------Goodbye! :)-------\n");
                        return; // Exit the program
                    default:
                        System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
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
        } //mainMenu ends
    } 
} //Main ends
