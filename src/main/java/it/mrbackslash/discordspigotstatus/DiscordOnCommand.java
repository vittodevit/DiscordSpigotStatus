package it.mrbackslash.discordspigotstatus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordOnCommand implements CommandExecutor {

    private DiscordSender ds = null;

    DiscordOnCommand(DiscordSender o){
        ds = o;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        sender.sendMessage("§9§l[i] §r§aPlaceholder!");
        return true;
    }

}
