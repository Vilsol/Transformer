package me.vilsol.transformer.managers;

import me.vilsol.transformer.engine.regions.CuboidRegion;
import me.vilsol.transformer.handlers.TransformerHandler;

public class PositionManager {

    private static PositionManager ourInstance = new PositionManager();

    public static PositionManager getInstance() {
        return ourInstance;
    }

    private PositionManager() {
    }

    public CuboidRegion getRegion(Object owner){
        TransformerHandler handler = HandlerManager.getInstance().getHandler(owner);

        if(handler.getPositionOne() == null || handler.getPositionTwo() == null){
            return null;
        }

        return new CuboidRegion(handler.getPositionOne(), handler.getPositionTwo());
    }

}
