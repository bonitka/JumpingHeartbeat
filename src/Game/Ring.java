package Game;

import java.awt.*;

class Ring {
    private Point position;
    private int radius;

    public Ring(Point position, int radius) {
        this.position = position;
        this.radius = radius;
    }

    public Point getPosition() {
        return position;
    }

    public int getRadius() {
        return radius;
    }

    public void move(int dx, int dy) {
        position.translate(dx, dy);
    }

    public boolean checkCollision(HeartRhythmCurve curve) {
        // Prosta logika sprawdzania kolizji
        for (Point point : curve.getCurvePoints()) {
            if (position.distance(point) < radius) {
                return true;
            }
        }
        return false;
    }
}