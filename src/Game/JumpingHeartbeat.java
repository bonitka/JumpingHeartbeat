package Game;

import javax.swing.*;

public class JumpingHeartbeat extends JFrame {
    private Level currentLevel;


    public JumpingHeartbeat() {
        setTitle("Jumping Heartbeat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setResizable(false);
        setLocationRelativeTo(null);

        MainScreen mainScreen = new MainScreen(this);
        setContentPane(mainScreen);
        setVisible(true);
    }

    public void startGame(Level level) {
        this.currentLevel = level;
        GameScreen gameScreen = new GameScreen(this, level);
        setContentPane(gameScreen);
        revalidate();
        repaint();
    }

    public void returnToMainScreen() {
        MainScreen mainScreen = new MainScreen(this);
        setContentPane(mainScreen);
        revalidate();
    }

    public void unlockNextLevel(int completedLevel) {
        MainScreen mainScreen = (MainScreen) getContentPane();
        if (completedLevel < mainScreen.unlockedLevels.length) {
            mainScreen.unlockedLevels[completedLevel] = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(JumpingHeartbeat::new);
    }
}