package me.vilsol.transformer.bukkit.gui.controlcenter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.api.manager.PositionManager;
import me.vilsol.transformer.api.regions.TransformerRegion;
import me.vilsol.transformer.bukkit.utils.ActionAPI;

public class TestItem implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void execute(Player plr, ClickType click) {
        TransformerRegion region = PositionManager.getRegion(plr);

        if (region == null) {
            ActionAPI.sendAction(plr, ChatColor.DARK_RED + "Please set both positions!");
            return;
        }

        region.getEnclosedBlocks().stream().forEach(b -> b.setID(Material.STONE.getId(), true));
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.STONE).name("TEST").item();
    }

}
