package pages;

import components.ChatPanel;
import components.InputText;
import controllers.*;
import ui.Background;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatForm extends JFrame {
    private Sender sender;
    private ChatPanel chatPanel;

    public ChatForm(Sender sender, ChatPanel chatPanel) {
        this.sender = sender;
        this.chatPanel = chatPanel;
        initializeUI();
    }

    private void initializeUI() {
        Background background = new Background();
        GUIMessageContainer messageContainer = new GUIMessageContainer(chatPanel);
        ChatController chatController = new ChatController(chatPanel);

        InputText inputText = new InputText(chatController, sender);

        // Cria o painel superior com o botão de sair
        JPanel topPanel = createTopPanel();

        background.setLayout(new BorderLayout());
        background.add(topPanel, BorderLayout.NORTH);  // Adiciona o painel superior
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

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton closeButton = new JButton("X");
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setForeground(Color.RED);

        closeButton.addActionListener(e -> System.exit(0));

        topPanel.add(closeButton, BorderLayout.EAST);
        return topPanel;
    }
}
