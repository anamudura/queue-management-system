import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
    private BlockingQueue<Client> clienti;
    private Integer waitingTime;
    private int nrMaxClients;

    public Integer getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Server(int nrMaxClients) {
        this.nrMaxClients = nrMaxClients;
        clienti = new ArrayBlockingQueue<Client>(nrMaxClients);
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
                System.out.println("WAITING!: "+ waitingTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }
}
