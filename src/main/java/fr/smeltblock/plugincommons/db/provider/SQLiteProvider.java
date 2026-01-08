package fr.smeltblock.plugincommons.db.provider;

import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteProvider implements DatabaseProvider {

    private final FileConfiguration config;
    private final File pluginDataFolder;

    public SQLiteProvider(FileConfiguration config, File pluginDataFolder) {
        this.config = config;
        this.pluginDataFolder = pluginDataFolder;
    }

    @Override
    public Connection connect() throws Exception {
        String fileName = config.getString("database.file", "armorstand.db");
        File dbFile = new File(pluginDataFolder, fileName);

        if (!dbFile.exists()) {
            dbFile.getParentFile().mkdirs();
            dbFile.createNewFile();
        }

        String url = "jdbc:sqlite:" + dbFile.getAbsolutePath();
        return DriverManager.getConnection(url);
    }
}
