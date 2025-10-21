package NetworkTools.Main;



public class Main {
    public static void main(String[] args ) throws Exception {

          try{
              UdpDiscovery.displayCurrentPort();
              CheckHosts.checkHosts("192.168.1");
          } catch (Exception e) {e.printStackTrace();}

      }

    }