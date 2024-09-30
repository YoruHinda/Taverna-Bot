package com.github.yoruhinda.tavernabot.commands;

import com.github.yoruhinda.tavernabot.api.TenorGifApi;
import com.github.yoruhinda.tavernabot.services.DrinkService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ServeDrinkCommand extends ListenerAdapter {
    private DrinkService drinkService;

    public ServeDrinkCommand(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        User client;
        if (event.getName().equalsIgnoreCase("servir")) {
            if(event.getOption("cliente") != null){
                if(event.getOption("bebida") != null){
                    if(drinkService.getDrinkByName(event.getOption("bebida").getAsString()) != null){
                        client = event.getOption("cliente").getAsUser();
                        EmbedBuilder embedBuilder = new EmbedBuilder();
                        embedBuilder.setColor(Color.PINK);
                        embedBuilder.setTitle("Ola! querido(a) cliente , Aqui esta seu/sua " + drinkService.getDrinkByName(event.getOption("bebida").getAsString()).getDrink_Name() + ".");
                        embedBuilder.setDescription(client.getAsMention()).setImage(TenorGifApi.searchGif("animedrink"));
                        event.replyEmbeds(embedBuilder.build()).queue();
                        return;
                    }
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.PINK);
                    embed.setTitle("Esta Bebida não esta no cardapio, BAKAA!");
                    embed.setImage(TenorGifApi.searchGif("animemaidangry"));
                    event.replyEmbeds(embed.build()).queue();
                }
            }else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle("Escolha quem servir BAKAA!");
                embed.setImage(TenorGifApi.searchGif("animemaidangry"));
                event.replyEmbeds(embed.build()).queue();
            }
        }
    }
}
