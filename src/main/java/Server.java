import java.util.concurrent.BlockingQueue;

public class Server implements Runnable {
    private BlockingQueue<Client> clienti;
    private int waitingTime;
    private int nrMaxClients;

    public int getNrMaxClients() {
        return nrMaxClients;
    }

    public void setNrMaxClients(int nrMaxClients) {
        this.nrMaxClients = nrMaxClients;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(Integer waitingTime) {
        this.waitingTime = waitingTime;
    }

    public Server(int nrMaxClients,BlockingQueue<Client> clienti) {
        this.nrMaxClients = nrMaxClients;
        this.clienti = clienti;
        this.waitingTime = 0;

    }

    public void  addClient(Client c) throws InterruptedException {

        clienti.put(c);
        waitingTime = waitingTime + c.gettService();
        setWaitingTime(waitingTime);
    }

    @Override
    public void run() {
        while (true) {
//            Client c = null;
//            try {
//                c = clienti.take();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            try {
//                Thread.sleep(c.gettService()*1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            waitingTime--;

            Thread.interrupted();
        }

    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }
}
