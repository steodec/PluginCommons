package com.humbrain.plugincommons.db;

import com.humbrain.plugincommons.db.provider.*;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class DatabaseFactory {

    public static DatabaseProvider getProvider(FileConfiguration config, File dataFolder) {
        DatabaseType type = DatabaseType.fromString(config.getString("database.type"));
        return switch (type) {
            case MYSQL -> new MySQLProvider(config);
            case POSTGRESQL -> new PostgreSQLProvider(config);
            default -> new SQLiteProvider(config, dataFolder);
        };
    }
}
