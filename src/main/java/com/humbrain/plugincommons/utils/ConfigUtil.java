package com.humbrain.plugincommons.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;

public class ConfigUtil {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin p) {
        plugin = p;
    }

    public static String getPrefix() {
        return plugin.getConfig().getString("messages.prefix", "[PluginCommons] ");
    }

    public static String msgInChat(String path) {
        String value = plugin.getConfig().getString(path);
        String prefix = getPrefix();
        String finalText = prefix + value;
        return TextUtil.parseString(finalText);
    }

    public static Component msg(String path) {
        String value = plugin.getConfig().getString(path);
        return TextUtil.parse(value != null ? value : "");
    }

    public static List<Component> lore(String path) {
        List<String> list = plugin.getConfig().getStringList(path);
        return list.stream().map(TextUtil::parse).collect(Collectors.toList());
    }

    public static String msgString(String path) {
        String raw = plugin.getConfig().getString(path);
        if (raw == null) {
            return "[" + path + " introuvable]";
        }
        return raw
                .replaceAll("&[0-9a-fk-or]", "") // remove color codes
                .replaceAll("§[0-9a-fk-or]", "")
                .replaceAll("&#([A-Fa-f0-9]{6})", "")
                .trim();
    }

    public static List<String> loreString(String path) {
        return plugin.getConfig().getStringList(path).stream()
                .map(it -> it.replaceAll("&[0-9a-fk-or]", "")
                        .replaceAll("§[0-9a-fk-or]", ""))
                .collect(Collectors.toList());
    }

    public static Material getMaterial(String path) {
        String materialName = plugin.getConfig().getString(path);
        if (materialName == null || materialName.isEmpty()) {
            return Material.AIR; // Default to AIR if not found
        }
        try {
            return Material.valueOf(materialName.toUpperCase());
        } catch (IllegalArgumentException e) {
            plugin.getLogger().warning("Material " + materialName + " not found, defaulting to AIR.");
            return Material.AIR;
        }
    }
}