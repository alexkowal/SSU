package third;

import first.Raben_Miller;
import first.Solovey_Strassen;
import first.Vilson;
import first.lil_Ferma;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class FermaFactorization {
    Scanner scan;
    ArrayList<Long> multip = new ArrayList<>();

    public static void main(String[] args) {
        new FermaFactorization().run();
    }

    void run() {
        scan = new Scanner(System.in);
        long n = scan.nextLong();
        FactFerm(n);
        System.out.print(n + " = ");

        for (int i = 0; i < multip.size() - 1; i++)
            if (multip.contains(multip.get(i) * multip.get(i + 1))) {
                int idx = multip.indexOf(multip.get(i) * multip.get(i + 1));
                multip.remove(idx);
                i = -1;
            }

        for (int i = 0; i < multip.size() - 1; i++)
            System.out.print(multip.get(i) + " * ");
        System.out.print(multip.get(multip.size() - 1));
    }

    void FactFerm(long n) {

        long x = (long) Math.sqrt(n) + 1;
        if ((long) (Math.sqrt(n)) * ((long) Math.sqrt(n)) == n) {
            x = (long) Math.sqrt(n);
            multip.add(x);
            multip.add(x);
return;
        }
        long y = n;
        long tmp = x * x - n;
        while (tmp != (long) Math.sqrt(tmp) * (long) Math.sqrt(tmp)) {
            x++;
            tmp = x * x - n;
            if (x > n)
                return;
        }
        y = (long) Math.sqrt(tmp);
        long a = x + y;
        long b = x - y;
        if (!multip.contains(a) && a != 1)
            multip.add(a);
        if (!multip.contains(b) && b != 1)
            multip.add(b);
        if (x + y != 1 && x - y != 1) {
            FactFerm(x + y);
            FactFerm(x - y);
        }
    }
}