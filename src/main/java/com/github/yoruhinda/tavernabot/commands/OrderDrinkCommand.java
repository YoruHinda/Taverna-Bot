package com.github.yoruhinda.tavernabot.commands;

import com.github.yoruhinda.tavernabot.api.TenorGifApi;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class OrderDrinkCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        if(event.getName().equalsIgnoreCase("pedir")){
            if(event.getOption("bebida") != null){
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle(event.getUser().getName() + " Esta pedindo uma " + event.getOption("bebida").getAsString() + "!!!");
                embed.setDescription("<@&969364973274955786>").setImage(TenorGifApi.searchGif("animedrink"));
                event.replyEmbeds(embed.build()).queue();
            }else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle("Escolha uma bebida BAKAAA!");
                embed.setImage(TenorGifApi.searchGif("animegirlbaka"));
                event.replyEmbeds(embed.build()).queue();
            }
        }
    }
}
