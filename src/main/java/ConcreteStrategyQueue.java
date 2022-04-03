import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addClient(List<Server> servers, Client c, FileWriter f, RTSim t) throws IOException, InterruptedException {
        int min = Integer.MAX_VALUE;
        for (Server s : servers)
            if (s.getClienti().size() < min)
                min = s.getClienti().size();
        for (Server s : servers)
            if (s.getClienti().size() == min) {
                s.addClient(c);
                break;
            }


        int i = 1;
        for (Server s : servers) {
            f.write("Queue " + i + ":");
            t.t.append("Queue " + i + ":");
            if(s.getClienti().size() == 0)
            {
                f.write("closed");
                t.t.append("closed");
            }
            for (Client p : s.getClienti()) {

                f.write("(" + p.getId() + " " + p.gettArrival() +" service:"+p.gettService()+ ")   ");
                t.t.append("(" + p.getId() + " " + p.gettArrival() + ")   ");
            }
            f.write("\n");
            t.t.append("\n");
            i++;
        }

        }


    }

