package UI;
import javax.swing.*;

public class FenetreQuitter extends JFrame {
    public FenetreQuitter() {
        setTitle("Quitter");
        setSize(200, 100);

        JButton quitterButton = new JButton("Quitter");
        quitterButton.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        panel.add(quitterButton);
        add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
