package countdownlatch.C_zadanieCountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author krzysztof.kramarz
 */
class Wyscig
{
   private CountDownLatch countDownLatch;

   public Wyscig( CountDownLatch countDownLatch)
   {
      this.countDownLatch = countDownLatch;
   }

   void cosTam()
   {

      try
      {
         countDownLatch.await();
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }
}
