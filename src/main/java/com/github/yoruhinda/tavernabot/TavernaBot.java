package com.github.yoruhinda.tavernabot;

import java.sql.SQLException;

public class TavernaBot {
    public static void main(String[] args) {
        try {
            initializeBot();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initializeBot() throws InterruptedException {
        Bot bot = new Bot();
        bot.launcher();
    }
}
