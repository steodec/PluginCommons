package com.humbrain.plugincommons.db;

public enum DatabaseType {
    MYSQL,
    POSTGRESQL,
    SQLITE;

    public static DatabaseType fromString(String value) {
        if (value == null) return null;
        return switch (value.toLowerCase()) {
            case "mysql" -> MYSQL;
            case "postgresql" -> POSTGRESQL;
            case "sqlite" -> SQLITE;
            default -> null;
        };
    }
}
