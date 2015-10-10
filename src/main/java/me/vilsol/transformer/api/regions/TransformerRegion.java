package me.vilsol.transformer.api.regions;

import java.util.List;

import me.vilsol.transformer.api.TBlock;
import me.vilsol.transformer.api.TLocation;

public interface TransformerRegion {

    long getBlockCount();

    List<TBlock> getEnclosedBlocks();

    boolean isInside(TLocation location);

    default boolean isInside(TBlock block) {
        return block == null ? false : isInside(block.getLocation());
    }

}
