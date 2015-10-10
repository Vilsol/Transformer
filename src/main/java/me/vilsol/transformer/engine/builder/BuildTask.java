package me.vilsol.transformer.engine.builder;

import me.vilsol.transformer.engine.algorithms.ActionAlgorithm;
import me.vilsol.transformer.engine.regions.TransformerRegion;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Iterator;

public class BuildTask {

    private TransformerRegion region;
    private ActionAlgorithm algorithm;

    private Iterator<Block> normalIterator;
    private Iterator<Block> transparentIterator;

    private boolean transparent;
    private boolean finished;

    public BuildTask(TransformerRegion region, ActionAlgorithm algorithm) {
        this.region = region;
        this.algorithm = algorithm;

        this.normalIterator = region.getNormalBlocks().iterator();
        this.transparentIterator = region.getTransparentBlocks().iterator();
    }

    public boolean isFinished() {
        return finished;
    }

    public void buildNext() {
        Block block = null;

        try {
            if (!transparent) {
                block = normalIterator.next();
            } else {
                block = transparentIterator.next();
            }
        } catch (Exception ignored) {
        }

        if(block == null){
            if(!transparent){
                transparent = true;
                return;
            }else {
                finished = true;
                return;
            }
        }

        Vector relativePosition = region.getRelativePosition(block);
        algorithm.applyToBlock(block, relativePosition);
    }

}
