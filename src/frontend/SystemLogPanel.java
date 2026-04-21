package frontend;

import javax.swing.*;
import java.awt.*;

public class SystemLogPanel extends JPanel {
    public SystemLogPanel(){
        int TITLE_FONT_SIZE = 30;
        this.setBackground(new Color(0xF6B1B0));
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("System Log");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, TITLE_FONT_SIZE));
        JPanel titleWrapper = new JPanel();
        titleWrapper.setLayout(new FlowLayout());
        titleWrapper.setOpaque(false);
        titleWrapper.add(title);
        title.setAlignmentX(JLabel.CENTER);
        this.add(titleWrapper, BorderLayout.NORTH);
    }
}
