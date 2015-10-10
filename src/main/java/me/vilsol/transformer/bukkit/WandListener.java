package me.vilsol.transformer.bukkit;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.vilsol.transformer.api.TBlock;
import me.vilsol.transformer.api.manager.ProviderManager;
import me.vilsol.transformer.bukkit.utils.ActionAPI;

public class WandListener implements Listener {

    @EventHandler
    public void onWandClick(PlayerInteractEvent event) {
        if (event.getPlayer().getItemInHand().isSimilar(R.WAND)) {
            Block targetBlock = event.getClickedBlock();

            if (targetBlock == null)
                targetBlock = event.getPlayer().getTargetBlock((Set<Material>) null, 30);

            Action action = event.getAction();
            if (action == Action.LEFT_CLICK_AIR || action == Action.LEFT_CLICK_BLOCK) {
                ProviderManager.getProvider(event.getPlayer()).setA(BukkitUtils.toTransformer(targetBlock.getLocation()));
                ActionAPI.sendAction(event.getPlayer(), ChatColor.DARK_GREEN + "Position 1 Set", true);
            } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                ProviderManager.getProvider(event.getPlayer()).setB(BukkitUtils.toTransformer(targetBlock.getLocation()));
                ActionAPI.sendAction(event.getPlayer(), ChatColor.DARK_GREEN + "Position 2 Set", true);
            }

            if (action != Action.PHYSICAL) {
                TBlock previousBlock = BukkitBlock.of(targetBlock);
                targetBlock.setType(Material.GLOWSTONE);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        previousBlock.apply();
                    }
                }.runTaskLater(TransformerPlugin.getInstance(), 10L);
            }
        }
    }

}
