package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class BallonText extends JPanel {
    private JLabel ballonText;

    public BallonText(String text){
        setLayout(new BorderLayout());
        setOpaque(false);

        ballonText = new JLabel();
        ballonText.setOpaque(false);
        setPreferredSize(new Dimension(0, 50));
        ballonText.setBorder(new EmptyBorder(18, 24, 18, 24));
        ballonText.setText(text);

        add(ballonText, BorderLayout.SOUTH);


    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Ativar antialiasing
        int width = getWidth();
        int height = getHeight();

        // Fundo com bordas arredondadas
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 50, 50);
        g2.setPaint(Color.WHITE);
        g2.fill(roundedRectangle);

        g2.dispose();
        super.paintComponent(g);
    }
}
