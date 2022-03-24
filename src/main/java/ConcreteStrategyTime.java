import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addClient(List<Server> servers, Client c) {
        int min = Integer.MAX_VALUE;
        for(Server s: servers)
            if(s.getWaitingTime()<min)
                min = s.getWaitingTime();
        for(int i = 1;i<=servers.size();i++)
            if(servers.get(i).getWaitingTime() == min) {
                System.out.println("In queue: " + c.getId());
                servers.get(i).addClient(c);
            }
    }
}
