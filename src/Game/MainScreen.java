package Game;

import javax.swing.*;
import java.awt.*;

class MainScreen extends JPanel {
    private HeartHealthGameApp parent;
    private EducationalContent educationalContent;

    public MainScreen(HeartHealthGameApp parent) {
        this.parent = parent;
        educationalContent = new EducationalContent();
        educationalContent.loadFromFile("educational_content.txt");

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Heart Health Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel levelPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        levelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 8; i++) {
            int levelNumber = i;
            JButton levelButton = new JButton("Poziom " + levelNumber);
            levelButton.addActionListener(e -> parent.startGame(createLevel(levelNumber)));
            levelPanel.add(levelButton);
        }

        add(levelPanel, BorderLayout.CENTER);

        JButton infoButton = new JButton("Edukacyjne Informacje");
        infoButton.addActionListener(e -> showEducationalContent());
        add(infoButton, BorderLayout.SOUTH);
    }

    private Level createLevel(int levelNumber) {
        String name = "Poziom " + levelNumber;
        HeartRhythmCurve curve = new HeartRhythmCurve(); // Domyślna krzywa
        String[] questions = {
                "Pytanie 1 dla Poziomu " + levelNumber,
                "Pytanie 2 dla Poziomu " + levelNumber
        };
        int ringSize = 50 + levelNumber * 5;
        int speed = 3 + levelNumber;
        return new Level(name, curve, questions, ringSize, speed);
    }
    private void showEducationalContent() {
        StringBuilder content = new StringBuilder();
        for (String line : educationalContent.getContent()) {
            content.append(line).append("\n");
        }
        JOptionPane.showMessageDialog(this, content.toString(), "Edukacyjne Informacje", JOptionPane.INFORMATION_MESSAGE);
    }
}