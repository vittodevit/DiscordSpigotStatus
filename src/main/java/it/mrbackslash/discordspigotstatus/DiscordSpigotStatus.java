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
        if(!conf.getString("discord_webhook").equals("none")) {
            ds = new DiscordSender(
                    conf.getString("server_ip"),
                    conf.getString("discord_webhook")
            );
            loadcomplete = true;
            autosend = conf.getBoolean("autosend");
            if (autosend) {
                if(!ds.sendOn()){
                    getLogger().warning("Unable to send 'online' message! Check your configuration...");
                }
            }
            //register commands
            this.getCommand("dss-on").setExecutor(new DiscordOnCommand());
            this.getCommand("dss-off").setExecutor(new DiscordOffCommand());
        }else{
            //register invalid config handler
            this.getCommand("dss-on").setExecutor(new InvalidConfigResponder());
            this.getCommand("dss-off").setExecutor(new InvalidConfigResponder());
            getLogger().warning("Invalid configuration file!");

        }
        this.getCommand("dss-info").setExecutor(new InfoCommand(
                this.getDescription().getVersion()
        ));
    }

    @Override
    public void onDisable(){
        if(loadcomplete && autosend){
            if(!ds.sendOff()){
                getLogger().warning("Unable to send 'offline' message! Check your configuration...");
            }else {
                getLogger().info("Shutdown message sent.");
            }
        }
        getLogger().info("Plugin has been unloaded.");
    }
}
