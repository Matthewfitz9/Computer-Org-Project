package com.JAMM;

import java.util.Scanner;

public class Main {
    // Static fields for shared access throughout the class
    public static Scanner scanner;

    public static void main(String[] args) {
        // Scanner for user input
        scanner = new Scanner(System.in);

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

    // Main menu provides access to all other modules
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
                CPU.cpuMenu(); 

            } else if (choice.contains("2") || 
                    choice.contains("memory") || 
                    choice.contains("ram")) {
                Memory.showMemoryGraph();

            } else if (choice.contains("3") || 
                    choice.contains("disk")) {
                Disk.diskMenu(); 

            } else if (choice.contains("4") || 
                    choice.contains("usb")) { 
                USB.usbMenu();

            } else if (choice.contains("5") || 
                    choice.contains("pci")) { 
                Pci.pciMenu();

            } else if (choice.contains("6") || 
                    choice.contains("system")) {
                SystemInfoModule.systemMenu();

            } else if (choice.contains("7") || 
                    choice.contains("about")) {
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

                // Wait for user input
                scanner.nextLine(); 
            
            } else if (choice.contains("0") || 
                    choice.contains("exit") || 
                    choice.contains("quit")) {
                System.out.println("========Exiting=========");
                System.out.println("System Information Viewer");
                System.out.println("------Goodbye! :)-------\n");
                return;

            } else {
                System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
            }        
        } // while loop ends
    } // mainMenu ends
}
