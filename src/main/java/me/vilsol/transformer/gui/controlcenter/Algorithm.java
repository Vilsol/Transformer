package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.gui.algorithm.AlgorithmMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class Algorithm implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        DynamicMenuModel.createMenu(plr, AlgorithmMenu.class).showToPlayer(plr);
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.HOPPER).name(ChatColor.AQUA + "Algorithm").item();
    }

}