package com.humbrain.plugincommons.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ItemUtils {

    private static final String TRANSLATION_URL = "https://raw.githubusercontent.com/InventivetalentDev/minecraft-assets/refs/heads/1.21.4/assets/minecraft/lang/fr_fr.json";
    private static final Map<String, String> TRANSLATION_MAP = new ConcurrentHashMap<>();
    private static boolean loaded = false;

    public static String translate(String id) {
        try {
            if (!loaded) {
                loadTranslationMap();
            }
            String blockKey = "block.minecraft." + id.toLowerCase();
            String itemKey = "item.minecraft." + id.toLowerCase();
            String entityKey = "entity.minecraft." + id.toLowerCase();

            // fallback
            if (TRANSLATION_MAP.containsKey(blockKey)) {
                return TRANSLATION_MAP.get(blockKey);
            } else if (TRANSLATION_MAP.containsKey(itemKey)) {
                return TRANSLATION_MAP.get(itemKey);
            } else return TRANSLATION_MAP.getOrDefault(entityKey, id);
        } catch (Exception e) {
            e.printStackTrace();
            return id;
        }
    }

    private static void loadTranslationMap() throws Exception {
        URL url = new URL(TRANSLATION_URL);
        try (InputStreamReader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Map<String, String> map = gson.fromJson(reader, new TypeToken<Map<String, String>>() {
            }.getType());
            TRANSLATION_MAP.putAll(map);
            loaded = true;
        }
    }

    @SuppressWarnings("deprecation")
    public String serializeItem(ItemStack item) {
        if (item == null || item.getType().isAir()) return null;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        BukkitObjectOutputStream dataOutput = null;
        try {
            dataOutput = new BukkitObjectOutputStream(output);
            dataOutput.writeObject(item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dataOutput != null) {
                try {
                    dataOutput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return Base64.getEncoder().encodeToString(output.toByteArray());
    }

    @SuppressWarnings("deprecation")
    public ItemStack deserializeItem(String data) {
        ByteArrayInputStream input = new ByteArrayInputStream(Base64.getDecoder().decode(data));
        BukkitObjectInputStream dataInput = null;
        ItemStack item = null;
        try {
            dataInput = new BukkitObjectInputStream(input);
            item = (ItemStack) dataInput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (dataInput != null) {
                try {
                    dataInput.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return item;
    }
}