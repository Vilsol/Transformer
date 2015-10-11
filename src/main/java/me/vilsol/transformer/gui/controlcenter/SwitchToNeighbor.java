package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.regions.RegionType;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SwitchToNeighbor implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        plr.closeInventory();
        HandlerManager.getInstance().getHandler(plr).setRegionType(RegionType.NEIGHBOR);
        ActionAPI.sendAction(plr, ChatColor.DARK_GREEN + "Switched to Neighbor region");
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.FENCE).name(ChatColor.AQUA + "Switch To Neighbor Region").item();
    }

}
