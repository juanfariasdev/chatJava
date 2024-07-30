package pages;

import components.ChatPanel;
import components.InputText;
import controllers.*;
import ui.Background;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;


public class ChatForm extends JFrame {
    private Sender sender;

    public ChatForm() {
        Background background = new Background();
        ChatPanel chatPanel = new ChatPanel();
        GUIMessageContainer messageContainer = new GUIMessageContainer(chatPanel);
        ChatController chatController = new ChatController(chatPanel);
        Scanner reader = new Scanner(System.in);
        System.out.print("Porta local: ");
        int localPort = reader.nextInt();
        System.out.print("Porta remota: ");
        int serverPort = reader.nextInt();

        System.out.print("Nome: ");
        String username = reader.nextLine();


        try {
            sender = ChatFactory.build("localhost", localPort, serverPort, messageContainer); // Ajuste as portas conforme necessário
        } catch (ChatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao iniciar o chat: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        InputText inputText = new InputText(chatController, sender);
        // Cria o painel superior com o botão de sair
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JButton closeButton = new JButton("X");
        closeButton.setContentAreaFilled(false);
        closeButton.setBorderPainted(false);
        closeButton.setFocusPainted(false);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setForeground(Color.RED);

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        topPanel.add(closeButton, BorderLayout.EAST);

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
}