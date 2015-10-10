package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.builder.BuildTask;
import me.vilsol.transformer.managers.BuilderManager;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.managers.PositionManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
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
        PositionManager.getInstance().getRegion(plr, region -> {
            plr.closeInventory();

            if (region == null) {
                ActionAPI.sendAction(plr, ChatColor.DARK_RED + "Please set all positions!");
                return;
            }

            BuildTask task = new BuildTask(region, HandlerManager.getInstance().getHandler(plr).getAlgorithm());
            BuilderManager.getInstance().addTask(task);
        });
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.STONE).name("TEST").item();
    }

}
