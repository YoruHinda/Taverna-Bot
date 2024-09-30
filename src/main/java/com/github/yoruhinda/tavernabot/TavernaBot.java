package com.github.yoruhinda.tavernabot;

import com.github.yoruhinda.tavernabot.connection.DatabaseConnection;

import java.sql.Connection;

public class TavernaBot {
    public static void main(String[] args) {
        try {
            initializeBot();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeBot() throws InterruptedException {
        Connection connection = DatabaseConnection.createConnection();
        Bot bot = new Bot(connection);
        bot.launcher();
    }
}
