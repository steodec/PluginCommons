package com.humbrain.plugincommons.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextUtil {

    private static final LegacyComponentSerializer legacy = LegacyComponentSerializer.legacyAmpersand();
    private static final Pattern hexPattern = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public static Component parse(String text) {
        text = translateHexColorCodes(text);
        if (text == null) return Component.empty(); // ✅ sécurité NPE
        Component component = legacy.deserialize(text);
        return component.decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

    public static String parseString(String text) {
        return translateHexColorCodes(text).replace('&', '§');
    }

    public static List<String> parseListString(List<String> text) {
        return text.stream()
                .map(TextUtil::parseString)
                .collect(Collectors.toList());
    }

    public static List<Component> parseList(List<String> lines) {
        return lines.stream()
                .map(TextUtil::parse)
                .collect(Collectors.toList());
    }

    public static String translateHexColorCodes(String text) {
        if (text == null) return null; // ✅ sécurité NPE

        Matcher matcher = hexPattern.matcher(text);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            String hex = matcher.group(1);
            StringBuilder replacement = new StringBuilder("&x");
            for (char c : hex.toCharArray()) {
                replacement.append('&').append(c);
            }
            matcher.appendReplacement(buffer, Matcher.quoteReplacement(replacement.toString()));
        }
        matcher.appendTail(buffer);

        return buffer.toString();
    }

}
