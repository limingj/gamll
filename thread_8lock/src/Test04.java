import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Source{
    int flag =1;
    Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();
    public void print1() throws InterruptedException {

        if(Thread.currentThread().getName().equals("AA")){
            lock.lock();
            try {
                //判断
                while (flag !=1){
                    c1.await();
                }
                //干活
                for (int i = 0; i < 5; i++) {
                    System.out.println(i+"\t"+"**********打印5次");
                }
                //通知
                flag = 2;
                c2.signal();
            } finally {
                lock.unlock();
            }
        }

        if(Thread.currentThread().getName().equals("BB")){
           lock.lock();
           try {
               while(flag !=2){
                   c2.await();
               }
               for (int i = 0; i < 10; i++) {
                   System.out.println(i+"\t"+"**********打印10次");
               }
               flag=3;
               c3.signal();
           }finally {
               lock.unlock();
           }
        }
        if(Thread.currentThread().getName().equals("CC")){
            lock.lock();
            try {
                while(flag != 3){
                    c3.await();
                }
                for (int i = 0; i < 15; i++) {
                    System.out.println(i+"\t"+"**********打印15次");
                }
                //通知
                flag = 1;
                c1.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
public class Test04 {
    public static void main(String[] args) {
        Source soure = new Source();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    soure.print1();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();


        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    soure.print1();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"BB").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    soure.print1();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"CC").start();
    }
}
