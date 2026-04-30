package frontend;

import javax.swing.*;
import java.awt.*;

public class SystemLogPanel extends JPanel {
    private static final JTextArea systemLog = new JTextArea();

    public SystemLogPanel(){
        int TITLE_FONT_SIZE = 30;
        int PINK_BACKGROUND = 0xF6B1B0;
        int CYAN_BACKGROUND = 0xAEEEEE;

        this.setBackground(new Color(PINK_BACKGROUND));
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("System Log");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, TITLE_FONT_SIZE));
        JPanel titleWrapper = new JPanel();
        titleWrapper.setLayout(new FlowLayout());
        titleWrapper.setOpaque(false);
        titleWrapper.add(title);
        title.setAlignmentX(JLabel.CENTER);

        // Center Pane
        JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.Y_AXIS));
        centerPane.setOpaque(false);

        // Text Area
        systemLog.setBackground(new Color(CYAN_BACKGROUND));
        systemLog.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        systemLog.setEditable(false);
        systemLog.setLineWrap(true);
        systemLog.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(systemLog);

        scrollPane.setPreferredSize(new Dimension(520, 400));
        scrollPane.setMaximumSize(new Dimension(550, 450));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));

        JPanel textAreaPane = new JPanel();
        textAreaPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        textAreaPane.setOpaque(false);
        textAreaPane.add(scrollPane);

        centerPane.add(Box.createVerticalGlue());
        centerPane.add(textAreaPane);
        centerPane.add(Box.createVerticalGlue());

        this.add(titleWrapper, BorderLayout.NORTH);
        this.add(centerPane, BorderLayout.CENTER);
    }

    public static void appendLog(String text){
        systemLog.append(text + '\n');
    }
}
