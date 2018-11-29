package fourth;

import java.io.*;
import java.util.Scanner;

public class GEAP2
{
    static BufferedReader in;
    static PrintWriter out;
    Scanner sc;

    public static void main(String[] args) throws IOException
    {
        new GEAP2().run();
    }

    int[] GEAP(int[] a, int m, int[] b, int n)
    {
        int[] res;
        int conta = Math.max(1, cont(a));
        int contb = Math.max(1, cont(b));
        int c = NOD(conta, contb);
        a = primetive(a, conta);

        if (a[m - 1] < 0)
        {
            a = primetive(a, -1);
        }

        b = primetive(b, contb);

        if (b[n - 1] < 0)
        {
            b = primetive(b, -1);
        }

        boolean flag = true;

        while (flag)
        {
            int st = 1;

            for (int i = 0; i < m - n + 1; i++)
            {
                st *= b[n - 1];
            }

            for (int i = 0; i < m; i++)
            {
                a[i] *= st;
            }

            int[] q = new int[m];

            for (int k = m - n; k >= 0; k--)
            {
                q[k] = a[n + k - 1] / b[n - 1];

                for (int j = n + k - 1; j >= k; j--)
                {
                    a[j] = a[j] - q[k] * b[j - k];
                }
            }

            int ida = 0;

            for (int i = 0; i < m; i++)
            {
                if (a[i] != 0)
                    ida = i;
            }

            int[] r = new int[ida + 1];

            for (int i = 0; i < ida + 1; i++)
            {
                r[i] = a[i];
            }

            a = new int[n];

            for (int i = 0; i < n; i++)
            {
                a[i] = b[i];
            }

            m = n;
            n = ida + 1;
            b = new int[n];

            for (int i = 0; i < n; i++)
            {
                b[i] = r[i];
            }

            int cnt = Math.max(1, cont(b));
            b = primetive(b, cnt);

            if (b[n - 1] < 0)
            {
                b = primetive(b, -1);
            }

            flag = false;

            for (int i = 0; i < n; i++)
            {
                if (b[i] != 0)
                    flag = true;
            }
        }
        if (m == 1)
        {
            res = new int[]{c};
        }
        else
        {
            for (int i = 0; i < m; i++)
            {
                a[i]*=c;
            }

            res = new int[m];

            for (int i = 0; i < m; i++)
            {
                res[i] = a[i];
            }
        }
        return res;
    }

    void run() throws IOException
    {
        sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] a = new int[m];

        for (int i = 0; i < m; i++)
        {
            a[m - i - 1] = sc.nextInt();
        }

        int n = sc.nextInt();
        int[] b = new int[n];

        for (int i = 0; i < n; i++)
        {
            b[n - i - 1] = sc.nextInt();
        }

        int[] res = GEAP(a, m, b, n);

        for (int i = 0; i < res.length; i++)
        {
            System.out.print(res[i] + " ");
        }
    }
    int[] primetive(int[] a, int conta)
    {
        for (int i = 0; i < a.length; i++)
        {
            a[i] /= conta;
        }
        return a;
    }

    int NOD(int a, int b)
    {
        while (b != 0)
        {
            int tmp = a % b;
            a = b;
            b = tmp;
        }

        return a;
    }

    int cont(int[] a)
    {
        int res = Math.abs(a[0]);
        for (int i = 0; i < a.length; i++)
        {
            if (a[i] != 0)
                res = NOD(res, Math.abs(a[i]));
        }
        return res;
    }
}