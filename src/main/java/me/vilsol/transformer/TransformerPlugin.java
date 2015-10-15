package me.vilsol.transformer;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.gui.AlgorithmMenu;
import me.vilsol.transformer.gui.ControlCenter;
import me.vilsol.transformer.gui.RegionMenu;
import me.vilsol.transformer.gui.algorithm.Algorithm;
import me.vilsol.transformer.gui.controlcenter.*;
import me.vilsol.transformer.gui.region.Region;
import me.vilsol.transformer.listeners.WandListener;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TransformerPlugin extends JavaPlugin implements Listener {

    private final CommandExecutor controlCenter = (sender, cmd, label, args) -> {
        if(!(sender instanceof Player)){
            sender.sendMessage("This command can only be used by players!");
            return true;
        }

        MenuModel.getMenu(ControlCenter.class).getMenu().showToPlayer((Player) sender);

        return true;
    };

    private static TransformerPlugin instance;

    public static TransformerPlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getServer().getPluginManager().registerEvents(this, this);

        registerMenus();
        registerListeners();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new WandListener(), this);
    }

    private void registerMenus() {

        new SwitchRegion().registerItem();
        new SwitchAlgorithm().registerItem();
        new TestItem().registerItem();
        new Undo().registerItem();
        new Wand().registerItem();
        new ChangeLimit().registerItem();
        new SwitchProgressBar().registerItem();
        new CancelAllTasks().registerItem();

        new ControlCenter();

        new Region().registerItem();

        new RegionMenu();

        new Algorithm().registerItem();

        new AlgorithmMenu();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(final PlayerCommandPreprocessEvent event){
        if(event.getMessage().equals("//") || event.getMessage().startsWith("// ")){
            String[] args = event.getMessage().equals("//") ? new String[0] : event.getMessage().substring(2).split("\\s");
            controlCenter.onCommand(event.getPlayer(), getCommand("//"), "//", args);
            event.setCancelled(true);
        }
    }

}
