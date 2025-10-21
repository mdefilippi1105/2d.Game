package NetworkTools.Main;
import java.io.IOException;
import java.net.InetAddress;


public class CheckHosts {

    public static void checkHosts(String subnet) throws IOException {

        int timeout =2000;

        for (int i = 1; i < 255; i++ ) {
            String host = subnet + "." + i;
            if (InetAddress.getByName(host).isReachable(timeout)){
                System.out.println(host + " is reachable");

            } else {
                System.out.println(host + " is not reachable");
            }

        }
    }
}


