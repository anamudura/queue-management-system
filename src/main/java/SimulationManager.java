import java.util.Collections;
import java.util.List;

public class SimulationManager implements Runnable {

    private Scheduler scheduler;
    private UI frame;
    private List<Client> generatedClients;

    public int simulationTime = Integer.parseInt(frame.t3.getText());
    public int maxServiceTime = Integer.parseInt(frame.t7.getText());
    public int minServiceTime = Integer.parseInt(frame.t6.getText());
    public int maxArrivalTime = Integer.parseInt(frame.t5.getText());
    public int minArrivalTime = Integer.parseInt(frame.t4.getText());
    public int nrofServers = Integer.parseInt(frame.t2.getText());
    public int nrofClients = Integer.parseInt(frame.t1.getText());
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    public SimulationManager() {
        scheduler = new Scheduler(nrofServers, nrofClients);
        for (int i = 1; i <= nrofServers; i++) {
            Thread t = new Thread();
            t.start();
        }
        ConcreteStrategyQueue q1 = new ConcreteStrategyQueue();
        ConcreteStrategyTime q2 = new ConcreteStrategyTime();
        // - de initializat frame pentru simulare
        generateClients();



    }
    private void generateClients()
    {
         for(int i = 0;i<nrofClients;i++)
         {
             Client c = new Client();
             c.settService(c.GenerateService(minServiceTime, maxServiceTime));
             c.settArrival(c.GenerateArrival(minArrivalTime, maxArrivalTime));
             c.setId(c.GenerateID(nrofClients));
             generatedClients.add(c);
         }
        Collections.sort(generatedClients);
    }

    @Override
    public void run() {
        int currentTime = 0;
        while(currentTime<simulationTime)
        {
            for(Client i: generatedClients)
                if(i.gettArrival()==currentTime) {
                    scheduler.dispatchClient(i);
                    generatedClients.remove(i);
                }
            currentTime++;


        }

    }


    //    public static void main(String[] args) {
//        JFrame window = new JFrame("SIMULATION MANAGER");
//        UI view = new UI(500,500);
//        window.setContentPane(view);
//        window.pack();
//        window.setResizable(true);
//        window.setLocation(150,100);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setVisible(true);
//    }
}
