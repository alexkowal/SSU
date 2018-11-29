package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solovey_Strassen {

    long k, n;

    public Solovey_Strassen(long n, long k) {

        this.n = n;
        this.k = k;
    }

    long mul(long a, long b, long m) {
        if (b == 1)
            return a;
        if (b % 2 == 0) {
            long t = mul(a, b / 2, m);
            return (2 * t) % m;
        }
        return (mul(a, b - 1, m) + a) % m;
    }

    long pows(long a, long b, long m) {
        if (b == 0)
            return 1;
        if (b % 2 == 0) {
            long t = pows(a, b / 2, m);
            return mul(t, t, m) % m;
        }
        return (mul(pows(a, b - 1, m), a, m)) % m;
    }


    public long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    long degree(long dig, long deg) {
        if (deg == 2)
            return dig * dig;
        long n = ((deg * 2) + 1);
        long result = dig;

        for (int i = 1; i <= deg - 1; i++) {
            result = ((result * dig)) % n;
        }
        return result;
    }

    public boolean test() {
        for (int i = 0; i < k; i++) {
            long a = (long) (Math.random() * (n - 2));
            while (a <= 2)
                a = (long) (Math.random() * (n - 2));
            //System.out.println("a = " + a);
            long w = (a + n - 1) / (n);//Jakobi

            if (gcd(a, n) > 1)
                return false;
            else {
                if (((degree(a, (n - 1) / 2) + (n - 1)) % n) == w)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter digit to test: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long c = Long.parseLong(br.readLine());
        Solovey_Strassen test = new Solovey_Strassen(c, 100);
        if (test.test())
            System.out.println("Yes");
        else
            System.out.println("No");
    }

}
