package com.yixu.Util.Permission;

import com.yixu.Util.Message.MessageUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermissionCheck {

    /**
     * 检查命令是否由玩家执行，否则发送提示。
     * @param sender 发送者
     * @return true 如果是玩家；false 否则
     */
    public static boolean checkIsPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            MessageUtil.sendMessage(sender, "commands.only-player");
            return false;
        }
        return true;
    }

    /**
     * 检查执行者是否有权限，否则发送提示。
     * @param sender 执行者
     * @param permission 权限节点
     * @return true 如果有权限；false 否则
     */
    public static boolean checkPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            MessageUtil.sendMessage(sender, "commands.no-permission");
            return false;
        }
        return true;
    }

    /**
     * 综合方法：先检测是否玩家，再检测权限。
     * @param sender 发送者
     * @param permission 权限节点
     * @return true 如果是玩家且有权限；false 否则
     */
    public static boolean checkPlayerWithPermission(CommandSender sender, String permission) {
        return checkIsPlayer(sender) && checkPermission(sender, permission);
    }
}
