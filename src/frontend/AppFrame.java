package frontend;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {

    public AppFrame(){
        this.setMinimumSize(new Dimension(700, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Silicon Spatula");
        this.setLayout(new BorderLayout());
        // should be visible in windows
        ImageIcon appIcon = new ImageIcon("/assets/app_icon.png");
        this.setIconImage(appIcon.getImage());


        this.setVisible(true);
    }
}
