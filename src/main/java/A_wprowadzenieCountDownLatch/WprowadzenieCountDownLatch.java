package A_wprowadzenieCountDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/*
* CountDownLatch to klasa, która pozwala nam sterować naszym programem tak, żeby jeden wątek poczekał
* z rozpoczęciem swojego zadania, aż kilka innych skończy pracę. Wątki te będą współdzieliły referencję do naszej
* instancji CountDownLatch. Na przykład, jeżeli mamy do wykonania 3 zadania, ale trzecie może ruszyć dopiero jak dwa pierwsze,
* które mogą działać równolegle - się zakończą.
* */
public class WprowadzenieCountDownLatch {
    /*
    * W konstruktorze przekazujemy ile wątków musi zakończyć swoją pracę, żeby kolejny mógł rozpocząć*/
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Task(countDownLatch),"Zadanie "+i);
            thread.start();
        }

        System.out.println("Jestem wątek, który musi poczekać na inne operacje do zakończenia, zanim pójdę dalej");
        try {
            countDownLatch.await(); // metoda, która zatrzymuje dalsze instrukcje, dopóki countDownLatch nie spadnie do 0;
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Ok, wszyscy skończyli, to lecę dalej!");
    }

}

class Task implements Runnable{
    private final CountDownLatch latch;

    Task(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Doing stuff");
        try {
            Thread.sleep(new Random().nextInt(10000));  //Symulujemy, że zadanie zajmuje troszkę czasu
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Skończyłem moje zadanko, dekrementuję latch! "+Thread.currentThread().getName());
        latch.countDown(); //Dekrementuje nasz licznik o jeden. Kiedy licznik osiągnie zero, zadanie oczekujące się rozpocznie.
    }
}
