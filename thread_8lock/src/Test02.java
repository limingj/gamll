import java.util.concurrent.CountDownLatch;

public class Test02 {
    public static void main(String[] args) throws InterruptedException {
       CountDownLatch count = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
           new Thread(() -> {
               System.out.println(Thread.currentThread().getName()+"离开教室");
              count.countDown();
               },String.valueOf(i)).start();
        }
        count.await();
        System.out.println(Thread.currentThread().getName()+"关门");
    }
}
