package it.mrbackslash.discordspigotstatus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordOffCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        sender.sendMessage("§9§l[i] §r§aPlaceholder!");
        return true;
    }

}
