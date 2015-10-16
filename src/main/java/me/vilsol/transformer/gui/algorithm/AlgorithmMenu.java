package me.vilsol.transformer.gui.algorithm;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.gui.controlcenter.BackToControlCenter;

public class AlgorithmMenu extends MenuModel {

    public AlgorithmMenu() {
        super(9, "Algorithm");

        getMenu().addItem(BackToControlCenter.class, 0);
        getMenu().addItem(SwitchAlgorithm.class, 1);
        getMenu().addItem(ConfigAlgorithm.class, 2);
    }

}
