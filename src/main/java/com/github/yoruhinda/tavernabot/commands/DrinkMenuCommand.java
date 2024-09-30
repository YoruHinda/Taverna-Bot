package com.github.yoruhinda.tavernabot.commands;

import com.github.yoruhinda.tavernabot.api.TenorGifApi;
import com.github.yoruhinda.tavernabot.model.Drink;
import com.github.yoruhinda.tavernabot.services.DrinkService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class DrinkMenuCommand extends ListenerAdapter {
    private DrinkService drinkService;

    public DrinkMenuCommand(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("menu")) {
            if (!drinkService.getAllDrinks().isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                for (Drink allDrink : drinkService.getAllDrinks()) {
                    stringBuilder.append("• ").append(allDrink.getDrink_Name()).append("\n");
                }
//                embed.setDescription(stringBuilder);
                embed.setTitle(event.getUser().getName() + ", Seu Cardapio! >_<" + "\n\n" + stringBuilder);
                embed.setDescription(event.getUser().getAsMention()).setImage(TenorGifApi.searchGif("animemaid"));
                event.replyEmbeds(embed.build()).queue();
                return;
            }
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.PINK);
            embed.setTitle(event.getUser().getName() + ", O cardapio esta vazio. >-<");
            event.replyEmbeds(embed.build()).queue();
        }
    }
}
