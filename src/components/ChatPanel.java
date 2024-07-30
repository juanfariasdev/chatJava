package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChatPanel extends JPanel {
    private JPanel chatContainer;
    private JScrollPane scrollPane;
    private boolean shouldScrollToBottom = true;

    public ChatPanel() {
        setOpaque(false);
        setLayout(new BorderLayout());

        chatContainer = new JPanel();
        chatContainer.setOpaque(false);
        chatContainer.setLayout(new BoxLayout(chatContainer, BoxLayout.Y_AXIS));
        chatContainer.setBorder(new EmptyBorder(0, 10, 0, 10));

        scrollPane = new JScrollPane(chatContainer);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
        verticalBar.addAdjustmentListener(e -> {
            if (!e.getValueIsAdjusting()) {
                shouldScrollToBottom = (e.getAdjustable().getMaximum() - e.getAdjustable().getVisibleAmount()) == e.getAdjustable().getValue();
            }
        });

        add(scrollPane, BorderLayout.CENTER);

        // Adicionar mensagens iniciais
        for (int i = 0; i < 5; i++) {
            addBallonText("opa", false);
            addBallonText("Mensagem curta", false);
            addBallonText("Este é um balão de conversa com um texto mais longo para testar o ajuste de tamanho. Vamos ver como o layout se comporta com várias linhas de texto.", true);
            addBallonText("Outro balão de texto.", false);
            addBallonText("Mais um balão de texto para testar.", true);
            addBallonText("Texto final do balão de conversa.", false);
        }
    }

    public void addBallonText(String text, boolean alignRight) {
        BallonText ballonText = new BallonText(text, alignRight);
        JPanel alignmentPanel = new JPanel();
        alignmentPanel.setLayout(new BorderLayout());
        // alignmentPanel.setBackground(Color.RED); // Pinta o fundo do alignmentPanel de vermelho
        alignmentPanel.setOpaque(false);

        alignmentPanel.add(Box.createVerticalGlue(), BorderLayout.NORTH); // Garante que o ballonText esteja no topo

        if (alignRight) {
            alignmentPanel.add(ballonText, BorderLayout.EAST);
        } else {
            alignmentPanel.add(ballonText, BorderLayout.WEST);
        }

        alignmentPanel.add(Box.createVerticalGlue(), BorderLayout.SOUTH); // Preenche o espaço inferior para alinhar ao topo

        chatContainer.add(alignmentPanel);
        chatContainer.revalidate();
        chatContainer.repaint();

        if (shouldScrollToBottom) {
            SwingUtilities.invokeLater(() -> {
                JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
                verticalBar.setValue(verticalBar.getMaximum());
            });
        }
    }

    @Override
    public void doLayout() {
        super.doLayout();
        for (Component component : chatContainer.getComponents()) {
            if (component instanceof JPanel) {
                for (Component ballonText : ((JPanel) component).getComponents()) {
                    if (ballonText instanceof BallonText) {
                        ballonText.setMaximumSize(new Dimension((int) (getWidth() * 0.75), ballonText.getPreferredSize().height));
                    }
                }
            }
        }
    }
}
