package com.github.yoruhinda.tavernabot.commands;

import com.github.yoruhinda.tavernabot.api.TenorGifApi;
import com.github.yoruhinda.tavernabot.services.DrinkService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class OrderDrinkCommand extends ListenerAdapter {
    private DrinkService drinkService;

    public OrderDrinkCommand(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("pedir")) {
            if (event.getOption("bebida") != null) {
                if (drinkService.getDrinkByName(event.getOption("bebida").getAsString()) != null) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.PINK);
                    embed.setTitle(event.getUser().getName() + ", Esta pedindo um drink chamado(a) " + drinkService.getDrinkByName(event.getOption("bebida").getAsString()).getDrink_Name() + "!!!");
                    embed.setDescription("<@&969364973274955786>").setImage(TenorGifApi.searchGif("animedrink"));
                    event.replyEmbeds(embed.build()).queue();
                    return;
                }
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle("Esta Bebida não esta no cardapio BAKAA!");
                embed.setImage(TenorGifApi.searchGif("animemaidangry"));
                event.replyEmbeds(embed.build()).queue();
            } else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle("Escolha uma bebida BAKAA!");
                embed.setImage(TenorGifApi.searchGif("animemaidangry"));
                event.replyEmbeds(embed.build()).queue();
            }
        }
    }
}
