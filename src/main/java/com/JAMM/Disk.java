package com.JAMM;

import java.util.Scanner;
import oshi.SystemInfo;
import oshi.hardware.*;
import java.util.List;

public class Disk {
    public void displayDiscInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        List<HWDiskStore> diskStore = hal.getDiskStores();
        for (HWDiskStore disk : diskStore) {
            System.out.println("\n----Disk Information----");
            System.out.println("Name: " + disk.getName());
            System.out.println("Model: " + disk.getModel());
            System.out.println("Partition info: " + disk.getPartitions());
            System.out.println("Number of bytes read by the disk: " + disk.getReadBytes());
            System.out.println("Size: " + disk.getSize());
            System.out.println("Serial of disk: " + disk.getSerial());
        }
    }
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            Disk info = new Disk();
            info.displayDiscInfo();
            System.out.println("Enter area you wish to know about");
            String option = sc.nextLine();
        }
}
