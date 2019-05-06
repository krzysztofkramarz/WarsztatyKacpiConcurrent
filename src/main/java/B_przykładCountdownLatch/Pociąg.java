package B_przykładCountdownLatch;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

class Pociąg {
    private final int zabukowaneBilety;
    private Queue<Pasażer> pasażerowieWPociągu;
    private final CountDownLatch latch;

    Pociąg(int zabukowaneBilety, BlockingQueue pasażerowieWPociągu) {
        System.out.println("Pociąg przyjechał na stację!!");
        this.zabukowaneBilety = zabukowaneBilety;
        this.pasażerowieWPociągu = pasażerowieWPociągu;
        this.latch = new CountDownLatch(zabukowaneBilety);
    }

    void pasażerStawiłSięNaPociąg(Pasażer pasażer){
        pasażerowieWPociągu.add(pasażer);
        System.out.println(pasażer+" przyszedł! zostało "+(zabukowaneBilety - pasażerowieWPociągu.size())+" miejsc");
    }

    void pociągOdjeżdża() throws InterruptedException {
        latch.await(); // zakomentuj tę linijkę i zobacz jak program zadziała. Teraz powinieneś zrozumieć, co tak naprawdę robi await();
        System.out.println("Jedziemy do Hogwartu!");
    }

    CountDownLatch getCountDownLatch() {
        return latch;
    }
}