package com.github.yoruhinda.tavernabot;

import com.github.yoruhinda.tavernabot.events.TestEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Akali {
    private static String token = "MTEzNzg4MTM5MTY3ODM3Mzk3MA.GXHq1g.yqoqjMirAa3-59WvVbPXLPNxPVxw8UR-iVly8U";
    private JDA api;

    public void launcher() throws InterruptedException {
        api = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES).build().awaitReady();
        command();
    }

    public void command(){
        api.addEventListener(new TestEvent());
        api.updateCommands().addCommands(
                Commands.slash("test","Testando akali")
        ).queue();
    }
}
