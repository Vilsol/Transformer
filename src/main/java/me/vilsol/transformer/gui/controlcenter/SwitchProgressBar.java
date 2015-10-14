package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class SwitchProgressBar implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        plr.closeInventory();
        ActionAPI.sendAction(plr, ChatColor.DARK_AQUA + "Progress Bar Switched");
        TransformerHandler<Player> handler = HandlerManager.getInstance().getHandler(plr);
        handler.setPercentageProgress(!handler.isPercentageProgress());
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.LEVER).name(ChatColor.GOLD + "Switch Progress Bar").lore(Collections.singletonList(ChatColor.GRAY + "Between percentage and block count")).item();
    }

}
