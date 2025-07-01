package com.yixu.Util.Permission;

import com.yixu.Util.Message.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionCheck {

    public static boolean checkIsPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            MessageUtil.sendMessage(sender, "commands.only-player");
            return false;
        }
        return true;
    }

    public static boolean checkPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            MessageUtil.sendMessage(sender, "commands.no-permission");
            return false;
        }
        return true;
    }

    public static boolean checkPlayerWithPermission(CommandSender sender, String permission) {
        return checkIsPlayer(sender) && checkPermission(sender, permission);
    }
}
