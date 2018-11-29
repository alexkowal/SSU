package third;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.math.BigInteger;

public class Dikson {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BigInteger n = new BigInteger(reader.readLine());
        BigInteger[] primes = new BigInteger[17];

        primes[0] = new BigInteger("2");
        primes[1] = new BigInteger("3");
        primes[2] = new BigInteger("5");
        primes[3] = new BigInteger("7");
        primes[4] = new BigInteger("11");
        primes[5] = new BigInteger("13");
        primes[6] = new BigInteger("17");
        primes[7] = new BigInteger("19");
        primes[8] = new BigInteger("23");
        primes[9] = new BigInteger("29");
        primes[10] = new BigInteger("31");
        primes[11] = new BigInteger("37");
        primes[12] = new BigInteger("41");
        primes[13] = new BigInteger("43");
        primes[14] = new BigInteger("47");
        primes[15] = new BigInteger("53");
        primes[16] = new BigInteger("59");

        System.out.print(dixonsAlgorithm(n, primes));
    }


    public static String dixonsAlgorithm(BigInteger n, BigInteger[] primes) {

        Random random = new Random();
        int arraySize = primes.length;
        BigInteger two = new BigInteger("2");
        int foundEqs = 0;

        int[][] equations = new int[arraySize][arraySize];
        BigInteger[] xvals = new BigInteger[arraySize];
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                equations[i][j] = 0;
            }
        }

        int[] tempEq = new int[arraySize];
        while (foundEqs < primes.length) {
            BigInteger x = new BigInteger(15, random);
            BigInteger x2modn = x.modPow(two, n);
            if (x2modn.compareTo(BigInteger.ZERO) == 0) {
                continue;
            }

            for (int i = 0; i < tempEq.length; i++) {
                tempEq[i] = 0;
            }

            for (int i = 0; i < primes.length; i++) {
                while (x2modn.divideAndRemainder(primes[arraySize - 1 - i])[1].equals(BigInteger.ZERO) && x2modn.intValue() != 1) {
                    tempEq[arraySize - 1 - i]++;
                    x2modn = x2modn.divideAndRemainder(primes[arraySize - 1 - i])[0];
                }
            }
            if (x2modn.intValue() == 1) {
                xvals[foundEqs] = x;
                System.arraycopy(tempEq, 0, equations[foundEqs], 0, primes.length);
                foundEqs++;
            }
        }

        int[][] mod2equations = mod2eqs(equations, arraySize);
        int[][] edmat = edmatrix(arraySize);
        int[][] matperest = gauss(mod2equations, edmat, arraySize, arraySize, true);
        int[][] edmatperest = gauss(mod2equations, edmat, arraySize, arraySize, false);

        combineeqs(xvals, equations, edmatperest, matperest, arraySize, n, primes);
        return "";
    }

    public static int[][] mod2eqs(int[][] equations, int arraySize) {
        for (int i = 0; i < arraySize; i++) {
            for (int j = 0; j < arraySize; j++) {
                if (equations[i][j] % 2 == 0) {
                    equations[i][j] = 0;
                } else {
                    equations[i][j] = 1;
                }
            }
        }
        return equations;
    }

    public static void combineeqs(BigInteger[] xVals, int[][] equations, int[][] edmatperest, int[][] iAdjusted, int arraySize, BigInteger n, BigInteger[] primes) {
        for (int i = 0; i < arraySize; i++) {
            BigInteger x = BigInteger.ONE;
            BigInteger y = BigInteger.ONE;
            if (isRowEmpty(edmatperest[i])) {
                for (int j = 0; j < arraySize; j++) {
                    if (iAdjusted[i][j] == 1) {
                        x = x.multiply(xVals[j]);
                    }
                    if (iAdjusted[i][j] == 1) {
                        for (int k = 0; k < arraySize; k++) {
                            y = y.multiply(primes[k].pow(equations[j][k]));
                        }
                    }
                }

                x = x.mod(n);
                y = sqrt(y).mod(n);


                if (x.compareTo(y) != 0) {
                    BigInteger gcd = x.subtract(y).abs().gcd(n);
                    if (gcd.compareTo(BigInteger.ONE) != 0) {
                        BigInteger factor = n.divide(gcd);
                        System.out.println("n = " + gcd + " * " + factor);
                        return;
                    }
                }
            }
        }


    }

    public static boolean isRowEmpty(int[] row) {
        for (int i = 0; i < row.length; i++) {
            if (row[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int[][] edmatrix(int size) {
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = (i == j) ? 1 : 0;
            }
        }
        return matrix;
    }

    public static BigInteger sqrt(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        while (b.compareTo(a) >= 0) {
            BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
            if (mid.multiply(mid).compareTo(n) > 0) {
                b = mid.subtract(BigInteger.ONE);
            } else {
                a = mid.add(BigInteger.ONE);
            }
        }
        return a.subtract(BigInteger.ONE);
    }


    public static int[][] gauss(int[][] matrix, int[][] edmatrix, int n, int m, boolean i) {
        for (int col = 0; col < m; col++) {

            if (matrix[col][col] == 0) {
                for (int row = col + 1; row < n; row++) {
                    if (matrix[row][col] == 1) {

                        for (int k = 0; k < m; k++) {
                            int tmp = matrix[row][k];
                            matrix[row][k] = matrix[col][k];
                            matrix[col][k] = tmp;
                        }

                        for (int k = 0; k < n; k++) {
                            int tmp = edmatrix[row][k];
                            edmatrix[row][k] = edmatrix[col][k];
                            edmatrix[col][k] = tmp;
                        }

                        break;
                    }
                }
            }

            if (matrix[col][col] == 1) {
                for (int row = col + 1; row < n; row++) {

                    if (matrix[row][col] == 1) {
                        for (int k = 0; k < m; k++) {
                            matrix[row][k] = (byte) ((matrix[row][k]
                                    + matrix[col][k]) % 2);
                        }
                        for (int k = 0; k < m; k++) {
                            edmatrix[row][k] = (byte) ((edmatrix[row][k]
                                    + edmatrix[col][k]) % 2);
                        }
                    }
                }
            }
        }
        if (i) {
            return edmatrix;
        } else {
            return matrix;
        }
    }

}
