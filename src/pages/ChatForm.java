package pages;

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

        background.setLayout(new BorderLayout());
        background.add(chatField, BorderLayout.NORTH);
        background.add(inputText, BorderLayout.SOUTH);
        background.setBorder(new EmptyBorder(10, 10, 10, 10));

        setTitle("Chat Application");
        setUndecorated(true);
        setContentPane(background);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(560, 669);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
