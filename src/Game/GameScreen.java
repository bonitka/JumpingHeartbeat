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
    private boolean isGameOver=false;
    private Timer movementTimer;


    public GameScreen(JumpingHeartbeat parent, Level level) {
        this.parent = parent;
        this.level = level;
        this.score = 0;
        ring = new Ring(new Point(100, 80), level.getRingSize(), level.getRingSize() * 2);

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
            level.getCurve().moveCurve(-2); // Przesuwamy krzywą w lewo

            List<Point> curvePoints = level.getCurve().getCurvePoints();  // Pobieramy punkty krzywej
            int centerX = ring.getCenterX();  // Środek elipsy
            int centerY = ring.getCenterY();
            int a = ring.getRadiusX();  // Półos pozioma
            int b = ring.getRadiusY();  // Półos pionowa

            check();
            if (isGameOver) {
                movementTimer.stop(); // Zatrzymujemy grę, jeśli nastąpiła kolizja
                return;
            }

            repaint();
        });
        movementTimer.start();

    }

    private void check(){
        for (Point point : level.getCurve().getCurvePoints()){
            if (point.x== ring.getCenterX()) {
                if (point.y >= (ring.getCenterY() - ring.getRadiusY())
                        && point.y <= (ring.getCenterY() + ring.getRadiusY())) {
                    return;
                }else{
                    isGameOver=true;
                    colision();
                    return;
                }
            }
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
        g.setColor(Color.RED);
        for (Point point : level.getCurve().getCurvePoints()) {
            g.fillOval(point.x, point.y, 5, 5);
        }
        if (ring != null) {
            ring.draw(g);
        }
    }

    protected void colision(){
        JOptionPane.showMessageDialog(this, "Kolizja! Koniec gry.");
        parent.returnToMainScreen();
    }
}