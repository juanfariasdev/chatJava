package pages;

import components.BallonText;
import components.ChatPanel;
import components.InputText;
import ui.Background;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatForm extends JFrame {
    public ChatForm() {
        Background background = new Background();
        InputText inputText = new InputText();
        ChatPanel chatField = new ChatPanel();

        background.add(chatField);

        background.add(inputText, BorderLayout.SOUTH);
        background.setBorder(new EmptyBorder(10, 10, 10, 10));
        setTitle("Chat Application");
        setUndecorated(true); // Defina como sem decoração antes de tornar o frame exibível
        setContentPane(background);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(522, 669);
        setLocationRelativeTo(null); // Centralize a janela na tela
        setVisible(true); // Torne o frame visível
    }
}