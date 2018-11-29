package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Vilson {
    long p;

    public Vilson(long p) {
        this.p = p;
    }

    long fact(long p) {
        long result = 1;
        for (int i = 2; i <= p; i++) {
            result = (result * i) % (p + 1);
        }
        return result;
    }

    public boolean test(long p) {
        long factorial = fact(p - 1);
        if ((factorial % p) == p - 1)
            return true;
        return false;
    }

    //Число p простое <=> когда (p-1)! + 1 делится на p.
    public static void main(String[] args) throws IOException {

        System.out.println("Enter Digit to test: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long p = Long.parseLong(br.readLine());
        Vilson vilson = new Vilson(p);
        if (vilson.test(vilson.p))
            System.out.println("Yes");
        else
            System.out.println("No");
    }
}

