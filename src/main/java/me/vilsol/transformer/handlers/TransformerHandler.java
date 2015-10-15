package me.vilsol.transformer.handlers;

import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.algorithms.TransformerAlgorithm;
import me.vilsol.transformer.engine.algorithms.ReplaceAlgorithm;
import me.vilsol.transformer.engine.regions.RegionType;
import me.vilsol.transformer.engine.selection.Selection;
import me.vilsol.transformer.engine.tasks.BuildTask;
import org.bukkit.Material;

import java.util.List;
import java.util.Stack;

public abstract class TransformerHandler<T> {

    private Selection selection;

    private TransformerAlgorithm algorithm = new ReplaceAlgorithm(new VirtualBlock(Material.STONE));
    private RegionType regionType = RegionType.CUBOID;
    private boolean percentageProgress = true;
    private BuildTask lastTask;

    private Stack<List<VirtualBlock>> undoHistory = new Stack<>();

    private T owner;

    public TransformerHandler(T owner) {
        this.owner = owner;
    }

    public T getOwner() {
        return owner;
    }

    public Selection getSelection() {
        return selection;
    }

    public void setSelection(Selection selection) {
        this.selection = selection;
    }

    public TransformerAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(TransformerAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public RegionType getRegionType() {
        return regionType;
    }

    public void setRegionType(RegionType regionType) {
        this.regionType = regionType;
    }

    public BuildTask getLastTask() {
        return lastTask;
    }

    public void setLastTask(BuildTask lastTask) {
        this.lastTask = lastTask;
    }

    public Stack<List<VirtualBlock>> getUndoHistory() {
        return undoHistory;
    }

    public boolean isPercentageProgress() {
        return percentageProgress;
    }

    public void setPercentageProgress(boolean percentageProgress) {
        this.percentageProgress = percentageProgress;
    }

}
