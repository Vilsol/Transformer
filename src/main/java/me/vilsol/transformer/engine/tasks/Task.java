package me.vilsol.transformer.engine.tasks;

import me.vilsol.transformer.handlers.PlayerHandler;

public abstract class Task {

    private PlayerHandler watcher;
    protected boolean finished;

    public Task(PlayerHandler watcher) {
        this.watcher = watcher;
    }

    public abstract void tick();

    public abstract double getProgress();

    public abstract int getParsedBlocks();

    public abstract int getTotalBlocks();

    public PlayerHandler getWatcher() {
        return watcher;
    }

    public void setWatcher(PlayerHandler watcher) {
        this.watcher = watcher;
    }

    public boolean isFinished() {
        return finished;
    }

}
