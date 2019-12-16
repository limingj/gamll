import java.util.concurrent.TimeUnit;

class Phone{
    public static synchronized void sendEmail() throws InterruptedException {
       TimeUnit.SECONDS.sleep(3);
        System.out.println("sendEmail");
    }
    public synchronized void sendSMS(){
        System.out.println("sendSMS");
    }

    public void Hello(){
        System.out.println("Hello");
    }
}

public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        //资源类
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        //线程

        new Thread(()->{
            try {
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(300);

        new Thread(() ->{
            try {
               phone1.sendSMS();
                //phone.sendSMS();
                //phone.Hello();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"B").start();
    }
}
