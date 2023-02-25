package com.gmail.necnionch.myplugin.papipattern.bukkit;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PatternPlaceholderExpansion extends PlaceholderExpansion {

    private final PlaceholderPatternPlugin plugin;

    public PatternPlaceholderExpansion(PlaceholderPatternPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "ppattern";
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join(", ", plugin.getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return plugin.getDescription().getVersion();
    }


    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        return plugin.processHolderPattern(player, params);
    }

}
