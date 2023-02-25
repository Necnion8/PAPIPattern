package com.gmail.necnionch.myplugin.papipattern.bukkit;

import com.gmail.necnionch.myplugin.papipattern.bukkit.commands.MainCommand;
import com.gmail.necnionch.myplugin.papipattern.bukkit.config.Configuration;
import com.gmail.necnionch.myplugin.papipattern.bukkit.pattern.HolderPattern;
import com.gmail.necnionch.myplugin.papipattern.bukkit.pattern.HolderPatternMatcher;
import org.bukkit.OfflinePlayer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public final class PlaceholderPatternPlugin extends JavaPlugin {
    private final Configuration config = new Configuration(this);
    private final PatternPlaceholderExpansion placeholderExpansion = new PatternPlaceholderExpansion(this);

    @Override
    public void onEnable() {
        config.load();
        placeholderExpansion.register();
        Optional.ofNullable(getCommand("papipattern")).ifPresent(cmd ->
                cmd.setExecutor(new MainCommand(this)));
    }

    @Override
    public void onDisable() {
        placeholderExpansion.unregister();
    }

    public void reloadConfig() {
        config.load();
    }

    public List<HolderPattern> getHolderPatterns() {
        return config.getHolders();
    }

    public @Nullable HolderPatternMatcher findHolderPattern(String params) {
        for (HolderPattern holder : config.getHolders()) {
            HolderPatternMatcher matcher = holder.find(params);
            if (matcher != null)
                return matcher;
        }
        return null;
    }

    public @Nullable String processHolderPattern(OfflinePlayer player, String params) {
        return Optional.ofNullable(findHolderPattern(params))
                .map(m -> m.setPlaceholders(player))
                .orElse(null);
    }

}
