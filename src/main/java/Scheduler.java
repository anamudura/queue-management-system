import java.util.List;

public class Scheduler {
    private List<Server> servers;

    public List<Server> getServers() {
        return servers;
    }

    private int MaxServers;
    private int MaxTask;
    private Strategy strategy;

    public Scheduler(int MaxServers, int MaxTask) {
        for (int i = 1; i < MaxServers; i++) {
            Server s = new Server();
            Thread t = new Thread(s);

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
        strategy.addClient(servers,c);
    }

}
