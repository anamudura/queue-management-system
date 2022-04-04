import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {

    private Scheduler scheduler;
    FileWriter f = new FileWriter("D:/LabPT/PT2022_30221_Mudura_Ana_assigment_2/src/main/java/LOG.txt");

    private List<Client> generatedClients = new ArrayList<>();
    private int simulationTime;
    private int maxServiceTime;
    private int minServiceTime;
    private int maxArrivalTime;
    private int minArrivalTime;
    private int nrofServers;
    private int nrofClients;
    private int maxClients;
    private JFrame window;
    private RTSim simul;
    private List<Server> s;

    public List<Server> getS() {
        return s;
    }

    public void setS(List<Server> s) {
        this.s = s;
    }

    public RTSim getSimul() {
        window = new JFrame("RTSIM");
        simul = new RTSim(600, 600);
        window.setContentPane(simul);
        window.pack();
        window.setResizable(true);
        window.setLocation(150, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        return simul;
    }

    public void setSimul(RTSim simul) {
        this.simul = simul;
    }

    private SelectionPolicy selectionPolicy;

    public SelectionPolicy getSelectionPolicy() {
        return selectionPolicy;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }

    public Scheduler getScheduler(int nrofServers, int nrofClients, List<Server> s) {
        scheduler = new Scheduler(nrofServers, nrofClients, s);
        return scheduler;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public void setMaxClients(int maxClients) {
        this.maxClients = maxClients;
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

    public SimulationManager() throws IOException {
        this.generatedClients = generatedClients;
        this.simul = getSimul();
    }

    public void generateClients(int nrofClients, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime, List<Client> generatedClients) {
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
        int maxc = 0;
        int ct = 0;
        while (currentTime < simulationTime) {
            if (generatedClients.size() == 0)
                break;
            System.out.println("Timp: " + currentTime);
            simul.t.append("Timp curent: " + currentTime + "\n");
            try {
                f.write("\nTimp curent: " + currentTime + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            int i = 0;
            while (i < generatedClients.size()) {
                if (generatedClients.get(i).gettArrival() == currentTime) {
                    try {
                        System.out.println("main put" + generatedClients.get(i));
                        scheduler.dispatchClient(generatedClients.get(i), f, simul);
                        if (scheduler.ComputePeakTime(s) > maxc) {
                            maxc = scheduler.ComputePeakTime(s);
                            ct = currentTime;
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    generatedClients.remove(generatedClients.get(i));
                }

                if (i < generatedClients.size()) {
                    simul.t.append(" ID: " + generatedClients.get(i).getId() + " Arrival:" + generatedClients.get(i).gettArrival()
                            + " Service:" + generatedClients.get(i).gettService() + "\n");
                    try {
                        f.write(" ID:" + generatedClients.get(i).getId() + " Arrival:" + generatedClients.get(i).gettArrival()
                                + " Service:" + generatedClients.get(i).gettService() + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                if (i < generatedClients.size())
                    if (generatedClients.get(i).gettArrival() == currentTime) {
                        try {
                            System.out.println("main put" + generatedClients.get(i));
                            scheduler.dispatchClient(generatedClients.get(i), f, simul);
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                        generatedClients.remove(generatedClients.get(i));
                    }
                if (i >= generatedClients.size()) {
                    float average = scheduler.ComputeAverageServiceTime(s);
                    float waiting = scheduler.ComputeAverageWaitingTime(s);
                    simul.t.append("Average waiting time:" + waiting);
                    simul.t.append("\nPeak time:" + ct + "\n");
                    simul.t.append("Average service time:" + average + "\n");
                    try {
                        f.write("\nAverage service time:" + average);
                        f.write("\nPeak time:" + ct + "\n");
                        f.write("\nAverage waiting time:" + waiting + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                i++;

            }
            currentTime++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            simul.t.setText("");
        }

        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Control p = new Control();


    }
}
