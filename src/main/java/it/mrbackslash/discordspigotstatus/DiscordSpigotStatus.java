package it.mrbackslash.discordspigotstatus;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class DiscordSpigotStatus extends JavaPlugin {
    private boolean loadcomplete, autosend = false;
    DiscordSender ds = null;
    @Override
    public void onEnable(){
        getLogger().info("Loading configuration...");
        //ensuring config file is present
        this.saveDefaultConfig();
        FileConfiguration conf = this.getConfig();
        //checking if config is valid
        if(conf.getString("discord_webhook") != "none") {
            ds = new DiscordSender(
                    conf.getString("server_ip"),
                    conf.getString("discord_webhook")
            );
            loadcomplete = true;
            autosend = conf.getBoolean("autosend");
            if (autosend) {
                ds.sendOn();
            }
            //register commands
            //TODO: Register actual commands
            this.getCommand("dss-on").setExecutor(new InvalidConfigResponder());
            this.getCommand("dss-off").setExecutor(new InvalidConfigResponder());
        }else{
            //register invalid config handler
            this.getCommand("dss-on").setExecutor(new InvalidConfigResponder());
            this.getCommand("dss-off").setExecutor(new InvalidConfigResponder());
        }
        this.getCommand("dss-info").setExecutor(new InfoCommand(
                this.getDescription().getVersion()
        ));
    }

    @Override
    public void onDisable(){
        if(loadcomplete && autosend){
            ds.sendOff();
            getLogger().info("Shutdown message sent.");
        }
        getLogger().info("Plugin has been unloaded.");
    }
}
