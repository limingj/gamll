import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Test06 {
    public static void main(String[] args) {
        CyclicBarrier barr = new CyclicBarrier(7,()->{
                System.out.println("召唤神龙");
        });

        for (int i = 1; i <=7; i++) {
            new Thread(() -> {
                System.out.println("集齐第"+Thread.currentThread().getName()+"颗龙珠");
                try {
                    barr.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
