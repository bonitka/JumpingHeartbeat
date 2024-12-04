package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class HeartRhythmCurve {
    private List<Point> curvePoints;
    private String rhythmName;

    public String getName(){
        return rhythmName;
    }

    public HeartRhythmCurve(String name) {
        this.rhythmName=name;
        this.curvePoints = new ArrayList<>();
        generateCurve();
    }

    public void generateCurve() {
        switch (rhythmName) {
            case "Sine" -> generateSine();
            case "Normal" -> generateNormalRhythm();
            case "Arrhythmia" -> generateArrhythmia();
            case "Bradycardia" -> generateBradycardia();
            case "Tachycardia" -> generateTachycardia();
            default -> generateSine(); // Domyślny rytm
        }
    }

    private void generateSine(){
        int amplitude = 50;
        int offsetY = 100;
        for (int x = 80; x < 2000; x++) { // Długość krzywej
            int y = (int) (amplitude * Math.sin(x * 0.03) + offsetY);
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateNormalRhythm() {
        curvePoints.clear();
        for (int x = 0; x < 800; x++) {
            int y = (int) (100 + 20 * Math.sin(0.1 * x)); // Sinusoida
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateArrhythmia() {
        curvePoints.clear();
        for (int x = 0; x < 800; x++) {
            int y = (int) (100 + (x % 50 < 25 ? 30 : 10)); // Nierównomierny rytm
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateBradycardia() {
        curvePoints.clear();
        for (int x = 0; x < 800; x++) {
            int y = (int) (100 + 10 * Math.sin(0.05 * x)); // Wolniejsza sinusoida
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateTachycardia() {
        curvePoints.clear();
        for (int x = 0; x < 800; x++) {
            int y = (int) (100 + 15 * Math.sin(0.2 * x)); // Szybsza sinusoida
            curvePoints.add(new Point(x, y));
        }
    }

    public List<Point> getCurvePoints() {
        return curvePoints;
    }

    public void moveCurve(int dx) {
        for (Point point : curvePoints) {
            point.x += dx;
        }
    }
}