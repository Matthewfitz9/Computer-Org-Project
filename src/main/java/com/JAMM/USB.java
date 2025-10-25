package com.JAMM;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.UsbDevice;
import java.util.List;
import java.util.Scanner;


public class USB {

        public void displayUSBInfo () {
            Scanner sc = new Scanner(System.in);
            // SystemInfo provides access to hardware data
            SystemInfo si = new SystemInfo();

            // Get the hardware abstraction layer
            HardwareAbstractionLayer hal = si.getHardware();

            // Get a list of all connected USB devices
            List<UsbDevice> usbDevices = hal.getUsbDevices(true);

            while (true) {
                System.out.println("\n----Main Menu----");
                System.out.println("1. USB name");
                System.out.println("2. Vendor information");
                System.out.println("3. Product information");
                System.out.println("4. Device information");
                System.out.println("5. Exit");
                System.out.print("Enter: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("USB 1 name is: " + usbDevices.get(0).getName());
                        System.out.println("USB 2 name is: " + usbDevices.get(1).getName());
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
                                    System.out.println("Vendor 1 is: " + usbDevices.get(0).getVendor());
                                    System.out.println("Vendor 2 is: " + usbDevices.get(1).getVendor());
                                    break;
                                case 2:
                                    System.out.println("Vendor ID 1 is: " + usbDevices.get(0).getVendorId());
                                    System.out.println("Vendor ID 2 is: " + usbDevices.get(1).getVendorId());
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
                        System.out.println("Product ID 1 is: " + usbDevices.get(0).getProductId());
                        System.out.println("Product ID 2 is: " + usbDevices.get(1).getProductId());
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
                                        if (serial == null || serial.isEmpty()) {
                                            System.out.println("Serial number not available for device " + (j + 1));
                                        } else {
                                            System.out.println("Serial Number " + (j + 1) + " is: " + serial);
                                        }
                                    }


                                    break;
                                case 2:
                                    System.out.println("Unique Device ID 1 is: " + usbDevices.get(0).getUniqueDeviceId());
                                    System.out.println("Unique Device ID 2 is: " + usbDevices.get(1).getUniqueDeviceId());
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
                    case 5:
                        System.out.println("Now exiting this programme, bye :)");
                        sc.close();
                        break;
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
