import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.*;

public class Fano {
    static String code = "";

    public void run(FileReader fr) throws IOException {
        ArrayList<Node> nodes = new ArrayList<>();

        HashMap<Character, Integer> m = new HashMap<>();
        String s = "";
        char c;
        while ((c = (char) (fr.read())) != '%')
            s += c;
        System.out.println(s);
        //Считывание документа

        for (int i = 0; i < s.length(); i++) {
            if (m.containsKey(s.charAt(i)))
                m.put(s.charAt(i), m.get(s.charAt(i)) + 1);
            else
                m.put(s.charAt(i), 1);
        }
//        for(Map.Entry<Character,Integer>p : m.entrySet())
//           System.out.println(p.getKey() + " " + p.getValue());
        for (Map.Entry<Character, Integer> p : m.entrySet()) {
            Node n = new Node();
            n.c = p.getKey();
            n.a = p.getValue();
            nodes.add(n);
        }
        //Строим дерево
        Collections.sort(nodes, (o1, o2) -> o1.a > o2.a ? 0 : -1);
        while (nodes.size() != 1) {
            Collections.sort(nodes, (o1, o2) -> o1.a > o2.a ? 0 : -1);

            Node lSon = nodes.get(0);
            nodes.remove(0);
            Node rSon = nodes.get(0);
            nodes.remove(0);

            Node parent = new Node(lSon, rSon);
            nodes.add(parent);
        }

        //Node root = nodes.get(0);
        // print(root, 0);
        //Вывод дерева, если надо.

        Collections.sort(nodes, (o1, o2) -> o1.a > o2.a ? 0 : -1);

        HashMap<Character, String> table1 = new HashMap<>();
        makeTable(nodes.get(0), table1);

        FileWriter fw = new FileWriter("C:\\Users\\Александр\\Desktop\\Учеба\\4 курс\\Compression\\src\\output.txt");
        FileWriter fw1 = new FileWriter("C:\\Users\\Александр\\Desktop\\Учеба\\4 курс\\Compression\\src\\outputbin.txt");

        String result = "";
        for (int i = 0; i < s.length(); i++) {
            result += table1.get(s.charAt(i));
        }
        fw1.write(result);

        while (result.length() % 8 != 0)
            result += '0';
        System.out.println(result);


        int count = 0;
        char buf = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            String temp = table1.get(ch);
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == '1') {
                    buf = (char) (buf | (1) << (7 - count));
                    count++;
                    if (count == 8) {
                        count = 0;
                        fw.write(buf);
                        System.out.println(buf);
                        buf = 0;
                    }
                } else {
                    buf = (char) (buf | (0) << (7 - count));
                    count++;
                    if (count == 8) {
                        count = 0;
                        fw.write(buf);
                        System.out.println(buf);
                        buf = 0;
                    }
                }
            }

        }
        fw.close();
        fw1.close();
        //Ready

        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Александр\\Desktop\\Учеба\\4 курс\\Compression\\src\\output.txt"));
       // String read = br.readLine();

        count = 0;
        char b;
        b = (char)(br.read());
        int bool = b &(1<<(7 - count));
        System.out.println(bool);



     /*   byte[] bytes = read.getBytes();
        System.out.println(bytes[1]);
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes)
        {
            int val = b;
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
        }
        System.out.println(binary);
*/
    }


    //////////////////////////////////////////////////////////////////////////////
    // ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public static byte[] stringToBytes(String str) {
        char[] buffer = str.toCharArray();
        byte[] b = new byte[buffer.length << 1];
        CharBuffer cBuffer = ByteBuffer.wrap(b).asCharBuffer();
        for (int i = 0; i < buffer.length; i++) {
            cBuffer.put(buffer[i]);
        }
        return b;
    }


    void print(Node root, int k) {
        if (root != null) {
            print(root.left, k + 3);

            for (int i = 0; i < k; i++)
                System.out.print(" ");

            if (root.c != 0) {
                System.out.print(root.a + " " + root.c);
                System.out.println();
            } else System.out.println(root.a);
            print(root.right, k + 3);
        }
    }


    void makeTable(Node root, HashMap<Character, String> table) {

        if (root.left != null) {
            code = code + "0";
            makeTable(root.left, table);
        }
        if (root.right != null) {
            code = code + "1";
            makeTable(root.right, table);
        }
        if (root.c != null) {
            for (Map.Entry<Character, String> q : table.entrySet())
                if (q.getKey() == root.c) {
                    q.setValue(code);
                    break;
                }
            table.put(root.c, code);
        }
        if (code.length() - 1 < 0)
            return;
        else
            code = code.substring(0, code.length() - 1);

    }


    public String stringToBinary(String s) {
        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
            System.out.println();
        return s;
    }

    public static void main(String[] args) throws IOException {
        Fano fano = new Fano();
        FileReader fr = new FileReader("C:\\Users\\Александр\\Desktop\\Учеба\\4 курс\\Compression\\src\\input.txt");
        fano.run(fr);
    }
}
