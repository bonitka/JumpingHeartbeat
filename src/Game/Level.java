package Game;

import java.awt.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Point;
import java.util.List;

class Level {
    private String name;
    private HeartRhythmCurve curve;
    private String[] questions;
    private int ringSize;
    private int speed;
    private int levelNumber;
    private ImageIcon levelImage;

    public Level(int levelNumber) {
        this.levelNumber = levelNumber;
        configureLevel();
    }

    private ImageIcon createScaledImageIcon(String filePath, int width, int height) {
        ImageIcon icon = new ImageIcon(filePath);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    private void configureLevel() {
        //ImageIcon levelImage = null;
        switch (levelNumber) {
            case 1 -> {
                name = "Sine";
                ringSize = 50;
                speed = 2;
                curve = new HeartRhythmCurve("Sine");
                levelImage = createScaledImageIcon("sine.jpg", 320, 85);
            }
            case 2 -> {
                name = "Healthy";
                ringSize = 30;
                speed = 2;
                curve = new HeartRhythmCurve("Healthy");
                levelImage = createScaledImageIcon("healthy.jpg", 320, 85);
            }
            case 3 -> {
                name = "Bradycardia";
                ringSize = 40;
                speed = 3;
                curve = new HeartRhythmCurve("Bradycardia");
                levelImage = createScaledImageIcon("bradycardia.jpg", 320, 85);
            }
            case 4 -> {
                name = "Atrial Fibrillation";
                ringSize = 50;
                speed = 4;
                curve = new HeartRhythmCurve("Atrial Fibrillation");
                levelImage = createScaledImageIcon("atrialFibrillation.jpg", 474, 85);
            }
            case 5 -> {
                name = "Arrhythmia";
                ringSize = 30;
                speed = 5;
                curve = new HeartRhythmCurve("Arrhythmia");
                levelImage = createScaledImageIcon("arrhythmia.jpg", 320, 85);
            }
            case 6 -> {
                name="Ventricular Tachycardia";
                ringSize = 50;
                speed = 2;
                curve = new HeartRhythmCurve("Ventricular Tachycardia");
                levelImage = createScaledImageIcon("ventricularTachycardia.jpg", 474, 85);
            }
            case 7 -> {
                name = "Tachycardia";
                ringSize = 80;
                speed = 4;
                curve = new HeartRhythmCurve("Tachycardia");
                levelImage = createScaledImageIcon("tachycardia.jpg", 320, 85);
            }
            case 8 -> {
                name="Asystoly";
                ringSize = 50;
                speed = 2;
                curve = new HeartRhythmCurve("Asystoly");
                levelImage = createScaledImageIcon("asystoly.jpg", 320, 85);
            }
        }
    }

    public ImageIcon getLevelImage() {
        return levelImage; // Metoda do zwrócenia levelImage
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