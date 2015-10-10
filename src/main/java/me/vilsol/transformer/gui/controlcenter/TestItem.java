package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.engine.regions.CuboidRegion;
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
        CuboidRegion region = PositionManager.getInstance().getRegion(plr);

        if (region == null) {
            ActionAPI.sendAction(plr, ChatColor.DARK_RED + "Please set both positions!");
            return;
        }

        PositionManager.getInstance().getRegion(plr).getEnclosedBlocks().stream().forEach(b -> b.setType(Material.STONE));
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.STONE).name("TEST").item();
    }

}
