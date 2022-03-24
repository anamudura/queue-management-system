import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {

    private Scheduler scheduler;


    private List<Client> generatedClients = new ArrayList<>();
    private int simulationTime;
    private int maxServiceTime;
    private int minServiceTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int nrofServers;
    private int nrofClients;
    private SelectionPolicy selectionPolicy;

    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public Scheduler getScheduler(int nrofServers, int nrofClients) {
        scheduler = new Scheduler(nrofServers,nrofClients);
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public List<Client> getGeneratedClients() {
        return generatedClients;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public void setMaxServiceTime(int maxServiceTime) {
        this.maxServiceTime = maxServiceTime;
    }

    public void setMinServiceTime(int minServiceTime) {
        this.minServiceTime = minServiceTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public void setNrofServers(int nrofServers) {
        this.nrofServers = nrofServers;
    }

    public void setNrofClients(int nrofClients) {
        this.nrofClients = nrofClients;
    }


    public int getSimulationTime() {
        return simulationTime;
    }

    public int getMaxServiceTime() {
        return maxServiceTime;
    }

    public int getMinServiceTime() {
        return minServiceTime;
    }

    public int getMaxArrivalTime() {
        return maxArrivalTime;
    }

    public int getMinArrivalTime() {
        return minArrivalTime;
    }

    public int getNrofServers() {
        return nrofServers;
    }

    public int getNrofClients() {
        return nrofClients;
    }

    public SimulationManager() {
        this.generatedClients = generatedClients;
    }

    public void generateClients(int nrofClients,int minArrivalTime,int maxArrivalTime,int minServiceTime,int maxServiceTime,List<Client> generatedClients) {
        for (int i = 0; i < nrofClients; i++) {
            Client c = new Client();
            c.settService(c.GenerateService(minServiceTime, maxServiceTime));
            c.settArrival(c.GenerateArrival(minArrivalTime, maxArrivalTime));
            c.setId(c.GenerateID(nrofClients));
            if (generatedClients.size() > 1) {
                for (int j = 0; j < generatedClients.size(); j++)
                    if (generatedClients.get(j).getId() == c.getId())
                        while (generatedClients.get(j).getId() == c.getId()) {
                            c.setId(c.GenerateID(nrofClients));
                        }

            }

            generatedClients.add(c);
        }
        Collections.sort(generatedClients);
    }

    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < simulationTime) {
            if (generatedClients.size() == 0)
                break;
            System.out.println("Waiting clients");
            for (int i = 0; i < generatedClients.size(); i++) {
                if (generatedClients.get(i).gettArrival() == currentTime) {
                    scheduler.dispatchClient(generatedClients.get(i));
                    generatedClients.remove(generatedClients.get(i));
                }

                if (i < generatedClients.size()){
                    System.out.println("Timp curent: " + currentTime + " ID:" + generatedClients.get(i).getId() + " Arrival:" + generatedClients.get(i).gettArrival()
                            + " Service:" + generatedClients.get(i).gettService());
                    }

            }

            currentTime++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//
        }

    }


    public static void main(String[] args) {
        JFrame window = new JFrame("SIMULATION MANAGER");
        UI frame = new UI(500, 500);
        window.setContentPane(frame);
        window.pack();
        window.setResizable(true);
        window.setLocation(150, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        Control p = new Control(frame);



    }
}
