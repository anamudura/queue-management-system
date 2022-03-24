import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;

    public List<Server> getServers() {
        return servers;
    }

    private int MaxServers;
    private int MaxClientsperServer;
    private Strategy strategy;

    public Scheduler(int MaxServers, int MaxClientsperServer) {
        servers = new ArrayList<Server>();
        for (int i = 1; i < MaxServers; i++) {
            Server s = new Server(MaxClientsperServer);
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
    public void dispatchClient(Client c)
    {
        //System.out.println("In queue:" + c.getId()+" "+c.gettArrival()+" "+ c.gettService());
        strategy.addClient(servers,c);
    }

}
