package me.vilsol.transformer.listeners;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.R;
import me.vilsol.transformer.TransformerPlugin;
import me.vilsol.transformer.engine.VirtualBlock;
import me.vilsol.transformer.engine.selection.OnePointSelection;
import me.vilsol.transformer.engine.selection.Selection;
import me.vilsol.transformer.engine.selection.TwoPointSelection;
import me.vilsol.transformer.gui.controlcenter.ControlCenter;
import me.vilsol.transformer.handlers.TransformerHandler;
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
import org.bukkit.event.player.PlayerItemHeldEvent;
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
            TransformerHandler handler = HandlerManager.getInstance().getHandler(event.getPlayer());
            Selection selection = handler.getSelection();

            if(!handler.getRegionType().getSelection().equalClass(selection)){
                selection = handler.getRegionType().getSelection().newInstance();
                handler.setSelection(selection);
            }

            switch(handler.getRegionType().getSelection()){
                case ONE_POINT:
                    ok = true;
                    ((OnePointSelection) selection).setPoint(targetBlock.getLocation());
                    ActionAPI.sendAction(event.getPlayer(), ChatColor.DARK_GREEN + "Position Set");
                    break;
                case TWO_POINTS:
                    if (action.equals(Action.LEFT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_BLOCK)) {
                        ok = true;
                        ((TwoPointSelection) selection).setPositionOne(targetBlock.getLocation());
                        ActionAPI.sendAction(event.getPlayer(), ChatColor.DARK_GREEN + "Position 1 Set");
                    } else if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
                        ok = true;
                        ((TwoPointSelection) selection).setPositionTwo(targetBlock.getLocation());
                        ActionAPI.sendAction(event.getPlayer(), ChatColor.DARK_GREEN + "Position 2 Set");
                    }
                    break;
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onSwitch(PlayerItemHeldEvent event){
        if(event.getPlayer().getInventory().getItem(event.getNewSlot()) != null) {
            if (event.getPlayer().getInventory().getItem(event.getNewSlot()).isSimilar(R.wand)) {
                ActionAPI.sendAction(event.getPlayer(), ChatColor.GOLD + "Press Q To Open Control Center!");
            }
        }
    }

}
