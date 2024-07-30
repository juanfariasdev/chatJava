package controllers;

public interface Sender {
    void send(String message) throws ChatException;
}