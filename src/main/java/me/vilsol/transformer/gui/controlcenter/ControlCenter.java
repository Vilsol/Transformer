package me.vilsol.transformer.gui.controlcenter;

import me.vilsol.menuengine.engine.MenuModel;
import org.bukkit.ChatColor;

public class ControlCenter extends MenuModel {

    public ControlCenter() {
        super(45, ChatColor.DARK_AQUA + "Transformer Control Center");

        getMenu().addItem(SwitchRegion.class, 0);
        getMenu().addItem(Algorithm.class, 2);

        getMenu().addItem(ChangeLimit.class, 4);

        getMenu().addItem(SwitchProgressBar.class, 6);

        getMenu().addItem(Undo.class, 8);

        getMenu().addItem(Wand.class, 31);

        getMenu().addItem(Run.class, 40);

        getMenu().addItem(CancelAllTasks.class, 44);
    }

}
