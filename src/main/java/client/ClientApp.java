package client;

import Network.ClientSide.RemoteClientImplementation;
import Network.ServerSide.RemoteServerImplementation;
import client.view.TUI;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * This Class is the launcher of the ClientSide part of <strong>MyShelfie</strong>
 * application. By running this Class a new <strong>MyShelfieClient</strong> will be created.
 */
public class ClientApp {

    /**
     * The root method of the process that has the responsibility of creating and realizing the correct
     * setup of the {@link RemoteClientImplementation RemoteClient}.
     * In detail, the method focuses on checking all the {@link NetworkInterface NetworkInterfaces} of the host
     * to set the correct hostname with whom the {@link java.rmi.registry.Registry RMIRegistry} will be called.
     * The method skips all the virtual {@link NetworkInterface NetworkInterfaces} and the eventual {@link NetworkInterface NetworkInterfaces}
     * that are created on the host by software that provide Virtualization services.
     * This way it assures that the {@link Network.ClientSide.RemoteClientImplementation RemoteClient} will
     * later be reachable by the {@link RemoteServerImplementation RemoteServer}.
     *
     * @param args an Array {@link String String} containing the eventual arguments
     */
    public static void main(String[] args) throws InterruptedException {

        boolean cliSelector = true; // default è true, se vogliamo usare anche gui si può cambiare

        try {
            boolean found = false;
            Enumeration<NetworkInterface> interfaceEnumeration = NetworkInterface.getNetworkInterfaces();

            while(interfaceEnumeration.hasMoreElements() && !found){
                NetworkInterface networkInterface = interfaceEnumeration.nextElement();

                if(!networkInterface.isVirtual() && !networkInterface.isLoopback() && networkInterface.isUp()) {
                    Enumeration<java.net.InetAddress> inetAddresses = networkInterface.getInetAddresses();
                    int count = 0;
                    ArrayList<String> address = new ArrayList<>();
                    java.net.InetAddress inetAddress;
                    while (inetAddresses.hasMoreElements()) {
                        inetAddress = inetAddresses.nextElement();

                        address.add(inetAddress.getHostAddress());
                        if (count == 1) {
                            boolean isIPv4 = true;
                            String[] partedAddress = address.get(0).split("\\.");
                            if (partedAddress.length != 4) {
                                isIPv4 = false;
                            }

                            for (String part : partedAddress) {
                                try {
                                    int value = Integer.parseInt(part);
                                    if (value < 0 || value > 255) {
                                        isIPv4 = false;
                                    }
                                } catch (NumberFormatException e) {
                                    isIPv4 = false;
                                }
                            }
                            if(isIPv4) {
                                System.setProperty("java.rmi.server.hostname", address.get(0));
                            }else {
                                System.setProperty("java.rmi.server.hostname", address.get(1));
                            }
                            System.out.println("Indirizzo IP: " + address.get(0));
                            System.out.println("Indirizzo IP: " + address.get(1));
                            found = true;
                        }

                        count++;
                    }
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        for (String arg : args) {
            if (arg.equals("--cli")) {
                cliSelector = true;
                break;
            }
        }

        if (cliSelector) {
            TUI view = new TUI();
            view.init();
        } else {
            //eventuale gestione apertura GUI
        }
    }
}