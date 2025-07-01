package com.yixu.Command.MainCommand;

import com.yixu.Command.SubCommand.ReloadCommand;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            MessageUtil.sendMessage(sender, "commands.usage");
            return true;
        }

        String subCommand = args[0].toLowerCase();

        switch (subCommand) {
            case "reload":
                new ReloadCommand(sender).runCommand();
                return true;
            default:
                MessageUtil.sendMessage(sender, "commands.unknown-subcommand");
                return true;
        }
    }
}
