package me.vilsol.transformer.engine.config;

import me.vilsol.menuengine.engine.ChatCallback;
import me.vilsol.transformer.engine.ParamCallback;
import me.vilsol.transformer.utils.ActionAPI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public abstract class ChatConfiguration<T> extends Configuration<T> implements ChatCallback {

    private String message;

    public ChatConfiguration(T defaultValue, Material itemMaterial, String name, List<String> description, String message, ParamCallback<T> callback) {
        super(defaultValue, itemMaterial, name, description, callback);
        this.message = message;
    }

    @Override
    public void onClick(Player player) {
        player.closeInventory();
        ActionAPI.sendAction(player, message);
        ChatCallback.locked_players.put(player, this);
    }

    public abstract boolean parseInput(Player player, String input);

    @Override
    public void onChatMessage(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        if (parseInput(e.getPlayer(), e.getMessage())) {
            ChatCallback.locked_players.remove(e.getPlayer());
        } else {
            ActionAPI.sendAction(e.getPlayer(), ChatColor.DARK_RED + "Please enter an integer!");
        }
    }

}
