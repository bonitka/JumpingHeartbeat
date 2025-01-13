package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsDialog extends JDialog {
    private int ringSizeSet;
    private int curveSpeedSet;
    private boolean confirmed;
    Color reddish=new Color(130,7,8);

    public SettingsDialog(JumpingHeartbeat parent, int initialRingSize, int initialCurveSpeed) {
        super(parent, "Ustawienia poziomu", true);
        this.ringSizeSet = initialRingSize;
        this.curveSpeedSet = initialCurveSpeed;
        this.confirmed = false;

        setLayout(new GridLayout(3, 2, 10, 10));

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
        confirmButton.addActionListener(e -> {
            this.ringSizeSet = ringSizeSlider.getValue();
            this.curveSpeedSet = curveSpeedSlider.getValue();
            this.confirmed = true;
            setVisible(false);
        });

        JButton cancelButton = new JButton("Anuluj");
        cancelButton.addActionListener(e -> {
            parent.returnToMainScreen();
            setVisible(false);
        });

        add(ringSizeLabel);
        add(ringSizeSlider);
        add(curveSpeedLabel);
        add(curveSpeedSlider);
        add(confirmButton);
        add(cancelButton);

        setSize(400, 200);
        setLocationRelativeTo(parent);
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

}
