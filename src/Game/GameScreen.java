package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class GameScreen extends JPanel {
    private HeartHealthGameApp parent;
    private Level level;
    private int score;

    public GameScreen(HeartHealthGameApp parent, Level level) {
        this.parent = parent;
        this.level = level;
        this.score = 0;

        setLayout(new BorderLayout());

        JLabel levelLabel = new JLabel("Rozgrywka: " + level.getName(), SwingConstants.CENTER);
        levelLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(levelLabel, BorderLayout.NORTH);

        JButton endGameButton = new JButton("Zakończ grę");
        endGameButton.addActionListener(this::endGame);
        add(endGameButton, BorderLayout.SOUTH);

        // Tu można dodać elementy do rysowania krzywej i pierścienia
        JPanel gamePanel = new JPanel();
        gamePanel.setBackground(Color.WHITE);
        add(gamePanel, BorderLayout.CENTER);
    }

    private void endGame(ActionEvent e) {
        parent.returnToMainScreen();
    }
}