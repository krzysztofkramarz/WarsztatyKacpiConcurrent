package B_przykładCountdownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Pasażer implements Runnable{
    private final Pociąg pociąg;
    private final CountDownLatch latch;

    Pasażer(Pociąg pociąg, CountDownLatch latch) {
        this.pociąg = pociąg;
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("O cholera! Lece na pociąg!");
        try {
            Thread.sleep(new Random().nextInt(10000));
            latch.countDown();
            pociąg.pasażerStawiłSięNaPociąg(this);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String toString() {
        return Thread.currentThread().getName();
    }
}
