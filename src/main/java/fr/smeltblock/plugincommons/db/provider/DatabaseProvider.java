package fr.smeltblock.plugincommons.db.provider;

import java.sql.Connection;

public interface DatabaseProvider {
    Connection connect() throws Exception;
}
