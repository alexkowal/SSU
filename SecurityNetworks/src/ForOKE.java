import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForOKE {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter port to kill");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int mainPort = Integer.parseInt(reader.readLine());
        int PID = 0;
        String netstatCommand = "netstat -ano ";
       // System.out.println(netstatCommand);

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
                String[] mas = s.split(" ");
                System.out.println("PID = " + mas[mas.length - 1]);
                if (mas[mas.length - 2] == "ESTABLISHED") {
                    PID = Integer.valueOf(mas[mas.length - 1]);
                    String commandToKill = "taskkill /t /f /pid " + PID;
                    Process taskKill = Runtime.getRuntime().exec(commandToKill);
                    System.out.println("Ready");

                }
            }
        }

        /*try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("localhost", mainPort), 2);
            socket.getChannel();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}