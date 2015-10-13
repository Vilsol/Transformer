package me.vilsol.transformer.gui.region;

import me.vilsol.menuengine.engine.BonusItem;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.transformer.engine.regions.RegionType;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class Region implements BonusItem<RegionType> {

    private RegionType region;

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        plr.closeInventory();
        HandlerManager.getInstance().getHandler(plr).setRegionType(region);
        ActionAPI.sendAction(plr, ChatColor.DARK_GREEN + "Switched to " + region.getExample().getIdentifierItem().getItemMeta().getDisplayName());
    }

    @Override
    public ItemStack getItem() {
        return region.getExample().getIdentifierItem();
    }

    @Override
    public void setBonusData(RegionType region) {
        this.region = region;
    }

}
