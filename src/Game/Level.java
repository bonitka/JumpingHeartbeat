package Game;

class Level {
    private String name;
    private HeartRhythmCurve curve;
    private String[] questions;
    private int ringSize;
    private int speed;

    public Level(String name, HeartRhythmCurve curve, String[] questions, int ringSize, int speed) {
        this.name = name;
        this.curve = curve;
        this.questions = questions;
        this.ringSize = ringSize;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public HeartRhythmCurve getCurve() {
        return curve;
    }

    public String[] getQuestions() {
        return questions;
    }

    public int getRingSize() {
        return ringSize;
    }

    public int getSpeed() {
        return speed;
    }
}