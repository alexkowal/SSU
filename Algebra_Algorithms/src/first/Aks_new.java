package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Aks_new {
    static class GFG {

        static BigInteger c[] = new BigInteger[10000];


        //Pascal Triangle
        static void coef(int n) {
            c[0] = BigInteger.valueOf(1);
            for (int i = 0; i < n; c[0] = c[0].multiply(BigInteger.valueOf(-1)), i++) {
                c[1 + i] = BigInteger.valueOf(1);
                for (int j = i; j > 0; j--)
                    c[j] = c[j - 1].subtract(c[j]);
            }
        }

        //All coeff divisible by n
        static boolean test(int n) {
            coef(n);
            c[0] = c[0].add(BigInteger.valueOf(1));
            c[n] = c[n].subtract(BigInteger.valueOf(1));

            int i = n;
            while ((i--) > 0 && c[i].mod(BigInteger.valueOf(n)).equals(BigInteger.valueOf(0))) ;
            return i < 0;
        }

        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Enter digit: ");
            int n = 1;
            while ((n = Integer.parseInt(br.readLine())) != 0) {
                if (test(n))
                    System.out.println("Prime");
                else
                    System.out.println("Not Prime");
                System.out.print("Enter digit: ");
            }
        }
    }
}
