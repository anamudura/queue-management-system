import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Scheduler {
    private List<Server> servers;

    public List<Server> getServers() {
        return servers;
    }

    private int MaxServers;
    private int MaxClientsperServer;
    private Strategy strategy;

    public Scheduler(int MaxServers, int MaxClientsperServer,List<Server> servers) {
        this.servers = servers;
        this.MaxClientsperServer = MaxClientsperServer;
        this.MaxServers = MaxServers;
        for (int i = 1; i < MaxServers; i++) {
            BlockingQueue<Client> clienti =  new ArrayBlockingQueue<Client>(MaxClientsperServer);;
            Server s = new Server(MaxClientsperServer,clienti);
            servers.add(s);
            Thread t = new Thread(s);
            t.start();

        }
    }
    public void ChangeStratey(SelectionPolicy policy)
    {
        if(policy == SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ConcreteStrategyQueue();
        if(policy == SelectionPolicy.SHORTEST_TIME)
            strategy = new ConcreteStrategyTime();
    }
    public void dispatchClient(Client c, FileWriter f,RTSim t) throws IOException, InterruptedException {
        System.out.println("dispatch " +c);
        strategy.addClient(servers,c,f,t);
    }
    public float ComputeAverageServiceTime(List<Server> servers)
    {
        float su=0,sum;
        for(Server s: servers) {
            sum = 0;
            for (Client p : s.getClienti())
                sum = p.gettService() + sum;
            sum = sum / s.getClienti().size();
            su = su + sum;
        }
        return su;

    }
    public float ComputeAverageWaitingTime(List<Server> servers)
    {
        float avg=0;
        for(Server s: servers) {
            avg = avg + s.getWaitingTime();
        }
        avg = avg + servers.size();
        return avg;
    }
    public int ComputePeakTime(List<Server> servers)
    {
        int sum = 0;
        for(Server s: servers)
        {
            if(!(s.getClienti().isEmpty()))
                sum = sum + s.getClienti().size();
        }
        return sum;
    }

}
