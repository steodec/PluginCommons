package com.humbrain.plugincommons.db.provider;

import java.sql.Connection;

public interface DatabaseProvider {
    Connection connect() throws Exception;
}
