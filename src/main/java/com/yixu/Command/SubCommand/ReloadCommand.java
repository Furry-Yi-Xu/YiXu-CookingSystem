package com.yixu.Command.SubCommand;

import com.yixu.Config.ConfigManager;
import com.yixu.Util.Message.MessageUtil;
import com.yixu.Util.Permission.PermissionCheck;
import com.yixu.Util.Permission.PermissionNodes;
import org.bukkit.command.CommandSender;

public class ReloadCommand {

    private final CommandSender sender;

    public ReloadCommand(CommandSender sender) {
        this.sender = sender;
    }

    public void runCommand() {
        if (PermissionCheck.checkPermission(sender, PermissionNodes.RELOAD)) {
            ConfigManager.reloadAll();
            MessageUtil.sendMessage(sender, "commands.reload.reload-succeed");
        }
    }
}
