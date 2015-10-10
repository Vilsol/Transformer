package me.vilsol.transformer.engine.algorithms;

import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public interface ActionAlgorithm {

    void applyToBlock(Block b, Vector relativePosition);

}
