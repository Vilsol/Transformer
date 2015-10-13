package me.vilsol.transformer.engine.tasks;

import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.handlers.TransformerHandler;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Task {

    private TransformerHandler owner;
    private TransformerHandler<Player> watcher;
    protected boolean finished;

    public Task(TransformerHandler owner, TransformerHandler<Player> watcher) {
        this.owner = owner;
        this.watcher = watcher;
    }

    public abstract void tick();

    public abstract double getProgress();

    public abstract int getParsedBlocks();

    public abstract int getTotalBlocks();

    public abstract List<VirtualBlock> getUndo();

    public TransformerHandler<Player> getWatcher() {
        return watcher;
    }

    public void setWatcher(TransformerHandler<Player> watcher) {
        this.watcher = watcher;
    }

    public boolean isFinished() {
        return finished;
    }

    public TransformerHandler getOwner() {
        return owner;
    }

}
