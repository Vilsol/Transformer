package me.vilsol.transformer.bukkit;

import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.vilsol.menuengine.engine.MenuModel;
import me.vilsol.transformer.TransformerAPI;
import me.vilsol.transformer.bukkit.gui.ControlCenter;
import me.vilsol.transformer.bukkit.gui.controlcenter.TestItem;
import me.vilsol.transformer.bukkit.gui.controlcenter.Wand;

public class TransformerPlugin extends JavaPlugin implements Listener {

    private final CommandExecutor controlCenter = (sender, cmd, label, args) -> {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players!");
            return true;
        }

        MenuModel.getMenu(ControlCenter.class).getMenu().showToPlayer((Player) sender);

        return true;
    };

    public static TransformerPlugin getInstance() {
        return JavaPlugin.getPlugin(TransformerPlugin.class);
    }

    @Override
    public void onEnable() {
        TransformerAPI.set(new BukkitTransformer());

        getServer().getPluginManager().registerEvents(this, this);

        registerMenus();
        registerListeners();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new WandListener(), this);
    }

    private void registerMenus() {
        new TestItem().registerItem();

        new Wand().registerItem();

        new ControlCenter();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(final PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equals("//") || event.getMessage().startsWith("// ")) {
            String[] args = event.getMessage().equals("//") ? new String[0]
                    : event.getMessage().substring(2).split("\\s");
            controlCenter.onCommand(event.getPlayer(), getCommand("//"), "//", args);
            event.setCancelled(true);
        }
    }

}
