package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.managers.TaskManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class CancelAllTasks implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        if(click.equals(ClickType.LEFT)){
            TransformerHandler<Player> handler = HandlerManager.getInstance().getHandler(plr);
            TaskManager.getInstance().cancelTasksOf(handler);
            ActionAPI.sendAction(plr, ChatColor.DARK_RED + "All your tasks cancelled");
        }else if(click.equals(ClickType.RIGHT)){
            TaskManager.getInstance().cancelAllTasks();
            ActionAPI.sendAction(plr, ChatColor.DARK_RED + "All tasks cancelled");
        }

        plr.closeInventory();
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.BARRIER).name(ChatColor.DARK_RED + "Cancel All Tasks").lore(Arrays.asList(ChatColor.GRAY + "Left-click to cancel your tasks", ChatColor.GRAY + "Right-click to cancel all tasks")).item();
    }

}
