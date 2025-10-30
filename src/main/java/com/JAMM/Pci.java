package com.JAMM;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;
import oshi.hardware.SoundCard;

public class Pci {

    // Lists to store detected PCI devices
    private static List<GraphicsCard> graphicsCardList;
    private static List<NetworkIF> networkInterfaceList;
    private static List<SoundCard> soundCardList;

    public static void pciMenu() {
        // Create SystemInfo object to access hardware information
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        // Retrieve hardware devices
        graphicsCardList = hal.getGraphicsCards();

        // gets only valid network interface lists
        networkInterfaceList = new ArrayList<>();
        for (NetworkIF net : hal.getNetworkIFs()) {
            if (!net.isKnownVmMacAddr() && !net.getDisplayName().toLowerCase().contains("loopback")) {
                networkInterfaceList.add(net);
            }
        }

        soundCardList = hal.getSoundCards();

        // main menu loop
        while (true) {
            System.out.println("\n=== PCI Device Information Menu ===");
            System.out.println("Select the type of PCI device:");
            System.out.println("1. Graphics Cards (GPU)");
            System.out.println("2. Network Interfaces");
            System.out.println("3. Sound Cards");
            System.out.println("0. Return to Main Menu");
            System.out.print("\nEnter your choice: ");
            String choice = Main.scanner.nextLine().trim().toLowerCase();

            if (choice.contains("1") ||
                choice.contains("graphic") ||
                choice.contains("gpu")) {
                selectGraphicsCard();

            } else if (choice.contains("2") ||
                       choice.contains("network") ||
                       choice.contains("interface")) {
                selectNetworkInterface();

            } else if (choice.contains("3") ||
                       choice.contains("sound")) {
                selectSoundCard();

            } else if (choice.contains("0") ||
                       choice.contains("exit") ||
                       choice.contains("quit") ||
                       choice.contains("return") ||
                       choice.contains("back")) {
                System.out.println("Returning to main menu...\n");
                return;

            } else {
                System.out.println("\n[ERROR] Invalid choice. Please enter a number or keyword.");
            }
        }
    }


    // Lets the user select which graphics card to inspect.
    private static void selectGraphicsCard() {
        if (graphicsCardList.isEmpty()) {
            System.out.println("\nNo Graphics Cards found on this system.");
            return;
        }

        // selecting GPU loop
        while (true) {
            System.out.println("\n--- Select Graphics Card ---");
            for (int i = 0; i < graphicsCardList.size(); i++) {
                System.out.println((i + 1) + ". " + graphicsCardList.get(i).getName());
            }
            System.out.println("0. Back to PCI Menu");
            System.out.print("\nEnter choice (number or 'exit'): ");
            String choice = Main.scanner.nextLine().trim().toLowerCase(); // Read as string

            // Check for exit condition first
            if (choice.contains("0") ||
                choice.contains("exit") ||
                choice.contains("quit") ||
                choice.contains("back")) {
                return; // Go back to pciMenu

            } else {
                // Try to parse as a number
                try {
                    int index = Integer.parseInt(choice) - 1; // Convert string to int index
                    if (index >= 0 && index < graphicsCardList.size()) {
                        showGraphicsInfo(graphicsCardList.get(index)); // Show info for selected card
                        // Stay in this menu after showing info
                    } else {
                        System.out.println("\n[ERROR] Invalid number.");
                    }
                } catch (NumberFormatException e) {
                    // If parsing fails, it wasn't a valid number or exit keyword
                    System.out.println("\n[ERROR] Invalid input. Please enter a number or 'exit'.");
                }
            }
        }
    }

