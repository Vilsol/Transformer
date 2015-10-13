package me.vilsol.transformer.engine.regions;

import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.selection.OnePointSelection;
import me.vilsol.transformer.engine.selection.SelectionType;
import me.vilsol.transformer.engine.tasks.SearchNeighborsTask;
import me.vilsol.transformer.handlers.PlayerHandler;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.managers.TaskManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NeighborRegion implements TransformerRegion {

    private ArrayList<Block> enclosedBlocks;
    private Location position;
    private TransformerHandler handler;

    public NeighborRegion(Location position, TransformerHandler handler, ParamCallback<TransformerRegion> callback) {
        this.position = position;
        this.handler = handler;

        recalculateBlocks(callback);
    }

    public Location getPosition() {
        return position;
    }

    public void setPosition(Location position) {
        this.position = position;

        recalculateBlocks(null);
    }

    private void recalculateBlocks(ParamCallback<TransformerRegion> callback){
        if(position == null){
            return;
        }

        if(enclosedBlocks == null) {
            enclosedBlocks = new ArrayList<>();
        }

        enclosedBlocks.clear();

        if(handler instanceof PlayerHandler) {
            TaskManager.getInstance().addTask(new SearchNeighborsTask(handler, handler, enclosedBlocks, this, callback));
        }else{
            TaskManager.getInstance().addTask(new SearchNeighborsTask(handler, enclosedBlocks, this, callback));
        }
    }

    @Override
    public List<Block> getEnclosedBlocks() {
        return (List<Block>) enclosedBlocks.clone();
    }

    @Override
    public Vector getRelativePosition(Block block) {
        return block.getLocation().subtract(position).toVector();
    }

    @Override
    public void newInstance(TransformerHandler handler, ParamCallback<TransformerRegion> callback) {
        new NeighborRegion(((OnePointSelection) handler.getSelection()).getPoint(), handler, callback);
    }

    @Override
    public SelectionType getRegionSelection() {
        return SelectionType.ONE_POINT;
    }

    @Override
    public ItemStack getIdentifierItem() {
        return new Builder(Material.LEASH).name(ChatColor.DARK_PURPLE + "Neighbor Region").lore(Collections.singletonList(ChatColor.GRAY + "Search blocks around selected block recursively")).item();
    }

}
