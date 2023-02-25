package com.gmail.necnionch.myplugin.papipattern.bukkit.pattern;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.OfflinePlayer;

import java.util.regex.Matcher;

public class HolderPatternMatcher {

    private final HolderPattern holder;
    private final Matcher matcher;

    public HolderPatternMatcher(HolderPattern holder, Matcher matcher) {
        this.holder = holder;
        this.matcher = matcher;
    }

    public HolderPattern getHolderPattern() {
        return holder;
    }

    public Matcher getMatcher() {
        return matcher;
    }

    public String setPlaceholders(OfflinePlayer player) {
        for (HolderPattern.MatchEntry entry : holder.getMatches()) {
            if (entry.getInput() == null) {
                return PlaceholderAPI.setPlaceholders(player, matcher.replaceAll(entry.getOutput()));
            } else if (entry.getCheck() == null) {
                continue;
            }

            Matcher check = entry.getCheck().matcher(PlaceholderAPI.setPlaceholders(player, matcher.replaceAll(entry.getInput())));
            if (check.find()) {
                return matcher.replaceAll(entry.getOutput());
            }
        }
        return "";
    }


}
