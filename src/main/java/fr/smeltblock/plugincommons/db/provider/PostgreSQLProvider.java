package fr.smeltblock.plugincommons.db.provider;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgreSQLProvider implements DatabaseProvider {

    private final FileConfiguration config;

    public PostgreSQLProvider(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public Connection connect() throws Exception {
        String host = config.getString("database.host", "localhost");
        int port = config.getInt("database.port", 5432);
        String name = config.getString("database.name", "armorstand_db");
        String user = config.getString("database.user", "postgres");
        String password = config.getString("database.password", "");
        String url = "jdbc:postgresql://" + host + ":" + port + "/" + name;
        return DriverManager.getConnection(url, user, password);
    }
}
