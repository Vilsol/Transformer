package me.vilsol.transformer.listeners;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.R;
import me.vilsol.transformer.TransformerPlugin;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.gui.ControlCenter;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WandListener implements Listener {

    private List<Block> pinging = new ArrayList<>();

    @EventHandler
    public void onWandClick(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand().isSimilar(R.wand)) {
            Block targetBlock = event.getClickedBlock();

            if(targetBlock == null){
                targetBlock = event.getPlayer().getTargetBlock((Set<Material>) null, 200);
            }

            boolean ok = false;
            Action action = event.getAction();
            if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
                ok = true;
                HandlerManager.getInstance().getHandler(event.getPlayer()).setPositionOne(targetBlock.getLocation());
                ActionAPI.sendAction(event.getPlayer(), ChatColor.DARK_GREEN + "Position 1 Set", true);
            } else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                ok = true;
                HandlerManager.getInstance().getHandler(event.getPlayer()).setPositionTwo(targetBlock.getLocation());
                ActionAPI.sendAction(event.getPlayer(), ChatColor.DARK_GREEN + "Position 2 Set", true);
            }

            if(ok){
                if(pinging.contains(targetBlock)){
                    return;
                }

                pinging.add(targetBlock);
                VirtualBlock previousBlock = new VirtualBlock(targetBlock);
                targetBlock.setType(Material.JACK_O_LANTERN, false);
                final Block finalTargetBlock = targetBlock;
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        previousBlock.buildBlock();
                        pinging.remove(finalTargetBlock);
                    }
                }.runTaskLater(TransformerPlugin.getInstance(), 10L);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onItemDrop(PlayerDropItemEvent event){
        if(event.getItemDrop().getItemStack().isSimilar(R.wand)){
            event.setCancelled(true);
            MenuModel.getMenu(ControlCenter.class).getMenu().showToPlayer(event.getPlayer());
        }
    }

}
