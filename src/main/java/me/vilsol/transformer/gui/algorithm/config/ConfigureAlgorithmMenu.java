package me.vilsol.transformer.gui.algorithm.config;

import me.vilsol.menuengine.engine.DynamicMenu;
import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.enums.InventorySize;
import me.vilsol.menuengine.utils.CallbackResult;
import me.vilsol.transformer.engine.config.Configuration;
import me.vilsol.transformer.gui.algorithm.BackToAlgorithmMenu;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.managers.HandlerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ConfigureAlgorithmMenu extends DynamicMenuModel {

    @Override
    public void addItems(DynamicMenu i, Player plr) {
        i.setName(ChatColor.GOLD + ChatColor.BOLD.toString() + "Algorithm Configuration");
        i.addItem(BackToAlgorithmMenu.class, 0);

        TransformerHandler handler = HandlerManager.getInstance().getHandler(plr);

        if (handler.getAlgorithm() != null && handler.getAlgorithm().getConfigurations() != null) {
            for (int j = 0; j < handler.getAlgorithm().getConfigurations().size(); j++) {
                Configuration configuration = handler.getAlgorithm().getConfigurations().get(j);
                i.addItemDynamic(new ConfigurationMenuItem(configuration), j + 1);
            }
        }
    }

    @Override
    public InventorySize getSize(Player plr) {
        TransformerHandler handler = HandlerManager.getInstance().getHandler(plr);
        InventorySize size = InventorySize.S_9;
        if (handler.getAlgorithm() != null && handler.getAlgorithm().getConfigurations() != null) {
            size = InventorySize.getMinAfter(handler.getAlgorithm().getConfigurations().size() + 1);
        }
        return size;
    }

    @Override
    public void canPlaceItem(DynamicMenu i, Player plr, int slot, ItemStack item, CallbackResult result, ClickType click) {
        result.setCallItem(true);
    }

    @Override
    public void canPickupItem(DynamicMenu i, Player plr, int slot, ItemStack item, CallbackResult result, ClickType click) {
        result.setCallItem(true);
    }

    @Override
    public void onPickupItem(DynamicMenu i, ItemStack item, int slot) {
    }

    @Override
    public void onPlaceItem(DynamicMenu i, ItemStack item, int slot) {
    }

}
