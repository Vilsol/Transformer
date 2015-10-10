package me.vilsol.transformer.bukkit.gui;

import org.bukkit.ChatColor;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.bukkit.gui.controlcenter.TestItem;
import me.vilsol.transformer.bukkit.gui.controlcenter.Wand;

public class ControlCenter extends MenuModel {

    public ControlCenter() {
        super(54, ChatColor.DARK_AQUA + "Transformer Control Center");

        getMenu().addItem(TestItem.class, 0);
        getMenu().addItem(Wand.class, 49);
    }

}
