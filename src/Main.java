import components.ChatPanel;
import pages.ChatForm;
import controllers.*;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Chat Configuration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel localPortLabel = new JLabel("Porta local:");
        JTextField localPortField = new JTextField(10);
        JLabel remotePortLabel = new JLabel("Porta remota:");
        JTextField remotePortField = new JTextField(10);
        JLabel usernameLabel = new JLabel("Nome de usuário:");
        JTextField usernameField = new JTextField(10);
        JButton submitButton = new JButton("Conectar");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(localPortLabel, gbc);

        gbc.gridx = 1;
        panel.add(localPortField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(remotePortLabel, gbc);

        gbc.gridx = 1;
        panel.add(remotePortField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        panel.add(usernameField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        frame.add(panel);
        frame.setVisible(true);

        submitButton.addActionListener(e -> {
            try {
                String localPortStr = localPortField.getText().trim();
                String remotePortStr = remotePortField.getText().trim();
                String username = usernameField.getText().trim();

                if (localPortStr.isEmpty() || remotePortStr.isEmpty() || username.isEmpty()) {
                    showError("Entrada inválida.");
                    return;
                }

                int localPort = Integer.parseInt(localPortStr);
                int remotePort = Integer.parseInt(remotePortStr);

                InetAddress serverAddress = InetAddress.getByName("localhost");

                ChatPanel chatPanel = new ChatPanel();
                GUIMessageContainer messageContainer = new GUIMessageContainer(chatPanel);

                Sender sender = ChatFactory.build(serverAddress.getHostName(), remotePort, localPort, messageContainer);

                new ChatForm(sender, chatPanel, username);

                frame.dispose(); // Fecha a janela de configuração após iniciar o chat

            } catch (NumberFormatException ex) {
                showError("Porta inválida.");
            } catch (UnknownHostException ex) {
                showError("Servidor não conhecido.");
            } catch (ChatException ex) {
                showError("Erro ao iniciar o chat: " + ex.getMessage());
            }
        });
    }

    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
