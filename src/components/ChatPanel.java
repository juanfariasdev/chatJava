package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatPanel extends JPanel {
    private JPanel chatContainer;

    public ChatPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());

        chatContainer = new JPanel();
        chatContainer.setOpaque(false);
        chatContainer.setLayout(new BoxLayout(chatContainer, BoxLayout.Y_AXIS));
        chatContainer.setBorder(new EmptyBorder(0, 10, 0, 0));

        JScrollPane scrollPane = new JScrollPane(chatContainer);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane, BorderLayout.CENTER);

        // Adicionar mensagens iniciais
        for (int i = 0; i < 10; i++) {
            addBallonText("opaaaaaaaaa");
            addBallonText("Este é um balão de conversa com um texto mais longo para testar o ajuste de tamanho.");
            addBallonText("Outro balão de texto.");
            addBallonText("Mais um balão de texto para testar.");
            addBallonText("Texto final do balão de conversa.");
        }
    }

    public void addBallonText(String text) {
        BallonText ballonText = new BallonText(text);
        ballonText.setSize(0, 0);
        ballonText.setAlignmentX(Component.LEFT_ALIGNMENT);
        chatContainer.add(ballonText);
        chatContainer.add(Box.createRigidArea(new Dimension(0, 10)));
        chatContainer.revalidate();
        chatContainer.repaint();
    }

    @Override
    public void doLayout() {
        super.doLayout();
        for (Component component : chatContainer.getComponents()) {
            if (component instanceof BallonText) {
                component.setMaximumSize(new Dimension((int) (getWidth() * 0.9), Integer.MAX_VALUE));
            }
        }
    }
}
