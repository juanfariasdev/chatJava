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
    private String username;
    private JLabel userLabel;

    public ChatForm(Sender sender, ChatPanel chatPanel, String username) {
        this.sender = sender;
        this.chatPanel = chatPanel;
        this.username = username;
        initializeUI();
        waitForSecondConnection();
    }

    private void initializeUI() {
        Background background = new Background();
        GUIMessageContainer messageContainer = new GUIMessageContainer(chatPanel);
        ChatController chatController = new ChatController(chatPanel);

        InputText inputText = new InputText(chatController, sender);

        // Cria o painel superior com o botão de sair e o nome do usuário
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

        userLabel = new JLabel("Aguardando conexão...");
        userLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        userLabel.setForeground(Color.WHITE);

        topPanel.add(userLabel, BorderLayout.CENTER);
        topPanel.add(closeButton, BorderLayout.EAST);
        return topPanel;
    }

    private void waitForSecondConnection() {
        // Simulando a espera por uma segunda conexão.
        // Em um cenário real, isso envolveria lógica de rede para detectar a conexão do segundo usuário.
        new Thread(() -> {
            try {
                Thread.sleep(5000); // Simula o tempo de espera para a segunda conexão
                SwingUtilities.invokeLater(() -> userLabel.setText("Conectado com o: " + username));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
