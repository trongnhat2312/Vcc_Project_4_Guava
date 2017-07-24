import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

public class GuavaTester {
    static RespondCache cache = new RespondCache();
    public static void main(String[] args) throws ExecutionException {
        cache.init();
        respond();
        ScheduledExecutorService stop = Executors.newSingleThreadScheduledExecutor();
        stop.schedule(() -> {
            stop();
            System.exit(0);
        }, 2, TimeUnit.MINUTES);
    }
    private static void respond() throws ExecutionException {
        port(8080);
        get("prime", (req, res) -> {
            int n = Integer.parseInt(req.queryParams("n"));
            return cache.get(n);
        });
    }
}
