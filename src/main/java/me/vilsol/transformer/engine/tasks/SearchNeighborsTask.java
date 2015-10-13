package me.vilsol.transformer.engine.tasks;

import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.regions.NeighborRegion;
import me.vilsol.transformer.engine.regions.TransformerRegion;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class SearchNeighborsTask extends Task {

    private static final int SEARCH_LIMIT = 100000;

    private ParamCallback<TransformerRegion> callback;
    private NeighborRegion region;
    private Material toReplace;
    private List<Block> enclosedBlocks;
    private LinkedBlockingQueue<Block> searchAround;
    private int searched = 0;

    public SearchNeighborsTask(TransformerHandler owner, List<Block> enclosedBlocks, NeighborRegion region, ParamCallback<TransformerRegion> callback) {
        this(owner, null, enclosedBlocks, region, callback);
    }

    public SearchNeighborsTask(TransformerHandler owner, TransformerHandler<Player> watcher, List<Block> enclosedBlocks, NeighborRegion region, ParamCallback<TransformerRegion> callback) {
        super(owner, watcher);
        this.enclosedBlocks = enclosedBlocks;
        this.callback = callback;
        this.region = region;

        toReplace = region.getPosition().getBlock().getType();
        searchAround = new LinkedBlockingQueue<>(Collections.singleton(region.getPosition().getBlock()));
    }

    @Override
    public void tick() {
        if(searchAround.size() > 0 && searched < SEARCH_LIMIT){
            Block b = searchAround.remove();
            for (int n = 0; n < 6; n++) {
                Block relative = b.getRelative(BlockFace.values()[n]);
                if(relative.getType().equals(toReplace) && !enclosedBlocks.contains(relative)){
                    enclosedBlocks.add(relative);
                    try {
                        searchAround.put(relative);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

    @Override
    public int getParsedBlocks() {
        return searched;
    }

    @Override
    public int getTotalBlocks() {
        return -1;
    }

    @Override
    public List<VirtualBlock> getUndo() {
        return null;
    }

}
