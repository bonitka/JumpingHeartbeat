package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;
import java.util.List;


class GameScreen extends JPanel {
    private JumpingHeartbeat parent;
    private Level level;
    private int score;
    private Ring ring;
    private boolean isGameOver = false;
    private Timer movementTimer;
    private boolean dialogShown = false;
    //int speed = SettingsDialog.getCurveSpeedSet();
    int speed = 5;
    Color reddish=new Color(130,7,8);

    public GameScreen(JumpingHeartbeat parent, Level level) {
        this.parent = parent;
        this.level = level;
        this.score = 0;
        ring = new Ring(new Point(100, 250), level.getRingSize(), level.getRingSize() * 4);

        Color offWhite=new Color(252,252,252);
        this.setBackground(offWhite);

        setLayout(new BorderLayout());

        JLabel levelImageLabel = new JLabel(level.getLevelImage());
        JLabel pointsLabel = new JLabel("Zebrane punkty: " + score);
        pointsLabel.setFont(new Font("Muli",Font.PLAIN,20));
        pointsLabel.setBorder(BorderFactory.createLineBorder(reddish,7));
        pointsLabel.setBackground(offWhite);
        pointsLabel.setBorder(BorderFactory.createCompoundBorder(
                pointsLabel.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        JPanel upPanel = new JPanel(new BorderLayout());
        upPanel.setBackground(offWhite);

        JPanel uprightPanel = new JPanel(new BorderLayout());
        uprightPanel.setBackground(offWhite);
        uprightPanel.add(pointsLabel, BorderLayout.EAST);
        uprightPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        JPanel upleftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        upleftPanel.setBackground(offWhite);
        upleftPanel.add(levelImageLabel);
        upPanel.add(upleftPanel, BorderLayout.WEST);
        upPanel.add(uprightPanel, BorderLayout.EAST);
        add(upPanel, BorderLayout.NORTH);

        ImageIcon restart = new ImageIcon("restart.jpg");
        Image scaledRestart = restart.getImage().getScaledInstance(105, 45, Image.SCALE_SMOOTH);
        JButton restartButton = new JButton(new ImageIcon(scaledRestart));
        restartButton.setContentAreaFilled(false);
        restartButton.setBorderPainted(false);

        ImageIcon finish = new ImageIcon("finish.jpg");
        Image scaledFinish = finish.getImage().getScaledInstance(105, 45, Image.SCALE_SMOOTH);
        JButton finishButton = new JButton(new ImageIcon(scaledFinish));
        finishButton.setContentAreaFilled(false);
        finishButton.setBorderPainted(false);

        JPanel downPanel = new JPanel(new BorderLayout());
        downPanel.setBackground(offWhite);

        JPanel downleftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        downleftPanel.setBackground(offWhite);
        downleftPanel.add(restartButton);
        downleftPanel.add(finishButton);

        downPanel.add(downleftPanel, BorderLayout.WEST);
        add(downPanel, BorderLayout.SOUTH);

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        finishButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Zamyka system
            }
        });

        setFocusable(true);
        addHierarchyListener(e -> {
            if (isShowing()) {
                requestFocusInWindow();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        movementTimer = new Timer(50, e -> {
            if (isGameOver==true) {
                movementTimer.stop();
                return;
            }
            level.getCurve().moveCurve(-speed);
            check();
            if(isGameOver == false)
            {
                checkWin();
            }
            repaint();
        });
        movementTimer.start();
    }

    private void resetGame() {
        ring.setCenter(new Point(100, 250));
        ring.setRadiusX(level.getRingSize());
        ring.setRadiusY(level.getRingSize() * 4);

        level.getCurve().reset();

        score = 0;
        isGameOver = false;
        dialogShown = false;

        movementTimer.start();

        repaint();
    }

    private void check(){
        if (isGameOver) {
            return;
        }
        for (Point point : level.getCurve().getCurvePoints()){
            if (point.x== ring.getCenterX()) {
                if (point.y >= (ring.getCenterY() - ring.getRadiusY()) && point.y <= (ring.getCenterY() + ring.getRadiusY())) {
                    return;
                }else{
                    isGameOver=true;
                    movementTimer.stop();
                    colision();
                    return;
                }
            }
        }
    }

    private void checkWin() {
        if (isGameOver || dialogShown){
            return;
        }
        boolean allPointsOffRing = level.getCurve().getCurvePoints().stream().allMatch(point -> point.x < 90);
        if (allPointsOffRing) {
            isGameOver = true;
            dialogShown = true;
            movementTimer.stop();
            JOptionPane.showMessageDialog(this, "Gratulacje! Wygrałeś poziom.");
            parent.returnToMainScreen();
        }
    }

    private void handleKeyPress(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP -> ring.move(0, -20);
            case KeyEvent.VK_DOWN -> ring.move(0, 20);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGame(g);
    }

    protected void paintGame(Graphics g) {
        Color reddish=new Color(130,7,8);
        g.setColor(reddish);
        for (Point point : level.getCurve().getCurvePoints()) {
            g.fillOval(point.x, point.y, 5, 5);
        }
        if (ring != null) {
            ring.draw(g);
        }
    }

    protected void colision(){
        if(dialogShown) {
            return;
        } else {
        isGameOver=true;
        dialogShown=true;
        JOptionPane.showMessageDialog(this, "Kolizja! Koniec gry.");
        parent.returnToMainScreen();
        }
    }

    private ImageIcon createScaledImageIcon(String filePath, int width, int height) {
        ImageIcon icon = new ImageIcon(filePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}