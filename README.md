# PluginCommons

**PluginCommons** est une bibliothèque Java modulaire pour les développeurs de plugins Minecraft utilisant Paper/Spigot. Elle propose des outils puissants pour la configuration, la base de données (multi-fournisseur), la gestion de logs, le texte coloré et les objets Minecraft.

---

## 🧩 Fonctionnalités

### 🗄️ Base de données multi-fournisseur
- Choix dynamique entre **SQLite**, **MySQL** et **PostgreSQL** via le fichier `config.yml`
- Connexion simplifiée avec `DatabaseManager`
- Interfaces extensibles via `DatabaseProvider`

### ⚙️ Configuration centralisée (`ConfigUtil`)
- Chargement de préfixes, messages formatés, `ItemStack`, `Material`, `CustomModelData`, etc.
- Support des messages en `String`, `Component`, ou `List<Component>`
- Fallback intégré en cas de clé manquante

### 🎨 Texte & messages (`TextUtil`)
- Parsing des couleurs `&a`, `&#FF00FF`, etc. vers `Component` Kyori Adventure
- Désactivation de l’italique par défaut
- Conversion de listes de `String` en `Component`

### 🪄 Objets Minecraft (`ItemUtils`)
- **Sérialisation / désérialisation** d’`ItemStack` en base64
- Compatible avec les systèmes de persistance et la base SQL

### 📢 Logger (`PluginLogger`)
- Logging coloré et clair :
    - `PluginLogger.success("Succès!")`
    - `PluginLogger.error("Erreur critique!")`
    - `PluginLogger.info(...)` / `warning(...)`

---

## 🗂️ Structure du projet
```
plugincommons/
├── db/
│ ├── DatabaseManager.java
│ ├── DatabaseFactory.java
│ ├── DatabaseType.java
│ └── provider/
│ ├── DatabaseProvider.java
│ ├── SQLiteProvider.java
│ ├── MySQLProvider.java
│ └── PostgreSQLProvider.java
├── utils/
│ ├── ConfigUtil.java
│ ├── TextUtil.java
│ ├── ItemUtils.java
│ └── PluginLogger.java
```

## 🚀 Exemple d'utilisation

### 1. Configuration `config.yml`
```yaml
messages:
  prefix: "&7[&bPluginCommons&7] "
  welcome: "&aBienvenue !"
database:
  type: sqlite
item:
  sword:
    material: DIAMOND_SWORD
    customModelData: 1234
```

### 2. Initialisation dans onEnable()
```java
@Override
public void onEnable() {
    saveDefaultConfig();

    PluginLogger.init(this);
    ConfigUtil.init(this);
    DatabaseManager.init(getConfig(), getDataFolder());

    PluginLogger.success("Plugin chargé avec succès !");
}
```

### 3. Utilisation des fonctionnalités
```java
Component welcome = ConfigUtil.msg("messages.welcome");
player.sendMessage(welcome);
```

### 4. Création d’un objet avec `CustomModelData`
```java
ItemStack item = ConfigUtil.getMaterial("item.sword.material", true);
player.getInventory().addItem(item);
```

## 🔐 Sécurité
- Null-checks inclus (value != null)
- Fallback automatique vers Material.AIR si mauvais matériel
- Logging des erreurs de parsing ou de connexion