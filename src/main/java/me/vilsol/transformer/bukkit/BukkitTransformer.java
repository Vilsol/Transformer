package me.vilsol.transformer.bukkit;

import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.vilsol.transformer.TransformerAPI;
import me.vilsol.transformer.api.TProvider;
import me.vilsol.transformer.api.TWorld;
import me.vilsol.transformer.bukkit.provider.ConsoleProvider;
import me.vilsol.transformer.bukkit.provider.PlayerProvider;
import me.vilsol.transformer.bukkit.provider.PluginProvider;

/**
 * @author Nick Robson
 */
public class BukkitTransformer extends TransformerAPI {

    @Override
    public String getName() {
        return "Bukkit";
    }

    @Override
    public TWorld[] getWorlds() {
        return Bukkit.getWorlds().stream().map(w -> BukkitWorld.of(w)).collect(Collectors.toList()).toArray(new TWorld[Bukkit.getWorlds().size()]);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> TProvider<T> getProvider(T owner) {
        TProvider<?> provider;
        if (owner instanceof Player)
            provider = new PlayerProvider((Player) owner);
        else if (owner instanceof Plugin)
            provider = new PluginProvider((Plugin) owner);
        else if (owner instanceof ConsoleCommandSender)
            provider = new ConsoleProvider((ConsoleCommandSender) owner);
        else
            return null;
        return (TProvider<T>) provider;
    }

}
