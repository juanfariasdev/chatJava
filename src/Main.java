import pages.ChatForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatForm::new);
    }
}
