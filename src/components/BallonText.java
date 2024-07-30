
package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BallonText extends JPanel {
    private JLabel ballonText;

    public BallonText(String text) {
        setLayout(new BorderLayout());
        setOpaque(false);

        ballonText = new JLabel("<html>" + text + "</html>"); // HTML para quebra de linha automática
        ballonText.setOpaque(false);
        ballonText.setBorder(new EmptyBorder(10, 15, 10, 15));

        add(ballonText, BorderLayout.CENTER);

        // Listener para ajustar a largura máxima
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                updateWidth();
            }
        });
    }

    private void updateWidth() {
        Container parent = getParent();
        if (parent != null) {
            int parentWidth = parent.getWidth();
            int maxWidth = (int) (parentWidth * 0.9);
            setMaximumSize(new Dimension(maxWidth, getPreferredSize().height));
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = ballonText.getPreferredSize();
        int width = Math.min(size.width + 30, (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.9));
        return new Dimension(width, size.height + 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();

        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, width, height, 20, 20);
        g2.setPaint(Color.WHITE);
        g2.fill(roundedRectangle);

        g2.dispose();
    }
}