package com.JAMM;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.UsbDevice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class USB {
    // Map to link Vendor IDs to readable Vendor Names
    private static final Map<String, String> vendorLookup = new HashMap<>();

    // Static block runs once when the class is loaded
    static {
        vendorLookup.put("0x8086", "Intel");
        vendorLookup.put("0x1022", "AMD");
        vendorLookup.put("0x0781", "SanDisk");
        vendorLookup.put("0x05AC", "Apple");
        vendorLookup.put("0x046D", "Logitech");
    }

        public void displayUSBInfo () {
            Scanner sc = new Scanner(System.in);
            // SystemInfo provides access to hardware data
            SystemInfo si = new SystemInfo();

            // Get the hardware abstraction layer
            HardwareAbstractionLayer hal = si.getHardware();

            // Get a list of all connected USB devices
            List<UsbDevice> usbDevices = hal.getUsbDevices(true);

            // Check if there are no connected USB devices
            if (usbDevices.isEmpty()) {
               System.out.println("There are currently no USB devices. Therefore no information can be displayed");
            }
            while (true) {
                System.out.println("\n----Main Menu----");
                System.out.println("1. USB name");
                System.out.println("2. Vendor information");
                System.out.println("3. Product information");
                System.out.println("4. Device information");
                System.out.println("0. Exit");
                System.out.print("Enter: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        for (int y = 0; y < usbDevices.size(); y++) {
                            System.out.println("USB " + (y + 1) + " name is: " + usbDevices.get(y).getName());
                        }
                        break;
                    // Submenu
                    case 2:
                        boolean i = true;
                        while (i) {
                            System.out.println("----Vendor Menu----");
                            System.out.println("1. Vendor name");
                            System.out.println("2. Vendor ID");
                            System.out.println("3. Go back to main menu");
                            System.out.print("Enter: ");
                            int h = sc.nextInt();

                            switch (h) {
                                case 1:
                                    for (int w = 0; w < usbDevices.size(); w++) {
                                        String vendorId = usbDevices.get(w).getVendorId();
                                        String vendorName = vendorLookup.getOrDefault(vendorId, "Unknown");
                                        System.out.println("Vendor " + (w + 1) + "'s" + " name is: " + usbDevices.get(w).getVendor() + " (" + vendorName + ")");
                                    }
                                    break;
                                case 2:
                                    for (int k = 0; k < usbDevices.size(); k++) {
                                        // Retrieve the vendor ID for each connected USB device
                                        String vendorId = usbDevices.get(k).getVendorId();
                                        // Get vendor name from lookup map, default to "Unknown" if not found
                                        String vendorName = vendorLookup.getOrDefault(vendorId, "Unknown");
                                        System.out.println("Vendor ID " + (k + 1) + " is: " + vendorId + " (" + vendorName + ")");
                                    }
                                    break;
                                case 3:
                                    i = false;
                                    break;
                                default:
                                    System.out.println("Error, please try again");
                                    break;
                            }
                        }
                        break;
                    case 3:
                        for (int z = 0; z < usbDevices.size(); z++) {
                            System.out.println("Product ID " + (z + 1) + " is: " + usbDevices.get(z).getProductId());
                        }
                        break;
                    // Submenu
                    case 4:
                        boolean f = true;
                        while (f) {
                            System.out.println("----Device information----");
                            System.out.println("1. Serial number");
                            System.out.println("2. Unique device ID");
                            System.out.println("3. Go back to main menu");
                            System.out.print("Enter: ");
                            int pick = sc.nextInt();

                            switch (pick) {
                                case 1:
                                    for (int j = 0; j < usbDevices.size(); j++) {
                                        String serial = usbDevices.get(j).getSerialNumber();
                                        // Handle devices that don’t have serial numbers
                                        if (serial == null || serial.isEmpty()) {
                                            System.out.println("Serial number not available for device " + (j + 1));
                                        } else {
                                            System.out.println("Serial Number " + (j + 1) + " is: " + serial);
                                        }
                                    }


                                    break;
                                case 2:
                                    for (int x = 0; x < usbDevices.size(); x++) {
                                        System.out.println("Unique Device ID " + (x + 1) + " is: " + usbDevices.get(x).getUniqueDeviceId());
                                    }
                                    break;
                                case 3:
                                    f = false;
                                    break;
                                default:
                                    System.out.println("Error, please try again");
                                    break;
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Now exiting this programme, bye :)");
                        return;
                    default:
                        System.out.println("Error, please try again");
                        break;
                }
            }
        }
    public static void main(String[] args) {
            USB obj = new USB();
            obj.displayUSBInfo();
    }
}
