package me.vilsol.transformer.managers;

import me.vilsol.transformer.R;
import me.vilsol.transformer.TransformerPlugin;
import me.vilsol.transformer.engine.tasks.Task;
import me.vilsol.transformer.handlers.PlayerHandler;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskManager extends BukkitRunnable {

    private static TaskManager ourInstance = new TaskManager();

    public static TaskManager getInstance() {
        return ourInstance;
    }

    private CopyOnWriteArrayList<Task> tasks = new CopyOnWriteArrayList<>();

    private TaskManager() {
        runTaskTimer(TransformerPlugin.getInstance(), 0L, 1L);
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    private int tick = 0;

    @Override
    public void run() {
        if(tasks.size() > 0) {
            HashMap<Task, PlayerHandler> watchers = new HashMap<>();
            long start = System.currentTimeMillis();
            for (int i = 0; i < R.globalLimit; i++) {
                List<Task> toRemove = new ArrayList<>();
                for (Task task : tasks) {
                    task.tick();

                    if (task.getWatcher() != null) {
                        watchers.put(task, task.getWatcher());
                    }

                    if (task.isFinished()) {
                        toRemove.add(task);

                        watchers.remove(task);
                        if (task.getWatcher() != null) {
                            ActionAPI.sendAction(task.getWatcher().getOwner(), ChatColor.DARK_GREEN + "Task Completed!");
                        }
                    }
                }
                tasks.removeAll(toRemove);

                if(System.currentTimeMillis() - start >= 50){
                    System.err.println("Overload on " + i + " out of " + R.globalLimit + "!");
                    break;
                }
            }

            if(tick % 5 == 0) {
                watchers.entrySet().stream().forEach(set -> {
                    String line = ChatColor.AQUA + "[";

                    if(set.getKey().getProgress() >= 0) {
                        line += ChatColor.DARK_GREEN;
                        int switchPoint = (int) (set.getKey().getProgress() * 0.3);
                        for (int i = 0; i < 30; i++) {
                            if (i == 15) {
                                line += " " + ((int) set.getKey().getProgress()) + "% ";
                                if (i == switchPoint) {
                                    line += ChatColor.DARK_RED;
                                }
                            } else if (i < switchPoint) {
                                line += "=";
                            } else if (i == switchPoint) {
                                line += ">" + ChatColor.DARK_RED;
                            } else {
                                line += "-";
                            }
                        }
                    }else{
                        int position = (tick % 105) / 5;
                        line += ChatColor.DARK_RED;
                        for (int i = 0; i < Math.min(20, position); i++) {
                            line += "-";
                        }

                        line += ChatColor.DARK_GREEN + "==========" + ChatColor.DARK_RED;

                        for (int i = position; i < 20; i++) {
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
