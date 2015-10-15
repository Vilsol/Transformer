package me.vilsol.transformer.engine.algorithms;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public interface TransformerAlgorithm {

    List<VirtualBlock> applyToBlock(Block b, Vector relativePosition);

    void newInstance(TransformerHandler handler, ParamCallback<TransformerAlgorithm> callback);

    ItemStack getIdentifierItem();

    default boolean singleX() {
        return false;
    }

    default boolean singleY() {
        return false;
    }

    default boolean singleZ() {
        return false;
    }

}
