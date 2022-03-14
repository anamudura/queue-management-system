import java.util.concurrent.BlockingQueue;

public class Product implements Runnable {
    BlockingQueue<Client> productqueue;

    public void run() {
        for (int i = 0; i < 10; i++) {
            Client c = new Client();
            c.settService(c.GenerateService(1, 4));
            c.settArrival(c.GenerateArrival(2, 10));
            c.setId(c.GenerateID(10));
            {

                try {
                    Thread.sleep(100);
                    productqueue.put(c);
                    System.out.println("am pus: " + c.getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Product(BlockingQueue q) {
        this.productqueue = q;
    }
}
