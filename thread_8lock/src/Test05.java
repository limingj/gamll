import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Mycache{
    private volatile Map<String,String> map =new HashMap<>();
    private Lock lock = new ReentrantLock();

    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key,String value){

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"  "+"写入开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"   "+"写入结束");
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void read(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"  "+"读取开始");
            String result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"   "+"读取结束"+result);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}


public class Test05 {
    public static void main(String[] args) {

        Mycache c = new Mycache();

        for (int i = 0; i < 10; i++) {
            final int item = i;
            new Thread(() -> {
                c.put(item+"",item+"");
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 10; i++) {
            final int item = i;
            new Thread(() -> {
                c.read(item+"");
            },String.valueOf(i)).start();
        }

    }
}
