package fr.smeltblock.plugincommons.db.provider;

import org.bukkit.configuration.file.FileConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLProvider implements DatabaseProvider {

    private final FileConfiguration config;

    public MySQLProvider(FileConfiguration config) {
        this.config = config;
    }

    @Override
    public Connection connect() throws Exception {
        String host = config.getString("database.host", "localhost");
        int port = config.getInt("database.port", 3306);
        String name = config.getString("database.name", "armorstand_db");
        String user = config.getString("database.user", "root");
        String password = config.getString("database.password", "");
        String url = "jdbc:mysql://" + host + ":" + port + "/" + name + "?useSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(url, user, password);
    }
}
