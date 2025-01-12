package Game;

import java.awt.*;

class Level {
    private String name;
    private HeartRhythmCurve curve;
    private String[] questions;
    private int ringSize;
    private int speed;
    private int levelNumber;

    public Level(String name, HeartRhythmCurve curve, String[] questions, int ringSize, int speed) {
        this.name = name;
        this.curve = curve;
        this.questions = questions;
        this.ringSize = ringSize;
        this.speed = speed;
        this.levelNumber = levelNumber;
        configureLevel();
    }

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        configureLevel();
    }

    private void configureLevel() {
        switch (levelNumber) {
            case 1 -> {
                name = "Sine";
                ringSize = 50;
                speed = 2;
                curve = new HeartRhythmCurve("Sine");
            }
            case 2 -> {
                name = "Healthy";
                ringSize = 30;
                speed = 2;
                curve = new HeartRhythmCurve("Healthy");
            }
            case 3 -> {
                name = "Bradycardia";
                ringSize = 40;
                speed = 3;
                curve = new HeartRhythmCurve("Bradycardia");
            }
            case 4 -> {
                name = "Atrial Fibrillation";
                ringSize = 50;
                speed = 4;
                curve = new HeartRhythmCurve("Atrial Fibrillation");
            }
            case 5 -> {
                name = "Arrhythmia";
                ringSize = 30;
                speed = 5;
                curve = new HeartRhythmCurve("Arrhythmia");
            }
            case 6 -> {
                name="Ventricular Tachycardia";
                ringSize = 50;
                speed = 2;
                curve = new HeartRhythmCurve("Ventricular Tachycardia");
            }
            case 7 -> {
                name = "Tachycardia";
                ringSize = 80;
                speed = 4;
                curve = new HeartRhythmCurve("Tachycardia");
            }
            case 8 -> {
                name="Asystoly";
                ringSize = 50;
                speed = 2;
                curve = new HeartRhythmCurve("Asystoly");
            }
        }
    }

    public HeartRhythmCurve getCurve() {
        return curve;
    }

    public int getRingSize() {
        return ringSize;
    }

    public void setRingSize(int ringSize) {
        this.ringSize = ringSize;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        if (curve != null) {
            curve.setSpeed(speed);
        }
    }
}