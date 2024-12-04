package Game;

import java.awt.*;

class Ring {
    private Point position;
    private int width;
    private int height;

    public Ring(Point position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    // Getter dla środka X
    public int getCenterX() {
        return position.x;
    }

    // Getter dla środka Y
    public int getCenterY() {
        return position.y;
    }

    // Getter dla półosi poziomej (promień w poziomie)
    public int getRadiusX() {
        return width / 2;
    }

    // Getter dla półosi pionowej (promień w pionie)
    public int getRadiusY() {
        return height / 2;
    }

    public void move(int dx, int dy) {
        position.translate(dx, dy);
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(3)); // Grubość linii
        g2d.setColor(Color.BLUE);
        g2d.drawOval(position.x - width / 2, position.y - height / 2, width, height);
    }
}