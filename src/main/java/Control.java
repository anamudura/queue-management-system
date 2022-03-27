import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Control{
    private UI frame;
    private JFrame window;
    private RTSim simul;



    public Control()
    {
        window = new JFrame("SIMULATION MANAGER");
        frame = new UI(650, 500);
        window.setContentPane(frame);
        window.pack();
        window.setResizable(true);
        window.setLocation(150, 100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        frame.addComputeButton(new StartListener());
    }

    class StartListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(frame.b.getText().equals("START")) {
                SimulationManager sim = null;
                try {
                    sim = new SimulationManager();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                sim.setSimulationTime(Integer.parseInt(frame.t3.getText()));
                sim.setMaxServiceTime(Integer.parseInt(frame.t7.getText()));
                sim.setMinServiceTime(Integer.parseInt(frame.t6.getText()));
                sim.setMaxArrivalTime(Integer.parseInt(frame.t5.getText()));
                sim.setMinArrivalTime(Integer.parseInt(frame.t4.getText()));
                sim.setNrofClients(Integer.parseInt(frame.t1.getText()));
                sim.setNrofServers(Integer.parseInt(frame.t2.getText()));
                sim.setMaxClients(Integer.parseInt(frame.t8.getText()));
                List<Server> servers = new ArrayList<Server>(sim.getNrofServers());
                sim.setS(servers);
                sim.setScheduler(sim.getScheduler(sim.getNrofServers(), sim.getMaxClients(),servers));
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
                sim.getScheduler(sim.getNrofServers(),sim.getMaxClients(),servers).ChangeStratey(sim.getSelectionPolicy());
                sim.generateClients(sim.getNrofClients(),sim.getMinArrivalTime(),sim.getMaxArrivalTime(),sim.getMinServiceTime(),sim.getMaxServiceTime(),sim.getGeneratedClients());
                Thread t = new Thread(sim);
                t.start();

                window.dispose();
                sim.setSimul(sim.getSimul());



            }
        }
    }
     class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(simul.b.getText().equals("BACK"))
            {
                window.dispose();
                window = new JFrame("SIMULATION MANAGER");
                frame = new UI(650, 500);
                window.setContentPane(frame);
                window.pack();
                window.setResizable(true);
                window.setLocation(150, 100);
                window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                window.setVisible(true);
                frame.addComputeButton(new StartListener());

            }

        }
    }

}
