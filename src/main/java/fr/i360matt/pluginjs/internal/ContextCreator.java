package fr.i360matt.pluginjs.internal;

import fr.i360matt.pluginjs.PluginJS;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;

public class ContextCreator {

    private static final Context context;
    static {

        Context.Builder builder = Context.newBuilder(PluginJS.languages.values().toArray(new String[0]));
        builder.allowHostAccess(HostAccess.ALL);
        builder.allowNativeAccess(true);
        builder.allowAllAccess(true);

        context = builder.build();


    }


    public static Context getContext () {
        return context;
    }

}

