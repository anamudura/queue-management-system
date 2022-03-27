import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public interface Strategy {
    public void addClient(List<Server> servers, Client c, FileWriter f,RTSim t) throws IOException, InterruptedException;
}
