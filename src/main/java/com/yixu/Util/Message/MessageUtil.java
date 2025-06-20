package com.yixu.Util.Message;

import com.yixu.Config.RecipeConfig.BaseConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.Map;

public class MessageUtil {
    private static BaseConfig messagesConfig;

    public static void init(Plugin plugin) {
        messagesConfig = new BaseConfig(plugin, "messages.yml");
    }

    public static String getRawMessage(String path) {
        return messagesConfig.getConfig().getString(path, "§c消息路径不存在：" + path);
    }

    public static String getMessage(String path, Map<String, String> placeholders) {
        String message = getRawMessage(path);
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            message = message.replace("%" + entry.getKey() + "%", entry.getValue());
        }
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void sendMessage(CommandSender sender, String path, Map<String, String> placeholders) {
        sender.sendMessage(getMessage(path, placeholders));
    }

    public static void sendMessage(CommandSender sender, String path) {
        sendMessage(sender, path, Map.of("prefix", getRawMessage("prefix")));
    }
}
