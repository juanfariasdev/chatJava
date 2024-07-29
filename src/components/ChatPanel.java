package components;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    private JPanel chatContainer;

    public ChatPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());

        chatContainer = new JPanel();
        chatContainer.setOpaque(false);
        chatContainer.setLayout(new BoxLayout(chatContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(chatContainer);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);

        // Adicionar mensagens iniciais
        addBallonText("opaaaaaaaaa");
        addBallonText("Este é um balão de conversa com um texto mais longo para testar o ajuste de tamanho.");
        addBallonText("Outro balão de texto.");
        addBallonText("Mais um balão de texto para testar.");
        addBallonText("Texto final do balão de conversa.");
    }

    public void addBallonText(String text) {
        BallonText ballonText = new BallonText(text);
        ballonText.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinhar à esquerda
        chatContainer.add(ballonText);
        chatContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre os balões
        chatContainer.revalidate();
        chatContainer.repaint();
    }
}
