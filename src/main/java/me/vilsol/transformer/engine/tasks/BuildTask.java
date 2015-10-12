package me.vilsol.transformer.engine.tasks;

import me.vilsol.transformer.engine.algorithms.ActionAlgorithm;
import me.vilsol.transformer.engine.regions.TransformerRegion;
import me.vilsol.transformer.handlers.PlayerHandler;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.Iterator;
import java.util.List;

public class BuildTask extends Task {

    private TransformerRegion region;
    private ActionAlgorithm algorithm;

    private Iterator<Block> normalIterator;
    private Iterator<Block> transparentIterator;

    private double totalBlocks;
    private double placedBlocks;

    private boolean normal;

    public BuildTask(TransformerRegion region, ActionAlgorithm algorithm) {
        this(region, algorithm, null);
    }

    public BuildTask(TransformerRegion region, ActionAlgorithm algorithm, PlayerHandler watcher) {
        super(watcher);
        this.region = region;
        this.algorithm = algorithm;

        List<Block> normalBlocks = region.getNormalBlocks();
        List<Block> transparentBlocks = region.getTransparentBlocks();

        this.totalBlocks = normalBlocks.size() + transparentBlocks.size();

        this.normalIterator = normalBlocks.iterator();
        this.transparentIterator = transparentBlocks.iterator();
    }

    public void tick() {
        Block block = null;

        try {
            if (!normal) {
                block = transparentIterator.next();
            } else {
                block = normalIterator.next();
            }
        } catch (Exception ignored) {
        }

        if (block == null) {
            if (!normal) {
                normal = true;
                return;
            } else {
                finished = true;
                return;
            }
        }

        Vector relativePosition = region.getRelativePosition(block);
        algorithm.applyToBlock(block, relativePosition);
        placedBlocks++;
    }

    public double getProgress() {
        return (100d / totalBlocks) * placedBlocks;
    }

    @Override
    public int getParsedBlocks() {
        return (int) placedBlocks;
    }

    @Override
    public int getTotalBlocks() {
        return (int) totalBlocks;
    }

}
