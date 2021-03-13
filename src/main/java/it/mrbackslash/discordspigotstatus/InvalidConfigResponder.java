package it.mrbackslash.discordspigotstatus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InvalidConfigResponder implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        sender.sendMessage("§4§l[!] §r§4Cannot load configuration file!");
        return true;
    }

}
