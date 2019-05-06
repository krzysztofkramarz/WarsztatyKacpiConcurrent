package E_przykładCyclicBarier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ChętnyNaZjeżdżalnię implements Runnable {

    private final CyclicBarrier barrier;

    ChętnyNaZjeżdżalnię(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("Podchodzę do stanowiska "+this);
            Thread.sleep(new Random().nextInt(5000));
            System.out.println("Ok, jestem gotowy! "+this);
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println(e.getMessage());
        }

    }

    @Override
    public String toString() {
        return Thread.currentThread().getName();
    }
}
