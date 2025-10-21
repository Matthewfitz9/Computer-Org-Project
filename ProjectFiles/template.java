/*
 *  Example class containing methods to read and display CPU, PCI and USB information
 *
 *  Copyright (c) 2024 Mark Burkley (mark.burkley@ul.ie)
 *	
 *	File version Artem Bosyi
 */
 
 
import java.util.Scanner;
public class template 
{
   public static void showPCI(pciInfo pci) {
    System.out.println("\nThis machine has " + pci.busCount() + " PCI buses");
    for (int i = 0; i < pci.busCount(); i++) {
        System.out.println("Bus " + i + " has " + pci.deviceCount(i) + " devices");
        for (int j = 0; j < 32; j++) {
            if (pci.functionCount(i, j) > 0) {
                System.out.println("Bus " + i + " device " + j + " has " + pci.functionCount(i, j) + " functions");
                for (int k = 0; k < 8; k++) {
                    if (pci.functionPresent(i, j, k) > 0) {
                        System.out.println("Bus " + i + " device " + j + " function " + k +
                            " has vendor " + String.format("0x%04X", pci.vendorID(i, j, k)) +
								" and product " + String.format("0x%04X", pci.productID(i, j, k)));
						}
					}
				}
			}
		}
	}

	public static void showUSB(usbInfo usb) {
		System.out.println("\nThis machine has " + usb.busCount() + " USB buses");
		for (int i = 1; i <= usb.busCount(); i++) {
			System.out.println("Bus " + i + " has " + usb.deviceCount(i) + " devices");
			for (int j = 1; j <= usb.deviceCount(i); j++) {
				System.out.println("Bus " + i + " device " + j +
					" has vendor " + String.format("0x%04X", usb.vendorID(i, j)) +
					" and product " + String.format("0x%04X", usb.productID(i, j)));
			}
		}
	}

	public static void showCPU(cpuInfo cpu) {
        System.out.println("CPU: " + cpu.getModel());
        System.out.println("Socket: " + cpu.socketCount() + ", Cores per socket: " + cpu.coresPerSocket());
        System.out.println("L1 cache (data/instruction): " + cpu.l1dCacheSize() + "/" + cpu.l1iCacheSize());
        System.out.println("L2 cache: " + cpu.l2CacheSize());
        System.out.println("L3 cache: " + cpu.l3CacheSize());
        // Можно добавить вычисление загрузки CPU
    }
	
    public static void showDisk(diskInfo disk) {
    for (int i = 0; i < disk.diskCount(); i++) {
			System.out.println("Disk " + disk.getName(i) + " has " +
				disk.getTotal(i) + " blocks, of which " +
				disk.getUsed(i) + " are used");
		}
	}

    public static void showMem(memInfo mem) {
		System.out.println("Total memory: " + mem.getTotal() + " units, used: " + mem.getUsed() + " units");
	}

    public static void main(String[] args)
    {
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        cpuInfo cpu = new cpuInfo();
		pciInfo pci = new pciInfo();
        usbInfo usb = new usbInfo();
        diskInfo disk = new diskInfo();
        memInfo mem = new memInfo();
		Scanner scanner = new Scanner(System.in);

		 while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1 - Show CPU information");
            System.out.println("2 - Show information about PCI devices");
            System.out.println("3 - Show information about USB devices");
            System.out.println("4 - Show disk information");
            System.out.println("5 - Show memory information");
            System.out.println("0 - Exit");
            System.out.print("Your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
				case 1:
					cpu.read(0);
					showCPU(cpu);
					break;
				case 2:
					pci.read();
					showPCI(pci);
					break;
				case 3:
					usb.read();
					showUSB(usb);
					break;
				case 4:
					disk.read();
					showDisk(disk);
					break;
				case 5:
					mem.read();
					showMem(mem);
					break;
				case 0:
					System.out.println("Exiting the program.");
					scanner.close();
					return;
				default:
					System.out.println("Incorrect choice, please try again.");
			}
        }
    }
}

