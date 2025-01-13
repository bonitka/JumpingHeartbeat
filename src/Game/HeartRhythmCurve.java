package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class HeartRhythmCurve {
    private List<Point> curvePoints;
    private String rhythmName;
    private int speed;

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
            case "Healthy" -> generateHealthyRhythm();
            case "Bradycardia" -> generateBradycardia();
            case "Atrial Fibrillation" -> generateFibrillation();
            case "Arrhythmia" -> generateArrhythmia();
            case "Ventricular Tachycardia" -> generateVentricularTachycardia();
            case "Tachycardia" -> generateTachycardia();
            case "Asystole" -> generateAsystole();
            default -> generateSine(); // Domyślny rytm
        }
    }

    private void generateSine(){
        int amplitude = 105;
        int offsetY = 400;
        for (int x=0;x<210;x++){
            curvePoints.add(new Point(x, offsetY));
        }
        for (int x = 210; x < 2000; x++) {
            int y = (int) (amplitude * Math.sin(x * 0.03) + offsetY);
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateHealthyRhythm(){
        double length = 655;
        double y=1;
        double cycleTime = 5.0;
        double baseline=400;
        double step = 0.5;
        int amplitude = 15;
        int move=0;
        int totalCycles = 4;

        List<Point> originalCurve = new ArrayList<>();

        for (int cycle = 0; cycle < totalCycles; cycle++) {
            for (double i = 0; i < 655; i += step) {
                double currentI = i + cycle * 655;

                if (currentI >= 0 && currentI < 210) {
                    y = 0;
                } else if (currentI >= 210 && currentI < 270) {
                    y = (amplitude * Math.sin(currentI * 0.1)) - 15;
                } else if (currentI >= 270 && currentI < 280) {
                    y = 0;
                } else if (currentI >= 280 && currentI < 300) {
                    y = 3 * (currentI - 300) + 60;
                } else if (currentI >= 300 && currentI < 400) {
                    y = -3 * (currentI - 400) - 245;
                } else if (currentI >= 400 && currentI < 500) {
                    y = 4 * (currentI - 530) + 280;
                } else if (currentI >= 500 && currentI < 553) {
                    y = -3 * (currentI - 550) + 10;
                } else if (currentI >= 553 && currentI < 570) {
                    y = 0;
                } else if (currentI >= 570 && currentI < 633) {
                    y = ((amplitude * 3) * Math.sin((currentI * 0.05) - 0.224));
                } else if (currentI >= 633 && currentI < 655) {
                    y = 0;
                }
                int x1 = (int) (5 * (currentI / cycleTime));
                int yInt = (int) (y + baseline);

                originalCurve.add(new Point(x1, yInt));
            }
        }

        for (Point point : originalCurve) {
            curvePoints.add(point);
            for(int j=1;j<5;j++) {
                curvePoints.add(new Point(point.x + (int) length * j, point.y));
            }
        }
    }

    private void generateArrhythmia() {
        curvePoints.clear();
        for (int x = 0; x < 800; x++) {
            int y = (int) (100 + (x % 50 < 25 ? 30 : 10)); // Nierównomierny rytm
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateVentricularTachycardia(){
        curvePoints.clear();
    }

    private void generateBradycardia() {
        curvePoints.clear();
        for (int x = 0; x < 800; x++) {
            int y = (int) (100 + 10 * Math.sin(0.05 * x)); // Wolniejsza sinusoida
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateFibrillation(){
        curvePoints.clear();
    }

    private void generateTachycardia() {
        curvePoints.clear();
        double length = 1200;
        double baseline = 280;
        double step = 0.05;
        double cycleTime = 150;
        double verticalScale1 = 20;
        double verticalScale2=2;

        for (double i = 0; i < length; i += step) {
            double y = 0;
            double timeInCycle = i % cycleTime;

            if (timeInCycle >= 0 && timeInCycle < 40) {
                y = 2 * Math.sin((timeInCycle / 40) * Math.PI) * verticalScale2;
            } else if (timeInCycle >= 40 && timeInCycle < 60) {
                y = -1 * (timeInCycle - 40) / 20 * verticalScale1;
            } else if (timeInCycle >= 60 && timeInCycle < 80) {
                y = 12 * (1 - Math.abs((timeInCycle - 70) / 10)) * verticalScale1;
            } else if (timeInCycle >= 80 && timeInCycle < 100) {
                y = -8 * (1 - Math.abs((timeInCycle - 90) / 10)) * verticalScale1;
            } else if (timeInCycle >= 100 && timeInCycle < 160) {
                y = 3 * Math.sin(((timeInCycle - 100) / 60) * Math.PI) * verticalScale2;
            } else {
                y = 0;
            }

            // Przeliczenie pozycji
            int x1 = (int) (i); // Skala pozioma (bez zmiany)
            int yInt = (int) (-y + baseline); // Przesunięcie na linię bazową

            curvePoints.add(new Point(x1, yInt));
        }
    }

    private void generateAsystole(){
        curvePoints.clear();
        int baseline = 280;
        for (int x = 100; x < 500; x++) {
            int y=baseline; // Wolniejsza sinusoida
            curvePoints.add(new Point(x, y));
        }
    }

    private void generateNewCurve(){
        double length = 1200; // Długość całej krzywej
        double baseline = 280;
        double step = 0.5; // Krok w milisekundach
        double cycleTime = 300;
        double verticalScale = 5.0;

        for (double i = 0; i < length; i += step) {
            double y = 0;
            double timeInCycle = i % cycleTime; // Czas w obrębie jednego cyklu

            if (timeInCycle >= 0 && timeInCycle < 50) {
                y = 2 * Math.sin((timeInCycle / 50) * Math.PI) * verticalScale;
            } else if (timeInCycle >= 50 && timeInCycle < 70) {
                y = -0.005 * (timeInCycle - 50) * verticalScale;
            } else if (timeInCycle >= 70 && timeInCycle < 90) {
                y = 15 * (1 - Math.abs((timeInCycle - 80) / 10)) * verticalScale;
            } else if (timeInCycle >= 90 && timeInCycle < 110) {
                y = -10 * (1 - Math.abs((timeInCycle - 100) / 10)) * verticalScale;
            } else if (timeInCycle >= 110 && timeInCycle < 160) {
                y = 4 * Math.sin(((timeInCycle - 110) / 50) * Math.PI) * verticalScale;
            } else {
                y = 0;
            }
            int x1 = (int) (i); // Skala pozioma
            int yInt = (int) (y + baseline); // Pozycja pionowa przesunięta linią bazową

            curvePoints.add(new Point(x1, yInt));
        }
    }

    public List<Point> getCurvePoints() {
        return curvePoints;
    }

    /*public void rotateCurveAroundBaseline(double angle) {
        double baseline = 280; // Linia bazowa
        double cosTheta = Math.cos(angle); // Kosinus kąta rotacji

        // Iterujemy przez wszystkie punkty krzywej
        for (int i = 0; i < curvePoints.size(); i++) {
            Point originalPoint = curvePoints.get(i);

            // Przesunięcie y względem linii bazowej
            int x = originalPoint.x; // Pozycja X pozostaje bez zmian
            int y = originalPoint.y;

            // Rotacja wokół baseline
            int rotatedY = (int) (baseline + (y - baseline) * cosTheta);

            // Aktualizacja punktu
            curvePoints.set(i, new Point(x, rotatedY));
        }
    }*/

    public void moveCurve(int dx) {
        for (Point point : curvePoints) {
            point.x += dx;
        }
    }

    public void reset() {
        this.curvePoints.clear();
        generateCurve();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}