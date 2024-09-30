package com.github.yoruhinda.tavernabot.commands;

import com.github.yoruhinda.tavernabot.api.TenorGifApi;
import com.github.yoruhinda.tavernabot.model.Drink;
import com.github.yoruhinda.tavernabot.services.DrinkService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class CreateDrinkCommand extends ListenerAdapter {
    private DrinkService drinkService;
    public CreateDrinkCommand(DrinkService drinkService){
        this.drinkService = drinkService;
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equalsIgnoreCase("adicionar")){
            if(event.getOption("drinkname") != null){
                if(drinkService.getDrinkByName(event.getOption("drinkname").getAsString()) == null){
                    EmbedBuilder embed = new EmbedBuilder();
                    Drink drink = new Drink(event.getOption("drinkname").getAsString());
                    drinkService.saveDrink(drink);
                    embed.setColor(Color.PINK);
                    embed.setTitle(drink.getDrink_Name() + ", Foi adicionado ao cardapio!!");
                    embed.setImage(TenorGifApi.searchGif("animemaid"));
                    event.replyEmbeds(embed.build()).queue();
                    return;
                }
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle(event.getOption("drinkname").getAsString() + ", Esta bebida ja existe!");
                embed.setImage(TenorGifApi.searchGif("animemaidangry"));
                event.replyEmbeds(embed.build()).queue();
                return;
            }
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.PINK);
            embed.setTitle("Insira o nome da bebida que deseja adicionar!");
            embed.setImage(TenorGifApi.searchGif("animemaidangry"));
            event.replyEmbeds(embed.build()).queue();
        }
    }
}
