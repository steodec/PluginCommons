package fr.smeltblock.plugincommons.db;

import fr.smeltblock.plugincommons.db.provider.DatabaseProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.Connection;

public class DatabaseManager {

    private static DatabaseProvider provider;
    private static Connection connection;


    public static void init(JavaPlugin plugin) {
        try {
            provider = DatabaseFactory.getProvider(plugin.getConfig(), plugin.getDataFolder());
            connection = provider.connect();
            plugin.getLogger().info("Connection à la bdd réussie");
        } catch (Exception e) {
            plugin.getLogger().info("Échec de la connexion à la base de données : " + e.getMessage());
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
