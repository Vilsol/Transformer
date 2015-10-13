package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.ChatCallback;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.menuengine.utils.Builder;
import me.vilsol.transformer.R;
import me.vilsol.transformer.utils.ActionAPI;
import me.vilsol.transformer.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

public class ChangeLimit implements MenuItem, ChatCallback {

    @Override
    public void onChatMessage(AsyncPlayerChatEvent e) {
        if(!Utils.isInteger(e.getMessage())){
            ActionAPI.sendAction(e.getPlayer(), ChatColor.DARK_RED + "Please enter a number!");
        }

        R.globalLimit = Integer.parseInt(e.getMessage());
        ActionAPI.sendAction(e.getPlayer(), ChatColor.DARK_GREEN + "Changed Limit To " + e.getMessage());

        ChatCallback.locked_players.remove(e.getPlayer());
    }

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        ChatCallback.locked_players.put(plr, this);
        plr.closeInventory();
        ActionAPI.sendAction(plr, ChatColor.DARK_AQUA + "Enter new limit");
    }

    @Override
    public ItemStack getItem() {
        return new Builder(Material.REDSTONE).name(ChatColor.DARK_BLUE + "Change Limit").item();
    }

}
