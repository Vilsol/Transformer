package me.vilsol.transformer.gui.algorithm.config;

import me.vilsol.menuengine.engine.MenuItem;
import me.vilsol.transformer.engine.config.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ConfigurationMenuItem implements MenuItem {

    private Configuration configuration;

    public ConfigurationMenuItem(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void registerItem() {
        MenuItem.items.put(this.getClass(), this);
    }

    @Override
    public void execute(Player plr, ClickType click) {
        configuration.onClick(plr);
    }

    @Override
    public ItemStack getItem() {
        return configuration.getIdentifierItem();
    }

}
