package me.vilsol.transformer.engine.regions;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.selection.SelectionType;
import me.vilsol.transformer.handlers.TransformerHandler;

public enum RegionType {

    CUBOID(new CuboidRegion(null, null)),
    NEIGHBOR(new NeighborRegion(null, null, null));

    private TransformerRegion region;

    RegionType(TransformerRegion region) {
        this.region = region;
    }

    public void newInstance(TransformerHandler handler, ParamCallback<TransformerRegion> callback){
        region.newInstance(handler, callback);
    }

    public SelectionType getSelection(){
        return region.getRegionSelection();
    }

    public TransformerRegion getExample() {
        return region;
    }
}
