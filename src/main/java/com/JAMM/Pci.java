package com.JAMM; 

import java.util.List;
import java.util.Scanner;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.SoundCard;

public class Pci {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nEnter which devices you would like to get information about.");
            System.out.println("Available devices: \nGraphics cards, \nNetwork interfaces, \nSound cards\n");
            System.out.println("Try phrases like: 'graphics', 'network', 'sound', etc.");
            System.out.println("Type 'exit' to quit.");
            System.out.print("Enter choice: ");
            String option = sc.nextLine().toLowerCase();

            // If the user types "exit", end the loop and terminate the program
            if (option.contains("exit")) {
                System.out.println("Exiting program...");
                break;
            }
                    
            if (option.contains("graphic") || option.contains("gpu")) {
                GraphicsInfo();
            }
            else if (option.contains("network") || option.contains("interface")) {
                NetworkInfo();
            }
            else if (option.contains("sound")) {
                SoundInfo();
            }
            else {
                System.out.println("No matching information found. Try a different keyword.");
            }
            
            // Call displayDiscInfo() with the user’s input
            displayInfo(option);
        }

        // closes the scanner
        sc.close();
    }

    public static void displayInfo(String choice) {
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

    public static void GraphicsInfo() {

    }

    public static void NetworkInfo() {

    }

    public static void SoundInfo() {
        
    }

    public static void displayPci(HardwareAbstractionLayer inHal) {
        // Initialise all the lists for pcie devices
        List<GraphicsCard> graphicsCards = inHal.getGraphicsCards();
        List<NetworkIF> networkDevices = inHal.getNetworkIFs();
        List<HWDiskStore> storageDevices = inHal.getDiskStores();
        List<SoundCard> soundCards = inHal.getSoundCards();
        
        for (GraphicsCard graphicsCard : graphicsCards) {
            System.out.printf("%n%n%s%n", graphicsCard.getDeviceId());
            System.out.println(graphicsCard.getName());
            System.out.println(graphicsCard.getVendor());
            System.out.println(graphicsCard.getVersionInfo());
            System.out.println(graphicsCard.getVRam());
        }

        for (NetworkIF netDevice : networkDevices) {
            // ignore virtual hardware to only get actual physical crap
            if (netDevice.isKnownVmMacAddr()) {
                continue;
            }
            System.out.printf("%n%n%s%n", netDevice.getDisplayName());
            System.out.println(netDevice.getMacaddr());
            for (String addr : netDevice.getIPv4addr()) {
                System.out.println(addr);
            }
            System.out.println(netDevice.getSpeed());
            
        }

        for (HWDiskStore storageDevice : storageDevices) {
            System.out.printf("%n%n%s%n", storageDevice.getModel());
            System.out.println(storageDevice.getSize());
            System.out.println(storageDevice.getSerial());

        }

        for (SoundCard soundCard : soundCards) {
            System.out.printf("%n%n%s%n", soundCard.getDriverVersion());
            System.out.println(soundCard.getCodec());
            System.out.println(soundCard.getName());

        }

    }

    public static void displayLiveDiskInfo(HardwareAbstractionLayer inHal) {

    }
         
}
