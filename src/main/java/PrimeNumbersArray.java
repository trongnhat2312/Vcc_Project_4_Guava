import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class PrimeNumbersArray {

    public static ArrayList<Integer> getPrimeNumbersArrayLE(int n) {
        ArrayList<Integer> primeNumbersArray = new ArrayList<>();
        if (n <= 1) return primeNumbersArray;
        for (int i = 2; i <= n; i++) {
            if (isPrime(i))
                primeNumbersArray.add(i);
        }
        System.out.println("Viet ham");
        return primeNumbersArray;
    }

    private static boolean isPrime(int i) {
        if (i == 2 || i == 3) return true;

        int sqrt_i = (int) Math.sqrt(i);
        for (int j = 2; j <= sqrt_i ; j++) {
            if (i % j == 0) return false;
        }
        return true;
    }
}
