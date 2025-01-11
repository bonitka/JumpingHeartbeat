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
            case "Healthy" -> generateHealthyRhythm();
            case "Bradycardia" -> generateBradycardia();
            case "Atrial Fibrillation" -> generateFibrillation();
            case "Arrhythmia" -> generateArrhythmia();
            case "Ventricular Tachycardia" -> generateVentricularTachycardia();
            case "Tachycardia" -> generateTachycardia();
            case "Asystoly" -> generateAsystoly();
            //case "New" -> generateNewCurve();
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

    private void generateHealthyRhythm(){
        double length=500;
        double x=1, y=1;
        double cycleTime = 5.0;
        double baseline=280;
        double step = 0.5;

        List<Point> originalCurve = new ArrayList<>();

        for (double i=0;i<length; i+=step){
            if (i >= 0 && i < 150){
                y=0;
            } else if (i>=150 && i<160){
                y=(-6)*(i-150);
            } else if (i>=160 && i<180){
                y=8*(i-167);
            } else if (i>=180 && i<250){
                y=(-5)*(i-200);
            } else if (i>=250 && i<345){
                y=(5)*(i-300);
            } else if (i>=345 && i<450){
                y=(-3)*(i-420);
            } else if (i>=450 && i<470){
                y=(6)*(i-465);
            } else if (i>=470 && i<480){
                y=(-8)*(i-473);
            } else if (i>=480 && i<490){
                y=6*(i-490);
            }
            int x1 = (int) (5 * (i/cycleTime)); // Skala pozioma
            int yInt = (int) (y+baseline); // Pozycja pionowa

            originalCurve.add(new Point(x1, yInt));
        }
        for (Point point : originalCurve) {
            curvePoints.add(point); // Oryginalna krzywa
            for(int j=1;j<10;j++) {
                curvePoints.add(new Point(point.x + (int) length * j, point.y)); // Kopia przesunięta
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

    private void generateAsystoly(){
        curvePoints.clear();
        int baseline = 280;
        for (int x = 0; x < 1800; x++) {
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

    public void rotateCurveAroundBaseline(double angle) {
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
    }

    public void moveCurve(int dx) {
        for (Point point : curvePoints) {
            point.x += dx;
        }
    }
}