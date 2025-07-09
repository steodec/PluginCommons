package com.humbrain.plugincommons.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;

public class ItemUtils {
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