package it.mrbackslash.discordspigotstatus;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class DiscordSpigotStatus extends JavaPlugin {
    @Override
    public void onEnable(){
        getLogger().info("Loading configuration...");
    }

    @Override
    public void onDisable(){
        getLogger().info("Plugin has been unloaded.");
    }
}
