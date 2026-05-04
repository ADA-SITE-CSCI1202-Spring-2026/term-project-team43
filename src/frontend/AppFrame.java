package frontend;

import app.AppManager;
import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    public AppFrame(AppManager appManager){
        // Setting up the panels
        JPanel ORDER_PANEL = new OrderPanel(appManager);
        JPanel INVENTORY_PANEL = new InventoryPanel(appManager);
        JPanel RESTOCK_PANEL = new RestockPanel(appManager);
        JPanel SYSTEM_LOG_PANEL = new SystemLogPanel(appManager);

        int SIDEBAR_BUTTON_WIDTH = 100;
        int SIDEBAR_BUTTON_HEIGHT = 30;
        this.setMinimumSize(new Dimension(700, 500));
        this.setMaximumSize(new Dimension(900, 600));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        // Change main content using card layout
        JPanel mainContent = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainContent.setLayout(cardLayout);
        mainContent.add(ORDER_PANEL, "Order Panel");
        mainContent.add(INVENTORY_PANEL, "Inventory Panel");
        mainContent.add(RESTOCK_PANEL, "Restock Panel");
        mainContent.add(SYSTEM_LOG_PANEL, "System Log Panel");

        button1.addActionListener(
                e -> cardLayout.show(mainContent, "Order Panel")
        );
        button2.addActionListener(
                e -> cardLayout.show(mainContent, "Inventory Panel")
        );
        button3.addActionListener(
                e -> cardLayout.show(mainContent, "Restock Panel")
        );
        button4.addActionListener(
                e -> cardLayout.show(mainContent, "System Log Panel")
        );

        this.add(sidebar, BorderLayout.WEST);
        this.add(mainContent, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
