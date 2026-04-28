package frontend;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private final static JTextArea inventoryInfo = new JTextArea(5, 25);

    public InventoryPanel(){
        int PINK_BACKGROUND = 0xF6B1B0;
        int CYAN_BACKGROUND = 0xAEEEEE;
        int TITLE_FONT_SIZE = 30;
        int TEXT_AREA_FONT_SIZE = 15;

        this.setBackground(new Color(PINK_BACKGROUND));
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Inventory");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, TITLE_FONT_SIZE));
        JPanel titleWrapper = new JPanel();
        titleWrapper.setLayout(new FlowLayout());
        titleWrapper.setOpaque(false);
        titleWrapper.add(title);
        title.setAlignmentX(JLabel.CENTER);

        // Setting up TextArea used of inventoryInfo
        inventoryInfo.setFont(
                new Font(Font.SANS_SERIF, Font.ITALIC, TEXT_AREA_FONT_SIZE)
        );

        inventoryInfo.setEditable(false);
        inventoryInfo.setLineWrap(true);
        inventoryInfo.setWrapStyleWord(true);
        inventoryInfo.setBackground(new Color(CYAN_BACKGROUND));

        JScrollPane inventoryScrollPane = new JScrollPane(inventoryInfo);
        inventoryScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
        );

        inventoryScrollPane.setPreferredSize(new Dimension(300, 250));
        inventoryScrollPane.setMaximumSize(new Dimension(300, 270));
        inventoryScrollPane.setBorder(
                BorderFactory.createLineBorder(Color.black, 5)
        );

        // Displaying inventory Info
        JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.Y_AXIS));
        centerPane.setOpaque(false);

        JPanel inventoryPane = new JPanel();
        inventoryPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        inventoryPane.setOpaque(false);
        inventoryPane.add(inventoryScrollPane);

        centerPane.add(Box.createVerticalGlue());
        centerPane.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPane.add(inventoryPane);
        centerPane.add(Box.createVerticalGlue());

        this.add(titleWrapper, BorderLayout.NORTH);
        this.add(centerPane, BorderLayout.CENTER);
    }

    public static void setInventoryInfo(String info){
        inventoryInfo.setText(info);
    }
}
