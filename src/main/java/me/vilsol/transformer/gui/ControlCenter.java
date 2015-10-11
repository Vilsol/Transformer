package me.vilsol.transformer.gui;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.gui.controlcenter.TestItem;
import me.vilsol.transformer.gui.controlcenter.Wand;
import org.bukkit.ChatColor;

public class ControlCenter extends MenuModel {

    public ControlCenter() {
        super(54, ChatColor.DARK_AQUA + "Transformer Control Center");

        getMenu().addItem(TestItem.class, 0);

        getMenu().addItem(Wand.class, 49);
    }

}