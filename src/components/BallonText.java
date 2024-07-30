package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BallonText extends JPanel {
    private final String text;
    private final Font font;
    private final boolean alignRight;
    private static final int MAX_WIDTH = 300; // Definindo uma largura máxima padrão

    public BallonText(String text, boolean alignRight) {
        this.text = text;
        this.font = new Font("Arial", Font.PLAIN, 14);
        this.alignRight = alignRight;
        setOpaque(false);
        setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics metrics = getFontMetrics(font);
        int width = Math.min(MAX_WIDTH, calculateTextWidth(metrics, text, MAX_WIDTH));
        int height = calculateTextHeight(metrics, text, width);
        return new Dimension(width + 30, height + 40);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setFont(font);

        FontMetrics metrics = g2.getFontMetrics();
        int width = Math.min(MAX_WIDTH, calculateTextWidth(metrics, text, MAX_WIDTH));
        int height = calculateTextHeight(metrics, text, width);

        int x = alignRight ? getWidth() - (width + 20) : 0;

        g2.setPaint(Color.WHITE);
        g2.fill(new RoundRectangle2D.Float(x, 0, width + 20, height + 30, 20, 20));

        g2.setPaint(Color.BLACK);
        drawText(g2, text, x + 10, 25, width);
        g2.dispose();
    }

    private void drawText(Graphics2D g2, String text, int x, int y, int maxWidth) {
        FontMetrics metrics = g2.getFontMetrics();
        int lineHeight = metrics.getHeight();
        int curX = x;
        int curY = y;

        for (String word : text.split(" ")) {
            int wordWidth = metrics.stringWidth(word + " ");
            if (curX + wordWidth > maxWidth + x) {
                curX = x;
                curY += lineHeight;
            }
            g2.drawString(word, curX, curY);
            curX += wordWidth;
        }
    }

    private int calculateTextWidth(FontMetrics metrics, String text, int maxWidth) {
        int curX = 0;
        int maxLineWidth = 0;

        for (String word : text.split(" ")) {
            int wordWidth = metrics.stringWidth(word + " ");
            if (curX + wordWidth > maxWidth) {
                maxLineWidth = Math.max(maxLineWidth, curX);
                curX = 0;
            }
            curX += wordWidth;
        }

        maxLineWidth = Math.max(maxLineWidth, curX);
        return maxLineWidth;
    }

    private int calculateTextHeight(FontMetrics metrics, String text, int maxWidth) {
        int lineHeight = metrics.getHeight();
        int curX = 0;
        int numLines = 1;

        for (String word : text.split(" ")) {
            int wordWidth = metrics.stringWidth(word + " ");
            if (curX + wordWidth > maxWidth) {
                numLines++;
                curX = 0;
            }
            curX += wordWidth;
        }

        return lineHeight * numLines;
    }
}
