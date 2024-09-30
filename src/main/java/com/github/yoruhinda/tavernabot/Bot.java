package com.github.yoruhinda.tavernabot;

import com.github.yoruhinda.tavernabot.commands.*;
import com.github.yoruhinda.tavernabot.services.DrinkService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.sql.Connection;

public class Bot {
    private JDA api;
    private Connection connection;
    private DrinkService drinkService;

    public Bot(Connection connection){
        this.connection = connection;
        drinkService = new DrinkService(connection);
        drinkService.createDrinkTable();
    }

    public void launcher() throws InterruptedException {
        String token = System.getenv("DISCORD_TOKEN");
        api = JDABuilder.create(token, GatewayIntent.GUILD_MESSAGES).build().awaitReady();
        api.addEventListener(new ServeDrinkCommand(drinkService));
        api.addEventListener(new OrderDrinkCommand(drinkService));
        api.addEventListener(new CreateDrinkCommand(drinkService));
        api.addEventListener(new DeleteDrinkCommand(drinkService));
        api.addEventListener(new DrinkMenuCommand(drinkService));
        commandManager();
    }

    public void commandManager() {
        api.updateCommands().addCommands(
                Commands.slash("servir", "Servir Bebida ao cliente!")
                .addOption(OptionType.STRING, "bebida", "Qual bebida?")
                .addOption(OptionType.MENTIONABLE, "cliente", "Cliente a servir!"),
                Commands.slash("pedir", "Pedir Bebida ao bartender!").addOption(OptionType.STRING, "bebida", "Qual bebida?"),
                Commands.slash("adicionar", "Adiciona a bebida ao cardapio").addOption(OptionType.STRING, "bebida", "Nome da bebida para adicionar!"),
                Commands.slash("remover", "Remove a bebida do cardapio").addOption(OptionType.STRING, "bebida", "Nome da bebida para remover!"),
                Commands.slash("menu", "Cardapio/Menu")
        ).queue();
    }
}
