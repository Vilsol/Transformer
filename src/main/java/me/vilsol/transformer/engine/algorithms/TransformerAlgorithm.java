package me.vilsol.transformer.engine.algorithms;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.config.Configuration;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public abstract class TransformerAlgorithm {

    private TransformerHandler owner;

    public TransformerAlgorithm(TransformerHandler owner) {
        this.owner = owner;
    }

    public abstract List<VirtualBlock> applyToBlock(Block b, Vector relativePosition);

    public abstract void newInstance(TransformerHandler handler, ParamCallback<TransformerAlgorithm> callback);

    public abstract ItemStack getIdentifierItem();

    public TransformerHandler getOwner() {
        return owner;
    }

    public boolean singleX() {
        return false;
    }

    public boolean singleY() {
        return false;
    }

    public boolean singleZ() {
        return false;
    }

    public List<Configuration> getConfigurations() {
        return null;
    }

}
