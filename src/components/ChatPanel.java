package components;

import javax.swing.*;
import java.awt.*;

public class ChatPanel extends JPanel {
    public ChatPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());
        JPanel chatField = new JPanel();

        BallonText ballonText = new BallonText("opaaaaaaaaa");
        BallonText ballonText2 = new BallonText("opaaaaaaaaa");
        BallonText ballonText3 = new BallonText("opaaaaaaaaa");
        BallonText ballonText4 = new BallonText("opaaaaaaaaa");
        BallonText ballonText5 = new BallonText("opaaaaaaaaa");

        chatField.add(ballonText);
        chatField.add(ballonText2);
        chatField.add(ballonText3);
        chatField.add(ballonText4);
        chatField.add(ballonText5, BorderLayout.CENTER);
    }
}