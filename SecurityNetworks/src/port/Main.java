package port;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main implements Runnable {
    public static int j = 0;
    public static HashSet<Integer> a = new HashSet<>();
    public static PortChecker portChecker = new PortChecker();

    public static void main(String[] args) throws IOException, InterruptedException {
        Main threads = new Main();
        Thread[] t = new Thread[50];

        for (int i = 0; i < t.length; i++)
            t[i] = new Thread(threads, "Thread " + i);

        for (int i = 0; i < t.length; i++)
            t[i].start();


        for (int i = 0; i < t.length; i++) {
            t[i].join();
        }
        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
        System.out.println(PortChecker.map.size());
        for (Map.Entry<Integer, Date> a : PortChecker.map.entrySet()) {
            System.out.println(a.getKey() + " " + df.format(a.getValue()));
        }
/*        System.out.println("Enter port to kill");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int mainPort = Integer.parseInt(reader.readLine());
        int PID = 0;
        String netstatCommand = "netstat -ano ";
        System.out.println(netstatCommand);

        Process netStatProcess = Runtime.getRuntime().exec(netstatCommand);
        InputStream in = netStatProcess.getInputStream();

        ArrayList ar = new ArrayList();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String l;
        while ((l = br.readLine()) != null) {
            ar.add(l);
        }
        //Считали результат netstat

        for (int i = 0; i < ar.size(); i++) {
            String s = ar.get(i).toString();
            if (s.contains(":" + String.valueOf(mainPort))) {
                System.out.println(s);
                String[] mas = s.split(" ");
                System.out.println(mas[mas.length - 1]);
                PID = Integer.valueOf(mas[mas.length - 1]);

                String commandToKill = "taskkill /t /f /pid " + PID;
                Process taskKill = Runtime.getRuntime().exec(commandToKill);

            }

        }
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", mainPort), 100);
        }catch(Exception e ){e.printStackTrace();
        }
        System.out.println("Ready");
*/
    }

    //netsh advfirewall firewall add rule dir=in action=block protocol=TCP localport=135 name="Block_TCP-135"
    @Override
    public void run() {

        while (j <= 65535) {
            if (portChecker.isOpen(j))
                a.add(j);
            synchronized (this) {
                double a = Math.random();
                if (a >= 0.99)
                    j = Math.max(0, j - 10);
                j++;
            }
        }
    }
}