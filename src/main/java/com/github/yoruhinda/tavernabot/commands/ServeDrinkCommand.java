package com.github.yoruhinda.tavernabot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class ServeCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        User client;
        if (event.getName().equalsIgnoreCase("servir")) {
            if(event.getOption("cliente") != null){
                client = event.getOption("cliente").getAsUser();
                EmbedBuilder embedBuilder = new EmbedBuilder();
                embedBuilder.setColor(Color.PINK);
                embedBuilder.setTitle("Ola! querido cliente, Aqui esta sua bebida S2!");
                embedBuilder.setDescription(client.getAsMention()).setImage("https://media.tenor.com/WkrtHIR7-pYAAAAC/anime-beer-bottle.gif");
                event.replyEmbeds(embedBuilder.build()).queue();
            }else {
                event.reply("Você precisa selecionar quem vai tomar bebida! BAKA").queue();
            }
        }
    }
}
