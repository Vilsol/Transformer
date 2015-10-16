package me.vilsol.transformer.gui.algorithm.switchalgorithm;

import me.vilsol.menuengine.engine.DynamicMenu;
import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.enums.InventorySize;
import me.vilsol.menuengine.utils.CallbackResult;
import me.vilsol.transformer.engine.algorithms.AlgorithmType;
import me.vilsol.transformer.gui.algorithm.BackToAlgorithmMenu;
import me.vilsol.transformer.handlers.TransformerHandler;
import me.vilsol.transformer.managers.HandlerManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class SwitchAlgorithmMenu extends DynamicMenuModel {

    @Override
    public void addItems(DynamicMenu i, Player plr) {
        i.setName(ChatColor.DARK_PURPLE + "Select Algorithm Type");
        i.addItem(BackToAlgorithmMenu.class, 0);

        TransformerHandler handler = HandlerManager.getInstance().getHandler(plr);

        int pos = 1;
        for (int j = 0; j < AlgorithmType.values().length; j++) {
            AlgorithmType type = AlgorithmType.values()[j];
            if (!handler.getAlgorithm().getClass().equals(type.getExample().getClass())) {
                i.addItemDynamic(Algorithm.class, pos++, type);
            }
        }
    }

    @Override
    public InventorySize getSize(Player plr) {
        return InventorySize.getMinAfter(AlgorithmType.values().length + 1);
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
