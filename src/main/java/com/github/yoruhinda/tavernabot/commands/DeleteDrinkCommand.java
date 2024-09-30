package com.github.yoruhinda.tavernabot.commands;

import com.github.yoruhinda.tavernabot.model.Drink;
import com.github.yoruhinda.tavernabot.services.DrinkService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class DeleteDrinkCommand extends ListenerAdapter {
    private DrinkService drinkService;

    public DeleteDrinkCommand(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equalsIgnoreCase("remover")) {
            if(event.getOption("drinkname") != null){
                Drink drink = drinkService.getDrinkByName(event.getOption("drinkname").getAsString());
                if (drink == null) {
                    EmbedBuilder embed = new EmbedBuilder();
                    embed.setColor(Color.PINK);
                    embed.setTitle(event.getOption("drinkname").getAsString() + ", Este drink não existe.");
                    event.replyEmbeds(embed.build()).queue();
                    return;
                }
                drinkService.deleteDrink(drink.getId());
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle(drink.getDrink_Name() + ", Foi Removido!");
                event.replyEmbeds(embed.build()).queue();
                return;
            }
            EmbedBuilder embed = new EmbedBuilder();
            embed.setColor(Color.PINK);
            embed.setTitle("Insira o nome da bebida que deseja remover!");
            event.replyEmbeds(embed.build()).queue();
        }
    }
}
