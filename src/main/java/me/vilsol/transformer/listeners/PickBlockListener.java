package me.vilsol.transformer.listeners;

import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.config.configurations.BlockConfiguration;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryCreativeEvent;

import java.util.HashMap;

public class PickBlockListener implements Listener {

    public static HashMap<HumanEntity, BlockConfiguration> locked_players = new HashMap<>();

    @EventHandler
    public void onPick(InventoryCreativeEvent event){
        if(locked_players.containsKey(event.getWhoClicked())) {
            if (event.getClick().equals(ClickType.CREATIVE)) {
                if (event.getAction().equals(InventoryAction.PLACE_ALL)) {
                    if (event.getCursor() != null) {
                        if (!event.getCursor().getType().equals(Material.AIR)) {
                            event.setCancelled(true);
                            locked_players.get(event.getWhoClicked()).changeBlock(new VirtualBlock(event.getCursor().getType()), (Player) event.getWhoClicked());
                        }
                    }
                }
            }
        }
    }

}
