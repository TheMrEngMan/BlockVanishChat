package com.MrEngMan.BlockVanishChat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.earth2me.essentials.Essentials;

import java.io.File;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;
    public static Essentials essentialsPlugin;

    // When plugin is first enabled
    @SuppressWarnings("static-access")
    @Override
    public void onEnable() {
        this.plugin = this;

        // Generate the config if need be
        if (!(new File(this.getDataFolder(), "config.yml").exists())) {
            this.saveDefaultConfig();
        }

        // Register stuff
        new ChatListener(this);
        ReloadCommandHandler reloadCommandHandler = new ReloadCommandHandler();
        this.getCommand("bvcreload").setExecutor(reloadCommandHandler);
        Bukkit.getPluginManager().registerEvents(this, this);

        // Get instance of Essentials plugin
        essentialsPlugin = (Essentials) getServer().getPluginManager().getPlugin("Essentials");

    }

    public void reloadTheConfig() {

        // Generate the config file if it was deleted
        if (!(new File(this.getDataFolder(), "config.yml").exists())) {
            this.saveDefaultConfig();
        }

        // Load new config values
        reloadConfig();

    }

    public class ReloadCommandHandler implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

            // Player issued command
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Make sure they have permission
                if (player.hasPermission("bvc.reload")) {
                    plugin.reloadTheConfig();
                    player.sendMessage(Utils.SendChatMessage(plugin.getConfig().getString("ReloadedMessage")));
                } else {
                    player.sendMessage(Utils.SendChatMessage(plugin.getConfig().getString("NoPermissionMessage")));
                }

            }

            // Console issued command
            else if (sender instanceof ConsoleCommandSender) {
                plugin.reloadTheConfig();
                ConsoleCommandSender console = getServer().getConsoleSender();
                console.sendMessage(Utils.SendChatMessage(plugin.getConfig().getString("ReloadedMessage")));
            }

            return true;
        }

    }

}