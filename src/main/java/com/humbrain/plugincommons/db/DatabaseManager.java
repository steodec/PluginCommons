package com.humbrain.plugincommons.db;

import com.humbrain.plugincommons.db.provider.DatabaseProvider;
import com.humbrain.plugincommons.utils.PluginLogger;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.sql.Connection;

public class DatabaseManager {

    private static DatabaseProvider provider;
    private static Connection connection;

    public static void init(FileConfiguration config, File pluginDataFolder) {
        try {
            provider = DatabaseFactory.getProvider(config, pluginDataFolder);
            connection = provider.connect();
            PluginLogger.success("Connection à la bdd réussie");
        } catch (Exception e) {
            PluginLogger.error("Échec de la connexion à la base de données : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static Connection requireConnection() {
        if (connection == null) {
            throw new IllegalStateException("La connexion à la base de données n'a pas été initialisée.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return connection;
    }
}
