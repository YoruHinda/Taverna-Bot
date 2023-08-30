package com.github.yoruhinda.tavernabot;

public class TavernaBot {
    public static void main(String[] args) {
        Bot bot = new Bot();
        try{
            bot.launcher();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
