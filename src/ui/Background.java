package ui;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    public Background(){
        setOpaque(false);
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        int width = getWidth();
        int height =getHeight();

        g2.setPaint(new GradientPaint(0,0, new Color(58, 72 , 85), width, 0, new Color(28, 38, 50)));
        g2.fillRect(0,0, width, height);
        g2.dispose();
        super.paintComponent(g);
    }
}
