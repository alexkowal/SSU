package second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.Buffer;

public class Luke {
    long[] prime = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131};
    long count;

    Luke(long count) {
        this.count = count;
    }

    BigInteger S = BigInteger.valueOf(4);

//p - prime. M(p) = 2^ p -1;
//M(p) - prime <=> M(p) / (p-1)-ый член последовательности задаваемой реккурентно S = {4,S^2(k-1) -2;
    void test() throws IOException {
        int c = 0;
        for (int i = 0; i < prime.length; i++) {
            if (c >= count)
                return;
            BigInteger S = BigInteger.valueOf(2);
            BigInteger M = S.pow((int) prime[i]);
            M = M.subtract(BigInteger.valueOf(1));
            //Построили M;
            S = BigInteger.valueOf(4);
            for (int j = 0; j < prime[i] - 2; j++) {
                S = S.pow(2);
                S = S.subtract(BigInteger.valueOf(2));
                S = S.mod(M);
            }
            BigInteger temp = S.mod(M);
            if (temp.equals(BigInteger.valueOf(0))) {
                System.out.println(M);
                c++;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        System.out.println("Введите количество простых чисел:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long p = Long.parseLong(br.readLine());
        Luke luke = new Luke(p);
        luke.test();
    }
}

