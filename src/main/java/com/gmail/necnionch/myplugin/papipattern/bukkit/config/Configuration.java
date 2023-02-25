package com.gmail.necnionch.myplugin.papipattern.bukkit.config;

import com.gmail.necnionch.myplugin.papipattern.bukkit.pattern.HolderPattern;
import com.gmail.necnionch.myplugin.papipattern.common.BukkitConfigDriver;
import com.google.common.collect.Lists;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class Configuration extends BukkitConfigDriver {
    private final List<HolderPattern> holders = Lists.newArrayList();

    public Configuration(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public boolean onLoaded(FileConfiguration config) {
        holders.clear();
        if (super.onLoaded(config)) {
            ConfigurationSection section = config.getConfigurationSection("placeholders");
            if (section != null) {
                for (String name : section.getKeys(false)) {
                    HolderPattern holder = HolderPattern.deserialize(name, section.getMapList(name));
                    holders.add(holder);
                }
            }
            return true;
        }
        return false;
    }


    public List<HolderPattern> getHolders() {
        return Collections.unmodifiableList(holders);
    }

}
