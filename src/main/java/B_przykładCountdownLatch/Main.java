package B_przykładCountdownLatch;

import fabryczkapomocnicza.MyThreadFactory;

import java.util.concurrent.*;

/*
 *  Przykładowe zadanko - problem polega na tym, że mamy pociąg do Hogwartu ;D Wyobraźmy sobie, że 10 czarodziejów
 *  właśnie otrzymało list, więc lecą ile sił w nogach na pociąg.
 * Konduktor jest na tyle spoko, że zaczeka na wszystkich, zanim ruszy.
 *  Włącz program, zobacz jak działa, prześledź na szybko kod.
 *  Zwróć uwagę na linię 26 w klasie pociąg.
 *  Za niedługo będziesz próbował napisać coś podobnego, jednak nie chodzi o to, żebyś wszystko odpisał.
 * Zrozum mechanizm i przy pisaniu zadania staraj się tu jak najmniej zaglącać :)
 *
 * */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Pasażer> pasażerowie = new LinkedBlockingQueue<>();

        Pociąg pociąg = new Pociąg(10, pasażerowie);

        CountDownLatch referencjaCounDownLatchZNaszegoPociągu = pociąg.getCountDownLatch();

        ThreadFactory threadFactory = new MyThreadFactory("Pasażer");

        ExecutorService executorService = Executors.newFixedThreadPool(10, threadFactory);

        for (int i = 0; i < 10; i++) {
            executorService.submit(new Pasażer(pociąg, referencjaCounDownLatchZNaszegoPociągu));
        }
        executorService.shutdown();

        pociąg.pociągOdjeżdża();
    }
}

