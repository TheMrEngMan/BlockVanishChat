package com.MrEngMan.BlockVanishChat;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    public ChatListener(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        // If player is vanished and does not have bypass permission
        if(!player.hasPermission("bvc.bypass") && Utils.isVanished(player)) {

            // Block message and inform player
            player.sendMessage(Utils.SendChatMessage(Main.plugin.getConfig().getString("ChatBlockedMessage")));
            event.setCancelled(true);

        }

    }

}
