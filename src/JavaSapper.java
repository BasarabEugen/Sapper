import javax.swing.*;
import java.awt.*;

public class JavaSapper extends JFrame {
    private JPanel panel;


    public static void main(String[] args) {
        new JavaSapper();
    }

    private JavaSapper() {
        initPanel();
        initFrame();
    }

    private void initPanel() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(500, 300));
        add(panel);
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sapper");
        setVisible(true);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
}
