package me.vilsol.transformer.bukkit.provider;

import org.bukkit.plugin.Plugin;

import me.vilsol.transformer.api.AbstractTProvider;

public class PluginProvider extends AbstractTProvider<Plugin> {

    public PluginProvider(Plugin owner) {
        super(owner);
    }

    @Override
    public String getName() {
        return getOwner().getName();
    }

}
