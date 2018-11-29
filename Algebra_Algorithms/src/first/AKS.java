package first;

public class AKS {
    int digit;

    AKS(int a) {
        this.digit = a;
    }

    long degree(long dig, long deg) {
        if (deg == 2)
            return dig * dig;
        long n = ((deg * 2) + 1);
        long result = dig;

        for (int i = 1; i <= deg - 1; i++) {
            result = ((result * dig)) % (digit + 1);
        }
        return result;
    }

    int phi (int n) {
        int result = n;
        for (int i=2; i*i<=n; ++i)
            if (n % i == 0) {
                while (n % i == 0)
                    n /= i;
                result -= result / i;
            }
        if (n > 1)
            result -= result / n;
        return result;
    }

    boolean test() {
        for (int a = 1; a < digit; a++)
            for (int b = 1; b < digit / 2; b++)
                if (degree(a,b)==digit)
                    return false;
return true;

    }
}
