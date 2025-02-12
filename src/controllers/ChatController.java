package controllers;

import components.ChatPanel;

public class ChatController {
    private final ChatPanel chatPanel;

    public ChatController(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    public void sendMessage(String text, boolean alignRight) {
        if (text != null && !text.trim().isEmpty()) {
            chatPanel.addBallonText(text, alignRight);
        }
    }
}
