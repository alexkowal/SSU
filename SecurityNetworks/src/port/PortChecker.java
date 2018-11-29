package port;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;
import java.util.TreeSet;


 class PortChecker {
    SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");


    static TreeMap<Integer, Date> map = new TreeMap();


    public boolean isOpen(int port) {
        try {
            Date date = new Date();
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", port), 2);
            socket.close();
           // System.out.println(port);
            map.put(port, date);
            // System.out.println(port + " " + df.format(date));
            return true;
        } catch (UnknownHostException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }
}