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

        for (NetworkIF networkDevice : networkDevices) {
        }

        for (HWDiskStore storageDevice : storageDevices) {
            System.out.printf("%n%n%s%n", storageDevice.getModel());
            System.out.println(storageDevice.getSize());
            System.out.println(storageDevice.getSerial());

        }

        for (SoundCard soundCard : soundCards) {

        }

    }

    public static void displayLiveDiskInfo(HardwareAbstractionLayer inHal) {

    }
         
    }
}