package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MainScreen extends JPanel {
    private JumpingHeartbeat parent;
    private EducationalContent educationalContent;
    public boolean[] unlockedLevels;
    String levelNameForButton;
    ImageIcon levelButtonIcon;

    public MainScreen(JumpingHeartbeat parent) {
        this.parent = parent;
        educationalContent = new EducationalContent();
        educationalContent.loadFromFile("educational_content.txt");
        Color offWhite=new Color(252,252,252);
        this.setBackground(offWhite);

        /*unlockedLevels = new boolean[8];
        unlockedLevels[0] = true;
        for (int i = 1; i < unlockedLevels.length; i++) {
            unlockedLevels[i] = false;
        }*/

        setLayout(new BorderLayout());

        ImageIcon nameIcon = new ImageIcon("name.jpg");
        Image scaledName = nameIcon.getImage().getScaledInstance(576, 102, Image.SCALE_SMOOTH);
        JLabel titleLabel = new JLabel(new ImageIcon(scaledName), SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        JPanel levelPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        levelPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        levelPanel.setBackground(offWhite);

        for (int i = 1; i <= 8; i++) {
            int levelNumber = i;
            switch (i) {
                case 1 -> {
                    levelNameForButton="Sinus";
                    levelButtonIcon = new ImageIcon("Level1.jpg");
                }
                case 2 -> {
                    levelNameForButton="Zdrowy rytm serca";
                    levelButtonIcon = new ImageIcon("Level2.jpg");
                }
                case 3 -> {
                    levelNameForButton="Bradykardia";
                    levelButtonIcon = new ImageIcon("Level3.jpg");
                }
                case 4 -> {
                    levelNameForButton="Migotanie Przedsionków";
                    levelButtonIcon = new ImageIcon("Level4.jpg");
                }
                case 5 -> {
                    levelNameForButton="Arytmia";
                    levelButtonIcon = new ImageIcon("Level5.jpg");
                }
                case 6 -> {
                    levelNameForButton="Częstoskurcz komorowy";
                    levelButtonIcon = new ImageIcon("Level6.jpg");
                }
                case 7 -> {
                    levelNameForButton="Tachykardia";
                    levelButtonIcon = new ImageIcon("Level7.jpg");
                }
                case 8 -> {
                    levelNameForButton="Asystolia";
                    levelButtonIcon = new ImageIcon("Level8.jpg");
                }
                default -> levelNameForButton="";
            }
            Image scaledIcon = levelButtonIcon.getImage().getScaledInstance(384, 108, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(scaledIcon);

            JButton levelButton = new JButton(levelNameForButton, scaledImageIcon);

            levelButton.setVerticalTextPosition(SwingConstants.BOTTOM);
            levelButton.setHorizontalTextPosition(SwingConstants.CENTER);
            levelButton.setContentAreaFilled(false);
            levelButton.setFocusPainted(false);

            if (1!=1) {
                levelButton.setEnabled(false);
            } else {
                levelButton.addActionListener(e -> parent.startGame(createLevel(levelNumber)));
            }
            levelButton.addActionListener(e -> parent.startGame(createLevel(levelNumber)));
            levelPanel.add(levelButton);
        }
        add(levelPanel, BorderLayout.CENTER);

        ImageIcon infoIcon = new ImageIcon("info.jpg");
        Image scaledImage = infoIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        JButton infoButton = new JButton(new ImageIcon(scaledImage));
        infoButton.setContentAreaFilled(false); // tło
        infoButton.addActionListener(e -> showEducationalContent());

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightPanel.add(infoButton, BorderLayout.NORTH);
        add(rightPanel, BorderLayout.EAST);
        rightPanel.setBackground(offWhite);

        ImageIcon restart = new ImageIcon("restart.jpg");
        Image scaledRestart = restart.getImage().getScaledInstance(70, 30, Image.SCALE_SMOOTH);
        JButton restartButton = new JButton(new ImageIcon(scaledRestart));
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);

        ImageIcon finish = new ImageIcon("finish.jpg");
        Image scaledFinish = finish.getImage().getScaledInstance(70, 30, Image.SCALE_SMOOTH);
        JButton finishButton = new JButton(new ImageIcon(scaledFinish));
        finishButton.setContentAreaFilled(false);
        finishButton.setBorderPainted(false);

        ImageIcon settings = new ImageIcon("settings.jpg");
        Image scaledSettings = settings.getImage().getScaledInstance(90, 30, Image.SCALE_SMOOTH);
        JButton settingsButton = new JButton(new ImageIcon(scaledSettings));
        settingsButton.setContentAreaFilled(false);
        settingsButton.setBorderPainted(false);

        JPanel downPanel = new JPanel(new BorderLayout());
        downPanel.setBackground(offWhite);

        JPanel downleftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        downleftPanel.setBackground(offWhite);
        downleftPanel.add(restartButton);
        downleftPanel.add(finishButton);

        JPanel downrightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        downrightPanel.setBackground(offWhite);
        downrightPanel.add(settingsButton);

        downPanel.add(downleftPanel, BorderLayout.WEST);
        downPanel.add(downrightPanel, BorderLayout.EAST);
        add(downPanel, BorderLayout.SOUTH);

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //action performed by this button
            }
        });

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
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