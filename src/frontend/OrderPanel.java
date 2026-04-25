package frontend;

import backend.order.Order;
import javax.swing.*;
import java.awt.*;

public class OrderPanel extends JPanel {
    private static final DefaultListModel<Order> model = new DefaultListModel<>();

    public OrderPanel(){
        int TITLE_FONT_SIZE = 30;

        this.setBackground(new Color(0xF6B1B0));
        this.setLayout(new BorderLayout());
        JLabel title = new JLabel("Orders");
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

        // Create scrollable list
        JList<Order> visualList = new JList<>(model);
        visualList.setLayoutOrientation(JList.VERTICAL);
        visualList.setFixedCellHeight(25);
        visualList.setSelectionModel(new NoSelectionModel());
        visualList.setBackground(new Color(0xAEEEEE));

        JScrollPane scrollPane = new JScrollPane(visualList);
        scrollPane.setPreferredSize(new Dimension(300, 180));
        scrollPane.setMaximumSize(new Dimension(350, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        // List container
        JPanel listPane = new JPanel();
        listPane.setOpaque(false);
        listPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        listPane.add(scrollPane);

        centerPane.add(Box.createVerticalGlue());
        centerPane.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPane.add(listPane);
        centerPane.add(Box.createVerticalGlue());

        this.add(titleWrapper, BorderLayout.NORTH);
        this.add(centerPane, BorderLayout.CENTER);
    }

    // Was Retrieved from stackoverflow at:
    // https://stackoverflow.com/questions/31669350/disable-jlist-cell-selection-property
    private static class NoSelectionModel extends DefaultListSelectionModel {

        @Override
        public void setAnchorSelectionIndex(final int anchorIndex) {}

        @Override
        public void setLeadAnchorNotificationEnabled(final boolean flag) {}

        @Override
        public void setLeadSelectionIndex(final int leadIndex) {}

        @Override
        public void setSelectionInterval(final int index0, final int index1) { }
    }


}
