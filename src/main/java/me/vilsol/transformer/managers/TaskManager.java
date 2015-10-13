package me.vilsol.transformer.managers;

import me.vilsol.transformer.R;
import me.vilsol.transformer.TransformerPlugin;
import me.vilsol.transformer.engine.tasks.Task;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
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
            HashMap<Task, TransformerHandler<Player>> watchers = new HashMap<>();
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

                        if(task.getOwner() != null && task.getUndo() != null){
                            Collections.reverse(task.getUndo());
                            task.getOwner().getUndoHistory().add(task.getUndo());
                        }

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
                    String line;

                    if(set.getKey().getProgress() >= 0) {
                        line = ChatColor.DARK_GREEN.toString();
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
                        line = ChatColor.DARK_RED.toString();
                        int position = (tick % 155) / 5;
                        String text = " " + set.getKey().getParsedBlocks() + " ";

                        for (int i = 0; i < 30; i++) {
                            if(i == position){
                                line += ChatColor.DARK_GREEN;
                            }else if(i == position + 11){
                                line += ChatColor.DARK_RED;
                            }

                            if(i >= (15 - text.length() / 2) && i < (15 + Math.ceil(text.length() / 2d))){
                                int insertPos = i - (15 - text.length() / 2);
                                line += text.substring(insertPos, insertPos + 1);
                            }else if(position > 20 && i <= (position + 10) - 31){
                                if(i == 0){
                                    line += ChatColor.DARK_GREEN;
                                }

                                line += "=";

                                if(i == (position + 10) - 31){
                                    line += ChatColor.DARK_RED;
                                }
                            }else if(i >= position && i <= position + 10){
                                line += "=";
                            }else{
                                line += "-";
                            }
                        }
                    }

                    ActionAPI.sendAction(set.getValue().getOwner(), ChatColor.AQUA + "[" + line + ChatColor.AQUA + "]");
                });
            }
        }

        tick++;
    }

}
