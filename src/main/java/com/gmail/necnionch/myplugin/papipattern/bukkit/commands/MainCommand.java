package com.gmail.necnionch.myplugin.papipattern.bukkit.commands;

import com.gmail.necnionch.myplugin.papipattern.bukkit.PlaceholderPatternPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainCommand implements TabExecutor {

    private final PlaceholderPatternPlugin plugin;

    public MainCommand(PlaceholderPatternPlugin plugin) {
        this.plugin = plugin;
    } 
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (1 <= args.length && args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            int patterns = plugin.getHolderPatterns().size();
            sender.sendMessage(ChatColor.GOLD + "設定ファイルからプレースホルダを " + patterns + "件 を読み込みました。");
            return true;
        }
        return false;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1)
            return Stream.of("reload")
                    .filter(c -> c.startsWith(args[0].toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        return Collections.emptyList();
    }

}
