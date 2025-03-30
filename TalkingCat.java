import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TalkingCat extends JPanel {
    private CatPanel catPanel;

    public TalkingCat() {
        setSize(600, 600);
        setLayout(new BorderLayout());
        JLabel catLabel = new JLabel("Mau Mau the Cat", SwingConstants.CENTER);
        catLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(catLabel, BorderLayout.NORTH);
        int choice = JOptionPane.showConfirmDialog(
                null,
                "Do you want to customize the cat?",
                "Customize Cat",
                JOptionPane.YES_NO_OPTION
        );

        Color bodyColor;
        Color backgroundColor;

        if (choice == JOptionPane.YES_OPTION) {
            // Customize cat: Ask the user for colors
            bodyColor = JColorChooser.showDialog(null, "Choose Body Color", Color.WHITE);
            if (bodyColor == null) bodyColor = Color.WHITE;

            backgroundColor = JColorChooser.showDialog(null, "Choose Background Color", Color.LIGHT_GRAY);
            if (backgroundColor == null) backgroundColor = Color.LIGHT_GRAY;
        } else {
            // Use default colors
            bodyColor = Color.WHITE;
            backgroundColor = Color.LIGHT_GRAY;
        }

        // Create CatPanel with the selected or default colors
        catPanel = new CatPanel(bodyColor, backgroundColor);
        add(catPanel, BorderLayout.CENTER);

        // Add buttons for actions
        JPanel buttonPanel = new JPanel(new FlowLayout());
        String[] actions = {"Feed", "Sleep", "Dance", "Customize"};
        for (String action : actions) {
            JButton button = new JButton(action);
            button.addActionListener(new CatActionListener(action, catPanel));
            buttonPanel.add(button);
        }
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TalkingCat());
    }
}

class CatActionListener implements ActionListener {
    private String action;
    private CatPanel catPanel;

    public CatActionListener(String action, CatPanel catPanel) {
        this.action = action;
        this.catPanel = catPanel;
    }

public void actionPerformed(ActionEvent e) {
    switch (action) {
        case "Feed":
            System.out.println("Feed action triggered!");
            JOptionPane.showMessageDialog(catPanel, "Mau Mau is eating!", "Feed", JOptionPane.INFORMATION_MESSAGE);
            catPanel.eat();
            break;
        case "Sleep":
            System.out.println("Sleep action triggered!");
            JOptionPane.showMessageDialog(catPanel, "Mau Mau is sleeping!", "Sleep", JOptionPane.INFORMATION_MESSAGE);
            catPanel.sleep();
            break;
        case "Dance":
            System.out.println("Dance action triggered!");
            JOptionPane.showMessageDialog(catPanel, "Mau Mau purrs happily!", "Pet", JOptionPane.INFORMATION_MESSAGE);
            catPanel.pet();
            break;
        case "Customize":
            System.out.println("Dress action triggered!");
            Color newBodyColor = JColorChooser.showDialog(null, "Choose New Body Color", Color.WHITE);
            if (newBodyColor == null) newBodyColor = Color.WHITE;
            Color newBackgroundColor = JColorChooser.showDialog(null, "Choose New Background Color", Color.LIGHT_GRAY);
            if (newBackgroundColor == null) newBackgroundColor = Color.LIGHT_GRAY;
            catPanel.change(newBodyColor, newBackgroundColor);
            break;
    }
}
}