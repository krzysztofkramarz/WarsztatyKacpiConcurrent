package countdownlatch.C_zadanieCountDownLatch;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author krzysztof.kramarz
 */
class Wyscig
{
   private CountDownLatch countDownLatch;
   private Queue<Kierowca> kolejkaKierowcow = new LinkedBlockingQueue<>();


   public Wyscig( CountDownLatch countDownLatch)
   {
      this.countDownLatch = countDownLatch;
   }

   void cosTam()
   {

      try
      {
         countDownLatch.await();
         System.out.println("Wyscigi start!!");
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }

   void kierowcaZglosilSie(Kierowca kierowca){
      System.out.println("Kierowca przyjechal na START");
      kolejkaKierowcow.add(kierowca);
   }
}
