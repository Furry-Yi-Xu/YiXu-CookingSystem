package com.yixu.Util.Message;

import com.yixu.Config.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;

import java.util.Map;

public class MessageUtil {

    private static final LegacyComponentSerializer serializer = LegacyComponentSerializer.legacyAmpersand();

    public static String getMessageFromFile(String path) {
        return ConfigManager.getMessagesConfig().getConfig().getString(path, "§c消息路径不存在：" + path);
    }

    public static Component getPrefixFromFile() {
        String rawPrefix = ConfigManager.getMessagesConfig().getConfig().getString("prefix", "§c插件前缀不存在");
        return serializer.deserialize(rawPrefix);
    }

    public static Component formatMessage(String path, Map<String, String> variables) {
        String message = getMessageFromFile(path);

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String placeholder = "%" + entry.getKey() + "%";
            String value = entry.getValue() != null ? entry.getValue() : "";
            message = message.replace(placeholder, value);
        }

        return serializer.deserialize(message);
    }

    public static Component formatMessage(String path) {
        String message = getMessageFromFile(path);
        return serializer.deserialize(message);
    }

    public static Component formatMessage(String path, Map<String, String> variables, TranslatableComponent translatableComponent) {
        String message = getMessageFromFile(path);

        for (Map.Entry<String, String> entry : variables.entrySet()) {
            String placeholder = "%" + entry.getKey() + "%";
            String value = entry.getValue() != null ? entry.getValue() : "";
            message = message.replace(placeholder, value);
        }

        return serializer.deserialize(message).append(translatableComponent);
    }

    public static void sendMessage(CommandSender sender, String path) {
        Component formattedPrefix = getPrefixFromFile();
        Component formattedMessage = formatMessage(path);

        sender.sendMessage(formattedPrefix.append(formattedMessage));
    }

    public static void sendMessage(CommandSender sender, String path, Map<String, String> variables) {
        Component formattedPrefix = getPrefixFromFile();
        Component formattedMessage = formatMessage(path, variables);

        sender.sendMessage(formattedPrefix.append(formattedMessage));
    }

    public static void sendMessage(CommandSender sender, String path, Map<String, String> variables, TranslatableComponent translatableComponent) {
        Component formattedPrefix = getPrefixFromFile();
        Component formattedMessage = formatMessage(path, variables, translatableComponent);

        sender.sendMessage(formattedPrefix.append(formattedMessage));
    }

}
