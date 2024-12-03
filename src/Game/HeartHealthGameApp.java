package Game;

import javax.swing.*;

public class HeartHealthGameApp extends JFrame {
    private Level currentLevel;

    public HeartHealthGameApp() {
        setTitle("Heart Health Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        MainScreen mainScreen = new MainScreen(this);
        setContentPane(mainScreen);
        setVisible(true);
    }

    public void startGame(Level level) {
        this.currentLevel = level;
        GameScreen gameScreen = new GameScreen(this, level);
        setContentPane(gameScreen);
        revalidate();
    }

    public void returnToMainScreen() {
        MainScreen mainScreen = new MainScreen(this);
        setContentPane(mainScreen);
        revalidate();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HeartHealthGameApp::new);
    }
}