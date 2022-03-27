import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addClient(List<Server> servers, Client c, FileWriter f,RTSim t) throws IOException, InterruptedException {
        int min = c.gettService();
        for (Server s : servers)
            if (s.getWaitingTime() < min)
                min = s.getWaitingTime();
        for(int i=0;i<servers.size();i++)
        {
            if(servers.get(i).getWaitingTime() == min) {
                servers.get(i).getClienti().put(c);
                break;
            }
            else
            if(i<servers.size()-1) {
                servers.get(i + 1).getClienti().put(c);
                break;
            }
        }
        int i = 1;
        for(Server s:servers) {
            f.write("Queue " + i+ ":");
            for (Client p : s.getClienti()) {
                f.write("(" + p.getId()+ " "+p.gettArrival()+"), ");
                t.t.append("(" + p.getId()+ " "+p.gettArrival()+"), ");

            }
            f.write("\n");
            t.t.append("\n");
            i++;
        }
    }
}
