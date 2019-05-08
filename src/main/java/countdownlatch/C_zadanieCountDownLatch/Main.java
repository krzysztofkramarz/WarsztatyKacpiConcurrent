package countdownlatch.C_zadanieCountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import fabryczkapomocnicza.MyThreadFactory;

/**
 * <p>Czas na pierwsze większe zadanko dla Ciebie :) Będzie ono bardzo podobne do zadania z przykładu,
 * jednak dla własnego dobra staraj się tam nie zaglądać. Użyj CountDownLatch, poczytaj dokumentację jeżeli
 * nie jesteś czegoś pewien. Jeżeli zrozumiałeś koncept, z zadaniem powinieneś sobie poradzić sam :)</p>
 *
 * @author Kacper Staszek
 */
public class Main {

    private static final int ILE_KIEROWCOW = 5;

    //TODO: Napisz program, który symuluje NIELEGALNE WYSCIGI W LA! Coś jak Need For Speed ;D
    //TODO:5 kierowców otrzymało smsa z miejscem rozpoczęcia, czym prędzej jadą na miejsce! Jednak każdy jest w innym
    //TODO:miejscu miasta, więc czas dotarcia będzie różny. Dopiero kiedy wszyscy pojawią się na miejscu - wyścig ruszy!
    //TODO:Skorzystaj śmiało z fabryki wątków, którą umieściłem w projekcie.
    //TODO:Kiedy skończysz, spróbuj napisać program tak, że po jakimś określonym czasie wyścig rozpocznie się niezależnie od tego, czy wszyscy zdążyli się stawić na miejscu :)
    //TODO:Po zadaniu zmień gałąź na wprowadzenieCyclicBarrier

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(ILE_KIEROWCOW);
        Wyscig wyscig = new Wyscig(countDownLatch);

        ThreadFactory threadFactory = new MyThreadFactory("Kierowca-bombowca");

        ExecutorService executorService = Executors.newFixedThreadPool(5, threadFactory);

        for (int i = 0; i < 5; i++)
        {

            executorService.submit(new Kierowca(countDownLatch));

        }
        wyscig.cosTam();

        executorService.shutdown();

    }
}
