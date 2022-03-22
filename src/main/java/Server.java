import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
    private BlockingQueue<Client> clienti;
    private Integer waitingTime;

    public Server() {
        clienti = new ArrayBlockingQueue<Client>(0);
        waitingTime = 0;

    }

    public void addClient(Client c) {
        clienti.add(c);
        waitingTime++;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Client c = clienti.take();
                Thread.sleep(c.gettService());
                waitingTime--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }
}
