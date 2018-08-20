import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Sapper.Box;
import Sapper.Coord;
import Sapper.Game;
import Sapper.Ranges;
import com.sun.javafx.scene.control.skin.Utils;

//  JFrame to create an application window
public class JavaSapper extends JFrame {
    private Game game;
    private final int COLS = 12;
    private final int ROWS = 12;
    private final int BOMBS = 50;
    private final int IMAGE_SIZE = 50;
    private JLabel label;

    public static void main(String[] args) {
//    new object
        new JavaSapper();
    }

    //   contruction of panel
    private JPanel panel = new JPanel() {
        @Override
//    paint
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (Coord coord : Ranges.getAllCoords())

                g.drawImage((Image) game.getBox(coord).image,
                        coord.x * IMAGE_SIZE,
                        coord.y * IMAGE_SIZE, this);


        }
    };

    //    create construction
    private JavaSapper() {
        game = new Game(COLS, ROWS, BOMBS);
//            Ranges.setSize(COLS, ROWS);
        game.start();
        setImages();
        initPanel();
        initLabel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel(getMessage());
        Font font = new Font("Verdana", Font.ROMAN_BASELINE, 18);
        label.setFont(font);
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                switch (e.getButton()) {
                    case MouseEvent.BUTTON1:
                        game.pressLeftButton(coord);
                        break;
                    case MouseEvent.BUTTON3:
                        game.pressRightButton(coord);
                        break;
                    case MouseEvent.BUTTON2:
                        game.start();
                        break;
                }
                label.setText(getMessage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }

    private void initFrame() {
        //   force close app when we click on the red X
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//   set title on the window
        setTitle("Java Sapper");
//   not permit to resize our object
        setResizable(false);
        pack();
//   set parameter setVisible
        setVisible(true);
//   place in the center of screen
        setLocationRelativeTo(null);
    }

    private void setImages() {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
        setIconImage(getImage("icon"));
    }


    private Image getImage(String name) {
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }

    private String getMessage() {
        switch (game.getState()) {
            case BOMBED:
                return "Ba-Da-Boom! You Lose!";
            case WINNER:
                return "Congratulation! All bombs have been marked!";
            case PLAYED:
            default:
                if (game.getTotalFlaget() == 0)
                    return "Welcome!";
                else
                    return "Think twice! Flagget " +
                            game.getTotalFlaget() + " of " +
                            game.getTotalBombs() + " bombs";


        }
    }

}

