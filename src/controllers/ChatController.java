package controllers;

import components.ChatPanel;

public class ChatController {
    private ChatPanel chatPanel;

    public ChatController(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    public void sendMessage(String text) {
        if (text != null && !text.trim().isEmpty()) {
            chatPanel.addBallonText(text);
        }
    }
}
