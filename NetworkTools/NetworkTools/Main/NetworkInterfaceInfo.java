package NetworkTools.Main;
import javax.swing.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Collections;
import java.util.Enumeration;


public class NetworkInterfaceInfo {
    //Globals needed to grab info. N
    //interface count could use a min/max range

    public NetworkInterface NETINT;
    Enumeration<NetworkInterface> ALL_INTERFACES;
    Enumeration<InetAddress> ALL_ADDRESSES;
    public int InterfaceCount = 0;
    public int InterfaceNumber = 0;

    // GUI STUFF
    private JFrame MainWindow = new JFrame("Mike D 2025");
    private JLabel L_Title = new JLabel("Network Interface");
    private JLabel L_TotalInterfaces = new JLabel("Total number of interfaces on this host: ");
    private JLabel L_TotalInterfacesBox = new JLabel();
    private JLabel L_InterfaceNumber = new JLabel("Interface Number: ");
    private JLabel L_InterfaceNumber_Box = new JLabel();
    private JLabel L_InterfaceName = new JLabel("Interface Name: ");
    private JLabel L_InterfaceName_Box = new JLabel();
    private JLabel L_Interface_ID = new JLabel("Interface ID: ");
    private JLabel L_Interface_ID_Box = new JLabel();
    private JLabel L_MAC = new JLabel("MAC Address: ");
    private JLabel L_MAC_Box = new JLabel();
    private JLabel L_IP = new JLabel("IP Address: ");
    private JLabel L_IP_Box = new JLabel();
    private JLabel L_HostName = new JLabel("Host Name: ");
    private JLabel L_HostName_Box = new JLabel();
    private JLabel L_MTU = new JLabel("MTU: ");
    private JLabel L_MTU_Box = new JLabel();
    private JLabel L_Status = new JLabel("Status: ");
    private JLabel L_Status_Box = new JLabel();
    private JLabel L_PointToPoint = new JLabel("Point to Point: ");
    private JLabel L_PointToPoint_Box = new JLabel();
    private JLabel L_Multicast  = new JLabel("Multicast Address: ");
    private JLabel L_Multicast_Box = new JLabel();
    private JLabel L_Loopback = new JLabel("Loopback Address: ");
    private JLabel L_Loopback_Box = new JLabel();
    private JLabel L_Virtual = new JLabel("Virtual Address: ");
    private JLabel L_Virtual_Box = new JLabel();
    private JButton B_NEXT = new JButton("Next");
    private JButton B_PREVIOUS = new JButton("Previous");
    private JButton B_QUIT = new JButton("Quit");

    // these are for testing
    private JButton B_TEST = new JButton("Test");
    private JLabel L_Test = new JLabel("Test Address: ");
    private JLabel L_Test_Box = new JLabel();

    // HERE IS THE MAIN METHOD
    public static void main(String[] args) {

        new NetworkInterfaceInfo();
    }

    public NetworkInterfaceInfo() {
        buildGUI();
        getInterfaces();
        displayInterfaceInfo(InterfaceNumber);
        System.out.println(NETINT);

    }

