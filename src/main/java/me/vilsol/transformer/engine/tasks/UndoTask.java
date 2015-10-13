package me.vilsol.transformer.engine.tasks;

import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.List;

public class UndoTask extends Task {

    private Iterator<VirtualBlock> blocks;

    private double totalBlocks;
    private double parsedBlocks;

    public UndoTask(TransformerHandler owner, List<VirtualBlock> blocks, TransformerHandler<Player> watcher) {
        super(owner, watcher);

        this.blocks = blocks.iterator();
        this.totalBlocks = blocks.size();
    }

    public void tick() {
        if(blocks.hasNext()){
            blocks.next().buildBlock(false);
        }else{
            finished = true;
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
        return null;
    }

}
