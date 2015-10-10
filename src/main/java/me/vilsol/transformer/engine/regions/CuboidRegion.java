package me.vilsol.transformer.engine.regions;

import me.vilsol.transformer.utils.LocationUtils;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CuboidRegion implements TransformerRegion {

    private Location positionOne;
    private Location positionTwo;

    public CuboidRegion(Location positionOne, Location positionTwo) {
        this.positionOne = LocationUtils.getLowestPosition(positionOne, positionTwo);
        this.positionTwo = LocationUtils.getHighestPosition(positionOne, positionTwo);
    }

    public Location getPositionOne() {
        return positionOne;
    }

    public void setPositionOne(Location positionOne) {
        this.positionOne = LocationUtils.getLowestPosition(positionOne, positionTwo);
        this.positionTwo = LocationUtils.getHighestPosition(positionOne, positionTwo);
    }

    public Location getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(Location positionTwo) {
        this.positionOne = LocationUtils.getLowestPosition(positionOne, positionTwo);
        this.positionTwo = LocationUtils.getHighestPosition(positionOne, positionTwo);
    }

    public List<Block> getEnclosedBlocks() {
        List<Block> blocks = new ArrayList<>();
        for (int x = positionOne.getBlockX(); x <= positionTwo.getBlockX(); x++) {
            for (int y = positionOne.getBlockY(); y <= positionTwo.getBlockY(); y++) {
                for (int z = positionOne.getBlockZ(); z <= positionTwo.getBlockZ(); z++) {
                    blocks.add(positionOne.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

}
