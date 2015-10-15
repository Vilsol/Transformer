package me.vilsol.transformer.gui.algorithm;

import me.vilsol.menuengine.engine.BonusItem;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.transformer.engine.algorithms.AlgorithmType;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.managers.HandlerManager;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class Algorithm implements BonusItem<AlgorithmType> {

    private AlgorithmType algorithm;

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        plr.closeInventory();
        TransformerHandler<Player> handler = HandlerManager.getInstance().getHandler(plr);
        algorithm.newInstance(handler, algorithm -> {
            handler.setAlgorithm(algorithm);
            ActionAPI.sendAction(plr, ChatColor.DARK_GREEN + "Switched to " + algorithm.getIdentifierItem().getItemMeta().getDisplayName());
        });
    }

    @Override
    public ItemStack getItem() {
        return algorithm.getExample().getIdentifierItem();
    }

    @Override
    public void setBonusData(AlgorithmType algorithm) {
        this.algorithm = algorithm;
    }

}