    private void getInterfaces() {
        try {
            //get all interfaces in the list and store it
            ALL_INTERFACES = NetworkInterface.getNetworkInterfaces();
            InterfaceCount = Collections.list(ALL_INTERFACES).size();
        } catch (SocketException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    //get all network interfaces, enumerate list, then store to ALL_INTERFACES
    //then, convert that list. grab the interface at index INT_NUM and stores in NETINT
    //if ALL_INTERFACES Null -- will throw null pointer exception
    //if INT_NUM out of range we'll get OutOfBoundsException

    public void displayInterfaceInfo(int INT_NUM) {
        try {
            ALL_INTERFACES = NetworkInterface.getNetworkInterfaces();
            NETINT = Collections.list(ALL_INTERFACES).get(INT_NUM);

            //get number, convert to string, then put in the box
            L_TotalInterfacesBox.setText(Integer.toString(InterfaceCount));
            L_InterfaceNumber_Box.setText(Integer.toString(INT_NUM+1));
            L_InterfaceName_Box.setText(NETINT.getDisplayName());
            L_Interface_ID_Box.setText(NETINT.getName());
            L_MAC_Box.setText(Arrays.toString(NETINT.getHardwareAddress()));

            ALL_ADDRESSES = NETINT.getInetAddresses();
            for (InetAddress X : Collections.list(ALL_ADDRESSES)) {
                L_IP_Box.setText(X.getHostAddress());
                L_HostName.setText(X.getHostName());
                L_MTU_Box.setText(Integer.toString(NETINT.getMTU()));
            }

            String STATUS ;
            if (NETINT.isUp()) {
                STATUS = "Host is UP";
            } else {
                STATUS = "Down!";
            }
            L_Status_Box.setText(STATUS);

            String POINTTOPOINT;
            if(NETINT.isPointToPoint()) {
                POINTTOPOINT = "Yes!";
            } else {
                POINTTOPOINT = "No!";
            }
            L_PointToPoint_Box.setText(POINTTOPOINT);

            String MULTICAST;
            if (NETINT.supportsMulticast()) {
                MULTICAST = "Yes! ";
            } else {
                MULTICAST = "No! ";
            }
            L_Loopback_Box.setText(MULTICAST);

            String LOOPBACK;
            if (NETINT.isLoopback()) {
                LOOPBACK = "Yes!";
            } else {
                LOOPBACK = "No!";
            }
            L_Loopback_Box.setText(LOOPBACK);

            String ISVIRTUAL;
            if (NETINT.isVirtual()) {
                ISVIRTUAL = "Yes!";
            } else {
                ISVIRTUAL = "No!";
            }
            L_Virtual_Box.setText(ISVIRTUAL);

        } catch (SocketException e) {
            System.out.println("Oh no");
        }
    }

    public void buildGUI() {
        MainWindow.setDefaultCloseOperation(
                javax.swing.WindowConstants.EXIT_ON_CLOSE);
        MainWindow.setSize(500,500);
        MainWindow.setLayout(null);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_Title);
        L_Title.setBounds(112, 0, 172, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_TotalInterfaces);
        L_TotalInterfaces.setBounds(12, 23,181, 16);

        L_TotalInterfacesBox.setForeground(new java.awt.Color(255, 0, 0));
        L_TotalInterfacesBox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_TotalInterfacesBox.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_TotalInterfacesBox);
        L_TotalInterfacesBox.setBounds(224, 23,136, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_InterfaceNumber);
        L_InterfaceNumber.setBounds(12, 46, 69,16);

        L_InterfaceNumber_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_InterfaceNumber_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_InterfaceNumber_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_InterfaceNumber_Box);
        L_InterfaceNumber_Box.setBounds(112, 46, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_InterfaceName);
        L_InterfaceName.setBounds(12, 69, 93,16);

        L_InterfaceName_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_InterfaceName_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_InterfaceName_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_InterfaceName_Box);
        L_InterfaceName_Box.setBounds(112, 69, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_Interface_ID);
        L_Interface_ID.setBounds(12, 92, 72,16);

        L_Interface_ID_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_Interface_ID_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Interface_ID_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_Interface_ID_Box);
        L_Interface_ID_Box.setBounds(112, 92, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_MAC);
        L_MAC.setBounds(12, 115, 81,16);

        L_MAC_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_MAC_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_MAC_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_MAC_Box);
        L_MAC_Box.setBounds(112, 115, 248, 16);
//---------------------------------------------------------------------------------------------

        MainWindow.add(L_IP);
        L_IP.setBounds(12, 138, 82,16);

        L_IP_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_IP_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_IP_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_IP_Box);
        L_IP_Box.setBounds(112, 138, 248, 16);
