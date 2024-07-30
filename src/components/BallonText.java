package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BallonText extends JPanel {
    private String text;
    private Font font;

    public BallonText(String text) {
        this.text = text;
        this.font = new Font("Arial", Font.PLAIN, 18); // Aumentando o tamanho da fonte para 18
        setOpaque(false);
        setBorder(new EmptyBorder(10, 15, 10, 15));
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics metrics = getFontMetrics(font);
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int maxWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.5);
        int width = Math.min(textWidth + 30, maxWidth);
        int lines = (textWidth / maxWidth) + 1;
        int height = textHeight * lines + 20;
        return new Dimension(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);

        FontMetrics metrics = g2.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        int maxWidth = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.3);
        int width = Math.min(textWidth + 30, maxWidth);
        int lines = (textWidth / maxWidth) + 1;
        int height = textHeight * lines + 30;

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 20, 20);
        g2.setPaint(Color.WHITE);
        g2.fill(roundedRectangle);

        g2.setPaint(Color.BLACK);
        drawString(g2, text, 15, 15, maxWidth);

        g2.dispose();
    }

    private void drawString(Graphics2D g2, String text, int x, int y, int maxWidth) {
        FontMetrics metrics = g2.getFontMetrics();
        int lineHeight = metrics.getHeight();
        int curX = x;
        int curY = y;

        for (String word : text.split(" ")) {
            int wordWidth = metrics.stringWidth(word + " ");
            if (curX + wordWidth > maxWidth) {
                curX = x;
                curY += lineHeight;
            }
            g2.drawString(word, curX, curY);
            curX += wordWidth;
        }
    }
}
