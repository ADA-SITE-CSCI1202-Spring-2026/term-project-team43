package frontend;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    public AppFrame(){
        int SIDEBAR_BUTTON_WIDTH = 100;
        int SIDEBAR_BUTTON_HEIGHT = 30;
        this.setMinimumSize(new Dimension(700, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Silicon Spatula");
        this.setLayout(new BorderLayout());
        // should be visible in windows
        ImageIcon appIcon = new ImageIcon("/assets/app_icon.png");
        this.setIconImage(appIcon.getImage());

        // Creating buttons
        JButton button1 = new JButton("Orders");
        JButton button2 = new JButton("Inventory");
        JButton button3 = new JButton("Restock");
        JButton button4 = new JButton("System log");

        button1.setPreferredSize(
                new Dimension(SIDEBAR_BUTTON_WIDTH, SIDEBAR_BUTTON_HEIGHT)
        );
        button2.setPreferredSize(
                new Dimension(SIDEBAR_BUTTON_WIDTH, SIDEBAR_BUTTON_HEIGHT)
        );
        button3.setPreferredSize(
                new Dimension(SIDEBAR_BUTTON_WIDTH, SIDEBAR_BUTTON_HEIGHT)
        );
        button4.setPreferredSize(
                new Dimension(SIDEBAR_BUTTON_WIDTH, SIDEBAR_BUTTON_HEIGHT)
        );

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttons.setPreferredSize(new Dimension(150, 145));
        buttons.setMaximumSize(new Dimension(150, 145));
        buttons.setOpaque(false);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        // Adding buttons to the sidebar
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(150, 500));
        sidebar.setBackground(Color.DARK_GRAY);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(buttons);
        sidebar.add(Box.createVerticalGlue());

        this.add(sidebar, BorderLayout.WEST);
        this.setVisible(true);

    }
}