    private static void selectNetworkInterface() {
        if (networkInterfaceList.isEmpty()) {
            System.out.println("\nNo relevant Network Interfaces found.");
            return;
        }

        while (true) {
            System.out.println("\n--- Select Network Interface ---");
            for (int i = 0; i < networkInterfaceList.size(); i++) {
                System.out.println((i + 1) + ". " + networkInterfaceList.get(i).getDisplayName());
            }
            System.out.println("0. Back to PCI Menu");
            System.out.print("\nEnter choice (number or 'exit'): ");
            String choice = Main.scanner.nextLine().trim().toLowerCase(); // Read as string

            // Check for exit condition first
            if (choice.contains("0") ||
                choice.contains("exit") ||
                choice.contains("quit") ||
                choice.contains("back")) {
                return; // Go back to pciMenu

            } else {
                 // Try to parse as a number
                try {
                    int index = Integer.parseInt(choice) - 1; // Convert string to int index
                    if (index >= 0 && index < networkInterfaceList.size()) {
                        showNetworkInfo(networkInterfaceList.get(index)); // Show info menu for selected interface
                         // Stay in this menu after showing info
                    } else {
                        System.out.println("\n[ERROR] Invalid number.");
                    }
                } catch (NumberFormatException e) {
                    // If parsing fails, it wasn't a valid number or exit keyword
                    System.out.println("\n[ERROR] Invalid input. Please enter a number or 'exit'.");
                }
            }
        }
    }

    private static void selectSoundCard() {
        if (soundCardList.isEmpty()) {
            System.out.println("\nNo Sound Cards found.");
            return;
        }
        while (true) {
            System.out.println("\n--- Select Sound Card ---");
            for (int i = 0; i < soundCardList.size(); i++) {
                System.out.println((i + 1) + ". " + soundCardList.get(i).getName());
            }
            System.out.println("0. Back to PCI Menu");
            System.out.print("\nEnter choice (number or 'exit'): ");
            String choice = Main.scanner.nextLine().trim().toLowerCase(); // Read as string

            // Check for exit condition first
            if (choice.contains("0") ||
                choice.contains("exit") ||
                choice.contains("quit") ||
                choice.contains("back")) {
                return; // Go back to pciMenu

            } else {
                 // Try to parse as a number
                try {
                    int index = Integer.parseInt(choice) - 1; // Convert string to int index
                    if (index >= 0 && index < soundCardList.size()) {
                        showSoundInfo(soundCardList.get(index)); // Show info for selected card
                         // Stay in this menu after showing info
                    } else {
                        System.out.println("\n[ERROR] Invalid number.");
                    }
                } catch (NumberFormatException e) {
                     // If parsing fails, it wasn't a valid number or exit keyword
                    System.out.println("\n[ERROR] Invalid input. Please enter a number or 'exit'.");
                }
            }
        }
    }

    // Information Display Menus

    private static void showGraphicsInfo(GraphicsCard card) {
        System.out.println("\n=== Graphics Card Info: " + card.getName() + " ===");
        System.out.println("  Vendor: " + card.getVendor());
        System.out.println("  Device ID: " + card.getDeviceId());
        // Use Math.pow for GiB conversion for consistency
        System.out.printf("  VRAM: %.2f GiB\n", card.getVRam() / Math.pow(1024, 3));
        System.out.println("  Version Info: " + card.getVersionInfo());
    }

    private static void showNetworkInfo(NetworkIF net) {
        while (true) {
            System.out.println("\n=== Network Interface Info: " + net.getDisplayName() + " ===");
            System.out.println("1. General Information");
            System.out.println("2. Live Download Speed Graph");
            System.out.println("3. Live Upload Speed Graph");
            System.out.println("0. Back to Network Selection");
            System.out.print("\nEnter choice: ");
            String choice = Main.scanner.nextLine().trim().toLowerCase();

            if (choice.equals("1") ||
                choice.contains("general")) {
                showNetworkGeneralInfo(net);

            } else if (choice.equals("2") ||
                       choice.contains("download")) {
                showNetworkSpeedGraph(net, true); // true for download

            } else if (choice.equals("3") ||
                       choice.contains("upload")) {
                showNetworkSpeedGraph(net, false); // false for upload

            } else if (choice.equals("0") ||
                       choice.contains("back") ||
                       choice.contains("exit") ||
                       choice.contains("quit")) {
                return; // Go back to the selectNetworkInterface menu

            } else {
                System.out.println("\n[ERROR] Invalid choice.");
            }
        }
    }

