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

    public void addClient(Client c)  {
        clienti.add(c);
        System.out.println(clienti.size());
        waitingTime = waitingTime + c.gettService();
        setWaitingTime(waitingTime);
    }

    @Override
    public void run() {
        while (true) {
            if(waitingTime > 0)
                waitingTime--;

                for(Client c: clienti)
                {
                    if(c.gettService() == 1)
                    {
                        try {
                            clienti.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        c.settService(c.gettService()-1);
                        System.out.println("AM SCAZUT TIMPUL");
                    }
                }

            try {
                Thread.sleep( 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public BlockingQueue<Client> getClienti() {
        return clienti;
    }
}
