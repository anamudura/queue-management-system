import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addClient(List<Server> servers, Client c,FileWriter f,RTSim t) throws IOException, InterruptedException {

        for(int i=0;i<servers.size();i++)
        {
            if(servers.get(i).getClienti().size()<servers.get(i).getNrMaxClients()) {
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
            for (Client p : s.getClienti()) {
                f.write("Queue: " + i + " " + p.getId()+ " "+p.gettArrival()+"\n");
                t.t.append("Queue: " + i + " " + p.getId()+ " "+p.gettArrival()+"\n");


            }
            i++;
        }


    }
}

