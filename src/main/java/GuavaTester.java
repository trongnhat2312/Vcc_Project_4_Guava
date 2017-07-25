import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static spark.Spark.*;

/**
 * Tra lai day so nguyen to tu 1 den n nhap vao tu 1 param tren url cua request,
 * su dung cache, neu sau 10s khong co request thi cache tu xoa
 */

public class GuavaTester {
    static RespondCache cache = new RespondCache();

    private static void respond() throws ExecutionException {
        port(8080);
        get("prime", (req, res) -> {
            int n = Integer.parseInt(req.queryParams("n"));
            return cache.get(n);
        });
    }

    public static void main(String[] args) throws ExecutionException {
        cache.init();
        respond();
        ScheduledExecutorService stop = Executors.newSingleThreadScheduledExecutor();
        stop.schedule(() -> {
            stop();
            System.exit(0);
        }, 2, TimeUnit.MINUTES);
    }
}
