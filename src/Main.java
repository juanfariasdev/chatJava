import components.ChatPanel;
import pages.ChatForm;
import controllers.*;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                String localPortStr = JOptionPane.showInputDialog(null, "Porta local:");
                String remotePortStr = JOptionPane.showInputDialog(null, "Porta remota:");

                if (localPortStr == null || remotePortStr == null) {
                    showError("Porta inválida.");
                    return;
                }

                int localPort = Integer.parseInt(localPortStr);
                int remotePort = Integer.parseInt(remotePortStr);

                InetAddress serverAddress = InetAddress.getByName("localhost");

                // Criação do ChatPanel aqui
                ChatPanel chatPanel = new ChatPanel();
                GUIMessageContainer messageContainer = new GUIMessageContainer(chatPanel);

                Sender sender = ChatFactory.build(serverAddress.getHostName(), remotePort, localPort, messageContainer);

                new ChatForm(sender, chatPanel);

            } catch (NumberFormatException e) {
                showError("Porta inválida.");
            } catch (UnknownHostException e) {
                showError("Servidor não conhecido.");
            } catch (ChatException e) {
                showError("Erro ao iniciar o chat: " + e.getMessage());
            }
        });
    }

    private static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
