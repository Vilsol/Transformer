package me.vilsol.transformer.handlers;

import me.vilsol.transformer.engine.builder.BuildTask;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.algorithms.ActionAlgorithm;
import me.vilsol.transformer.engine.algorithms.ReplaceAlgorithm;
import me.vilsol.transformer.engine.regions.RegionType;
import org.bukkit.Location;
import org.bukkit.Material;

public abstract class TransformerHandler<T> {

    private Location positionOne;
    private Location positionTwo;

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

    public Location getPositionOne() {
        return positionOne;
    }

    public void setPositionOne(Location positionOne) {
        this.positionOne = positionOne;
    }

    public Location getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(Location positionTwo) {
        this.positionTwo = positionTwo;
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
