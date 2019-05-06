package fabryczkapomocnicza;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadFactory implements ThreadFactory {
    private AtomicInteger licznik = new AtomicInteger(0);
    private final String nazwa;
    public MyThreadFactory(String nazwa) {
        this.nazwa = nazwa;
    }

    @Override
    public Thread newThread(Runnable r) {
        licznik.getAndIncrement();
        return new Thread(r,nazwa+" "+licznik);
    }
}
