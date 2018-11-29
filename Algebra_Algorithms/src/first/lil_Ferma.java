package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class lil_Ferma {
    public static int q = 1;
    int a;
    int p;

    public lil_Ferma(int digit) {
        this.p = digit;
        this.a = chooseHelpDigit(digit);
    }

    int chooseHelpDigit(int p) {

        while (true) {
            q++;
            if (p % q != 0)
                break;
        }
        return q;
    }

    long degree(int p) {
        long result = a;
        for (int i = 1; i < p - 1; i++) {
            result = (result * a) % p;
        }
        return result;
    }

    boolean test(int p) {

        long c = degree(p) - 1;
        c = c % p;
        if (c == 0)
            return true;
        return false;
    }

    //Если p - простое число и а - целое число не делящееся на p,то a^(p-1)-1 делится на p.
    public static void main(String[] args) throws IOException {

        System.out.println("Enter Digit to test: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int digit = Integer.parseInt(br.readLine());
        lil_Ferma ferma = new lil_Ferma(digit);
        int count = 0;
        for (int i = 0; i < 100; i++) {
            if (ferma.test(ferma.p))
                count++;
        }
        if (count == 100)
            System.out.println("Yes");
        else System.out.println("No");
    }

}


