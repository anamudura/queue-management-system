import java.util.List;

public class ConcreteStrategyQueue implements Strategy{
    @Override
    public void addClient(List<Server> servers, Client c) {
        int min = Integer.MAX_VALUE;
        for (Server server : servers)
            if (server.getClienti().size() < min)
                min = server.getClienti().size();
        for (int i = 1;i<=servers.size();i++)
            if (servers.get(i).getClienti().size() == min)
            {
                System.out.println("In queue:" +i+" "+ c.getId()+" "+c.gettArrival()+" "+ c.gettService());
                servers.get(i).getClienti().add(c);
            }


    }
}
