import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Control{
    private UI frame;
    public Control(UI frame)
    {
        this.frame = frame;
        frame.addComputeButton(new StartListener());
    }

    class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(frame.b.getText().equals("START")) {
                System.out.println("YAS");
                SimulationManager sim = new SimulationManager();
                sim.setSimulationTime(Integer.parseInt(frame.t3.getText()));
                sim.setMaxServiceTime(Integer.parseInt(frame.t7.getText()));
                sim.setMinServiceTime(Integer.parseInt(frame.t6.getText()));
                sim.setMaxArrivalTime(Integer.parseInt(frame.t5.getText()));
                sim.setMinArrivalTime(Integer.parseInt(frame.t4.getText()));
                sim.setNrofClients(Integer.parseInt(frame.t1.getText()));
                sim.setNrofServers(Integer.parseInt(frame.t2.getText()));
                sim.setScheduler(sim.getScheduler(sim.getNrofServers(), sim.getNrofClients()));
                for (int i = 1; i <= sim.getNrofServers(); i++) {
                    Thread t = new Thread();
                    t.start();
                }
                switch(frame.operator.getSelectedIndex())
                {
                    case 0:
                    {
                        sim.setSelectionPolicy(SelectionPolicy.SHORTEST_TIME);
                    }
                    case 1:
                    {
                        sim.setSelectionPolicy(SelectionPolicy.SHORTEST_QUEUE);
                    }
                }
                sim.getScheduler(sim.getNrofServers(),sim.getNrofClients()).ChangeStratey(sim.getSelectionPolicy());
                sim.generateClients(sim.getNrofClients(),sim.getMinArrivalTime(),sim.getMaxArrivalTime(),sim.getMinServiceTime(),sim.getMaxServiceTime(),sim.getGeneratedClients());
                Thread t = new Thread(sim);
                t.start();

            }
        }
    }

}