//---------------------------------------------------------------------------------------------

        MainWindow.add(L_HostName);
        L_HostName.setBounds(12, 161, 82,16);

        L_HostName_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_HostName_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_HostName_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_HostName_Box);
        L_HostName_Box.setBounds(112, 162, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_MTU);
        L_MTU.setBounds(12, 184, 31,16);

        L_MTU_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_MTU_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_MTU_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_MTU_Box);
        L_MTU_Box.setBounds(112, 184, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_Status);
        L_Status.setBounds(15, 207, 41,16);

        L_Status_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_Status_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Status_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_Status_Box);
        L_Status_Box.setBounds(112, 207, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_PointToPoint);
        L_PointToPoint.setBounds(12, 230, 100,16);

        L_PointToPoint_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_PointToPoint_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_PointToPoint_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_PointToPoint_Box);
        L_PointToPoint_Box.setBounds(112, 230, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_Multicast);
        L_Multicast.setBounds(12, 253, 100,16);

        L_Multicast_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_Multicast_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Multicast_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_Multicast_Box);
        L_Multicast_Box.setBounds(112, 253, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_Loopback);
        L_Loopback.setBounds(12, 276, 100,16);

        L_Loopback_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_Loopback_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Loopback_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_Loopback_Box);
        L_Loopback_Box.setBounds(112, 276, 248, 16);
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_Virtual);
        L_Virtual.setBounds(12, 299, 100,16);

        L_Virtual_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_Virtual_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Virtual_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_Virtual_Box);
        L_Virtual_Box.setBounds(112, 299, 248, 16);
//---------------------------------------------------------------------------------------------
        B_PREVIOUS.setBackground(new java.awt.Color(0, 0, 0));
        B_PREVIOUS.setForeground(new java.awt.Color(0, 0, 0));
        B_PREVIOUS.setText("PREV");
        B_PREVIOUS.setToolTipText("Previous");
        MainWindow.add(B_PREVIOUS);
        B_PREVIOUS.setBounds(0, 322, 65, 25);
//---------------------------------------------------------------------------------------------
        B_NEXT.setBackground(new java.awt.Color(0, 0, 0));
        B_NEXT.setForeground(new java.awt.Color(0, 0, 0));
        B_NEXT.setText("NEXT");
        B_NEXT.setToolTipText("Next");
        MainWindow.add(B_NEXT);
        B_NEXT.setBounds(150, 322, 63, 25);
//---------------------------------------------------------------------------------------------
        B_QUIT.setBackground(new java.awt.Color(0, 0, 0));
        B_QUIT.setForeground(new java.awt.Color(0, 0, 0));
        B_QUIT.setText("QUIT");
        B_QUIT.setToolTipText("Quit Program");
        MainWindow.add(B_QUIT);
        B_QUIT.setBounds(297, 322, 63, 25);

//---------------------------------------------------------------------------------------------
        // TEST TEST TEST ///
//---------------------------------------------------------------------------------------------
        MainWindow.add(L_Test);
        L_Test.setBounds(12, 400, 100,16);

        L_Test_Box.setForeground(new java.awt.Color(255, 0, 0));
        L_Test_Box.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        L_Test_Box.setBorder(javax.swing.BorderFactory.createLineBorder(
                new java.awt.Color(0, 0, 0)));

        MainWindow.add(L_Test_Box);
        L_Test_Box.setBounds(112, 400, 248, 16);

        B_TEST.setBackground(new java.awt.Color(0, 0, 0));
        B_TEST.setForeground(new java.awt.Color(0, 0, 0));
        B_TEST.setText("TEST");
        B_TEST.setToolTipText("Test");
        MainWindow.add(B_TEST);
        B_TEST.setBounds(400, 322, 63, 25);

        addListeners();
        MainWindow.setVisible(true);
    }
    /*
    This is used to add events to specific buttons
    listens for event then performs event
    */

    public void addListeners() {
        B_PREVIOUS.addActionListener(evt -> actionBPrevious ());

        B_NEXT.addActionListener(evt -> actionBNext());

        B_QUIT.addActionListener(evt -> actionBQuit());

        B_TEST.addActionListener(evt -> {actionBTest();});
    }

    public void actionBPrevious() {
        if (InterfaceNumber > 0) {
            displayInterfaceInfo(InterfaceNumber);
        }
    }

    public void actionBNext() {
        if (InterfaceNumber < (InterfaceCount - 1)) {
            InterfaceNumber++;
            displayInterfaceInfo(InterfaceNumber);
        }
    }

    public void actionBQuit() {
        MainWindow.setVisible(false);
        MainWindow.dispose();
    }

    public void actionBTest() {
       String testLine =  NETINT.getDisplayName();
        L_Test_Box.setText(testLine);
//        L_Test_Box.setVisible(false);
        int getName = NETINT.getIndex();
    }

























}
