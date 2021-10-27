package fr.i360matt.pluginjs;

import fr.i360matt.pluginjs.internal.ContextCreator;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Loader implements Closeable {

    private static final Set<Loader> loaders = new HashSet<>();




    private final Map<File, Context> loadedFiles = new HashMap<>();
    private boolean closed = false;

    public Loader () {
        loaders.add(this);
    }

    public Loader load (final File file) throws IOException {
        if (!closed) {
            if (file.exists()) {
                if (file.isFile()) {
                    String lang = null;

                    for (final Map.Entry<String, String> entry : PluginJS.languages.entrySet()) {
                        if (file.getName().endsWith(entry.getKey())) {
                            lang = entry.getValue();
                            break;
                        }
                    }

                    if (lang != null) {
                        Context context = ContextCreator.getContext();
                        loadedFiles.put(file, context);

                        context.eval(Source.newBuilder(lang, file).build());

                        // TODO in multi-thread

                    }
                } else {
                    final File[] children = file.listFiles();
                    if (children != null) {
                        for (final File child : children) {
                            this.load(child);
                        }
                    }

                }
            }
        }
        return this;
    }

    public Value execute (final String function, final Object... args) {
        if (!closed) {
            Context context = ContextCreator.getContext();
            Value value = context.getBindings("js").getMember(function);
            if (value != null && !value.isNull()) {
                if (value.canExecute()) {
                    return value.execute(args);
                }
            }
        }
        return null;
    }


    public void close () {
        closed = true;
        loaders.remove(this);
    }
}
