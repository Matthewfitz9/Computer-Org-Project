package com.JAMM; 

import java.util.List;

import oshi.hardware.GraphicsCard;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.SoundCard;

public class Pci {
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
}