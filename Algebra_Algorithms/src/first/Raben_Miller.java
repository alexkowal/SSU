package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Raben_Miller {
    long digit, count;

    long mul(long a, long b, long m) {
        if (b == 1)
            return a;
        if (b % 2 == 0) {
            long t = mul(a, b / 2, m);
            return (2 * t) % m;
        }
        return (mul(a, b - 1, m) + a) % m;
    }

    long degree(long dig, long deg) {
        if (deg == 2)
            return dig * dig;
        long n = ((deg * 2) + 1);
        long result = dig;

        for (int i = 1; i <= deg - 1; i++) {
            result = ((result * dig)) % digit;
        }
        return result;
    }


    public Raben_Miller(long c, long k) {
        this.digit = c;
        this.count = k;
    }

    boolean test() {

        if (digit == 2 || digit == 3)
            return true;
        // если n < 2 или n четное - возвращаем false
        if (digit < 2 || digit % 2 == 0)
            return false;

       // return digit < 2 && digit%2==0 ? false : true;

        long n = (digit - 1);
        long deg = 0, k = n, d = 0;

        while (k % 2 == 0) {
            k /= 2;
            deg++;
            d = k;
        }

        for (int j = 0; j < count; j++) {
            long a = (int) (Math.random() * n);
            long x = degree(a, d);
            if (x == 1 || x == digit - 1)
                return true;
            for (int i = 2; i <= deg; i++) { //s-1 раз делаем возведение в квадрат по модулю. Если оостаток 1 то составное
                x = degree(x, i);
                if (x == 1)
                    return false;
                if (x == digit - 1)
                    return false;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Enter digit to test: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long c;
        while ((c = Long.parseLong(br.readLine())) != 0) {
            if (c <= 2) {
                System.out.println("Error!");
                return;
            }
            Raben_Miller test = new Raben_Miller(c, 10);
            if (test.test())
                System.out.println("Yes");
            else
                System.out.println("No");
        }
    }
}
