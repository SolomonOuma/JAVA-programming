
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PetSelector extends JFrame {
    private JRadioButton birdRadio, catRadio, dogRadio, rabbitRadio, pigRadio;
    private JLabel imageLabel;
    private ButtonGroup buttonGroup;

    // Updated for .jpg
    private final String[] imagePaths = {
        "images/bird.jpg",
        "images/cat.jpg",
        "images/dog.jpg",
        "images/rabbit.jpg",
        "images/pig.jpg"
    };

    private final String[] petNames = {"Bird", "Cat", "Dog", "Rabbit", "Pig"};

    public PetSelector() {
        setTitle("RadioButtonDemo");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Left panel: Radio buttons
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new GridLayout(5, 1, 10, 10));
        radioPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        birdRadio = new JRadioButton("Bird");
        catRadio = new JRadioButton("Cat");
        dogRadio = new JRadioButton("Dog");
        rabbitRadio = new JRadioButton("Rabbit");
        pigRadio = new JRadioButton("Pig", true);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(birdRadio);
        buttonGroup.add(catRadio);
        buttonGroup.add(dogRadio);
        buttonGroup.add(rabbitRadio);
        buttonGroup.add(pigRadio);

        radioPanel.add(birdRadio);
        radioPanel.add(catRadio);
        radioPanel.add(dogRadio);
        radioPanel.add(rabbitRadio);
        radioPanel.add(pigRadio);

        // Center: Image display
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        updateImage("Pig");

        // Action listeners
        ActionListener listener = new PetSelectionListener();
        birdRadio.addActionListener(listener);
        catRadio.addActionListener(listener);
        dogRadio.addActionListener(listener);
        rabbitRadio.addActionListener(listener);
        pigRadio.addActionListener(listener);

        add(radioPanel, BorderLayout.WEST);
        add(imageLabel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateImage(String petName) {
        int index = -1;
        for (int i = 0; i < petNames.length; i++) {
            if (petNames[i].equals(petName)) {
                index = i;
                break;
            }
        }

        if (index != -1) {
            try {
                ImageIcon icon = new ImageIcon(imagePaths[index]);
                Image image = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(image));
                imageLabel.setText("");
            } catch (Exception e) {
                imageLabel.setIcon(null);
                imageLabel.setText("Image not found: " + imagePaths[index]);
            }
        }
    }

    private class PetSelectionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton selected = (JRadioButton) e.getSource();
            String pet = selected.getText();
            updateImage(pet);

            JOptionPane.showMessageDialog(
                PetSelector.this,
                "You selected: " + pet,
                "Pet Selection",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PetSelector::new);
    }
}