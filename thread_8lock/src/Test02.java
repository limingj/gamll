import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Test02 {
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i < 2; i++) {
           new Thread(() -> {
               list.add(UUID.randomUUID().toString().substring(0,6)) ;
               System.out.println(list);
           },String.valueOf(i)).start();
        }
    }
}
