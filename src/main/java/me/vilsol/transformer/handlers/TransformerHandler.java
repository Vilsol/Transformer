package me.vilsol.transformer.handlers;

import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.algorithms.ActionAlgorithm;
import me.vilsol.transformer.engine.algorithms.ReplaceAlgorithm;
import me.vilsol.transformer.engine.tasks.BuildTask;
import me.vilsol.transformer.engine.regions.RegionType;
import me.vilsol.transformer.engine.selection.Selection;
import org.bukkit.Material;

public abstract class TransformerHandler<T> {

    private Selection selection;

    private ActionAlgorithm algorithm = new ReplaceAlgorithm(new VirtualBlock(Material.STONE));
    private RegionType regionType = RegionType.CUBOID;
    private BuildTask lastTask;

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

    public ActionAlgorithm getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(ActionAlgorithm algorithm) {
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

}
