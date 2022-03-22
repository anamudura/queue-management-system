import javax.swing.*;
import java.awt.*;

public class UI extends JPanel {
    JTextField t1, t2, t3, t4, t5, t6, t7;
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JButton b = new JButton("START SIIMULATION");

    public UI(int width, int height) {
        setLayout(null);
        setBackground(new Color(181, 241, 230, 171));
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        setPreferredSize(new Dimension(width, height));

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        t7 = new JTextField();

        l1 = new JLabel("Introduce the number of clients");
        l2 = new JLabel("Introduce the number of queues");
        l3 = new JLabel("Introduce the simulation time");
        l4 = new JLabel("Minimul arrival time");
        l5 = new JLabel("Maximum arrival time");
        l6 = new JLabel("Minimul service time");
        l7 = new JLabel("Maximum service time");

        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(t6);
        add(t7);

        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(l5);
        add(l6);
        add(l7);
        add(b);

        l1.setBounds(15, height - 480, width - 200, 23);
        t1.setBounds(15, height - 450, width - 400, 23);
        l2.setBounds(15, height - 420, width - 200, 23);
        t2.setBounds(15, height - 390, width - 400, 23);
        l3.setBounds(15, height - 360, width - 200, 23);
        t3.setBounds(15, height - 330, width - 400, 23);
        l4.setBounds(15, height - 300, width - 200, 23);
        t4.setBounds(15, height - 270, width - 400, 23);
        l5.setBounds(15, height - 240, width - 200, 23);
        t5.setBounds(15, height - 210, width - 400, 23);
        l6.setBounds(15, height - 180, width - 200, 23);
        t6.setBounds(15, height - 150, width - 400, 23);
        l7.setBounds(15, height - 120, width - 200, 23);
        t7.setBounds(15, height - 90, width - 400, 23);
        b.setBounds(15, height - 60, width - 200, 23);


    }
}
