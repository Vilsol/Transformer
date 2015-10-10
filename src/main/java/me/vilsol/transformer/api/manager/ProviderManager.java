package me.vilsol.transformer.api.manager;

import java.util.HashMap;
import java.util.Map;

import me.vilsol.transformer.TransformerAPI;
import me.vilsol.transformer.api.TProvider;

/**
 * @author Nick Robson
 */
public class ProviderManager {

    private static final Map<Object, TProvider<?>> handlers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public static <T> TProvider<T> getProvider(T owner) {
        if (!handlers.containsKey(owner)) {
            TProvider<?> handler = TransformerAPI.get().getProvider(owner);
            if (handler == null)
                return null;
            handlers.put(owner, handler);
        }
        return (TProvider<T>) handlers.get(owner);
    }

}
