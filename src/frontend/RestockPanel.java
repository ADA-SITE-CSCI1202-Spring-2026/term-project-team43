package frontend;

import backend.pantry.Ingredient;
import javax.swing.*;
import java.awt.*;

public class RestockPanel extends JPanel {

    private final static DefaultComboBoxModel<Ingredient> ingredientData =
            new DefaultComboBoxModel<>();

    public RestockPanel(){
        int TITLE_FONT_SIZE = 30;
        int PINK_BACKGROUND = 0xF6B1B0;
        int CYAN_BACKGROUND = 0xAEEEEE;
        int DROPDOWN_LABEL_FONT_SIZE = 15;

        this.setBackground(new Color(PINK_BACKGROUND));
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Restock");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, TITLE_FONT_SIZE));
        JPanel titleWrapper = new JPanel();
        titleWrapper.setLayout(new FlowLayout());
        titleWrapper.setOpaque(false);
        titleWrapper.add(title);
        title.setAlignmentX(JLabel.CENTER);

        // Layout, for the BorderLayout.Center
        JPanel centerPane = new JPanel();
        centerPane.setLayout(new BoxLayout(centerPane, BoxLayout.Y_AXIS));
        centerPane.setOpaque(false);

        // Dropdown List
        JLabel listLabel = new JLabel("Selected Ingredient: ");
        listLabel.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, DROPDOWN_LABEL_FONT_SIZE));

        JComboBox<Ingredient> ingredientList = new JComboBox<>(ingredientData);
        ingredientList.setBackground(new Color(CYAN_BACKGROUND));
        ingredientList.setPreferredSize(new Dimension(300, 50));
        ingredientList.setMaximumSize(new Dimension(350, 80));

        // Dropdown List container
        JPanel dropdownPane = new JPanel();
        dropdownPane.setOpaque(false);
        dropdownPane.setLayout(new BoxLayout(dropdownPane, BoxLayout.X_AXIS));
        dropdownPane.add(listLabel);
        dropdownPane.add(Box.createRigidArea(new Dimension(-3, 0)));
        dropdownPane.add(ingredientList);

        centerPane.add(Box.createVerticalGlue());
        centerPane.add(dropdownPane);
        centerPane.add(Box.createVerticalGlue());
        centerPane.add(Box.createVerticalGlue());

        this.add(centerPane, BorderLayout.CENTER);
        this.add(titleWrapper, BorderLayout.NORTH);
    }
}
