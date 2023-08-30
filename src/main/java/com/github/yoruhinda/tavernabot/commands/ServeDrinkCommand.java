package com.github.yoruhinda.tavernabot.commands;

import com.github.yoruhinda.tavernabot.api.TenorGifApi;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ServeDrinkCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        User client;
        if (event.getName().equalsIgnoreCase("servir")) {
            if(event.getOption("cliente") != null){
                client = event.getOption("cliente").getAsUser();
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.PINK);
                embedBuilder.setTitle("Ola! querido cliente, Aqui esta sua bebida S2!");
                embedBuilder.setDescription(client.getAsMention()).setImage(TenorGifApi.searchGif("animealcoholdrink"));
                event.replyEmbeds(embedBuilder.build()).queue();
            }else {
                EmbedBuilder embed = new EmbedBuilder();
                embed.setColor(Color.PINK);
                embed.setTitle("Escolha quem servir BAKAAAA!");
                embed.setImage(TenorGifApi.searchGif("animegirlbaka"));
                event.replyEmbeds(embed.build()).queue();
            }
        }
    }
}
