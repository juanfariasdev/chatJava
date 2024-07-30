package controllers;

import components.ChatPanel;

public class GUIMessageContainer implements MessageContainer {
    private ChatPanel chatPanel;

    public GUIMessageContainer(ChatPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    @Override
    public void newMessage(String message) {
        chatPanel.addBallonText(message.trim(), false);
    }
}