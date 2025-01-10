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
            case "Normal" -> {
                generateHealthyRhythm();

            }
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

    private void generateNormalRhythm() {
        curvePoints.clear();
        int resolution = 1000; // Liczba punktów w jednym cyklu
        double cycleTime = 2.0; // Czas trwania jednego cyklu (w sekundach)
        double timeStep = cycleTime / resolution; // Krok czasu
        double baseline = 50; // Pozycja linii bazowej (przesunięcie w pionie)

        for (int i = 0; i < resolution; i++) {
            double t = i * timeStep;

            double y = baseline;

            if (t >= 0.0 && t < 50.0/90.0) {
                y = 3.0*t;
            }

            if (t >= 50.0/90.0 && t < 50.0/36.0) {
                y =-5.0*(t-5.0/90.0)+1.0/6.0;
            }

            if (t >= 50.0/36.0 && t < 3.531746031745) {
                y =7.0*(t-5.0/90.0)+20.0/6.0;
            }

            if (t >= 3.531746031745 && t < 230.0/36.0) {
                y =-7.0*(t-5.0/90.0)+20.0/6.0;
            }

            if (t >= 230.0/36.0 && t < 8.38888888888) {
                y =5.0*(t-5.0/90.0)-22.0/6.0;
            }

            if (t >= 8.388888888 && t < 9.0972222222222) {
                y =-5.0*(t-5.0/90.0)+25.0/6.0;
            }

            if (t >= 9.0972222222222 && t < 290.0/30.0) {
                y =3.0*(t-5.0/90.0)-16.0/6.0;
            }

            if (t >= 290.0/30.0 && t < 10.0) {
                y =-2.0*(t-3.75/90.0)+11.5/6.0;
            }

            /*if (t >= 0.9 && t < 1) {
                y =-2*t+11.5;
            }*/

            // Konwersja na współrzędne pikselowe
            int x = (int) (800 * (t / cycleTime)); // Skala pozioma
            int yInt = (int) (y-baseline); // Pozycja pionowa

            curvePoints.add(new Point(x, yInt));
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