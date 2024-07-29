package components;

import controllers.ChatController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class InputText extends JPanel {
    private JTextField textField;
    private JButton sendButton;
    private ChatController chatController;

    public InputText(ChatController chatController) {
        this.chatController = chatController;

        setLayout(new BorderLayout());
        setOpaque(false);
        setPreferredSize(new Dimension(0, 50));

        textField = new JTextField();
        textField.setOpaque(false);
        textField.setBorder(new EmptyBorder(5, 20, 5, 0));

        add(textField, BorderLayout.CENTER);

        sendButton = new JButton();
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/send.png")));
        Image image = icon.getImage();
        Image newimg = resizeImage(image, 30, 30);
        icon = new ImageIcon(newimg);
        sendButton.setIcon(icon);
        sendButton.setContentAreaFilled(false);
        sendButton.setBorderPainted(false);
        sendButton.setFocusPainted(false);
        sendButton.setOpaque(false);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                chatController.sendMessage(text);
                textField.setText("");
            }
        });

        add(sendButton, BorderLayout.EAST);
    }

    private Image resizeImage(Image originalImage, int targetWidth, int targetHeight) {
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2.dispose();
        return resizedImage;
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
