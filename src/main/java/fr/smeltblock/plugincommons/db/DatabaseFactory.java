package fr.smeltblock.plugincommons.db;

import com.humbrain.plugincommons.db.provider.*;
import fr.smeltblock.plugincommons.db.provider.DatabaseProvider;
import fr.smeltblock.plugincommons.db.provider.MySQLProvider;
import fr.smeltblock.plugincommons.db.provider.PostgreSQLProvider;
import fr.smeltblock.plugincommons.db.provider.SQLiteProvider;
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
