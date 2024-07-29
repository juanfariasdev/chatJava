package components;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    public ChatPanel() {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        addBallonText("opaaaaaaaaa");
       addBallonText("Este é um balão de conversa com um texto mais longo para testar o ajuste de tamanho.");
       addBallonText("Outro balão de texto.");
       addBallonText("Mais um balão de texto para testar.");
       addBallonText("Texto final do balão de conversa.");
       addBallonText("Texto final do balão de conversa.");
       addBallonText("Texto final do balão de conversa.");
       addBallonText("Texto final do balão de conversa.");
       addBallonText("Texto final do balão de conversa.");
       addBallonText("Texto final do balão de conversa.");


    }

    private void addBallonText(String text) {
        BallonText ballonText = new BallonText(text);
        ballonText.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinhar à esquerda
        add(ballonText);
        add(Box.createRigidArea(new Dimension(0, 10))); // Espaço entre os balões
    }
}
