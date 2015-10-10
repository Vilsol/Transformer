package me.vilsol.transformer.managers;

import me.vilsol.transformer.R;
import me.vilsol.transformer.TransformerPlugin;
import me.vilsol.transformer.engine.builder.BuildTask;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BuilderManager extends BukkitRunnable {

    private static BuilderManager ourInstance = new BuilderManager();

    public static BuilderManager getInstance() {
        return ourInstance;
    }

    private Set<BuildTask> tasks = new HashSet<>();

    private BuilderManager() {
        runTaskTimer(TransformerPlugin.getInstance(), 0L, 1L);
    }

    public void addTask(BuildTask task){
        tasks.add(task);
    }

    @Override
    public void run() {
        if(tasks.size() > 0) {
            for (int i = 0; i < R.globalLimit; i++) {
                Iterator<BuildTask> iterator = tasks.iterator();
                while(iterator.hasNext()){
                    BuildTask task = iterator.next();

                    task.buildNext();

                    if(task.isFinished()){
                        iterator.remove();
                    }
                }
            }
        }
    }

}
