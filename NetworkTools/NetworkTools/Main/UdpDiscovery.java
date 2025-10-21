package NetworkTools.Main;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpDiscovery {
    private static DatagramSocket socket;
    private static int localPort;

    public static int start() throws SocketException {
        socket = new DatagramSocket(0);
        localPort = socket.getLocalPort();
        return localPort;
    }

    public static void stop() {
    // make sure actually points to a socket and make sure socket not already closed
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }

    public static void displayCurrentPort() {
        try{
            int port =  UdpDiscovery.start();
            UdpDiscovery.start();
            System.out.println(" UDP ON PORT: " + port);
            UdpDiscovery.stop();

        } catch (Exception e) {e.printStackTrace();}
    }
}
