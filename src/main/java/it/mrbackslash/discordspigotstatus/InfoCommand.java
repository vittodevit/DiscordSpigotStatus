package it.mrbackslash.discordspigotstatus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class InfoCommand implements CommandExecutor {
    private String vers;
    InfoCommand(String version){
        vers = version;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        sender.sendMessage("§9§l[i] §r§aDiscordSpigotStatus v" + vers + " by mrBackSlash-it");
        return true;
    }

}