package me.vilsol.transformer.engine.regions;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.selection.SelectionType;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.utils.BlockUtils;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.List;

public interface TransformerRegion {

    List<Block> getEnclosedBlocks();

    default List<Block> getTransparentBlocks(){
        List<Block> transparent = getEnclosedBlocks();
        transparent.removeIf(b -> !BlockUtils.isTransparent(b.getType()));
        return transparent;
    }

    default List<Block> getNormalBlocks(){
        List<Block> transparent = getEnclosedBlocks();
        transparent.removeIf(b -> BlockUtils.isTransparent(b.getType()));
        return transparent;
    }

    Vector getRelativePosition(Block block);

    void newInstance(TransformerHandler handler, ParamCallback<TransformerRegion> callback);

    SelectionType getRegionSelection();

}
