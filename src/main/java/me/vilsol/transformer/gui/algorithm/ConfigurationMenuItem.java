package me.vilsol.transformer.gui.algorithm;

import me.vilsol.menuengine.engine.DynamicMenuModel;
import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.transformer.engine.config.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ConfigurationMenuItem implements MenuItem {

    private Configuration configuration;
    private Class<? extends DynamicMenuModel> menu;

    public ConfigurationMenuItem(Configuration configuration, Class<? extends DynamicMenuModel> menu) {
        this.configuration = configuration;
        this.menu = menu;
    }

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        configuration.onClick(plr, menu);
    }

    @Override
    public ItemStack getItem() {
        return configuration.getIdentifierItem();
    }

}
