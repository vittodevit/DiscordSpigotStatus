package it.mrbackslash.discordspigotstatus;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class DiscordSpigotStatus extends JavaPlugin {
    private boolean loadcomplete = false;
    @Override
    public void onEnable(){
        getLogger().info("Loading configuration...");
        //ensuring config file is present
        this.saveDefaultConfig();
        FileConfiguration conf = this.getConfig();
        DiscordSender ds = null;
        if(conf.getString("discord_webhook") != "none") {
            ds = new DiscordSender(
                    conf.getString("server_ip"),
                    conf.getString("discord_webhook")
            );
            loadcomplete = true;
            if (conf.getBoolean("autosend")) {
                ds.sendOn();
            }
            //register commands
            //TODO: Register actual commands
            this.getCommand("dss-on").setExecutor(new InvalidConfigResponder());
            this.getCommand("dss-off").setExecutor(new InvalidConfigResponder());
        }else{
            this.getCommand("dss-on").setExecutor(new InvalidConfigResponder());
            this.getCommand("dss-off").setExecutor(new InvalidConfigResponder());
        }
        this.getCommand("dss-info").setExecutor(new InfoCommand(
                this.getDescription().getVersion()
        ));
    }

    @Override
    public void onDisable(){
        //if config is ok
            //send message
        getLogger().info("Plugin has been unloaded.");
    }
}
