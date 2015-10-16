package me.vilsol.transformer.gui.region;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.engine.regions.RegionType;
import me.vilsol.transformer.gui.region.Region;

public class RegionMenu extends MenuModel {

    public RegionMenu() {
        super(9, "Select Region Type");

        for (int i = 0; i < RegionType.values().length; i++) {
            RegionType type = RegionType.values()[i];
            getMenu().addItem(Region.class, i, type);
        }
    }

}
