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

    private void generateHealthyRhythm() {
        curvePoints.clear();
        double length = 633;
        double y = 1;
        double cycleTime = 5.0;
        double baseline = 400;
        double step = 0.5;
        int amplitude = 15;
        int totalCycles = 4;

        for (int cycle = 0; cycle < totalCycles; cycle++) {
            for (double i = 0; i < length; i += step) {
                double currentI = i;

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
                }

                int x1 = (int) (5 * (currentI / cycleTime) + cycle * length);
                int yInt = (int) (y + baseline);
                curvePoints.add(new Point(x1, yInt));
            }
        }
    }

    private void generateBradycardia() {
        curvePoints.clear();
        double length = 1200;
        double y = 1;
        double cycleTime = 5.0;
        double baseline = 400;
        double step = 0.5;
        int amplitude = 15;
        int totalCycles = 3;

        for (int cycle = 0; cycle < totalCycles; cycle++) {
            for (double i = 0; i < length; i += step) {
                double currentI = i;

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
                } else if (currentI >= 633 && currentI < 1200) {
                    y = 0;
                }
                int x1 = (int) (5 * (currentI / cycleTime) + cycle * length);
                int yInt = (int) (y + baseline);
                curvePoints.add(new Point(x1, yInt));
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

    private void generateFibrillation(){
        curvePoints.clear();
    }

    private void generateTachycardia() {
        curvePoints.clear();
        double cycleLength = 445;
        double y = 1;
        double cycleTime = 5.0;
        double baseline = 400;
        double step = 0.5;
        int amplitude = 15;
        int totalCycles = 4;

        for (int cycle = 0; cycle < totalCycles; cycle++) {
            for (double i = 0; i < cycleLength; i += step) {
                double shiftedI = i + cycle * cycleLength;

                if (i >= 0 && i < 60) {
                    y = (amplitude * Math.sin((i + 210) * 0.1)) - 15;
                } else if (i >= 60 && i < 70) {
                    y = 0;
                } else if (i >= 70 && i < 90) {
                    y = 3 * ((i + 210) - 300) + 60;
                } else if (i >= 90 && i < 190) {
                    y = -3 * ((i + 210) - 400) - 245;
                } else if (i >= 190 && i < 290) {
                    y = 4 * ((i + 210) - 530) + 280;
                } else if (i >= 290 && i < 343) {
                    y = -3 * ((i + 210) - 550) + 10;
                } else if (i >= 343 && i < 360) {
                    y = 0;
                } else if (i >= 360 && i < 423) {
                    y = ((amplitude * 3) * Math.sin(((i + 210) * 0.05) - 0.224));
                } else if (i >= 423 && i < 445) {
                    y = 0;
                }
                int x1 = (int) (5 * (shiftedI / cycleTime));
                int yInt = (int) (y + baseline);

                curvePoints.add(new Point(x1, yInt));
            }
        }
    }

    private void generateAsystole(){
        curvePoints.clear();
        int baseline = 400;
        for (int x = 100; x < 1500; x++) {
            int y=baseline;
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