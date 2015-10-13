package me.vilsol.transformer.engine.regions;

import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.selection.SelectionType;
import me.vilsol.transformer.engine.selection.TwoPointSelection;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.utils.LocationUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CuboidRegion implements TransformerRegion {

    private ArrayList<Block> enclosedBlocks;

    private Location positionOne;
    private Location positionTwo;

    public CuboidRegion(Location positionOne, Location positionTwo) {
        this.positionOne = LocationUtils.getLowestPosition(positionOne, positionTwo);
        this.positionTwo = LocationUtils.getHighestPosition(positionOne, positionTwo);

        recalculateBlocks();
    }

    public Location getPositionOne() {
        return positionOne;
    }

    public void setPositionOne(Location positionOne) {
        this.positionOne = LocationUtils.getLowestPosition(positionOne, positionTwo);
        this.positionTwo = LocationUtils.getHighestPosition(positionOne, positionTwo);

        recalculateBlocks();
    }

    public Location getPositionTwo() {
        return positionTwo;
    }

    public void setPositionTwo(Location positionTwo) {
        this.positionOne = LocationUtils.getLowestPosition(positionOne, positionTwo);
        this.positionTwo = LocationUtils.getHighestPosition(positionOne, positionTwo);

        recalculateBlocks();
    }

    private void recalculateBlocks(){
        if(positionOne == null || positionTwo == null){
            return;
        }

        if(enclosedBlocks == null) {
            enclosedBlocks = new ArrayList<>();
        }

        enclosedBlocks.clear();

        for (int x = positionOne.getBlockX(); x <= positionTwo.getBlockX(); x++) {
            for (int y = positionOne.getBlockY(); y <= positionTwo.getBlockY(); y++) {
                for (int z = positionOne.getBlockZ(); z <= positionTwo.getBlockZ(); z++) {
                    enclosedBlocks.add(positionOne.getWorld().getBlockAt(x, y, z));
                }
            }
        }
    }

    public List<Block> getEnclosedBlocks() {
        return (List<Block>) enclosedBlocks.clone();
    }

    @Override
    public Vector getRelativePosition(Block block) {
        return block.getLocation().subtract(positionOne).toVector();
    }

    @Override
    public void newInstance(TransformerHandler handler, ParamCallback<TransformerRegion> callback) {
        callback.callback(new CuboidRegion(((TwoPointSelection) handler.getSelection()).getPositionOne(), ((TwoPointSelection) handler.getSelection()).getPositionTwo()));
    }

    @Override
    public SelectionType getRegionSelection() {
        return SelectionType.TWO_POINTS;
    }

    @Override
    public ItemStack getIdentifierItem() {
        return new Builder(Material.GLASS).name(ChatColor.DARK_PURPLE + "Cuboid Region").lore(Collections.singletonList(ChatColor.GRAY + "Select blocks within 2 points")).item();
    }

}
