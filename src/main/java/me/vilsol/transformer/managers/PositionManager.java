package me.vilsol.transformer.managers;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.regions.TransformerRegion;
import me.vilsol.transformer.handlers.TransformerHandler;

public class PositionManager {

    private static PositionManager ourInstance = new PositionManager();

    public static PositionManager getInstance() {
        return ourInstance;
    }

    private PositionManager() {
    }

    public void getRegion(Object owner, ParamCallback<TransformerRegion> callback){
        TransformerHandler handler = HandlerManager.getInstance().getHandler(owner);
        handler.getRegionType().newInstance(handler, callback);
    }

}
