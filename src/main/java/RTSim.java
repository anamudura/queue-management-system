import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RTSim extends JPanel {
    JTextArea t;
    JLabel l;
    JButton b;
    JScrollPane s;
    public RTSim(int width, int height)
    {
        setLayout(null);
        setBackground(new Color(255, 213, 236, 171));
        setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLUE));
        setPreferredSize(new Dimension(width, height));
        t = new JTextArea();
        t.setEditable(false);
        s = new JScrollPane(t,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        l = new JLabel("REAL TIME EVOLUTION");
        b = new JButton("BACK");
        add(t);
        add(l);
        add(b);
        add(s);


        l.setBounds(15, height - 590, width - 400, 23);
        t.setBounds(15, height - 500, width - 350, 400);
        b.setBounds(15, height - 540, width - 400, 23);
        s.setBounds(15, height - 500, width - 350, 400);

    }
    void addBackButton(ActionListener back) {
        b.addActionListener(back);
    }
}
