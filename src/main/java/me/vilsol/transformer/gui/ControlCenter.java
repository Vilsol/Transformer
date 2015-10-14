package me.vilsol.transformer.gui;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.gui.controlcenter.*;
import org.bukkit.ChatColor;

public class ControlCenter extends MenuModel {

    public ControlCenter() {
        super(54, ChatColor.DARK_AQUA + "Transformer Control Center");

        getMenu().addItem(TestItem.class, 0);

        getMenu().addItem(SwitchRegion.class, 2);

        getMenu().addItem(ChangeLimit.class, 4);

        getMenu().addItem(SwitchProgressBar.class, 6);

        getMenu().addItem(Undo.class, 8);

        getMenu().addItem(Wand.class, 49);
    }

}
