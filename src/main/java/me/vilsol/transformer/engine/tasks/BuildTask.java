package me.vilsol.transformer.engine.tasks;

import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.algorithms.TransformerAlgorithm;
import me.vilsol.transformer.engine.regions.TransformerRegion;
import me.vilsol.transformer.handlers.PlayerHandler;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.utils.BlockUtils;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BuildTask extends Task {

    private TransformerRegion region;
    private TransformerAlgorithm algorithm;

    private Iterator<Block> normalIterator;
    private Iterator<Block> transparentIterator;

    private double totalBlocks;
    private double parsedBlocks;

    private List<VirtualBlock> history = new ArrayList<>();

    private boolean normal;

    public BuildTask(TransformerHandler owner, TransformerRegion region, TransformerAlgorithm algorithm) {
        this(owner, region, algorithm, null);
    }

    public BuildTask(TransformerHandler owner, TransformerRegion region, TransformerAlgorithm algorithm, PlayerHandler watcher) {
        super(owner, watcher);
        this.region = region;
        this.algorithm = algorithm;

        List<Block> normalBlocks = region.getNormalBlocks();
        List<Block> transparentBlocks = region.getTransparentBlocks();

        normalBlocks = BlockUtils.cleanupSingles(normalBlocks, algorithm.singleX(), algorithm.singleY(), algorithm.singleZ());
        transparentBlocks = BlockUtils.cleanupSingles(transparentBlocks, algorithm.singleX(), algorithm.singleY(), algorithm.singleZ());

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
        List<VirtualBlock> before = algorithm.applyToBlock(block, relativePosition);
        if(before != null){
            history.addAll(before);
        }
        parsedBlocks++;
    }

    public double getProgress() {
        return (100d / totalBlocks) * parsedBlocks;
    }

    @Override
    public int getParsedBlocks() {
        return (int) parsedBlocks;
    }

    @Override
    public int getTotalBlocks() {
        return (int) totalBlocks;
    }

    @Override
    public List<VirtualBlock> getUndo() {
        return history;
    }

}
