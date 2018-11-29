package fourth;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class SqrtPol
{
    Scanner sc;
    public static void main(String[] args) throws IOException
    {
        new SqrtPol().run();
    }

    void run() throws IOException
    {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int[] pStart = new int[m];

        for (int i = 0; i < m; i++)
        {
            pStart[m - i - 1] = sc.nextInt();
        }
        ArrayList<Double> p = new ArrayList<>();
        ArrayList<Double> q = new ArrayList<>();
        p.add(0.0);

        for (int i = 1; i <= Math.abs(pStart[0]); i++)
        {
            if (pStart[0] % i == 0)
            {
                p.add(i * (1.0));
            }
        }
        for (int i = 1; i <= Math.abs(pStart[m - 1]); i++)
        {
            if (pStart[m - 1] % i == 0)
            {
                q.add(i * (1.0));
            }
        }

        HashSet<Double> ans = new HashSet<>();
        for (double pp : p)
        {
            for (double qq : q)
            {
                if (isTrue(pStart, pp / qq))
                {
                    ans.add(pp / qq);
                }
                if (isTrue(pStart, (-1.0) * (pp / qq)))
                {
                    ans.add((-1.0) * (pp / qq));
                }
            }
        }

        if (!ans.isEmpty())
            for (double aa : ans)
            {
                System.out.print(aa + " ");
            }
        else
            System.out.print("Error");
    }

    boolean isTrue(int[] a, double x)
    {
        double tmp = 1.0;
        double res = 0.0;

        for (int i = 0; i < a.length; i++)

        {
            res += a[i] * tmp;
            tmp *= x;
        }

        return (Math.abs(res) < 1e-9);
    }
}