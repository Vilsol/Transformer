package me.vilsol.transformer.managers;

import me.vilsol.transformer.R;
import me.vilsol.transformer.TransformerPlugin;
import me.vilsol.transformer.engine.builder.BuildTask;
import me.vilsol.transformer.handlers.PlayerHandler;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
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

    private int tick = 0;

    @Override
    public void run() {
        if(tasks.size() > 0) {
            HashMap<BuildTask, PlayerHandler> watchers = new HashMap<>();

            for (int i = 0; i < R.globalLimit; i++) {
                Iterator<BuildTask> iterator = tasks.iterator();
                while(iterator.hasNext()){
                    BuildTask task = iterator.next();

                    task.buildNext();

                    if(task.getWatcher() != null){
                        watchers.put(task, task.getWatcher());
                    }

                    if(task.isFinished()){
                        iterator.remove();

                        watchers.remove(task);
                        if(task.getWatcher() != null){
                            ActionAPI.sendAction(task.getWatcher().getOwner(), ChatColor.DARK_GREEN + "Task Completed!");
                        }
                    }
                }
            }

            if(tick % 5 == 0) {
                watchers.entrySet().stream().forEach(set -> {
                    String line = ChatColor.AQUA + "[";

                    line += ChatColor.DARK_GREEN;
                    int switchPoint = (int) (set.getKey().getProgress() * 0.3);
                    for (int i = 0; i < 30; i++) {
                        if(i == 15){
                            line += " " + ((int) set.getKey().getProgress()) + "% ";
                            if(i == switchPoint){
                                line += ChatColor.DARK_RED;
                            }
                        }else if(i < switchPoint) {
                            line += "=";
                        }else if(i == switchPoint){
                            line += ">" + ChatColor.DARK_RED;
                        }else{
                            line += "-";
                        }
                    }

                    line += ChatColor.AQUA + "]";

                    ActionAPI.sendAction(set.getValue().getOwner(), line);
                });
            }
        }
        tick++;
    }

}
