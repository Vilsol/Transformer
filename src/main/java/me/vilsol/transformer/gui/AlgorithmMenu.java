package me.vilsol.transformer.gui;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.engine.algorithms.AlgorithmType;
import me.vilsol.transformer.gui.algorithm.Algorithm;

public class AlgorithmMenu extends MenuModel {

    public AlgorithmMenu() {
        super(9, "Select Algorithm Type");

        for (int i = 0; i < AlgorithmType.values().length; i++) {
            AlgorithmType type = AlgorithmType.values()[i];
            getMenu().addItem(Algorithm.class, i, type);
        }
    }

}
