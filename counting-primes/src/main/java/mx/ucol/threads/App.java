package mx.ucol.threads;
import java.util.ArrayList;
import java.util.List;

public class App {
    private final static int MAX = 5_000_000;

    // TODO
    // Create a nested class that uses the countPrimes() method and implements Runnable
    // HINT: You can use System.currentTimeMillis() to capture the current time of the system
    static class numerosPrimos implements Runnable{
        public void run() {
            long inicio = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" counted "+countPrimes(0,MAX)+" primes in "+ ((System.currentTimeMillis()-inicio)/1000)+" seconds.");
        }
    }

    public static void main(String args[]) throws InterruptedException {
        int numberOfThreads = 0;
        if (args.length > 0) {
            try {
                numberOfThreads = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }

        if (numberOfThreads == 0) {
            System.out.println("Argument must be more than zero.");
            System.exit(1);
        }

        // TODO
        // Create threads (HINT: You can store threads in arrays)
        // HINT: You can store threads in arrays
        List<Thread> arrayHilos = new ArrayList<>();
        for (int i = 0; i < numberOfThreads; i++) {
            Thread nuevoHilo = new Thread(new numerosPrimos());
            arrayHilos.add(nuevoHilo);
            arrayHilos.get(i).start();
        }
   }

    // This methods counts the number of primes in the range min to max
    static int countPrimes(int min, int max) {
        int count = 0;
        for (int i = min; i <= max; i++) {
            if (isPrime(i)) {
                count++;
            }
        }
        return count;
    }

    private static boolean isPrime(int x) {
        assert x > 1;
        int top = (int)Math.sqrt(x);

        for (int i = 2; i <= top; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
}
