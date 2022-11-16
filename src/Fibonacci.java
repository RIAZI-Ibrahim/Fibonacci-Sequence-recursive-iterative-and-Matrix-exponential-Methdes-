import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;

public class Fibonacci {
    static TimeHanlder timeHanlder = new TimeHanlder();

    public Fibonacci() {
    }


    public static BigInteger fiboRecursive(BigInteger n) {



    if (n.compareTo(BigInteger.ONE) <= 0){return n;}

    return fiboRecursive(n.subtract(BigInteger.TWO)).add(fiboRecursive(n.subtract(BigInteger.ONE)));

    }




    static int iterative(int n){
        int nbr =0, nbr1=0, nbr2=1;

        if(n == 0){
            nbr= nbr1;
        }
        else if (n ==1){
            nbr = nbr2;
        }
        else {

            for (int i = 2; i <= n; i++) {
                nbr = nbr1 + nbr2;
                nbr1 = nbr2;
                nbr2 = nbr;
            }
        }
        return nbr;
    }

    static BigInteger fiboIterative(BigInteger n) {
        if (n.compareTo(BigInteger.ONE) <= 0){return n;}

        BigInteger pred = new BigInteger("0");
        BigInteger next = new BigInteger("1");
        BigInteger i = new BigInteger("1");
        BigInteger sum = new BigInteger("0");

        while (i.compareTo(n) != 0) {
            sum = pred.add(next);
            pred = next;
            next = sum;
            i = i.add(BigInteger.ONE);
        }

        return sum;

    }

    static BigInteger fiboExponentiation(BigInteger n) {

        if (n.compareTo(BigInteger.ONE) <= 0)
            return n;

        BigInteger[][] fibonacci_matrix = {

                {BigInteger.ONE, BigInteger.ONE},
                {BigInteger.ONE, BigInteger.ZERO}
        };

        fibonacci_matrix = powerOfMatrix(fibonacci_matrix, n);

        return fibonacci_matrix[0][1];

    }


    static BigInteger[][] powerOfMatrix(BigInteger[][] matrix, BigInteger p) {

        if (p.compareTo(BigInteger.ONE) <= 0)
            return matrix;
        // si p est pair
        if (p.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0){
            return powerOfMatrix(squaredMatrix(matrix), p.shiftRight(1));
        } else{ // si p est impair
            return multiplication(matrix, powerOfMatrix(squaredMatrix(matrix), p.shiftRight(1)));
        }

    }


    static BigInteger[][] squaredMatrix(BigInteger[][] matrix) {
        return multiplication(matrix, matrix);
    }


    static BigInteger[][] multiplication(BigInteger[][] one, BigInteger[][] two) {




        BigInteger[][] result = new BigInteger[2][2];



        result[0][0] = one[0][0].multiply(two[0][0]).add(one[0][1].multiply(two[1][0]));
        result[0][1] = one[0][0].multiply(two[0][1]).add(one[0][1].multiply(two[1][1]));
        result[1][0] = one[1][0].multiply(two[0][0]).add(one[1][1].multiply(two[1][0]));
        result[1][1] = one[1][0].multiply(two[0][1]).add(one[1][1].multiply(two[1][1]));

        return result;

    }

    public static void fileHandler(int n) throws IOException {
        Writer file = new FileWriter("src/iterative.dat");
        for(int i=0; i <= n ; i+=20000){
//            final long start = System.nanoTime();
            timeHanlder.start();
            fiboIterative(BigInteger.valueOf(i));
            timeHanlder.stop();
//            final long end = System.nanoTime();
//            long timeSeconds = (end - start)/ 1000000000;

            file.write(i+" "+timeHanlder.getElapsedTime()/1000_000_000 +"\n");
        }
        file.close();
    }







    public static void main(String args[]) throws IOException {

//        System.out.println(fiboRecursive(BigInteger.valueOf(50)));

        fileHandler(200000);






//        int n = 50;
//        final long start = System.nanoTime();
//        fiboExponentiation(BigInteger.valueOf(n));
//        final long end = System.nanoTime();
//
//        System.out.println("Took: " + ((end - start) / 1000000) + "ms");
//        System.out.println("Took: " + (end - start)/ 1000000000 + " seconds");
//        System.out.println(fibonacciRecursive(n));
//        System.out.println(fibonacciIterative(n));
//        System.out.println(matrixExponentiation(new BigInteger(String.valueOf(9))));

    }
}
