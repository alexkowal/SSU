package fourth;
import java.util.ArrayList;
import java.util.Scanner;

public class PDF
{
    Scanner scan;

    public static void main(String[] args)
    {
        new PDF().run();
    }

    public void run()
    {
        scan = new Scanner(System.in);
        int m = scan.nextInt();

        int [] c = new int [m + 1];

        for (int i = m; i >= 0; i--)
            c[i] = scan.nextInt();

        int n = scan.nextInt();

        int [] d = new int[n + 1];

        for (int i = n; i >= 0; i--)
            d[i] = scan.nextInt();

        int[] q = new int [m];

        for (int k = m - n; k >= 0; k--)
        {
            q[k] = c[n + k] / d[n];

            for (int j = n + k - 1; j >= k; j--)
                c[j] = c[j] - q[k] * d[j - k];
        }

        System.out.print("q(x) = ");
        for (int i = m - n; i >= 0; i--)
            System.out.print(q[i] + " ");
        System.out.println();

        System.out.print("r(x) = ");

        for (int i = n - 1; i >= 0; i--)
            System.out.print(c[i] + " ");
    }
}
