import java.util.concurrent.BlockingQueue;

public class Consumator implements Runnable {
    BlockingQueue consproduct;
    public Consumator(BlockingQueue q)
    {
        this.consproduct=q;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Client c = (Client) consproduct.take();
                Thread.sleep(100);
                System.out.println("am scos: " + c.getId());
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }
}
