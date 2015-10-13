package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.tasks.UndoTask;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.managers.TaskManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Undo implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        plr.closeInventory();

        TransformerHandler<Player> handler = HandlerManager.getInstance().getHandler(plr);
        if(handler.getUndoHistory().size() == 0){
            ActionAPI.sendAction(plr, ChatColor.DARK_RED + "Your undo history is empty!");
            return;
        }

        List<VirtualBlock> history = handler.getUndoHistory().pop();
        TaskManager.getInstance().addTask(new UndoTask(handler, history, handler));
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.REDSTONE_TORCH_ON).name(ChatColor.AQUA + "Undo").item();
    }

}
