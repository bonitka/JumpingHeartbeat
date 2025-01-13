package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog {
    private int ringSizeSet;
    private int curveSpeedSet;
    private boolean confirmed;
    private EducationalContent [] educationalContents;
    Color reddish=new Color(130,7,8);
    private int currentLevel;
    Color offWhite=new Color(252,252,252);

    public SettingsDialog(JumpingHeartbeat parent, int initialRingSize, int initialCurveSpeed, Level level) {
        super(parent, "Ustawienia poziomu", true);
        this.ringSizeSet = initialRingSize;
        this.curveSpeedSet = initialCurveSpeed;
        this.confirmed = false;
        this.currentLevel = level.getLevelNumber();
        String[] fileNames = {
                "educational_content_Healthy.txt",
                "educational_content_Bradycardia.txt",
                "educational_content_AtrialFibrillation.txt",
                "educational_content_Arrhythmia.txt",
                "educational_content_VentricularTachycardia.txt",
                "educational_content_Tachycardia.txt",
                "educational_content_Asystole.txt"
        };
        educationalContents = new EducationalContent[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            educationalContents[i] = new EducationalContent();
            educationalContents[i].loadFromFile(fileNames[i]);
        }
        setLayout(new BorderLayout());

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new GridLayout(2, 2, 10, 10));
        panelMain.setBorder(BorderFactory.createCompoundBorder(
                panelMain.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));

        JLabel ringSizeLabel = new JLabel("Rozmiar pierścienia:");
        JSlider ringSizeSlider = new JSlider(10, 100, initialRingSize);
        ringSizeSlider.setMajorTickSpacing(10);
        ringSizeSlider.setPaintTicks(true);
        ringSizeSlider.setPaintLabels(true);
        ringSizeSlider.setUI(new javax.swing.plaf.basic.BasicSliderUI(ringSizeSlider) {
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(reddish);
                g2.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4);
            }
        });
        ringSizeSlider.setForeground(reddish);

        JLabel curveSpeedLabel = new JLabel("Prędkość przesuwania krzywej:");
        JSlider curveSpeedSlider = new JSlider(0, 10, initialCurveSpeed);
        curveSpeedSlider.setMajorTickSpacing(1);
        curveSpeedSlider.setPaintTicks(true);
        curveSpeedSlider.setPaintLabels(true);
        curveSpeedSlider.setUI(new javax.swing.plaf.basic.BasicSliderUI(curveSpeedSlider) {
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(reddish);
                g2.fillRect(trackRect.x, trackRect.y + trackRect.height / 2 - 2, trackRect.width, 4);
            }
        });
        curveSpeedSlider.setForeground(reddish);
        curveSpeedSlider.addChangeListener(e -> {
            curveSpeedSet = curveSpeedSlider.getValue();
        });

        JButton confirmButton = new JButton("Zatwierdź");
        confirmButton.setContentAreaFilled(false);
        confirmButton.setPreferredSize(new Dimension(150, 30));
        confirmButton.setBorder(BorderFactory.createLineBorder(reddish,3));
        confirmButton.addActionListener(e -> {
            this.ringSizeSet = ringSizeSlider.getValue();
            this.curveSpeedSet = curveSpeedSlider.getValue();
            this.confirmed = true;
            setVisible(false);
        });

        JButton cancelButton = new JButton("Anuluj");
        cancelButton.setContentAreaFilled(false);
        cancelButton.setPreferredSize(new Dimension(150, 30));
        cancelButton.setBorder(BorderFactory.createLineBorder(reddish,3));
        cancelButton.addActionListener(e -> {
            parent.returnToMainScreen();
            setVisible(false);
        });

        ImageIcon infoIcon = new ImageIcon("info.jpg");
        Image scaledImage = infoIcon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
        JButton infoButton = new JButton(new ImageIcon(scaledImage));
        infoButton.setContentAreaFilled(false); // tło
        infoButton.addActionListener(e -> showEducationalContent());

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                rightPanel.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        rightPanel.add(infoButton, BorderLayout.NORTH);
        if(currentLevel>1){
            add(rightPanel, BorderLayout.EAST);
        }

        JPanel  downPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        downPanel.setBorder(BorderFactory.createCompoundBorder(
                downPanel.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        downPanel.add(cancelButton, BorderLayout.NORTH);
        downPanel.add(confirmButton, BorderLayout.NORTH);
        add(downPanel, BorderLayout.SOUTH);

        panelMain.add(ringSizeLabel);
        panelMain.add(ringSizeSlider);
        panelMain.add(curveSpeedLabel);
        panelMain.add(curveSpeedSlider);
        //panelMain.add(cancelButton);
        //panelMain.add(confirmButton);
        add(panelMain, BorderLayout.CENTER);

        setSize(550, 200);
        setLocationRelativeTo(parent);
        currentLevel = level.getLevelNumber();
    }

    public int getRingSize() {
        return ringSizeSet;
    }

    public int getCurveSpeedSet() {
        return curveSpeedSet;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
    private void showEducationalContent() {
        if (currentLevel >= 1 && currentLevel <= educationalContents.length+1) {
            EducationalContent currentContent = educationalContents[currentLevel - 2];

            StringBuilder content = new StringBuilder();
            for (String line : currentContent.getContent()) {
                content.append(line).append("\n");
            }

            JOptionPane.showMessageDialog(this, content.toString(),
                    "Edukacyjne Informacje - Poziom " + currentLevel,
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Nieprawidłowy poziom!",
                    "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        /*StringBuilder content = new StringBuilder();
        for (String line : educationalContent.getContent()) {
            content.append(line).append("\n");
        }
        JOptionPane.showMessageDialog(this, content.toString(), "Edukacyjne Informacje", JOptionPane.INFORMATION_MESSAGE);*/
    }
}

