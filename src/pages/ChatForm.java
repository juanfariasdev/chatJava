package pages;

import components.ChatPanel;
import components.InputText;
import controllers.ChatController;
import ui.Background;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatForm extends JFrame {
    public ChatForm() {
        Background background = new Background();
        ChatPanel chatPanel = new ChatPanel();
        ChatController chatController = new ChatController(chatPanel);
        InputText inputText = new InputText(chatController);

        // Cria o painel superior com o botão de sair
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton closeButton = new JButton("X");
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 20));
        closeButton.setForeground(Color.RED);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        topPanel.add(closeButton, BorderLayout.EAST);

        background.setLayout(new BorderLayout());
        background.add(topPanel, BorderLayout.NORTH);
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
