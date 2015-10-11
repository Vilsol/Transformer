package me.vilsol.transformer.engine.tasks;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.regions.NeighborRegion;
import me.vilsol.transformer.engine.regions.TransformerRegion;
import me.vilsol.transformer.handlers.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class SearchNeighborsTask extends Task {

    private static final int SEARCH_LIMIT = 100000;

    private ParamCallback<TransformerRegion> callback;
    private NeighborRegion region;
    private Material toReplace;
    private List<Block> enclosedBlocks;
    private List<Block> searchAround;
    private ListIterator<Block> i;
    private int searched = 0;

    public SearchNeighborsTask(List<Block> enclosedBlocks, NeighborRegion region, ParamCallback<TransformerRegion> callback) {
        this(null, enclosedBlocks, region, callback);
    }

    public SearchNeighborsTask(PlayerHandler watcher, List<Block> enclosedBlocks, NeighborRegion region, ParamCallback<TransformerRegion> callback) {
        super(watcher);
        this.enclosedBlocks = enclosedBlocks;
        this.callback = callback;
        this.region = region;

        toReplace = region.getPosition().getBlock().getType();
        searchAround = new ArrayList<>(Collections.singleton(region.getPosition().getBlock()));
        i = searchAround.listIterator();
        i.next();
    }

    @Override
    public void tick() {
        if(i.hasPrevious() && searched < SEARCH_LIMIT){
            Block b = i.previous();
            i.remove();
            for (int n = 0; n < BlockFace.values().length; n++) {
                Block relative = b.getRelative(BlockFace.values()[n]);
                if(relative.getType().equals(toReplace) && !enclosedBlocks.contains(relative)){
                    enclosedBlocks.add(relative);
                    i.add(relative);
                }
            }

            searched++;
        }else{
            if(callback != null) {
                callback.callback(region);
            }

            finished = true;
        }
    }

    @Override
    public double getProgress() {
        return -1;
    }

}
