package me.vilsol.transformer.engine.algorithms;

import me.vilsol.transformer.engine.VirtualBlock;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class ReplaceAlgorithm implements ActionAlgorithm {

    private VirtualBlock replacement;

    public ReplaceAlgorithm(VirtualBlock replacement) {
        this.replacement = replacement;
    }

    @Override
    public void applyToBlock(Block b, Vector relativePosition) {
        replacement.buildBlock(b.getLocation());
    }

    public VirtualBlock getReplacement() {
        return replacement;
    }

    public void setReplacement(VirtualBlock replacement) {
        this.replacement = replacement;
    }

}
