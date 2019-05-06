package E_przykładCyclicBarier;

import java.util.concurrent.CyclicBarrier;

class Zjeżdżalnia implements Runnable{
    /*
     * Nasza zjeżdżalnia służy do wyścigów. Mamy 4 pozycje do startu, dlatego nasza bariera
     * będzie oczekiwać na 4 chętnych, gotowych do startu. Dopiero wtedy osoba pilnująca zjeżdżali
     * da sygnał do startu.
     * */
    private CyclicBarrier cyklicznaBariera = new CyclicBarrier(4,this);

    @Override
    public void run() {
        System.out.println("\033[1;32mMamy 4 chętych, odjeżdżamy!");
        System.out.print("\033[0m");
    }

    CyclicBarrier getBarrier() {
        return cyklicznaBariera;
    }
}
