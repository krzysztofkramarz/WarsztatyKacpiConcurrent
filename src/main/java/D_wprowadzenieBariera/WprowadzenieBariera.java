package D_wprowadzenieBariera;

import fabryczkapomocnicza.MyThreadFactory;

import java.util.Random;
import java.util.concurrent.*;

/*
 * Bariera działa bardzo podobnie do CountDownLatch, ale jest wielokrotnego użytku. Działa cyklicznie.
 * Możemy jej używać, kiedy mamy powtarzające się zadania, a któreś z nich musi poczekać na inne, zanim się rozpocznie.
 * Jeżeli zrozumiałeś mniej więcej koncept CountDownLatch - to powinno pójść gładko :)
 * */
public class WprowadzenieBariera {
    public static void main(String[] args) {
        /*
         * W konstruktorze przekazujemy ilość zadań do zakończenia, jako drugi argument do bariery,
         * możemy przekazać jakieś zadanie (Runnable),
         * które uruchomi się za każdym razem, kiedy wykona się cykl bariery.
         * Uruchom program, zobacz co robi. Następnie prześledź kod ;)
         * */
        CyclicBarrier cyclicBarrier =
                new CyclicBarrier(3, () -> {
                    System.out.println("\033[1;32mCykl się skończył, robię swoje i zaraz od nowa");
                    System.out.print("\033[0m");
                    //te dziwne cyferki to tylko zmiana kolorków, żeby działanie programu było lepiej widoczne
                });
        ThreadFactory myThreadFactory = new MyThreadFactory("Zadanko");
        ExecutorService executorService = Executors.newFixedThreadPool(3, myThreadFactory);
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task(cyclicBarrier));
        }
        executorService.shutdown();
    }
}

class Task implements Runnable {
    private final CyclicBarrier barrier;

    Task(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    @Override
    public void run() {
        System.out.println("Robię swoje rzeczy, ktoś tam na mnie czeka");
        try {
            Thread.sleep(new Random().nextInt(5000)); //Symulujemy, że zadanie zajmuje troszkę czasu
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        try {
            System.out.println("Skończyłem " + Thread.currentThread().getName());
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            System.err.println(e.getMessage());
        }
    }
}
