package fourth;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PSQF6
{
    static BufferedReader in;
    static PrintWriter out;

    public static void main(String[] args) throws IOException
    {
        new PSQF6().run();
    }

    void run() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();

        int[] s = new int[m];
        for (int i = 0; i < m; i++)
        {
            s[i] = sc.nextInt();
        }

        int[] a = new int[s.length];
        int[] b = new int[s.length - 1];

        for (int i = 0; i < m; i++)
        {
            a[m - i - 1] = s[i];

            if (m - i - 2 >= 0)
                b[m - i - 2] = s[i] * (m - i - 1);
        }

        int[] tmp = a.clone();
        int[] r = new GEAP6().geap(a.clone(), m, b, m - 1);
        int degR = r.length;
        int[] t = PDF(a.clone(), r);
        int j = 1;

        ArrayList<int[]> ans = new ArrayList<>();

        while (true)
        {
            if (deg(r) == 1)
            {
                int e = j;
                ans.add(t);
                break;
            }

            int[] v = new GEAP6().geap(r.clone(), deg(r), t, deg(t));
            ans.add(PDF(t.clone(), v));
            r = PDF(r.clone(), v);
            t = v.clone(); j++;
        }

        for (int i = 0; i < ans.size(); i++)
        {
            int deg = deg(ans.get(i));
            System.out.print(i+1 + ": ");
            for (int k = 0; k < deg; k++)
            {
                System.out.print( ans.get(i)[k] + " ");
            }
            System.out.println();
        }
    }

    int deg(int[] a)
    {
        int len = 0;

        for (int i = 0; i < a.length; i++)
        {
            if (a[i] != 0)
                len = i;
        }
        return len + 1;
    }

    int[] PDF(int[] a, int[] b)
    {
        int m = a.length;
        int n = b.length;
        int[] q = new int[m];

        for (int k = m - n; k >= 0; k--)
        {
            q[k] = a[n + k - 1] / b[n - 1];

            for (int j = n + k - 1; j >= k; j--)
            {
                a[j] = a[j] - q[k] * b[j - k];
            }
        }
        return q;
    }
}
