package com.humbrain.plugincommons.utils;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class PluginLogger {
    private static Logger logger;
    private static final String PREFIX = "";

    public static void init(JavaPlugin plugin) {
        logger = plugin.getLogger();
    }

    public static void info(String message) {
        logger.info(PREFIX + message);
    }

    public static void error(String message) {
        logger.severe(PREFIX + message);
    }

    public static void success(String message) {
        logger.info(PREFIX + " [SUCCESS] " + message);
    }

    public static void warning(String message) {
        logger.warning(PREFIX + " [WARNING] " + message);
    }
}