package com.JAMM;

import java.util.Scanner;
import oshi.SystemInfo;
import oshi.hardware.*;
import java.util.List;

public class Disk2 {

    public void displayDiscInfo(String choice) {
        // Create SystemInfo and HardwareAbstractionLayer objects to access hardware data
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        // Get the list of all physical disks connected to the system
        List<HWDiskStore> diskStore = hal.getDiskStores();

        // Convert input to lowercase
        String input = choice.toLowerCase();

        // Loop through each detected disk
        for (HWDiskStore disk : diskStore) {
            System.out.println("\n----Disk Information----");

            // Check for keywords
            if (input.contains("name")) {
                System.out.println("Name: " + disk.getName());
            }
            else if (input.contains("model") || input.contains("mod")) {
                System.out.println("Model: " + disk.getModel());
            }
            else if (input.contains("partition") || input.contains("part")) {
                System.out.println("Partition info: " + disk.getPartitions());
            }
            else if (input.contains("read") || input.contains("bytes")) {
                System.out.println("Number of bytes read by the disk: " + disk.getReadBytes());
            }
            else if (input.contains("size") || input.contains("capacity")) {
                System.out.println("Size: " + disk.getSize());
            }
            else if (input.contains("serial") || input.contains("number") || input.contains("ser")) {
                System.out.println("Serial of disk: " + disk.getSerial());
            }
            else if (input.contains("all") || input.contains("everything")) {
                System.out.println("Name: " + disk.getName());
                System.out.println("Model: " + disk.getModel());
                System.out.println("Partition info: " + disk.getPartitions());
                System.out.println("Number of bytes read by the disk: " + disk.getReadBytes());
                System.out.println("Size: " + disk.getSize());
                System.out.println("Serial of disk: " + disk.getSerial());
            }
            else {
                System.out.println("No matching information found. Try keywords like name, model, size, serial, etc.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Disk2 info = new Disk2();  // Create an instance of Disk2 to call methods

        // Infinite loop until the user decides to quit
        while (true) {
            System.out.println("\nEnter the information you wish to know about.");
            System.out.println("Try phrases like: 'disk model', 'serial number', 'show all', etc.");
            System.out.println("Type 'exit' to quit.");
            System.out.print("Enter choice: ");
            String option = sc.nextLine();

            // If the user types "exit", end the loop and terminate the program
            if (option.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                break;
            }

            // Call displayDiscInfo() with the user’s input
            info.displayDiscInfo(option);
        }

        // closes the scanner
        sc.close();
    }
}
