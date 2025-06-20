package com.yixu.Command;

import com.yixu.Command.MainCommand.MainCommand;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            MessageUtil.sendMessage(sender, "commands.command-usage");
            return true;
        }

        String sub = args[0].toLowerCase();

        switch (sub) {
            case "reload":
                return new MainCommand().onCommand(sender, command, label, args);
            default:
                MessageUtil.sendMessage(sender, "commands.command-usage");
                return true;
        }
    }
}
