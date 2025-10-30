package com.JAMM;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.UsbDevice;


public class USB {
    // Map to link Vendor IDs to readable Vendor Names
    private static final Map<String, String> vendorLookup = new HashMap<>();

    private static List<UsbDevice> usbDevices;

    // Static block runs once when the class is loaded
    static {
        vendorLookup.put("0x8086", "Intel");
        vendorLookup.put("0x1022", "AMD");
        vendorLookup.put("0x0781", "SanDisk");
        vendorLookup.put("0x05AC", "Apple");
        vendorLookup.put("0x046D", "Logitech");
    }

    public static void usbMenu() {
        // Create SystemInfo and HardwareAbstractionLayer objects to access hardware data
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        // Get the list of all physical disks connected to the system
        usbDevices = hal.getUsbDevices(false);

        while (true) {
            // Check if there are no connected USB devices
            if (usbDevices.isEmpty()) {
                System.out.println("!-- There are currently no USB devices. --!");
                return;
            }

            System.out.println("=== Available USB Devices ===");
            for (int i = 0; i < usbDevices.size(); i++) {
                System.out.println((i + 1) + ". " + usbDevices.get(i).getName());
            }

            System.out.println("0. Exit");
            System.out.print("\nEnter the list number of the USB device you want to examine: ");
            String choice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
            System.out.println("");

            if (choice.contains("0") || choice.contains("exit") ||  choice.contains("quit")) {
                System.out.println("Returning to main menu...\n");
                return;
            }

            displayUSBInfo(choice);
        }
    }

    private static void displayUSBInfo (String choice) {

        int usbNum;
        
        try {
            usbNum = Integer.valueOf(choice) - 1; // index for list
        } catch (Exception e) {
            System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.\n");
            return;
        }
        
        UsbDevice usb;

        try {
            usb = usbDevices.get(usbNum);
        } catch (Exception e) {
            System.out.println("\n[ERROR] Invalid choice. Please enter a value within range.\n");
            return;
        }

        while (true) {

            // sub menu
            System.out.println("\n=== " + usbDevices.get(usbNum).getName() + " ===\n");
            System.out.println("1. General information");
            System.out.println("2. Vendor information");
            System.out.println("0. Exit");
            System.out.print("\nEnter your choice: ");
            String usbChoice = Main.scanner.nextLine().trim().replaceAll("\\s{2,}", " ").toLowerCase();
            System.out.println("");

            if (usbChoice.contains("1") || 
                usbChoice.contains("general info") || 
                usbChoice.contains("general")) {
                showGeneralInfo(usb);
            }

            else if (usbChoice.contains("2") ||
                usbChoice.contains("vendor") ||
                usbChoice.contains("vendor info")) {
                showVendorInfo(usb);
                
            } else if (usbChoice.contains("0") || 
                    usbChoice.contains("exit") || 
                    usbChoice.contains("quit")) {
                System.out.println("Returning to USB select menu...\n");
                return;

            } else { // This replaces the 'default' case
                System.out.println("\n[ERROR] Invalid choice. Please enter number or keyword.");
            }
        }
    }
    
    // info for each usb device
    private static void showGeneralInfo(UsbDevice usb) {
        System.out.println("== USB Information ==\n");

        String serial = usb.getSerialNumber();
        // Handle devices that don’t have serial numbers
        if (serial == null || serial.isEmpty()) {
            System.out.println("Serial number not available for device.");
        } else {
            System.out.println(" Serial Number: " + serial);
        }

        System.out.println(" Product ID: " + usb.getProductId());
        System.out.println(" Unique Device ID: " + usb.getUniqueDeviceId());
        System.out.println(" Class: " + usb.getClass());
    }

    private static void showVendorInfo(UsbDevice usb) {
        System.out.println("== Vendor Information ==\n");
        String vendorId = usb.getVendorId();
        String vendorName = vendorLookup.getOrDefault(vendorId, "Unknown");
        System.out.println("Vendor: " + usb.getVendor() + " (" + vendorName + ")");
    }
} // class
