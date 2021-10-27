package fr.i360matt.pluginjs;

import fr.i360matt.pluginjs.internal.CommandCatcher;
import fr.i360matt.pluginjs.internal.DynamicEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    public static Main plugin;

    public Main () {
        super();
        plugin = this;

        PluginJS.dynamicEvent = new DynamicEvent();
        PluginJS.commandCatcher = new CommandCatcher();
        PluginJS.loader = new Loader();
    }

    public static final Map<String, String> languages = new HashMap<String, String>() {{
        put("js", "js");
        put("py", "py");
    }};

    @Override
    public void onEnable () {



        Bukkit.getPluginManager().registerEvents(PluginJS.commandCatcher, this);

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        try {
            PluginJS.loader.load(getDataFolder());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
