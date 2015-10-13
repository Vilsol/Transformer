package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.tasks.BuildTask;
import me.vilsol.transformer.handlers.PlayerHandler;
import me.vilsol.transformer.managers.TaskManager;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.managers.PositionManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class TestItem implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        plr.closeInventory();

        PositionManager.getInstance().getRegion(plr, region -> {
            plr.closeInventory();

            if (region == null) {
                return;
            }

            PlayerHandler handler = (PlayerHandler) HandlerManager.getInstance().getHandler(plr);
            BuildTask task = new BuildTask(handler, region, handler.getAlgorithm(), handler);
            TaskManager.getInstance().addTask(task);
        });
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.STONE).name("TEST").item();
    }

}
