package me.vilsol.transformer;

import me.vilsol.transformer.api.TProvider;
import me.vilsol.transformer.api.TWorld;

/**
 * @author Nick Robson
 */
public abstract class TransformerAPI {

    private static TransformerAPI transformer;

    public static TransformerAPI get() {
        return transformer;
    }

    public static void set(TransformerAPI trans) {
        if (transformer == null && trans != null)
            transformer = trans;
    }

    public abstract String getName();

    public abstract TWorld[] getWorlds();

    public abstract <T> TProvider<T> getProvider(T owner);

}
