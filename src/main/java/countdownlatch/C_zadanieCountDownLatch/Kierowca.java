package countdownlatch.C_zadanieCountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author krzysztof.kramarz
 */
class Kierowca implements Runnable
{
   private CountDownLatch countDownLatch;

   public Kierowca(CountDownLatch countDownLatch)
   {
      this.countDownLatch = countDownLatch;
   }

   @Override
   public void run()
   {
      System.out.println("O ja cię, mam esemesa, zaczynaja się zaraz wyscigi! Pa kochanie, połóż dzieci sama");
      countDownLatch.countDown();
   }
}
