package Game;

import javax.swing.*;
import java.awt.*;

class MainScreen extends JPanel {
    private JumpingHeartbeat parent;
    private EducationalContent educationalContent;
    public boolean[] unlockedLevels;
    String levelNameForButton;

    public MainScreen(JumpingHeartbeat parent) {
        this.parent = parent;
        educationalContent = new EducationalContent();
        educationalContent.loadFromFile("educational_content.txt");

        /*unlockedLevels = new boolean[8];
        unlockedLevels[0] = true; // Odblokowany poziom 1
        for (int i = 1; i < unlockedLevels.length; i++) {
            unlockedLevels[i] = false; // Pozostałe poziomy zablokowane
        }*/

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Jumping Heartbeat", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        JPanel levelPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        levelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 1; i <= 8; i++) {
            int levelNumber = i;
            switch (i) {
                case 1 -> levelNameForButton="Sinus";
                case 2 -> levelNameForButton="Zdrowy rytm serca";
                case 3 -> levelNameForButton="Bradykardia";
                case 4 -> levelNameForButton="Migotanie Przedsionków";
                case 5 -> levelNameForButton="Arytmia";
                case 6 -> levelNameForButton="Częstoskurcz komorowy";
                case 7 -> levelNameForButton="Tachykardia";
                case 8 -> levelNameForButton="Asystolia";
                default -> levelNameForButton="";
            }
            //JButton levelButton = new JButton("Poziom " + levelNumber+" \n"+levelNameForButton);
            JButton levelButton = new JButton("<html><div style='text-align: center;'>Poziom " + levelNumber + "<br>" + levelNameForButton + "</div></html>");
            //if (!unlockedLevels[i - 1]) {
            if (1!=1) {
                levelButton.setEnabled(false); // Wyłącz przycisk dla zablokowanego poziomu
            } else {
                levelButton.addActionListener(e -> parent.startGame(createLevel(levelNumber)));
            }
            //levelButton.addActionListener(e -> parent.startGame(createLevel(levelNumber)));
            levelPanel.add(levelButton);
        }

        add(levelPanel, BorderLayout.CENTER);

        JButton infoButton = new JButton("Edukacyjne Informacje");
        infoButton.addActionListener(e -> showEducationalContent());
        add(infoButton, BorderLayout.SOUTH);


    }
    private Level createLevel(int levelNumber) {
        return new Level(levelNumber);
    }

    private void showEducationalContent() {
        StringBuilder content = new StringBuilder();
        for (String line : educationalContent.getContent()) {
            content.append(line).append("\n");
        }
        JOptionPane.showMessageDialog(this, content.toString(), "Edukacyjne Informacje", JOptionPane.INFORMATION_MESSAGE);
    }
}