    private static void showSoundInfo(SoundCard card) {
        System.out.println("\n=== Sound Card Info: " + card.getName() + " ===");
        System.out.println("  Codec: " + card.getCodec());
        System.out.println("  Driver Version: " + card.getDriverVersion());
    }

    // Helper Info Display Functions

    private static void showNetworkGeneralInfo(NetworkIF net) {
        System.out.println("\n== General Network Info ==\n");
        System.out.println("  Display Name: " + net.getDisplayName());
        System.out.println("  Internal Name: " + net.getName());
        System.out.println("  MAC Address: " + net.getMacaddr());

        double speedMbps = net.getSpeed() / 1_000_000.0;
        System.out.printf("  Link Speed: %.0f Mbps\n", speedMbps);
        System.out.println("  Status: " + net.getIfOperStatus());

        System.out.print("  IPv4 Addresses: ");
        if (net.getIPv4addr().length > 0) {
            System.out.println(String.join(", ", net.getIPv4addr())); // Cleaner output
        } else {
            System.out.println("N/A");
        }

        System.out.print("  IPv6 Addresses: ");
        if (net.getIPv6addr().length > 0) {
             System.out.println(String.join(", ", net.getIPv6addr())); // Cleaner output
        } else {
            System.out.println("N/A");
        }
    }

    // Integrated Network Graph Code

    private static final double MAX_SPEED_MBPS = 1000.0;
    private static final double MAX_SPEED_BPS = MAX_SPEED_MBPS * 1000.0 * 1000.0 / 8.0;

    private static void showNetworkSpeedGraph(NetworkIF net, boolean isDownload) {

        final int graphWidth = 50, graphHeight = 10;
        double[] netHistory = new double[graphWidth];
        String graphTitle = isDownload ? "Download" : "Upload";

        // Initial byte and timestamp values
        long prevBytes = isDownload ? net.getBytesRecv() : net.getBytesSent();
        long prevTimestamp = net.getTimeStamp();
        double currentSpeedMbps = 0;

        // loop until user presses enter
        while (true) {
            // Refresh NIC data
            if (!net.updateAttributes()) {
                System.out.println("\nFailed to update network stats. Returning...");
                System.out.println("Press Enter to continue...");
                try { Main.scanner.nextLine(); } catch (Exception e) {}
                return;
            }

            long newBytes = isDownload ? net.getBytesRecv() : net.getBytesSent();
            long newTimestamp = net.getTimeStamp();

            // change to bytes per second
            double totalMs = newTimestamp - prevTimestamp;
            double speedBps = (totalMs > 0) ? (newBytes - prevBytes) / (totalMs / 1000.0) : 0;

            double load = (speedBps / MAX_SPEED_BPS) * 100.0;
            load = Math.min(100.0, Math.max(0.0, load));

            // change to bytes per second
            currentSpeedMbps = (speedBps * 8) / 1000000.0;

            // Shift old data left, add new point to history
            System.arraycopy(netHistory, 1, netHistory, 0, graphWidth - 1);
            netHistory[graphWidth - 1] = load;

            // clear screen
            System.out.print("\033[H\033[2J");
            System.out.flush();
            // Draw graph
            System.out.println("=".repeat(graphWidth));
            System.out.println("Network " + graphTitle + " Speed Graph: " + net.getDisplayName());
            System.out.println("-".repeat(graphWidth));

            for (int h = graphHeight - 1; h >= 0; h--) {
                double threshold = ((double) h / (graphHeight - 1)) * 100.0;
                for (int x = 0; x < graphWidth; x++)
                    System.out.print(netHistory[x] >= threshold ? "█" : " ");
                System.out.println();
            }
            System.out.println("_".repeat(graphWidth));
            System.out.printf("%-10s: %.2f Mbps\n\n", graphTitle, currentSpeedMbps);
            System.out.println("=".repeat(graphWidth));
            System.out.println("\nPress Enter to return to menu...");

            prevBytes = newBytes;
            prevTimestamp = newTimestamp;

            try {
                if (System.in.available() > 0) {
                    System.in.read(new byte[System.in.available()]);
                    break;
                }
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}