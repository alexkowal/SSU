package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

public class karmaykl {

    long n;

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

    ArrayList<Long> funct(long digit) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i = 2; i < digit; i++)
            if (gcd(digit, i) == 1)
                list.add(i);
        return list;
    }
    //NOD


    boolean test(long digit) {
        ArrayList<Long> list;
        int count = 0;
        list = funct(digit);

        for (long b : list) {

            long c = pows(b, digit - 1, digit);
            c %= digit;
            if (c == 1)
                count++;
        }
        if (count != list.size())
            return false;

        boolean free = false;
        boolean flag = false;
        for (int i = 2; i < digit; i++) {
            if (digit % (i * i) != 0)
                free = true;
        }
        for (int i = 3; i <= digit / 2; i++) {

            Vilson vilson = new Vilson(i);
            if (vilson.test(i)) {
                if (digit % i == 0)
                    if ((digit - 1) % (i - 1) == 0)
                        flag = true;
            }
        }
        if (flag && free)
            return false;
        return true;
    }

    //Число кармайлка - составное число n которое удовлетворяет b^(n-1) сравнимо с 1 по модулю n для всех целых b взаимно простых с n.

    public static void main(String[] args) throws IOException {
        long test;
        System.out.println("Enter digit to test: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        test = Long.parseLong(br.readLine());
        karmaykl karmaykl = new karmaykl();
        karmaykl.n = test;
        if (karmaykl.test(test) == true)
            System.out.println("Yes");
        else System.out.println("No");
    }
}



