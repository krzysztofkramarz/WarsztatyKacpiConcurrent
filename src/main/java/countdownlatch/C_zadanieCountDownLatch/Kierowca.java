package countdownlatch.C_zadanieCountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author krzysztof.kramarz
 */
class Kierowca implements Runnable
{
   private CountDownLatch countDownLatch;
   private Wyscig wyscig;

   public Kierowca(CountDownLatch countDownLatch)
   {
      this.countDownLatch = countDownLatch;
   }

   @Override
   public void run()
   {
      System.out.println(
            "O ja cię, mam esemesa, zaczynaja się zaraz wyscigi! Pa kochanie, połóż dzieci sama. To ja jestem: " + Thread.currentThread().getName());
      try
      {
         Thread.sleep(ThreadLocalRandom.current().nextInt(1000));
      }
      catch (InterruptedException e)
      {

      }
      countDownLatch.countDown();
      System.out.println("Przyjechalem! " + Thread.currentThread().getName());
   }
}
