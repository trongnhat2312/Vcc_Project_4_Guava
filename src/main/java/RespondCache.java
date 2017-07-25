import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class RespondCache {
    private LoadingCache<Integer, String> cache;
    public void init() {
        //create a cache for prime numbers not larger than n
        cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer n) throws Exception {
                        ArrayList<Integer> listOfPrime = PrimeNumbersArray.getPrimeNumbersArrayLE(n);
                        if (listOfPrime.isEmpty())
                            return "There is no prime number less than or equal to " + n;
                        StringBuilder str = new StringBuilder();
                        str.append("List of prime numbers less than or equal to ").append(n).append(": <br/>");
                        for (Integer pn : listOfPrime) {
                            str.append(pn).append(" ");
                        }
                        return str.toString();
                    }
                });
    }
    public String get(Integer key) throws ExecutionException {
        System.out.println(TimeUnit.NANOSECONDS.toSeconds(System.nanoTime()));
        return cache.get(key);
    }
}
