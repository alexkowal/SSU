package second;

import javafx.print.Collation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

//n>=3 and n = F*R + 1;(F делит n-1) F>R, NOD(F,R) = 1;
// Если для любого q = делящегося на F, есть целое A>1: A^n-1 = 1(mod n) and NOD(A^(n-1/q) - 1 , n ) = 1
// то каждый делитель числа А имеет вид p = q^k*r + 1;;

public class Poclington {
    long count;

    Poclington(long p) {
        this.count = p;
    }

    void test() {
        ArrayList<Long> vec = new ArrayList<>();
        ArrayList<Integer> prime = new ArrayList<>();
        Collections.addAll(prime, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97);
        int j = 0;
        while (j < count) {
            boolean f = true;
            while (f) {
                int rnd = new Random().nextInt(prime.size());
                Random r = new Random();
                int c = 1 + r.nextInt(3);
                long F = myPow(prime.get(rnd), c);

                Random rr = new Random();
                int R = (1 + rr.nextInt(10)) * 2;
                long n = R * F + 1;
                if (n < 100000)
                    continue;
                boolean g = true;
                for (int b = 0; b < 100; b++) {
                    Random gg = new Random();
                    long a = myRand(n - 1);
                    if (powBig(a, n - 1) != 1)
                        break;
                    if (powBig(a, (n - 1) / (prime.get(rnd))) % n != 1)
                        g = false;
                }
                if (!g && vec.size() == 0) {
                    vec.add(n);
                    f = false;
                    j++;
                } else if (!g && vec.size() > 0) {
                    for (int i = 0; i < vec.size(); i++) {
                        if (!vec.contains(n)) {
                            vec.add(n);
                            f = false;
                            j++;
                        }
                    }
                }
            }
        }
        Collections.sort(vec);
        for (long i : vec)
            System.out.println(i);
    }

    private long powBig(long a, long l) {
        long res = 1;
        for (long i = 0; i < l; i++)
            res = (res * a) % (l + 1);
        return res;
    }

    private long myRand(long l) {
        return (100 * (l - 1)) % l;
    }

    private long myPow(Integer integer, int c) {
        long res = 1;
        for (int i = 0; i < c; i++)
            res *= integer;
        return res;
    }


    public static void main(String[] args) throws IOException {
        System.out.println("Введите количество простых чисел:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long p = Long.parseLong(br.readLine());
        Poclington poc = new Poclington(p);
        poc.test();
    }
}
