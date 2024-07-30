package pages;

import components.ChatPanel;
import components.InputText;
import controllers.ChatController;
import ui.Background;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatForm extends JFrame {
    public ChatForm() {
        Background background = new Background();
        ChatPanel chatPanel = new ChatPanel();
        ChatController chatController = new ChatController(chatPanel);
        InputText inputText = new InputText(chatController);

        background.setLayout(new BorderLayout());
        background.add(chatPanel, BorderLayout.CENTER);
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
