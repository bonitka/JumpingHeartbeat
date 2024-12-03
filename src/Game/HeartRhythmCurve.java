package Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class HeartRhythmCurve {
    private List<Point> curvePoints;

    public HeartRhythmCurve() {
        this.curvePoints = new ArrayList<>();
        generateCurve();
    }

    public void generateCurve() {
        // Przykładowe generowanie punktów krzywej
        for (int i = 0; i < 500; i++) {
            curvePoints.add(new Point(i, (int) (50 * Math.sin(i / 50.0) + 100)));
        }
    }

    public List<Point> getCurvePoints() {
        return curvePoints;
    }
}