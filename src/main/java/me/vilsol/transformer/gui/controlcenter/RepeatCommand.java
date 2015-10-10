package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.R;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.Collections;

public class RepeatCommand implements MenuItem {

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        plr.getInventory().remove(R.wand);
        plr.setItemInHand(R.wand.clone());
        plr.closeInventory();
        ActionAPI.sendAction(plr, "Hello World");
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.BLAZE_ROD).name(ChatColor.AQUA + "TransformerPlugin Wand").lore(Collections.singletonList(ChatColor.DARK_AQUA + "Gives you the wand")).item();
    }

}
