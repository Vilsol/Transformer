package me.vilsol.transformer.api.manager;

import me.vilsol.transformer.api.TProvider;
import me.vilsol.transformer.api.regions.CuboidRegion;
import me.vilsol.transformer.api.regions.TransformerRegion;

public class PositionManager {

    public static TransformerRegion getRegion(Object owner) {
        TProvider<?> handler = ProviderManager.getProvider(owner);
        if (handler == null || handler.getA() == null || handler.getB() == null)
            return null;
        return new CuboidRegion(handler.getA(), handler.getB());
    }

}
