import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main{
    public static void main(String[] args) {


        BlockingQueue<Client> q = new ArrayBlockingQueue<Client>(10);
        Product p = new Product(q);
        Consumator c = new Consumator(q);
        Thread t1 = new Thread(p);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
    }

}
