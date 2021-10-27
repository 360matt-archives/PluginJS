package fr.i360matt.pluginjs;

import fr.i360matt.pluginjs.internal.CommandCatcher;
import fr.i360matt.pluginjs.internal.DynamicEvent;

import java.util.HashMap;
import java.util.Map;

public class PluginJS {

    public static DynamicEvent dynamicEvent;
    public static CommandCatcher commandCatcher;
    public static Loader loader;

    public static PluginJS plugin;

    public static final Map<String, String> languages = new HashMap<String, String>() {{
        put("js", "js");
        put("py", "py");
    }};

}
