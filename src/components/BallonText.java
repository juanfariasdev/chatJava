package components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BallonText extends JPanel {
    private final String text;
    private final Font font;

    public BallonText(String text) {
        this.text = text;
        this.font = new Font("Arial", Font.PLAIN, 14); // Definindo a fonte Arial, tamanho 14
        setOpaque(false); // Torna o painel transparente
        setBorder(new EmptyBorder(10, 10, 10, 15)); // Define uma borda vazia ao redor do painel
    }

    @Override
    public Dimension getPreferredSize() {
        int maxWidth = (int) (getParent().getWidth() * 0.75); // 75% da largura do contêiner pai
        FontMetrics metrics = getFontMetrics(font); // Obtém as métricas da fonte
        int width = Math.min(maxWidth, calculateTextWidth(metrics, text, maxWidth)); // Calcula a largura do texto com base no limite máximo
        int height = calculateTextHeight(metrics, text, width); // Calcula a altura do texto com base na largura calculada
        return new Dimension(width + 30, height + 40); // Adiciona uma margem extra para bordas
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create(); // Cria um objeto Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Ativa o antialiasing
        g2.setFont(font); // Define a fonte

        FontMetrics metrics = g2.getFontMetrics(); // Obtém as métricas da fonte
        int maxWidth = (int) (getParent().getWidth() * 0.75); // 75% da largura do contêiner pai
        int width = Math.min(maxWidth, calculateTextWidth(metrics, text, maxWidth)); // Calcula a largura do texto com base no limite máximo
        int height = calculateTextHeight(metrics, text, width); // Calcula a altura do texto com base na largura calculada

        // Desenha o retângulo arredondado
        g2.setPaint(Color.WHITE); // Define a cor de preenchimento como branco
        g2.fill(new RoundRectangle2D.Float(0, 0, width + 20, height + 30, 20, 20)); // Cria e preenche um retângulo arredondado

        // Desenha o texto
        g2.setPaint(Color.BLACK); // Define a cor do texto como preto
        drawText(g2, text, 10, 25, width); // Desenha o texto
        g2.dispose(); // Descarta o objeto Graphics2D
    }

    private void drawText(Graphics2D g2, String text, int x, int y, int maxWidth) {
        FontMetrics metrics = g2.getFontMetrics(); // Obtém as métricas da fonte
        int lineHeight = metrics.getHeight(); // Obtém a altura da linha
        int curX = x; // Posição X atual para desenhar
        int curY = y; // Posição Y atual para desenhar
        int wordLeght = text.split(" ").length;

        for (String word : text.split(" ")) { // Divide o texto em palavras
            int wordWidth = metrics.stringWidth(word + " "); // Calcula a largura da palavra
            if (curX + wordWidth > maxWidth) { // Se a largura atual mais a largura da palavra exceder o máximo permitido
                curX = x; // Reseta a posição X
                curY += lineHeight; // Move para a próxima linha
            }
            if( wordLeght <= 1){
                curY = y;
            }
            g2.drawString(word, curX, curY); // Desenha a palavra na posição atual
            curX += wordWidth; // Atualiza a posição X
        }
    }

    private int calculateTextWidth(FontMetrics metrics, String text, int maxWidth) {
        int curX = 0; // Posição X atual
        int maxLineWidth = 0; // Largura máxima da linha

        for (String word : text.split(" ")) { // Divide o texto em palavras
            int wordWidth = metrics.stringWidth(word + " "); // Calcula a largura da palavra
            if (curX + wordWidth > maxWidth) { // Se a largura atual mais a largura da palavra exceder o máximo permitido
                maxLineWidth = Math.max(maxLineWidth, curX); // Atualiza a largura máxima da linha
                curX = 0; // Reseta a posição X
            }
            curX += wordWidth; // Atualiza a posição X
        }

        maxLineWidth = Math.max(maxLineWidth, curX); // Calcula a largura máxima da linha final
        return maxLineWidth;
    }

    private int calculateTextHeight(FontMetrics metrics, String text, int maxWidth) {
        int lineHeight = metrics.getHeight(); // Obtém a altura da linha
        int curX = 0; // Posição X atual
        int numLines = 1; // Número de linhas necessárias

        for (String word : text.split(" ")) { // Divide o texto em palavras
            int wordWidth = metrics.stringWidth(word + " "); // Calcula a largura da palavra
            if (curX + wordWidth > maxWidth) { // Se a largura atual mais a largura da palavra exceder o máximo permitido
                numLines++; // Incrementa o número de linhas
                curX = 0; // Reseta a posição X
            }
            curX += wordWidth; // Atualiza a posição X
        }

        return lineHeight * numLines; // Calcula a altura total do texto
    }
}
