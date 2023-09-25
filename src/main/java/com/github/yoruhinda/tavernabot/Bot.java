package com.github.yoruhinda.tavernabot;

import com.github.yoruhinda.tavernabot.commands.OrderDrinkCommand;
import com.github.yoruhinda.tavernabot.commands.ServeDrinkCommand;
import com.github.yoruhinda.tavernabot.utils.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class Bot {
    private JDA api;

    public void launcher() throws InterruptedException {
        String token = Config.discordApiKey;
        api = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES).build().awaitReady();
        api.addEventListener(new ServeDrinkCommand());
        api.addEventListener(new OrderDrinkCommand());
        commandManager();
    }

    public void commandManager() {
        api.updateCommands().addCommands(
                Commands.slash("servir", "Servir Bebida")
                .addOption(OptionType.STRING, "bebida", "Qual bebida?")
                .addOption(OptionType.MENTIONABLE, "cliente", "Cliente a servir!"),
                Commands.slash("pedir", "Pedir Bebida").addOption(OptionType.STRING, "bebida", "Qual bebida?")
        ).queue();
    }
}
