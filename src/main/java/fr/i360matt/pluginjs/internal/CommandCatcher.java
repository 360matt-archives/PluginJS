package fr.i360matt.pluginjs.internal;

import fr.i360matt.pluginjs.PluginJS;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.graalvm.polyglot.Value;


public class CommandCatcher implements Listener {


    public boolean incoming (final CommandSender sender, String command) {
        final String[] brut = command.split(" ");

        final String name = brut[0];
        final String[] args = new String[brut.length-1];
        System.arraycopy(brut, 1, args, 0, brut.length - 1);

        Value res = PluginJS.loader.execute("onCommand", name, sender, args);
        return (res != null) && res.asBoolean();
    }

    @EventHandler (ignoreCancelled = true)
    public void onCommand (final ServerCommandEvent event) {
        boolean res = incoming(event.getSender(), event.getCommand());
        event.setCancelled(res);
    }

    @EventHandler (ignoreCancelled = true)
    public void onCommand (final PlayerCommandPreprocessEvent event) {
        boolean res = incoming(event.getPlayer(), event.getMessage());
        event.setCancelled(res);
    }



}
