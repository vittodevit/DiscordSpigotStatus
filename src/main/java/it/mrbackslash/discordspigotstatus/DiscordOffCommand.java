package it.mrbackslash.discordspigotstatus;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DiscordOffCommand implements CommandExecutor {

    private DiscordSender ds = null;

    DiscordOffCommand(DiscordSender o){
        ds = o;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args.length != 0){
            return false;
        }
        if(ds.sendOff()){
            sender.sendMessage("§a§l[i] §r§aMessage sent successfully!");
        }else{
            sender.sendMessage("§c§l[i] §r§cUnable to send your message, check your configuration...");
        }
        return true;
    }

}
