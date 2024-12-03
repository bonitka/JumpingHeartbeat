package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class EducationalContent {
    private List<String> content;

    public EducationalContent() {
        content = new ArrayList<>();
    }

    public void loadFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading educational content file: " + e.getMessage());
        }
    }

    public List<String> getContent() {
        return content;
    }
}