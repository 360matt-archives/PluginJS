package fr.i360matt.pluginjs.internal;

import fr.i360matt.pluginjs.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public class DynamicEvent {

    public Listener createListener () {
        return new Listener() {};

    }

    public void registerListener (final Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, Main.plugin);
    }

}
