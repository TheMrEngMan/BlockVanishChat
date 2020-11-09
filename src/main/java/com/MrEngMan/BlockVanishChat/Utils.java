package com.MrEngMan.BlockVanishChat;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import static com.MrEngMan.BlockVanishChat.Main.essentialsPlugin;

public class Utils {

    // Translate '&' as formatting codes
    public static String SendChatMessage(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    // Check if player is vanished
    public static boolean isVanished(Player player) {

        // For SuperVanish / PremiumVanish / VanishNoPacket / possibly more
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }

        // For Essentials
        if(essentialsPlugin != null) {
            return essentialsPlugin.getUser(player).isVanished();
        }

        // Otherwise, player is not vanished
        return false;

    }

}

